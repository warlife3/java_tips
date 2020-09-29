package com.ronzhin.tips.patterns.creational.abstractfactory.core.luminescent;

import com.ronzhin.tips.patterns.creational.abstractfactory.core.Lampholder;

public class LampholderLuminescent implements Lampholder {
    @Override
    public void hold() {
        System.out.println("Luminescent hold");
    }
}