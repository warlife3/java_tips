package com.ronzhin.tips.patterns.solid.examples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class OpenClosed {

    // Bad example
    // Can not use this function with TreeSet or another algorithm
    private void messageProcessing(ArrayList<Message> messageList) {
        messageList.forEach(msg -> System.out.println(msg.toString()));
    }

    // Good example
    private void messageProcessing(Collection<Message> messageList, Processor<Message> processor) {
        messageList.forEach(processor::action);
    }

    // Usage of good example
    void good() {
        messageProcessing(new HashSet<>(), msg -> System.out.println(msg.toString()));
        messageProcessing(new ArrayList<>(), msg -> System.out.println(msg.toString() + "R"));
    }

    @FunctionalInterface
    private interface Processor<T> {
        void action(T msg);
    }

    private class Message {
    }

}