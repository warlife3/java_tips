package com.ronzhin.annotation.core;

import java.lang.reflect.Method;
import java.util.List;

public class TestsRunner {


    private final Class<?> clazz;
    private final Object[] constructorParameters;

    public TestsRunner(Class<?> clazz, Object... constructorParameters) {
        this.clazz = clazz;
        this.constructorParameters = constructorParameters;
    }

    public void run() {

        ReflectedObject reflectedObject = new ReflectedObject(clazz);
        TestItem testItem = new TestItem(reflectedObject);
        AnnotationScanner annotationScanner = new AnnotationScanner(testItem);
        annotationScanner.scan();
        List<Method> beforeTests = testItem.getStageMethods(TestStage.BEFORE);
        List<Method> tests = testItem.getStageMethods(TestStage.TEST);
        List<Method> afterTest = testItem.getStageMethods(TestStage.AFTER);

        for (Method method : tests) {
            Object instance = testItem.getReflectedObject().generateInstance();
            beforeTests.forEach(method1 -> testItem.callMethod(instance, method1));
            testItem.callMethod(instance, method);
            afterTest.forEach(method1 -> testItem.callMethod(instance, method1));
        }

    }

}