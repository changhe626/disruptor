package com.onyx.distruptor.high;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

import java.util.concurrent.TimeUnit;

/**
 * 只用实现一个借口就行了,任意一个借口都是可以的.
 */
public class Handle1 implements EventHandler<Trade>, WorkHandler<Trade> {

    //EventHandler
    @Override
    public void onEvent(Trade event, long sequence, boolean endOfBatch) throws Exception {
        onEvent(event);
    }

    //WorkHandler ,用在多消费者的时候,多消费者的时候不能使用EventHandle
    @Override
    public void onEvent(Trade event) throws Exception {
        System.out.println("Handle1  : set name ");
        event.setName("enEvent");
        TimeUnit.SECONDS.sleep(1);
    }
}
