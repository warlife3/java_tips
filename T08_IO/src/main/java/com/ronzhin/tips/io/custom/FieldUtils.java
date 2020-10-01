package com.ronzhin.tips.io.custom;

import java.lang.reflect.Field;

public class FieldUtils {

    public static Object get(Field field, Object object) {
        if (!field.canAccess(object))
            field.setAccessible(true);
        try {
            return field.get(object);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

}