package com.ronzhin.tips.jdbc.jdbc.mapper;

public interface JdbcRepository<T> {
    void insert(T objectData);

    void update(T objectData);

    void insertOrUpdate(T objectData);

    T findById(long id, Class<T> clazz);
}