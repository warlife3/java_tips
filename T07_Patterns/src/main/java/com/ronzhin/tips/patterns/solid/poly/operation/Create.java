package com.ronzhin.tips.patterns.solid.poly.operation;

public class Create implements Operation {

    @Override
    public void action(String data) {
        System.out.println("create, data:" + data);
    }

}