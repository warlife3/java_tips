package com.ronzhin.tips.patterns.creational.abstractfactory.core.luminescent;

import com.ronzhin.tips.patterns.creational.abstractfactory.core.Bulb;

public class BulbLuminescent implements Bulb {
    @Override
    public void light() {
        System.out.println("Luminescent light");
    }
}