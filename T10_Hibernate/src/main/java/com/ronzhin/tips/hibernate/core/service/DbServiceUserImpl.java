package com.ronzhin.tips.hibernate.core.service;


import com.ronzhin.tips.hibernate.core.dao.UserDao;
import com.ronzhin.tips.hibernate.core.model.User;
import com.ronzhin.tips.hibernate.hibernate.sessionmanager.SessionManagerHibernate;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
public class DbServiceUserImpl implements DBServiceUser {

    private final UserDao userDao;

    public DbServiceUserImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public long saveUser(User user) {
        try (SessionManagerHibernate sessionManager = userDao.getSessionManager()) {
            sessionManager.beginSession();
            try {
                var userId = userDao.insertUser(user);
                sessionManager.commitSession();

                log.info("created user: {}", userId);
                return userId;
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                sessionManager.rollbackSession();
                throw new DbServiceException(e);
            }
        }
    }

    @Override
    public Optional<User> getUser(long id) {
        try (var sessionManager = userDao.getSessionManager()) {
            sessionManager.beginSession();
            try {
                Optional<User> userOptional = userDao.findById(id);

                log.info("user: {}", userOptional.orElse(null));
                return userOptional;
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                sessionManager.rollbackSession();
            }
            return Optional.empty();
        }
    }
}
