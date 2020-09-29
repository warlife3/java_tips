package com.ronzhin.tips.patterns.structural.bridge.core;

public class MastercardPS implements PaymentSystem {
    @Override
    public void printName() {
        System.out.println("MastercardPS");
    }
}