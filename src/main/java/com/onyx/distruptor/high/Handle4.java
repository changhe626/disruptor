package com.onyx.distruptor.high;

import com.lmax.disruptor.EventHandler;

public class Handle4 implements EventHandler<Trade> {
    @Override
    public void onEvent(Trade event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("handle set price");
        event.setPrice(32.5D);
    }
}
