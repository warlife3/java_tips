package com.ronzhin.tips.bytecodes.proxy.core;

public class ConsoleShower implements Shower {

    @Override
    public void show(String string) {
        System.out.println(getClass().getName());
        System.out.printf("This is my output: %s\n", string);
    }

}