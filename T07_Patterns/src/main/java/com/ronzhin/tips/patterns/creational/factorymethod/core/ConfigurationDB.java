package com.ronzhin.tips.patterns.creational.factorymethod.core;

public class ConfigurationDB implements Configuration {
    @Override
    public String params() {
        return "params from DB";
    }
}