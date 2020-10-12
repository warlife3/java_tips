package com.ronzhin.tips.cache.core.service;

import com.ronzhin.tips.cache.core.model.User;

import java.util.Optional;

public interface DBServiceUser {

    long saveUser(User user);

    Optional<User> getUser(long id);
}