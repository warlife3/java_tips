package com.ronzhin.tips.patterns.creational.builder;

public class Demo {
    public static void main(String[] args) {
        // Without pattern
        BigObject bigObject1 = new BigObject(null, "2", null, "4", "5");
        System.out.println(bigObject1);

        // With pattern
        BigObject bigObject2 = new BigObject.Builder("1")
                .withParam2("2")
                .withParam3("3")
                .withParam5("5")
                .build();

        System.out.println(bigObject2);
    }
}
