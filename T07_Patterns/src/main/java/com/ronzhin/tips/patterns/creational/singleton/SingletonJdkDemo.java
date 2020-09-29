package com.ronzhin.tips.patterns.creational.singleton;

public class SingletonJdkDemo {
    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();

        System.out.println("freeMemory = " + runtime.freeMemory());
    }
}
