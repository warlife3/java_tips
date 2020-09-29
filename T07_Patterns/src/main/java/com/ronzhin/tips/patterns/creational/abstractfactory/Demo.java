package com.ronzhin.tips.patterns.creational.abstractfactory;

import com.ronzhin.tips.patterns.creational.abstractfactory.core.AbstractFactory;
import com.ronzhin.tips.patterns.creational.abstractfactory.core.Bulb;
import com.ronzhin.tips.patterns.creational.abstractfactory.core.Lampholder;
import com.ronzhin.tips.patterns.creational.abstractfactory.core.led.LedFactory;
import com.ronzhin.tips.patterns.creational.abstractfactory.core.luminescent.LuminescentFactory;

public class Demo {

    private Demo(AbstractFactory abstractFactory) {
        Bulb bulb = abstractFactory.createBulb();
        Lampholder lampholder = abstractFactory.createLampholder();

        bulb.light();
        lampholder.hold();
    }

    public static AbstractFactory configuration(String param) {
        if ("Led".equals(param)) {
            return new LedFactory();
        }
        if ("Luminescent".equals(param)) {
            return new LuminescentFactory();
        }
        throw new IllegalArgumentException("unknown param:" + param);
    }

    public static void main(String[] args) {
        AbstractFactory ledFactory = configuration("Led");
        new Demo(ledFactory);

        AbstractFactory luminescentFactory = configuration("Luminescent");
        new Demo(luminescentFactory);
    }

}