package com.ronzhin.tips.annotations.core;

import com.ronzhin.tips.annotations.munit.reflection.ReflectedObject;
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

}