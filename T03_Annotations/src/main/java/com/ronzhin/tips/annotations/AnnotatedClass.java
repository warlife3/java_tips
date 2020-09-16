package com.ronzhin.tips.annotations;

import com.ronzhin.tips.annotations.annotation.After;
import com.ronzhin.tips.annotations.annotation.Before;
import com.ronzhin.tips.annotations.annotation.Test;

public class AnnotatedClass {

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