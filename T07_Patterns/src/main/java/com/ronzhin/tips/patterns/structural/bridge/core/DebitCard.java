package com.ronzhin.tips.patterns.structural.bridge.core;

public class DebitCard extends Card {
    public DebitCard(PaymentSystem paymentSystem) {
        super(paymentSystem);
    }

    @Override
    protected void cardType() {
        System.out.println("debit card");
    }
}