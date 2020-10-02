package com.ronzhin.tips.annotations;

import com.ronzhin.tips.annotations.munit.runner.TestRunner;
import com.ronzhin.tips.annotations.munit.runner.UnitTestRunner;

public class App {
    public static void main(String[] args) {
        TestRunner testRunner = new UnitTestRunner();
        testRunner.run(AnnotatedClass.class);
    }
}