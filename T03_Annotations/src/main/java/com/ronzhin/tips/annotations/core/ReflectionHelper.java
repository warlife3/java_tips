package com.ronzhin.tips.annotations.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class ReflectionHelper {

    public static <T> T instance(Class<T> clazz, Object... args) {
        try {
            Class<?>[] classes = toClasses(args);
            return clazz.getDeclaredConstructor(classes).newInstance(args);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void methodInvoke(Object object, String methodName, Object... parameters) {

        try {
            Method declaredMethod = object.getClass().getDeclaredMethod(methodName, toClasses(parameters));
            declaredMethod.invoke(object, parameters);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private static Class<?>[] toClasses(Object... objects) {
        return Arrays.stream(objects).map(Object::getClass).toArray(Class<?>[]::new);
    }

    public static List<Annotation> getAnnotations(Method method) {
        return List.of(method.getDeclaredAnnotations());
    }

}