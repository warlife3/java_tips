package com.ronzhin.tips.patterns.behavioral.strategy.core;

public class Taxi implements Strategy {
    @Override
    public void transportation() {
        System.out.println("by taxi");
    }
}