package com.ronzhin.tips.patterns.behavioral.observer.core;

public class ConsumerA implements Listener {

    @Override
    public void onUpdate(String data) {
        System.out.println("ConsumerA data:" + data);
    }

}