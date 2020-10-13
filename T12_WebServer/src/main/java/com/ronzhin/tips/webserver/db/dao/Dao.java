package com.ronzhin.tips.webserver.db.dao;

import com.ronzhin.tips.webserver.db.hibernate.sessionmanager.SessionManagerHibernate;
import com.ronzhin.tips.webserver.db.model.IDEntity;

import java.util.List;
import java.util.Optional;

public interface Dao<V, T extends IDEntity<V>> {
    Optional<T> findById(V id);

    List<T> findAll();

    V insert(T object);

    void update(T object);

    void insertOrUpdate(T object);

    SessionManagerHibernate getSessionManager();
}