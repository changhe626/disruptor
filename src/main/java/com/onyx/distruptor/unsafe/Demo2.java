package com.onyx.distruptor.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class Demo2 {

    private static Unsafe unsafe = null;
    private static Field getUnsafe = null;

    static {
        try {
            getUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            getUnsafe.setAccessible(true);
            unsafe = (Unsafe) getUnsafe.get(null);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        /**
         * 操作数组:
         * 可以获取数组的在内容中的基本偏移量（arrayBaseOffset），获取数组内元素的间隔（比例），
         * 根据数组对象和偏移量获取元素值（getObject），设置数组元素值（putObject），示例如下。
         */
        String[] strings = new String[]{"1", "2", "3"};
        long i = unsafe.arrayBaseOffset(String[].class);
        System.out.println("string[] base offset is :" + i);

        //every index scale
        long scale = unsafe.arrayIndexScale(String[].class);
        System.out.println("string[] index scale is " + scale);

        //print first string in strings[]
        System.out.println("first element is :" + unsafe.getObject(strings, i));

        //set 100 to first string
        unsafe.putObject(strings, i + scale * 0, "100");

        //print first string in strings[] again
        System.out.println("after set ,first element is :" + unsafe.getObject(strings, i + scale * 0));
    }


}
/**
 * 将一个线程进行挂起是通过park方法实现的，调用park后，线程将一直阻塞直到超时或者中断等条件出现。
 * unpark可以终止一个挂起的线程，使其恢复正常。整个并发框架中对线程的挂起操作被封装在 LockSupport类中，
 * LockSupport类中有各种版本pack方法，但最终都调用了Unsafe.park()方法；
 *
 * unpark函数为线程提供“许可(permit)”，线程调用park函数则等待“许可”。这个有点像信号量，
 * 但是这个“许可”是不能叠加的，“许可”是一次性的；比如线程B连续调用了三次unpark函数，
 * 当线程A调用park函数就使用掉这个“许可”，如果线程A再次调用park，则进入等待状态
 *
 *
 * 注意，unpark函数可以先于park调用。比如线程B调用unpark函数，给线程A发了一个“许可”，
 * 那么当线程A调用park时，它发现已经有“许可”了，那么它会马上再继续运行。实际上，park函数即使没有“许可”，
 * 有时也会无理由地返回，实际上在SUN Jdk中，object.wait()也有可能被假唤醒；
 *
 * 注意：unpark方法最好不要在调用park前对当前线程调用unpark
 */
