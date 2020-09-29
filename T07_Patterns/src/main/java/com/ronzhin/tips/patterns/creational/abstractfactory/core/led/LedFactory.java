package com.ronzhin.tips.patterns.creational.abstractfactory.core.led;

import com.ronzhin.tips.patterns.creational.abstractfactory.core.AbstractFactory;
import com.ronzhin.tips.patterns.creational.abstractfactory.core.Bulb;
import com.ronzhin.tips.patterns.creational.abstractfactory.core.Lampholder;

public class LedFactory implements AbstractFactory {
    @Override
    public Bulb createBulb() {
        return new BulbLed();
    }

    @Override
    public Lampholder createLampholder() {
        return new LampholderLed();
    }
}