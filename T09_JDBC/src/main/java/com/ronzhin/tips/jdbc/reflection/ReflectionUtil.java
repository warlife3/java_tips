package com.ronzhin.tips.jdbc.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ReflectionUtil {

    public static String getName(Class<?> clazz) {
        return clazz.getSimpleName();
    }

    public static <T> Constructor<T> getConstructor(Class<T> clazz) {
        return (Constructor<T>) clazz.getDeclaredConstructors()[0];
    }

    public static <T> List<Field> getAllFields(Class<T> clazz) {
        return List.of(clazz.getDeclaredFields());
    }

    public static <T> List<Object> getValues(List<Field> fields, T object) {
        return fields.stream().map(field -> ReflectionUtil.getValue(field, object))
                .filter(Objects::nonNull).collect(Collectors.toList());
    }

    public static <T> Object getValue(Field field, T object) {
        field.setAccessible(true);
        try {
            return field.get(object);
        } catch (IllegalAccessException e) {
            return null;
        }
    }
}