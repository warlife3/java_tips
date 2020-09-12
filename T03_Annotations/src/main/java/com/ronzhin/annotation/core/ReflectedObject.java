package com.ronzhin.annotation.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;

public class ReflectedObject {

    private final Class<?> clazz;

    public ReflectedObject(Class<?> clazz) {
        this.clazz = clazz;
    }

    public Object generateInstance(Object... parameters) {
        return ReflectionHelper.instance(clazz, parameters);
    }

    public void invokeMethod(Object instance, String methodName, Object... parameters) {
        Objects.requireNonNull(instance);
        ReflectionHelper.methodInvoke(instance, methodName, parameters);
    }

    public Method[] getMethods() {
        return clazz.getDeclaredMethods();
    }

    public List<Annotation> getAnnotations(Method method) {
        return ReflectionHelper.getAnnotations(method);
    }

}