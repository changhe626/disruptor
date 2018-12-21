package com.onyx.distruptor.high;

import com.lmax.disruptor.BusySpinWaitStrategy;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    //构建一个线程池,用于提交任务
    private static ExecutorService pool=Executors.newFixedThreadPool(8);
    //这里的线程数量和监听的数量有关系,和消费者的数量有关
    private static ExecutorService pool2=Executors.newFixedThreadPool(5);

    public static void main(String[] args) throws InterruptedException {

        long l = System.currentTimeMillis();

        EventFactory<Trade> factory = new EventFactory<Trade>() {
            @Override
            public Trade newInstance() {
                Trade trade = new Trade();
                return trade;
            }
        };


        //构建
        Disruptor<Trade> disruptor=new Disruptor<Trade>(
                factory,
                1024*1024,
                pool2,
                ProducerType.SINGLE,
                new BusySpinWaitStrategy()
                );

        //2.设置消费者到Disruptor中的handleEventWith

        //2.1串行操作
        /*disruptor.handleEventsWith(new Handle1())
                .handleEventsWith(new Handle2()).
                handleEventsWith(new Handle3());*/

        //2.2并行操作,添加多个handle的实现
       /* disruptor.handleEventsWith(new Handle1());
        disruptor.handleEventsWith(new Handle2());
        disruptor.handleEventsWith(new Handle3());*/

       //2.2并行方式2
        //disruptor.handleEventsWith(new Handle1(),new Handle2(),new Handle3());


        //2.3菱形操作
        /**
         *      H2
         * P1          H3
         *      H1
         * P1 可以用到H1 或H2 到达H3
         * 1 2 一起,再到3 和CyclingBarrier一样的效果
         */
        //1.方案
       /* disruptor.handleEventsWith(new Handle1(),new Handle2()).
                handleEventsWith(new Handle3());*/
        //2.方案
       /* EventHandlerGroup<Trade> group = disruptor.handleEventsWith(new Handle1(), new Handle2());
        group.then(new Handle3());*/


        //2.4六边形
        /**
         *         C1   C2
         * P1                   C3
         *          C4   C5
         *  这种六边形的
         */
        //注意,这里就不能再那样的new 了.因为涉及到重用了.
        EventHandler handle1 = new Handle1();
        EventHandler handle2 = new Handle2();
        EventHandler handle3 = new Handle3();
        EventHandler handle4 = new Handle4();
        EventHandler handle5 = new Handle5();
        disruptor.handleEventsWith(handle1,handle4);
        disruptor.after(handle1).handleEventsWith(handle2);
        disruptor.after(handle4).handleEventsWith(handle5);
        disruptor.after(handle2,handle5).handleEventsWith(handle3);



        //3.启动
        RingBuffer<Trade> ringBuffer = disruptor.start();

        CountDownLatch countDownLatch = new CountDownLatch(1);

        pool.submit(new TradePublisher(countDownLatch,ringBuffer));

        //等待再向下执行
        countDownLatch.await();
        pool.shutdown();
        pool2.shutdown();
        disruptor.shutdown();
        System.out.println("总耗时"+(System.currentTimeMillis()-l));


    }

}
