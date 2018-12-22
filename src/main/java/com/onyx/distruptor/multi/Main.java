package com.onyx.distruptor.multi;

import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.ExceptionHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.SequenceBarrier;
import com.lmax.disruptor.WorkerPool;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.ProducerType;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 多生产,多消费
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {

        //1.创建一个Ringbuffer
        RingBuffer<Order> ringBuffer = RingBuffer.
                create(ProducerType.MULTI,
                        new EventFactory<Order>() {
                            @Override
                            public Order newInstance() {
                                return new Order();
                            }
                        },
                        1024*1024,
                        new YieldingWaitStrategy());


        //2.通过ringbuffer创建一个屏障
        SequenceBarrier sequenceBarrier = ringBuffer.newBarrier();

        //3.创建多消费者数组
        Consumer[] consumers = new Consumer[10];
        for (int i = 0; i < 10; i++) {
            consumers[i]=new Consumer("Con"+i);
        }

        //4.构建多消费者工作池
        WorkerPool<Order> workerPool = new WorkerPool<Order>(ringBuffer,
                sequenceBarrier,
                new EventExceptionHandle(),
                consumers
                );

        //5.设置多个消费者的sequence序号,用于单独统计消费进度.并且设置到Ringbuffer中
        ringBuffer.addGatingSequences(workerPool.getWorkerSequences());

        //6.启动workPool
        workerPool.start(Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()*2));


        //7.多生产者
        CountDownLatch countDownLatch = new CountDownLatch(1);
        for (int i = 0; i < 100; i++) {
            Producer producer = new Producer(ringBuffer);
            new Thread(()->{
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                for (int j = 0; j < 100; j++) {
                    producer.sendData(UUID.randomUUID().toString());
                }
                
            }).start();
        }

        TimeUnit.SECONDS.sleep(3);
        System.out.println("~~~~线程创建完毕,开始生产数据");
        countDownLatch.countDown();

        TimeUnit.SECONDS.sleep(5);
        System.out.println("消费者处理的任务总数:"+consumers[0].getCount());

    }



    /**
     * 异常的处理
     */
    static class EventExceptionHandle implements ExceptionHandler<Order>{
        @Override
        public void handleEventException(Throwable ex, long sequence, Order event) {
        }

        @Override
        public void handleOnStartException(Throwable ex) {
        }

        @Override
        public void handleOnShutdownException(Throwable ex) {
        }
    }


}
