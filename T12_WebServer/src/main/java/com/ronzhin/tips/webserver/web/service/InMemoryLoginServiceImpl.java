package com.ronzhin.tips.webserver.web.service;

import com.ronzhin.tips.webserver.db.model.User;
import com.ronzhin.tips.webserver.db.service.DBServiceUser;
import lombok.RequiredArgsConstructor;
import org.eclipse.jetty.security.AbstractLoginService;
import org.eclipse.jetty.util.security.Password;

import java.util.Optional;

@RequiredArgsConstructor
public class InMemoryLoginServiceImpl extends AbstractLoginService {

    private final DBServiceUser userService;

    @Override
    protected String[] loadRoleInfo(UserPrincipal userPrincipal) {
        return new String[]{ "user" };
    }

    @Override
    protected UserPrincipal loadUserInfo(String login) {
        System.out.println(String.format("InMemoryLoginService#loadUserInfo(%s)", login));
        Optional<User> dbUser = userService.getUserByLogin(login);
        return dbUser.map(u -> new UserPrincipal(u.getLogin(), new Password(u.getPassword()))).orElse(null);
    }
}