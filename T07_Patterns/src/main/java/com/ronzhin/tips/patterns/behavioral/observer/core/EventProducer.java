package com.ronzhin.tips.patterns.behavioral.observer.core;

import java.util.ArrayList;
import java.util.List;

public class EventProducer {

    private final List<Listener> listeners = new ArrayList<>();

    public void addListener(Listener listener) {
        listeners.add(listener);
    }

    public void removeListener(Listener listener) {
        listeners.remove(listener);
    }

    public void event(String data) {
        listeners.forEach(listener -> listener.onUpdate(data));
    }

}