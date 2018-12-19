package com.onyx.distruptor.quickstart;

/**
 * 订单,
 * 因为走的是纯内存,所以不用实现serializer接口
 */
public class OrderEvent {

    /**
     * 订单的价格
     */
    private long value;

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }


}
