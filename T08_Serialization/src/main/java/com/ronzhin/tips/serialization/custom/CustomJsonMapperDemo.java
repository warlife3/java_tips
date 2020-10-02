package com.ronzhin.tips.serialization.custom;

import com.google.gson.Gson;

public class CustomJsonMapperDemo {

    private static final Gson gson = new Gson();
    private static Object object = new User(0, "Samuel", 46);

    public static void main(String[] args) {
        String customJsonUser = new JsonSimpleMapper().toJson(object);
        System.out.println(customJsonUser);
        String gsonJson = gson.toJson(2.);
        System.out.println(gsonJson);
        User userFromCustomJson = gson.fromJson(gsonJson, User.class);
        System.out.println(object.equals(userFromCustomJson));
    }

}