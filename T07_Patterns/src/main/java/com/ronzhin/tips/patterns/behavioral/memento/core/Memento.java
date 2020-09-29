package com.ronzhin.tips.patterns.behavioral.memento.core;

public class Memento {
    private final State state;

    Memento(State state) {
        this.state = new State(state);
    }

    State getState() {
        return state;
    }

}