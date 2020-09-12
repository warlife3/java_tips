package com.ronzhin.annotation.core;

import com.ronzhin.annotation.annotation.After;
import com.ronzhin.annotation.annotation.Before;
import com.ronzhin.annotation.annotation.Test;

import java.lang.annotation.Annotation;

public enum TestStage {

    BEFORE, AFTER, TEST;

    public static TestStage mapper(Annotation annotation) {
        TestStage testStage = null;
        if (annotation.annotationType() == Test.class)
            testStage = TestStage.TEST;
        else if (annotation.annotationType() == Before.class)
            testStage = TestStage.BEFORE;
        else if (annotation.annotationType() == After.class)
            testStage = TestStage.AFTER;
        return testStage;
    }

}