package com.ronzhin.tips.patterns.behavioral.state;

import com.ronzhin.tips.patterns.behavioral.state.core.BulbContext;

public class Demo {

    public static void main(String[] args) {
        BulbContext context = new BulbContext();
        context.performAction();
        context.performAction();
        context.performAction();
    }

}