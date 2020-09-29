package com.ronzhin.tips.patterns.structural.flyweight;

import com.ronzhin.tips.patterns.structural.flyweight.core.ObjectFactory;
import com.ronzhin.tips.patterns.structural.flyweight.core.ObjectOnLine;

public class Demo {

    public static void main(String[] args) {
        ObjectFactory objectFactory = new ObjectFactory();

        ObjectOnLine object1 = objectFactory.create(1);
        System.out.println(object1.toString());

        ObjectOnLine object2 = objectFactory.create(2);
        System.out.println(object2.toString());

        ObjectOnLine object3 = objectFactory.create(3);
        System.out.println(object3.toString());
    }

}