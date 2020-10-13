package com.ronzhin.tips.webserver.web.service;

import com.ronzhin.tips.webserver.db.service.DBServiceUser;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserAuthServiceImpl implements UserAuthService {

    private final DBServiceUser userService;

    @Override
    public boolean authenticate(String login, String password) {
        return userService.getUserByLogin(login).map(user -> user.getPassword().equals(password)).orElse(false);
    }
}