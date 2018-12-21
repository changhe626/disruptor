package com.onyx.distruptor.high;

import com.lmax.disruptor.BusySpinWaitStrategy;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Main {
    //构建一个线程池,用于提交任务
    private static ExecutorService pool=Executors.newFixedThreadPool(8);

    public static void main(String[] args) throws InterruptedException {

        EventFactory<Trade> factory = new EventFactory<Trade>() {
            @Override
            public Trade newInstance() {
                Trade trade = new Trade("1", "手机", 32.3D);
                return trade;
            }
        };


        //构建
        Disruptor<Trade> disruptor=new Disruptor<Trade>(
                factory,
                1024*1024,
                Executors.newFixedThreadPool(8),
                ProducerType.SINGLE,
                new BusySpinWaitStrategy()
                );

        //2.设置消费者到Disruptor中的handleEventWith

        //3.启动
        RingBuffer<Trade> ringBuffer = disruptor.start();


        CountDownLatch countDownLatch = new CountDownLatch(8);

        pool.submit(new TradePublisher(countDownLatch,ringBuffer));

        countDownLatch.await();
        pool.shutdown();
        disruptor.shutdown();


    }

}
