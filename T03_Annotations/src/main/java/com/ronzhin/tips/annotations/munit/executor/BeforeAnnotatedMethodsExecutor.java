package com.ronzhin.tips.annotations.munit.executor;

import com.ronzhin.tips.annotations.annotation.Before;
import com.ronzhin.tips.annotations.munit.reflection.ReflectionUtil;

public class BeforeAnnotatedMethodsExecutor extends AnnotatedMethodsExecutor<Before, Object> {

    @Override
    public void execute(AnnotatedMethod<Before> annotatedMethod, Object object) {
        System.out.println("Stage Before. Description: " + annotatedMethod.getAnnotation().desc());
        ReflectionUtil.invoke(annotatedMethod.getMethod(), object);
    }
}