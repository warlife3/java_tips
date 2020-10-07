package com.ronzhin.tips.hibernate.core.dao;

import com.ronzhin.tips.hibernate.core.model.User;
import com.ronzhin.tips.hibernate.hibernate.sessionmanager.SessionManagerHibernate;

import java.util.Optional;

public interface UserDao {
    Optional<User> findById(long id);

    long insertUser(User user);

    void updateUser(User user);

    void insertOrUpdate(User user);

    SessionManagerHibernate getSessionManager();
}
