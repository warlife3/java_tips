package com.ronzhin.tips.patterns.behavioral.strategy;

import com.ronzhin.tips.patterns.behavioral.strategy.core.Bus;
import com.ronzhin.tips.patterns.behavioral.strategy.core.Car;
import com.ronzhin.tips.patterns.behavioral.strategy.core.Context;
import com.ronzhin.tips.patterns.behavioral.strategy.core.Taxi;

public class Demo {

    public static void main(String[] args) {
        Context context = new Context();
        context.setStrategy(new Bus());
        context.applyStrategy();

        context.setStrategy(new Car());
        context.applyStrategy();

        context.setStrategy(new Taxi());
        context.applyStrategy();

        context.setStrategy(() -> System.out.println("by rocket"));
        context.applyStrategy();
    }

}