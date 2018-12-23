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
         *
         *
         * 自定义线程池的数量设置和任务有关系,是CPU密集型还是IO密集型的????依据是什么?
         * 线程池的大小,数量?
         *
         * 线程池执行前后执行的方法.做日志的监控.
         * 使用线程池一定要关闭.
         *
         * 推荐使用javabean 创建线程池,在destory中进行关闭
         *
         */

        /**
         * AQS架构
         * AQS维护了一个volatile int state (代表共享资源)和一个FIFO线程等
         * 待队列(多线程争用资源被阻塞时会进入此队列)
         *
         * AQS定义两种资源共享方式 Exclusive(独占)  Share(共享)
         *
         *
         * isHeldExclusively 方法,该线程是否正在独占资源
         *
         * tryAcquire/tryRelease  独占的方式尝试获取和释放资源
         *
         * tryAcquireShared/tryReleaseShared 共享方式尝试和释放资源
         *
         *
         * 以ReentrantLock重入锁为例,state初始化为0 ,表示未锁定状态
         * A线程lock时,会调用tryAcquire()独占该锁并将state+1
         * 此后,其他线程再tryAcquire()时候就会失败,知道A线程unlock()到state=0(释放锁)为止
         * ,其他县城才有机会获取该锁
         * 一个线程加了几次锁,就要释放几次.
         *
         *
         * CountDownLatch分析:
         * 任务分为N个子线程去执行,state也初始化为N,注意N要与线程个数一直
         * 这N个子线程是并行执行的,每个子线程执行完后,countDown()一次,state会CAS减1
         * 等到所有子线程都执行完后即state=0,会unpark()调用线程,
         * 然后主调用线程就会从await()函数返回,继续后余动作
         *
         *
         * 消除伪共享.....怎么消除?jdk中有用到吗?
         */




    }

}
