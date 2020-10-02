package com.ronzhin.tips.annotations.munit.runner;

import com.ronzhin.tips.annotations.annotation.After;
import com.ronzhin.tips.annotations.annotation.Before;
import com.ronzhin.tips.annotations.annotation.Test;
import com.ronzhin.tips.annotations.munit.executor.AnnotatedMethod;
import com.ronzhin.tips.annotations.munit.executor.AfterAnnotatedMethodsExecutor;
import com.ronzhin.tips.annotations.munit.executor.BeforeAnnotatedMethodsExecutor;
import com.ronzhin.tips.annotations.munit.executor.TestAnnotatedMethodsExecutor;
import com.ronzhin.tips.annotations.munit.reflection.ReflectedObject;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class UnitTestRunner implements TestRunner {

    private final TestAnnotatedMethodsExecutor testsExecutor = new TestAnnotatedMethodsExecutor();
    private final AfterAnnotatedMethodsExecutor afterExecutor = new AfterAnnotatedMethodsExecutor();
    private final BeforeAnnotatedMethodsExecutor beforeExecutor = new BeforeAnnotatedMethodsExecutor();

    @Override
    public void run(Class<?> target) {
        ReflectedObject reflectedObject = new ReflectedObject(target);
        Method[] methods = reflectedObject.getMethods();
        annotationSplitter(methods);

        testsExecutor.setAfterAnnotatedMethodsExecutor(afterExecutor);
        testsExecutor.setBeforeAnnotatedMethodsExecutor(beforeExecutor);
        testsExecutor.execute(reflectedObject);
    }

    private void annotationSplitter(Method[] methods) {
        for (Method method : methods) {
            Annotation[] declaredAnnotations = method.getDeclaredAnnotations();
            for (Annotation annotation : declaredAnnotations) {
                if (annotation instanceof Before) {
                    beforeExecutor.addAnnotatedMethod(new AnnotatedMethod<>(method, (Before) annotation));
                } else if (annotation instanceof Test) {
                    testsExecutor.addAnnotatedMethod(new AnnotatedMethod<>(method, (Test) annotation));
                } else if (annotation instanceof After) {
                    afterExecutor.addAnnotatedMethod(new AnnotatedMethod<>(method, (After) annotation));
                }
            }
        }
    }
}