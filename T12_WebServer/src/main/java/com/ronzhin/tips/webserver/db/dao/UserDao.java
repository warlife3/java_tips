package com.ronzhin.tips.webserver.db.dao;

import com.ronzhin.tips.webserver.db.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao extends Dao<Long, User> {
    Optional<User> findByLogin(String login);

    List<User> findAll();

    List<User> findByRole(String role);
}