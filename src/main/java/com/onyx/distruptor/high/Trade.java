package com.onyx.distruptor.high;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Disruptor 中的Event
 */
public class Trade {

    private String id;
    private String name;
    private double  price;
    private AtomicInteger count=new AtomicInteger(0);

    public Trade() {
    }

    public Trade(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
        count.incrementAndGet();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public AtomicInteger getCount() {
        return count;
    }

    public void setCount(AtomicInteger count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Trade{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", count=" + count +
                '}';
    }
}
