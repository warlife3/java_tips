package com.ronzhin.tips.patterns.behavioral.strategy.core;

public class Bus implements Strategy {
    @Override
    public void transportation() {
        System.out.println("by bus");
    }
}
