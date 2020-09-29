package com.ronzhin.tips.patterns.creational.abstractfactory.strategy;


import com.ronzhin.tips.patterns.creational.abstractfactory.core.AbstractFactory;
import com.ronzhin.tips.patterns.creational.abstractfactory.core.led.LedFactory;

public class LedStrategy implements Strategy {
    @Override
    public AbstractFactory configuration() {
        return new LedFactory();
    }
}