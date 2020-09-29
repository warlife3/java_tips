package com.ronzhin.tips.patterns.behavioral.strategy.core;

public class Car implements Strategy {
    @Override
    public void transportation() {
        System.out.println("by car");
    }
}
