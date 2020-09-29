package com.ronzhin.tips.patterns.solid.poly.operation;

public class Update implements Operation {

    @Override
    public void action(String data) {
        System.out.println("update, data:" + data);
    }

}
