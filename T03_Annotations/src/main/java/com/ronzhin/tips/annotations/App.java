package com.ronzhin.tips.annotations;

import com.ronzhin.tips.annotations.core.TestsRunner;

public class App {

    public static void main(String[] args) throws Exception {

        new TestsRunner(AnnotatedClass.class).run();

    }

}