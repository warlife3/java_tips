package com.ronzhin.tips.io.classic;

import java.io.Serializable;

public class Person implements Serializable {
    private static final long serialVersionUID = 1L;

    private final int age;
    private final String name;
    private final transient String hidden;

    public String newField = "ddd";

    {
        System.out.println("I see you after my birthday");
    }

    static {
        System.out.println("I always see you");
    }

    Person(int age, String name, String hidden) {
        System.out.println("Person was born");
        this.age = age;
        this.name = name;
        this.hidden = hidden;
    }

    @Override
    public String toString() {
        return String.format("Person name: '%s', age: '%d', hidden: '%s', newField: '%s'", name, age, hidden, newField);
    }

}