package com.ronzhin.tips.patterns.creational.factorymethod.core;

public class ConfigurationFile implements Configuration {
    @Override
    public String params() {
        return "params from file";
    }

}