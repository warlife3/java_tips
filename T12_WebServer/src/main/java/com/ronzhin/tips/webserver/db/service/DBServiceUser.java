package com.ronzhin.tips.webserver.db.service;

import com.ronzhin.tips.webserver.db.model.User;
import com.ronzhin.tips.webserver.web.view.UserView;

import java.util.List;
import java.util.Optional;

public interface DBServiceUser {

    Optional<UserView> getRandomUser();

    long saveUser(User user);

    Optional<UserView> getUser(long id);

    Optional<User> getUserByLogin(String login);

    List<User> getAllUsers();

    List<User> getUsersByRole(String role);

}