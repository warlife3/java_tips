package com.ronzhin.tips.annotations.munit.reflection;

import lombok.SneakyThrows;

import java.lang.reflect.Method;

public class ReflectedObject {

    private final Class<?> clazz;

    public ReflectedObject(Class<?> clazz) {
        this.clazz = clazz;
    }

    @SneakyThrows
    public Object generateInstance(Object... args) {
        var classes = ReflectionUtil.toClasses(args);
        return clazz.getConstructor(classes).newInstance(args);
    }

    public Method[] getMethods() {
        return clazz.getDeclaredMethods();
    }

}