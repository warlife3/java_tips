package com.ronzhin.tips.patterns.creational.abstractfactory.core.luminescent;

import com.ronzhin.tips.patterns.creational.abstractfactory.core.AbstractFactory;
import com.ronzhin.tips.patterns.creational.abstractfactory.core.Bulb;
import com.ronzhin.tips.patterns.creational.abstractfactory.core.Lampholder;

public class LuminescentFactory implements AbstractFactory {
    @Override
    public Bulb createBulb() {
        return new BulbLuminescent();
    }

    @Override
    public Lampholder createLampholder() {
        return new LampholderLuminescent();
    }
}