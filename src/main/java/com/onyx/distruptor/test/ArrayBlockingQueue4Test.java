package com.onyx.distruptor.test;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * ArrayBlockingQueue 的测试
 */
public class ArrayBlockingQueue4Test {

    public static void main(String[] args) {
        final ArrayBlockingQueue<Data> queue = new ArrayBlockingQueue<Data>(100000000);
        final long startTime = System.currentTimeMillis();
        //向容器中添加元素
        new Thread(new Runnable() {
            @Override
            public void run() {
                long i = 0;
                while (i < Constants.EVENT_NUM_OM) {
                	Data data = new Data(i, "c" + i);
                    try {
                        queue.put(data);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    i++;
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                int k = 0;
                while (k < Constants.EVENT_NUM_OM) {
                    try {
                        queue.take();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    k++;
                }
                long endTime = System.currentTimeMillis();
                System.out.println("ArrayBlockingQueue costTime = " + (endTime - startTime) + "ms");
            }
        }).start();
    }
}