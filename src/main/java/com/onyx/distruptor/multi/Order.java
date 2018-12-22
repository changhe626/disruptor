package com.onyx.distruptor.multi;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Disruptor 中的Event
 */
public class Order {

    private String id;
    private String name;
    private double  price;

    public Order() {
    }

    public Order(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
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


    @Override
    public String toString() {
        return "Trade{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
