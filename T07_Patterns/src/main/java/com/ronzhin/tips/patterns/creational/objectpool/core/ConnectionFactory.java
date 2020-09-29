package com.ronzhin.tips.patterns.creational.objectpool.core;

public class ConnectionFactory {
    Connection create() {
        return new ConnectionOracle();
    }
}
