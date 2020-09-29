package com.ronzhin.tips.patterns.creational.abstractfactory.strategy;


import com.ronzhin.tips.patterns.creational.abstractfactory.core.AbstractFactory;
import com.ronzhin.tips.patterns.creational.abstractfactory.core.luminescent.LuminescentFactory;

public class LuminescentStrategy implements Strategy {
    @Override
    public AbstractFactory configuration() {
        return new LuminescentFactory();
    }
}