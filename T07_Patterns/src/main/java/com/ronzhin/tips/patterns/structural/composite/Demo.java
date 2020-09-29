package com.ronzhin.tips.patterns.structural.composite;

import com.ronzhin.tips.patterns.structural.composite.core.Group;
import com.ronzhin.tips.patterns.structural.composite.core.Marine;
import com.ronzhin.tips.patterns.structural.composite.core.Tank;

public class Demo {
    public static void main(String[] args) {
        Group group = new Group();
        group.addUnit(new Marine());
        group.addUnit(new Marine());
        group.addUnit(new Marine());
        group.addUnit(new Tank());

        System.out.println("first group:");
        group.move();

        Group group2 = new Group();
        group2.addUnit(group);
        group2.addUnit(new Tank());

        System.out.println("second group:");
        group2.move();
    }
}