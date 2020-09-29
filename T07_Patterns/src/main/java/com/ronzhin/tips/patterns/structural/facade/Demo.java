package com.ronzhin.tips.patterns.structural.facade;

import com.ronzhin.tips.patterns.structural.facade.core.Facade;
import com.ronzhin.tips.patterns.structural.facade.core.HellSystemA;
import com.ronzhin.tips.patterns.structural.facade.core.HellSystemB;

public class Demo {

    public static void main(String[] args) {
        HellSystemA systemA = new HellSystemA();
        HellSystemB systemB = new HellSystemB();

        Facade facade = new Facade(systemA, systemB);
        facade.simpleAction();
    }

}