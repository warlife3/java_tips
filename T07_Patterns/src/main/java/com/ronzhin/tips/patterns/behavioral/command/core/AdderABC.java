package com.ronzhin.tips.patterns.behavioral.command.core;

public class AdderABC implements Command {

    @Override
    public String execute(String data) {
        return data + "ABC";
    }

}
