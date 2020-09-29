package com.ronzhin.tips.patterns.creational.abstractfactory.strategy;


import com.ronzhin.tips.patterns.creational.abstractfactory.core.AbstractFactory;
import com.ronzhin.tips.patterns.creational.abstractfactory.core.Bulb;
import com.ronzhin.tips.patterns.creational.abstractfactory.core.Lampholder;

public class Demo {

    public Demo(AbstractFactory abstractFactory) {
        Bulb bulb = abstractFactory.createBulb();
        Lampholder lampholder = abstractFactory.createLampholder();

        bulb.light();
        lampholder.hold();
    }

    public static AbstractFactory configuration(Strategy strategy) {
        return strategy.configuration();
    }

    public static void main(String[] args) {
        new Demo(configuration(new LedStrategy()));
        new Demo(configuration(new LuminescentStrategy()));
    }

}