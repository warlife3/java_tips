package com.ronzhin.tips.serialization.custom;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Function;

public class JsonSimpleMapper {

    public String toJson(Object object) {
        Field[] fields = object.getClass().getDeclaredFields();

        Function<Field, String> mapper = field -> String.format("\"%s\":%s", field.getName(), FieldUtils.get(field, object));

        String json = Arrays.stream(fields).map(mapper)
                .filter(Objects::nonNull)
                .reduce((s, s2) -> String.join(",", s, s2)).get();

        return String.format("{%s}", json);
    }

}