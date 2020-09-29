package com.ronzhin.tips.patterns.behavioral.command.core;

public class Echo implements Command {

    @Override
    public String execute(String data) {
        return data;
    }

}