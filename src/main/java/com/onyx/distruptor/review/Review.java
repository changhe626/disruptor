package com.onyx.distruptor.review;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class Review {
    public static void main(String[] args) {

        ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<>();
        ConcurrentSkipListMap<String, String> concurrentSkipListMap = new ConcurrentSkipListMap<>();

        //读多写少
        CopyOnWriteArrayList<String> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        CopyOnWriteArraySet<String> copyOnWriteArraySet = new CopyOnWriteArraySet<>();

        //ArrayBlockingQueue
        //LinkedBlockingDeque
        //SynchronousQueue
        //PriorityBlockingQueue
        //DelayQueue


        /**
         * volatile
         * 1.多线程之间可见性
         * 2.组织指令重排序
         */
        /**
         * Unsafe
         * 1.内存操作
         * 2.字段的定位于修改
         * 3.挂起于恢复
         * 4.CAS操作(乐观锁)
         *
         */

        /**
         * Exchanger  线程数据交换器
         *
         * LockSupport 基于线程的锁
         */




    }

}
