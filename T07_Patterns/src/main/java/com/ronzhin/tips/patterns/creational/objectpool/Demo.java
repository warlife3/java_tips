package com.ronzhin.tips.patterns.creational.objectpool;

import com.ronzhin.tips.patterns.creational.objectpool.core.ConnectionFactory;
import com.ronzhin.tips.patterns.creational.objectpool.core.ConnectionPool;

public class Demo {
    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        ConnectionPool pool = new ConnectionPool(5, connectionFactory);
        pool.get().select();
        pool.get().select();
        pool.get().select();
        pool.get().select();
        pool.get().select();
        pool.get().select();
    }
}
