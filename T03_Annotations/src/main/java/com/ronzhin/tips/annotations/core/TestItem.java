package com.ronzhin.tips.annotations.core;

import java.lang.reflect.Method;
import java.util.*;

public class TestItem {

    private Map<TestStage, List<Method>> stageMap;

    private final ReflectedObject reflectedObject;

    public TestItem(ReflectedObject object) {
        this.reflectedObject = object;
        this.stageMap = new HashMap<>();
        for (TestStage testStage : TestStage.values()) {
            this.stageMap.put(testStage, new ArrayList<>());
        }
    }

    public ReflectedObject getReflectedObject() {
        return reflectedObject;
    }

    public void addMethod(TestStage testStage, Method method) {
        this.stageMap.computeIfPresent(testStage, (key, value) -> {value.add(method); return value; });
    }

    public List<Method> getStageMethods(TestStage testStage) {
        return stageMap.get(testStage);
    }

    public void callMethod(Object instance, Method method, Object... parameters) {
        reflectedObject.invokeMethod(instance, method.getName(), parameters);
    }
}