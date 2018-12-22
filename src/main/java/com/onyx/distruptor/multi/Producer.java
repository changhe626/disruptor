package com.onyx.distruptor.multi;

import com.lmax.disruptor.RingBuffer;

public class Producer {

    private RingBuffer<Order> ringBuffer;

    public Producer(RingBuffer<Order> ringBuffer) {
        this.ringBuffer=ringBuffer;
    }


    public void sendData(String toString) {
        long next = ringBuffer.next();
        try{
            Order order = ringBuffer.get(next);
            order.setId(toString);
        }finally {
            ringBuffer.publish(next);
        }
    }
}
