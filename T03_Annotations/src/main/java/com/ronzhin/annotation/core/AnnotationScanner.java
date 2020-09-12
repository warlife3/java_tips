package com.ronzhin.annotation.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;
import java.util.function.Consumer;

public class AnnotationScanner {

    private final TestItem testItem;

    public AnnotationScanner(TestItem testItem) {
        this.testItem = testItem;
    }

    public void scan() {
        ReflectedObject reflectedObject = testItem.getReflectedObject();
        for (Method method : reflectedObject.getMethods()) {
            List<Annotation> annotations = reflectedObject.getAnnotations(method);
            Consumer<TestStage> consumer = stage -> testItem.addMethod(stage, method);
            annotations.stream().map(TestStage::mapper)
                    .forEach(consumer);
        }
    }

}