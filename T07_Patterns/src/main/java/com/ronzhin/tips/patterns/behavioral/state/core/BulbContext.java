package com.ronzhin.tips.patterns.behavioral.state.core;

public class BulbContext {

    private State state = StateProvider.getOffState();

    public void performAction() {
        this.setState(state.action());
    }

    void setState(State state) {
        this.state = state;
    }

}
