package com.ronzhin.tips.jdbc.core.service;

import com.ronzhin.tips.jdbc.core.model.User;

import java.util.Optional;

public interface DBServiceUser {

    long saveUser(User user);

    Optional<User> getUser(long id);
}