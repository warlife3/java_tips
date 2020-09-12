package com.ronzhin.annotation;

import com.ronzhin.annotation.annotation.After;
import com.ronzhin.annotation.annotation.Before;
import com.ronzhin.annotation.annotation.Test;

public class AnnotationsTest {

    @Before
    public void setUp() {
        System.out.println("SetUp is done");
    }

    @Test(name = "Super test number 01")
    public void test01() {
        System.out.println("test01 is done");
    }

    @Test(name = "Super test number 02")
    public void test02() {
        System.out.println("test02 is done");
    }

    @Test(name = "Super test number 03")
    public void test03() {
        System.out.println("test03 is done");
    }

    @After
    public void clean() {
        System.out.println("clean is done");
    }

}