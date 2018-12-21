package com.onyx.distruptor.high;

import com.lmax.disruptor.EventHandler;

public class Handle3 implements EventHandler<Trade> {

    @Override
    public void onEvent(Trade event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("Handle3 : event:"+event);
    }
}
