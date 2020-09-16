package com.ronzhin.tips.dependency;

import com.google.common.collect.Lists;

import java.util.List;

public class App {

    public static void main(String[] args) {
        var res = Lists.reverse(List.of(args));
        System.out.println(res);
    }

}