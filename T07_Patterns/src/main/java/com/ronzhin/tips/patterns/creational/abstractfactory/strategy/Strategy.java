package com.ronzhin.tips.patterns.creational.abstractfactory.strategy;


import com.ronzhin.tips.patterns.creational.abstractfactory.core.AbstractFactory;

public interface Strategy {
    AbstractFactory configuration();
}