package com.ronzhin.tips.patterns.structural.bridge.core;

public class MirPS implements PaymentSystem {
    @Override
    public void printName() {
        System.out.println("Mir");
    }
}