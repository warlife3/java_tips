package com.ronzhin.tips.patterns.creational.abstractfactory.core.led;

import com.ronzhin.tips.patterns.creational.abstractfactory.core.Bulb;

public class BulbLed implements Bulb {
    @Override
    public void light() {
        System.out.println("Led light");
    }
}