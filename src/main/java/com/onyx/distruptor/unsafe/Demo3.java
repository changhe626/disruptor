package com.onyx.distruptor.unsafe;

import com.onyx.distruptor.test.Data;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class Demo3 {

    public static void main(String[] args) throws Exception {
        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        Unsafe unsafe = (Unsafe) f.get(null);


        /**
         * CAS操作
         * Compare And Swap（比较并交换），当需要改变的值为期望的值时，那么就替换它为新的值，是原子
         * （不可在分割）的操作。很多并发框架底层都用到了CAS操作，CAS操作优势是无锁，可以减少线程切换耗费
         * 的时间，但CAS经常失败运行容易引起性能问题，也存在ABA问题。在Unsafe中包含compareAndSwapObject、
         * compareAndSwapInt、compareAndSwapLong三个方法，compareAndSwapInt的简单示例如下。
         */
        Data data = new Data();
        data.setId(1L);
        Field id = data.getClass().getDeclaredField("id");
        long l = unsafe.objectFieldOffset(id);
        id.setAccessible(true);
        //比较并交换，比如id的值如果是所期望的值1，那么就替换为2，否则不做处理
        unsafe.compareAndSwapLong(data,1L,1L,2L);
        System.out.println(data.getId());

    }

    /**
     * 内存栅栏
     * 用于防止指令重排序，包含fullFence，loadFence，StoreFence三个方法。
     * 现代的CPU运行速度很快，很多指令重排序的例子已经无法得到想要的效果
     */
        /* CPU 0:
        void shutDownWithFailure (void) {
                failure = 1; // must use SOB as this is owned by CPU 1
        SFENCE // next instruction will execute after all SOBs are processed
                shutdown = 1; // can execute immediately as it is owned be CPU 0
        }

        // CPU1:
        void workLoop (void) {
        while (shutdown == 0) { ...}
        LFENCE // next instruction will execute after all LOBs are processed
        if (failure) { ...}
        }*/



}
