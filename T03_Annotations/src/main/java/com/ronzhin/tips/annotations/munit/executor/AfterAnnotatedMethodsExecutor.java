package com.ronzhin.tips.annotations.munit.executor;

import com.ronzhin.tips.annotations.annotation.After;
import com.ronzhin.tips.annotations.munit.reflection.ReflectionUtil;

public class AfterAnnotatedMethodsExecutor extends AnnotatedMethodsExecutor<After, Object> {

    @Override
    public void execute(AnnotatedMethod<After> annotatedMethod, Object object) {
        System.out.println("Stage After. Description: " + annotatedMethod.getAnnotation().desc());
        ReflectionUtil.invoke(annotatedMethod.getMethod(), object);

    }
}