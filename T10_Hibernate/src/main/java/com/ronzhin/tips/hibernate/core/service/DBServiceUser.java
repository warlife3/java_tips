package com.ronzhin.tips.hibernate.core.service;

import com.ronzhin.tips.hibernate.core.model.User;

import java.util.Optional;

public interface DBServiceUser {

    long saveUser(User user);

    Optional<User> getUser(long id);
}