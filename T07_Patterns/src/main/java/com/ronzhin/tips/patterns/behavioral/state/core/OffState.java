package com.ronzhin.tips.patterns.behavioral.state.core;

public class OffState implements State {

    @Override
    public State action() {
        System.out.println("dark");
        return StateProvider.getOnState();
    }

}