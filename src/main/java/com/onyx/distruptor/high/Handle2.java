package com.onyx.distruptor.high;


import com.lmax.disruptor.EventHandler;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Handle2 implements EventHandler<Trade> {

    @Override
    public void onEvent(Trade event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("Handle2  set id");
        event.setId(UUID.randomUUID().toString());
        TimeUnit.SECONDS.sleep(2);
    }
}
