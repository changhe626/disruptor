package com.onyx.distruptor.high;

import com.lmax.disruptor.EventTranslator;
import com.lmax.disruptor.RingBuffer;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class TradePublisher implements Runnable {

    private CountDownLatch countDownLatch;
    private RingBuffer<Trade> ringBuffer;

    public TradePublisher(CountDownLatch countDownLatch, RingBuffer<Trade> ringBuffer) {
        this.countDownLatch=countDownLatch;
        this.ringBuffer=ringBuffer;
    }

    @Override
    public void run() {

        //新的提交任务的方式

        for (int i = 0; i < 10; i++) {
            ringBuffer.publishEvent(new TradeEventTranslator());
            countDownLatch.countDown();
        }

    }
}


class TradeEventTranslator implements EventTranslator<Trade> {

    private Random r=new Random();

    @Override
    public void translateTo(Trade event, long sequence) {
        this.generateTrade(event);
    }

    private void generateTrade(Trade event) {
        event.setPrice(r.nextDouble()*999);
    }
}
