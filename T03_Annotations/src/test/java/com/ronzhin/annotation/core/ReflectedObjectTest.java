package com.ronzhin.annotation.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class ReflectedObjectTest {

    private final ReflectedObject reflectedObject;
    private Object instance;

    public ReflectedObjectTest() {
        this.reflectedObject = new ReflectedObject(AnnotationsTest.class);
    }

    @BeforeEach
    public void setUp() {
        instance = reflectedObject.generateInstance();
    }

    @Test
    public void instanceTest() {
        assertThat(instance).isNotNull();
    }

    @Test
    public void numberOfMethodsReadRight() {
        Method[] methods = reflectedObject.getMethods();
        int expectedResult = 5;
        int result = methods.length;
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void readRightMethods() {
        List<String> expectedMethodNames = new ArrayList<>(List.of("setUp", "test01", "test02", "test03", "clean"));
        expectedMethodNames.sort(String::compareTo);
        Method[] methods = reflectedObject.getMethods();
        List<String> methodNames = Arrays.stream(methods).map(Method::getName).sorted().collect(Collectors.toList());
        assertThat(methodNames).containsExactlyElementsOf(expectedMethodNames);
    }

    @Test
    public void readRightAnnotations() {
        Map<String, List<String>> expectedResult = new HashMap<>();
        expectedResult.put("test01", List.of("com.ronzhin.annotation.annotation.Test"));
        expectedResult.put("test02", List.of("com.ronzhin.annotation.annotation.Test"));
        expectedResult.put("test03", List.of("com.ronzhin.annotation.annotation.Test"));
        expectedResult.put("setUp", List.of("com.ronzhin.annotation.annotation.Before"));
        expectedResult.put("clean", List.of("com.ronzhin.annotation.annotation.After"));

        Map<String, List<String>> result = new HashMap<>();
        for (Method method : reflectedObject.getMethods()) {
            List<String> annotationNames = reflectedObject.getAnnotations(method).stream()
                    .map(annotation -> annotation.annotationType().getName())
                    .collect(Collectors.toList());
            result.put(method.getName(), annotationNames);
        }
        assertThat(result).containsExactlyInAnyOrderEntriesOf(expectedResult);
    }

}