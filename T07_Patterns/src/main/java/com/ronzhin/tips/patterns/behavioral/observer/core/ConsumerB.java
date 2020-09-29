package com.ronzhin.tips.patterns.behavioral.observer.core;

public class ConsumerB {

    private final Listener listener = data -> System.out.println("ConsumerB data:" + data);

    public Listener getListener() {
        return listener;
    }

}