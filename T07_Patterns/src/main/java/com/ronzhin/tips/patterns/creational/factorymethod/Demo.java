package com.ronzhin.tips.patterns.creational.factorymethod;

import com.ronzhin.tips.patterns.creational.factorymethod.core.Configuration;
import com.ronzhin.tips.patterns.creational.factorymethod.core.ConfigurationFactory;

public class Demo {
    public static void main(String[] args) {
        Configuration config;

        config = ConfigurationFactory.getConfiguration("file");
        System.out.println(config.params());

        config = ConfigurationFactory.getConfiguration("db");
        System.out.println(config.params());

    }
}
