package com.ronzhin.tips.annotations.munit.executor;

public interface Command<T> {
    void execute(T t);
}