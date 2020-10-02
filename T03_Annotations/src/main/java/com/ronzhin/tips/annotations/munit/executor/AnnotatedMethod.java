package com.ronzhin.tips.annotations.munit.executor;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@AllArgsConstructor
public class AnnotatedMethod<T extends Annotation> {

    @Getter
    private final Method method;

    @Getter
    private final T annotation;

}