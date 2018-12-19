package com.onyx.distruptor.quickstart;

import com.lmax.disruptor.EventFactory;

/**
 * 订单工厂
 */
public class OrderFactory implements EventFactory<OrderEvent> {


    @Override
    public OrderEvent newInstance() {
        //这个方法就是为了返回空的消息(数据对象,Event)
        return new OrderEvent();
    }
}
