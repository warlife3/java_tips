package com.ronzhin.tips.jdbc.jdbc.mapper;

import com.ronzhin.tips.jdbc.UserForTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class EntityClassMetaDataImplTest {

    private EntityClassMetaData<?> classMetaData;

    @BeforeEach
    public void setUp() {
        classMetaData = new EntityClassMetaDataImpl<>(UserForTest.class);
    }

    @Test
    public void checkSimpleNameCurrentAndInLowerCase() {
        String name = classMetaData.getName();
        String expectedName = "UserForTest".toLowerCase();
        assertThat(name).isEqualTo(expectedName);
    }

    @Test
    public void checkIdField() {
        String name = classMetaData.getIdField().getName();
        String expectedName = "id";
        assertThat(name).isEqualTo(expectedName);
    }

    @Test
    public void checkAllFields() {
        List<String> names = classMetaData.getAllFields().stream().map(Field::getName).collect(Collectors.toList());
        List<String> expectedNames = List.of("id", "name");
        assertThat(names).containsExactlyElementsOf(expectedNames);
    }

    @Test
    public void checktFieldsWithoutId() {
        List<String> names = classMetaData.getFieldsWithoutId().stream().map(Field::getName).collect(Collectors.toList());
        List<String> expectedNames = List.of("name");
        assertThat(names).containsExactlyElementsOf(expectedNames);
    }

}