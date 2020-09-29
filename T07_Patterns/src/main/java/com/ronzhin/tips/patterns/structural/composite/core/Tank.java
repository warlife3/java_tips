package com.ronzhin.tips.patterns.structural.composite.core;

public class Tank implements Unit {
    @Override
    public void move() {
        System.out.println("Tank is moving");
    }
}