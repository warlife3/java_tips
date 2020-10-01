package com.ronzhin.tips.io.json.jackson;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties({ "transientProperty" })
public class User {

    private int age;

    @JsonProperty("user_name")
    private String name;

    @JsonIgnore
    private String transientProperty = "lostData";

    @JsonCreator
    public User(@JsonProperty("age") int age, @JsonProperty("user_name") String name) {
        System.out.println("JsonCreator makes object...");
        this.age = age;
        this.name = name;
    }

    User(int age, String name, String transientProperty) {
        this.age = age;
        this.name = name;
        this.transientProperty = transientProperty;
    }

    @Override
    public String toString() {
        return "User{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", transientProperty='" + transientProperty + '\'' +
                '}';
    }
}
