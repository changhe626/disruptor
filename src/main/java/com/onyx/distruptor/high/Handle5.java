package com.onyx.distruptor.high;

import com.lmax.disruptor.EventHandler;

public class Handle5 implements EventHandler<Trade> {
    @Override
    public void onEvent(Trade event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("Handle 5: get Price:"+event.getPrice());
        event.setPrice(event.getPrice()+4.3);
    }

}
