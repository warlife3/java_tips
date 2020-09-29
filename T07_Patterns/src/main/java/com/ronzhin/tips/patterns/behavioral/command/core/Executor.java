package com.ronzhin.tips.patterns.behavioral.command.core;

import java.util.ArrayList;
import java.util.List;

public class Executor {

    private final List<Command> commands = new ArrayList<>();

    public void addCommand(Command command) {
        commands.add(command);
    }

    public void executeCommands() {
        commands.stream().map(cmd -> cmd.execute("value"))
                .forEach(System.out::println);
    }

}