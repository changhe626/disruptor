package com.onyx.distruptor.review;

import java.util.concurrent.locks.LockSupport;

public class LockSupportDemo2 {
    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();

        Thread thread = new Thread(() -> {
            int sum = 0;
            for (int i = 0; i < 10; i++) {
                sum += i;
            }
            LockSupport.park();
            System.out.println("sum+="+sum);
        });
        thread.start();
        Thread.sleep(2000);

        LockSupport.unpark(thread);
    }


}
