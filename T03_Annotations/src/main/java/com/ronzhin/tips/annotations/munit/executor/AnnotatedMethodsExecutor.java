package com.ronzhin.tips.annotations.munit.executor;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

public abstract class AnnotatedMethodsExecutor<T extends Annotation, S> implements Command<S> {

    private final List<AnnotatedMethod<T>> annotatedMethods = new ArrayList<>();

    int countMethods() { return annotatedMethods.size(); }

    public void addAnnotatedMethod(AnnotatedMethod<T> testAnnotatedMethod) {
        annotatedMethods.add(testAnnotatedMethod);
    }

    public void execute(S object) {
        for (AnnotatedMethod<T> annotatedMethod : annotatedMethods) {
            execute(annotatedMethod, object);
        }
    }

    public abstract void execute(AnnotatedMethod<T> annotatedMethod, S object);

}