package com.ronzhin.tips.annotations.munit.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class ReflectionUtil {
    public static Class<?>[] toClasses(Object... objects) {
        return Arrays.stream(objects).map(Object::getClass).toArray(Class<?>[]::new);
    }

    public static void invoke(Method method, Object object) {
        try {
            method.setAccessible(true);
            method.invoke(object);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e.getCause().getMessage());
        }
    }

}