package com.ronzhin.tips.jdbc.jdbc.mapper;

import com.google.common.reflect.TypeToken;
import com.ronzhin.tips.jdbc.annotation.Id;
import com.ronzhin.tips.jdbc.reflection.ReflectionUtil;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("UnstableApiUsage")
public class EntityClassMetaDataImpl<T> implements EntityClassMetaData<T> {

    private final Class<T> clazz;
    private String name;
    private Constructor<T> constructor;
    private Field idField;
    private List<Field> allFields;
    private List<Field> fieldsWithOutId;

    public EntityClassMetaDataImpl(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public String getName() {
        if (name == null) {
            name = ReflectionUtil.getName(clazz).toLowerCase();
        }
        return name;
    }

    @Override
    public Constructor<T> getConstructor() {
        if (constructor == null)
            constructor = ReflectionUtil.getConstructor(clazz);
        return constructor;
    }

    @Override
    public Field getIdField() {
        if (idField == null)
            idField = getAllFields().stream().filter(field -> field.isAnnotationPresent(Id.class)).findAny().get();
        return idField;
    }

    @Override
    public List<Field> getAllFields() {
        if (allFields == null)
            allFields = ReflectionUtil.getAllFields(clazz);
        return allFields;
    }

    @Override
    public List<Field> getFieldsWithoutId() {
        if (fieldsWithOutId == null)
            fieldsWithOutId = getAllFields().stream().filter(field -> field != getIdField()).collect(Collectors.toList());
        return fieldsWithOutId;
    }

    private abstract static class TypeHolder<T> {
        TypeToken<T> type = new TypeToken<T>(getClass()) {};
    }
}