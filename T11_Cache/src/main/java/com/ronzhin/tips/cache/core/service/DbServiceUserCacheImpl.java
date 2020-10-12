package com.ronzhin.tips.cache.core.service;

import com.ronzhin.tips.cache.core.dao.UserDao;
import com.ronzhin.tips.cache.core.model.User;
import com.ronzhin.tips.cache.custom.Cache;
import com.ronzhin.tips.cache.custom.CacheImpl;
import com.ronzhin.tips.hibernate.hibernate.sessionmanager.SessionManagerHibernate;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
public class DbServiceUserCacheImpl implements DBServiceUser {

    private final UserDao userDao;
    private final Cache<Long, User> userCache = new CacheImpl<>();

    public DbServiceUserCacheImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public long saveUser(User user) {
        try (SessionManagerHibernate sessionManager = userDao.getSessionManager()) {
            sessionManager.beginSession();
            try {
                var userId = userDao.insertUser(user);
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
    public Optional<User> getUser(long id) {
        try (var sessionManager = userDao.getSessionManager()) {
            sessionManager.beginSession();
            try {
                var user = Optional.of(userCache.get(id));
                if (user.isEmpty()) {
                    user = userDao.findById(id);
                    user.ifPresent(usr -> userCache.put(id, usr));
                }
                return user;
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                sessionManager.rollbackSession();
            }
            return Optional.empty();
        }
    }
}
