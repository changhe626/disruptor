package com.onyx.distruptor.quickstart;

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

public class OrderEventProducer {

    private RingBuffer<OrderEvent> ringBuffer;

    public OrderEventProducer(RingBuffer<OrderEvent> ringBuffer) {
        this.ringBuffer=ringBuffer;
    }

    /**
     * 设置数据
     */
    public void sendData(ByteBuffer buffer){
        //1.在生产者发送消息的时候,首先需要从我们的ringBuffer获取一个可用的序号
        long sequence = ringBuffer.next();
        try {
            //2.根据这个序号,找到具体的"OrderEvent" 元素
            //此时这个OrderEvent 对象是个未填充的对象
            OrderEvent event = ringBuffer.get(sequence);
            //3.进行赋值
            event.setValue(buffer.getLong(0));
        }finally {
            //4.提交操作
            ringBuffer.publish(sequence);
        }
    }

}
