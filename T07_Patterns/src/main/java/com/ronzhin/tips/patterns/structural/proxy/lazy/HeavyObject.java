package com.ronzhin.tips.patterns.structural.proxy.lazy;

public interface HeavyObject {

    void init(String value);

    boolean isInit();

    String getValue();
}