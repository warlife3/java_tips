package com.ronzhin.tips.jdbc.reflection;

import com.ronzhin.tips.jdbc.UserForTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class ReflectionUtilTest {

    private Class<?> clazz;

    @BeforeEach
    public void SetUp() {
        clazz = UserForTest.class;
    }

    @Test
    public void returnSimpleName() {
        String result = ReflectionUtil.getName(clazz);
        String expectedResult = "UserForTest";
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void getFields() {
        List<Field> allFields = ReflectionUtil.getAllFields(clazz);
        List<String> fieldNames = allFields.stream().map(Field::getName).collect(Collectors.toList());
        List<String> expectedNames = List.of("id", "name");
        assertThat(fieldNames).containsExactlyElementsOf(expectedNames);
    }

}