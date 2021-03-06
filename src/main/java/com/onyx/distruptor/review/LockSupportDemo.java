package com.onyx.distruptor.review;

public class LockSupportDemo {
    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();

        Thread thread = new Thread(() -> {
            int sum = 0;
            for (int i = 0; i < 10; i++) {
                sum += i;
            }
            synchronized (lock){
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("sum+="+sum);
        });
        thread.start();
        Thread.sleep(2000);

        synchronized (lock){
            lock.notify();
        }


    }


}
