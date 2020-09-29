package com.ronzhin.tips.patterns.behavioral.strategy.core;

public class Context {
    private Strategy strategy;

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public void applyStrategy() {
        strategy.transportation();
    }
}
