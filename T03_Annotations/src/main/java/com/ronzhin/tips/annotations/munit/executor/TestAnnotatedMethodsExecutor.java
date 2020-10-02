package com.ronzhin.tips.annotations.munit.executor;

import com.ronzhin.tips.annotations.annotation.Test;
import com.ronzhin.tips.annotations.munit.reflection.ReflectedObject;
import com.ronzhin.tips.annotations.munit.reflection.ReflectionUtil;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class TestAnnotatedMethodsExecutor extends AnnotatedMethodsExecutor<Test, ReflectedObject> {

    private final List<AnnotatedMethod<Test>> failedAnnotatedTests = new ArrayList<>();
    @Setter
    private AfterAnnotatedMethodsExecutor afterAnnotatedMethodsExecutor;
    @Setter
    private BeforeAnnotatedMethodsExecutor beforeAnnotatedMethodsExecutor;

    @Override
    public void execute(ReflectedObject instance) {
        super.execute(instance);

        int allTests = super.countMethods();
        int failedTests = failedAnnotatedTests.size();
        int successfulTests = allTests - failedTests;

        System.out.printf("\n\nAll tests: %d, successful: %d, failed: %d ", allTests, successfulTests, failedTests);
    }

    @Override
    public void execute(AnnotatedMethod<Test> annotatedMethod, ReflectedObject reflectedObject) {
        Object instance = reflectedObject.generateInstance();
        System.out.println();
        beforeAnnotatedMethodsExecutor.execute(instance);
        try {
            System.out.println("Stage Test. Description: " + annotatedMethod.getAnnotation().desc());
            ReflectionUtil.invoke(annotatedMethod.getMethod(), instance);
            System.out.println("Test was successful");
        } catch (RuntimeException ex) {
            System.out.println("Test was failed. Reason: " + ex.getMessage());
            failedAnnotatedTests.add(annotatedMethod);
        }
        afterAnnotatedMethodsExecutor.execute(instance);
    }

}