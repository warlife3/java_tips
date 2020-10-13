package com.ronzhin.tips.webserver.db.model;

public interface IDEntity<T> {
    void setId(T t);

    T getId();
}