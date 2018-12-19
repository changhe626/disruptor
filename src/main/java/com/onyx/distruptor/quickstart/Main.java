package com.onyx.distruptor.quickstart;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {

        //参数准备
        OrderFactory orderFactory = new OrderFactory();
        int ringBufferSize=1024*1024;
        ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        /**
         * 1.实例化Disruptor对象
         *
         * 1.消息(event)工厂对象
         * 2.容器的长度
         * 3.线程池(建议自定义线程池,拒绝的接口Rejected)
         * 4.单生产者还是多生产者
         * 5.等待策略
         *
         */
        Disruptor<OrderEvent> disruptor = new Disruptor<OrderEvent>(orderFactory,
                ringBufferSize,
                pool,
                ProducerType.SINGLE,
                new BlockingWaitStrategy());


        /**
         * 2.添加消费者的监听,disruptor与消费者的一个关联关系
         */
        disruptor.handleEventsWith(new OrderEventHandle());

        /**
         * 3.启动disruptor
         */
        disruptor.start();

        /**
         * 4.获取实际存储数据的容器:RingBuffer
         */
        RingBuffer<OrderEvent> ringBuffer = disruptor.getRingBuffer();

        OrderEventProducer producer=new OrderEventProducer(ringBuffer);

        ByteBuffer buffer = ByteBuffer.allocate(8);
        for (int i = 0; i < 100; i++) {
            buffer.putLong(0,(long)i);
            producer.sendData(buffer);
        }

        //关闭
        disruptor.shutdown();
        pool.shutdown();
    }


}
