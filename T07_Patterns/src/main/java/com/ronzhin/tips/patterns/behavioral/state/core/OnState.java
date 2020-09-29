package com.ronzhin.tips.patterns.behavioral.state.core;

public class OnState implements State {

    @Override
    public State action() {
        System.out.println("bulb is lightning");
        return StateProvider.getOffState();
    }

}