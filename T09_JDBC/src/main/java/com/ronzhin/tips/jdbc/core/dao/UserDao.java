package com.ronzhin.tips.jdbc.core.dao;

import com.ronzhin.tips.jdbc.core.model.User;
import com.ronzhin.tips.jdbc.core.sessionmanager.SessionManager;

import java.util.Optional;

public interface UserDao {
    Optional<User> findById(long id);

    long insertUser(User user);

    //void updateUser(User user);
    //void insertOrUpdate(User user);

    SessionManager getSessionManager();
}
