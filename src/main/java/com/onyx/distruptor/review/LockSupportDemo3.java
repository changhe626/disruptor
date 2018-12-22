package com.onyx.distruptor.review;

import java.util.concurrent.locks.LockSupport;

public class LockSupportDemo3 {
    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();

        Thread thread = new Thread(() -> {
            int sum = 0;
            for (int i = 0; i < 10; i++) {
                sum += i;
            }
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //阻塞
            LockSupport.park();
            System.out.println("sum+="+sum);
        });
        thread.start();
        Thread.sleep(1000);

        //先唤醒了...
        LockSupport.unpark(thread);
    }


}
