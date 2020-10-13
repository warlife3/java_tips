package com.ronzhin.tips.webserver.db.service;

import com.ronzhin.tips.webserver.cache.Cache;
import com.ronzhin.tips.webserver.cache.CacheImpl;
import com.ronzhin.tips.webserver.db.dao.UserDao;
import com.ronzhin.tips.webserver.db.hibernate.sessionmanager.SessionManagerHibernate;
import com.ronzhin.tips.webserver.db.model.User;
import com.ronzhin.tips.webserver.web.view.UserView;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@Slf4j
public class DbServiceUserCacheImpl implements DBServiceUser {

    private final UserDao userDao;
    private final Cache<Long, User> userCache = new CacheImpl<>();

    public DbServiceUserCacheImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Optional<UserView> getRandomUser() {
        try (SessionManagerHibernate sessionManager = userDao.getSessionManager()) {
            sessionManager.beginSession();
            try {
                final User user = userDao.findAll().get(0);
                return Optional.of(new UserView(user));
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                sessionManager.rollbackSession();
                throw new DbServiceException(e);
            }
        }
    }

    @Override
    public long saveUser(User user) {
        try (SessionManagerHibernate sessionManager = userDao.getSessionManager()) {
            sessionManager.beginSession();
            try {
                var userId = userDao.insert(user);
                sessionManager.commitSession();
                userCache.put(userId, user);
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
    public Optional<UserView> getUser(long id) {
        try (var sessionManager = userDao.getSessionManager()) {
            sessionManager.beginSession();
            try {

                final Optional<User> optionalUser = Optional.of(userCache.get(id)).or(() -> {
                    Optional<User> user = userDao.findById(id);
                    user.ifPresent(usr -> userCache.put(id, usr));
                    return user;
                });
                return Optional.of(new UserView(optionalUser.get()));
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                sessionManager.rollbackSession();
            }
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> getUserByLogin(String login) {
        try (var sessionManager = userDao.getSessionManager()) {
            sessionManager.beginSession();
            try {
                return userDao.findByLogin(login);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                sessionManager.rollbackSession();
            }
            return Optional.empty();
        }
    }

    @Override
    public List<User> getUsersByRole(String role) {
        try (var sessionManager = userDao.getSessionManager()) {
            sessionManager.beginSession();
            try {
                return userDao.findByRole(role);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                sessionManager.rollbackSession();
            }
            return List.of();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (var sessionManager = userDao.getSessionManager()) {
            sessionManager.beginSession();
            try {
                return userDao.findAll();
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                sessionManager.rollbackSession();
            }
            return List.of();
        }
    }
}