package com.ronzhin.tips.webserver.web.service;

public interface UserAuthService {
    boolean authenticate(String login, String password);
}
