package com.ronzhin.tips.patterns.behavioral.command.core;

@FunctionalInterface
public interface Command {

    String execute(String data);

}