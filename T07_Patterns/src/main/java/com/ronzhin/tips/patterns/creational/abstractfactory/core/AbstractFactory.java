package com.ronzhin.tips.patterns.creational.abstractfactory.core;


public interface AbstractFactory {
    Bulb createBulb();

    Lampholder createLampholder();
}
