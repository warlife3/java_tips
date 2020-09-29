package com.ronzhin.tips.patterns.behavioral.command;

import com.ronzhin.tips.patterns.behavioral.command.core.AdderABC;
import com.ronzhin.tips.patterns.behavioral.command.core.Echo;
import com.ronzhin.tips.patterns.behavioral.command.core.Executor;

public class Demo {

    public static void main(String[] args) {
        Executor executor = new Executor();

        executor.addCommand(new AdderABC());
        executor.addCommand(new Echo());
        executor.addCommand(new AdderABC());
        executor.addCommand(String::toUpperCase);

        executor.executeCommands();
    }

}