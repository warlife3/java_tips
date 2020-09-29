package com.ronzhin.tips.patterns.creational.abstractfactory.core.led;


import com.ronzhin.tips.patterns.creational.abstractfactory.core.Lampholder;

public class LampholderLed implements Lampholder {
    @Override
    public void hold() {
        System.out.println("Led hold");
    }
}