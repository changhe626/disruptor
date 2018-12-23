package com.onyx.distruptor.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;

public class Demo4 {

    public static void main(String[] args) throws Exception {
        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        Unsafe unsafe = (Unsafe) f.get(null);

        /**
         * 常量获取
         *
         * 可以获取地址大小（addressSize），页大小（pageSize），基本类型数组的偏移量
         * （Unsafe.ARRAY_INT_BASE_OFFSET\Unsafe.ARRAY_BOOLEAN_BASE_OFFSET等）、
         * 基本类型数组内元素的间隔（Unsafe.ARRAY_INT_INDEX_SCALE\Unsafe.ARRAY_BOOLEAN_INDEX_SCALE等）
         */
        //get os address size
        System.out.println("address size is :" + unsafe.addressSize());
        //get os page size
        System.out.println("page size is :" + unsafe.pageSize());
        //int array base offset
        System.out.println("unsafe array int base offset:" + Unsafe.ARRAY_INT_BASE_OFFSET);

        /**
         * 线程许可
         * 许可线程通过（park），或者让线程等待许可(unpark)，
         */
        Thread packThread = new Thread(() -> {
            long startTime = System.currentTimeMillis();
            //纳秒，相对时间park
            unsafe.park(false,3000000000L);
            //毫秒，绝对时间park
            //unsafe.park(true,System.currentTimeMillis()+3000);

            System.out.println("main thread end,cost :"+(System.currentTimeMillis()-startTime)+"ms");
        });
        packThread.start();
        TimeUnit.SECONDS.sleep(1);
        //注释掉下一行后，线程3秒数后进行输出,否则在1秒后输出
        unsafe.unpark(packThread);

    }


}
