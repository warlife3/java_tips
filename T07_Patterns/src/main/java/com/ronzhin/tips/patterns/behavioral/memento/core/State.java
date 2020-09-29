package com.ronzhin.tips.patterns.behavioral.memento.core;

import java.util.Arrays;

public class State {

    private final String[] array;

    public State(String[] array) {
        this.array = array;
    }

    State(State state) {
        this.array = Arrays.copyOf(state.getArray(), state.getArray().length);
    }

    public String[] getArray() {
        return array;
    }

    @Override
    public String toString() {
        return "State{" +
                "array=" + (array == null ? null : Arrays.asList(array)) +
                '}';
    }

}