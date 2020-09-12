package com.ronzhin.annotation;

import com.ronzhin.annotation.core.TestsRunner;

public class App {

    public static void main(String[] args) throws Exception {

        new TestsRunner(AnnotationsTest.class).run();

    }

}