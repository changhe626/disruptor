package com.onyx.distruptor.multi;

import com.lmax.disruptor.WorkHandler;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

//多消费者一定要实现的接口
public class Consumer implements WorkHandler<Order> {
    private String consumerId;
    private static AtomicInteger integer=new AtomicInteger(0);
    private Random r=new Random();

    public Consumer(String consumerId) {
        this.consumerId = consumerId;
    }

    public  AtomicInteger getCount() {
        return integer;
    }

    @Override
    public void onEvent(Order event) throws Exception {
        Thread.sleep(1*r.nextInt(5));
        System.out.println("this counsumer is:"+consumerId+",消费信息:"+event);
        integer.incrementAndGet();
    }
}
