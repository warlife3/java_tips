package com.ronzhin.tips.cache.hibernate.dao;

import com.ronzhin.tips.cache.core.dao.UserDao;
import com.ronzhin.tips.cache.core.model.User;
import com.ronzhin.tips.hibernate.hibernate.sessionmanager.DatabaseSessionHibernate;
import com.ronzhin.tips.hibernate.hibernate.sessionmanager.SessionManagerHibernate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
@AllArgsConstructor
public class UserHibernateDao implements UserDao {

    private final SessionManagerHibernate sessionManager;

    @Override
    public Optional<User> findById(long id) {
        DatabaseSessionHibernate currentSession = sessionManager.getCurrentSession();
        try {
            return Optional.ofNullable(currentSession.getHibernateSession().find(User.class, id));
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return Optional.empty();
    }

    @Override
    public long insertUser(User user) {
        DatabaseSessionHibernate currentSession = sessionManager.getCurrentSession();
        try {
            currentSession.getHibernateSession().save(user);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return user.getId();
    }

    @Override
    public void updateUser(User user) {
        insertUser(user);
    }

    @Override
    public void insertOrUpdate(User user) {
        insertUser(user);
    }

    @Override
    public SessionManagerHibernate getSessionManager() {
        return sessionManager;
    }

}