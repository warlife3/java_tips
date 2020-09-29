package com.ronzhin.tips.patterns.structural.bridge.core;

public class VisaPS implements PaymentSystem {
    @Override
    public void printName() {
        System.out.println("VisaPS");
    }
}