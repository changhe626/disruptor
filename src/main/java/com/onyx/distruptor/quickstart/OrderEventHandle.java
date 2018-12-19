package com.onyx.distruptor.quickstart;


import com.lmax.disruptor.EventHandler;

/**
 * 就是消费者
 */
public class OrderEventHandle implements EventHandler<OrderEvent> {

    @Override
    public void onEvent(OrderEvent event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("消费者:"+event.getValue());
    }
}
