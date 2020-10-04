package com.ronzhin.tips.jdbc.jdbc.dao;


import com.ronzhin.tips.jdbc.core.dao.UserDao;
import com.ronzhin.tips.jdbc.core.dao.UserDaoException;
import com.ronzhin.tips.jdbc.core.model.User;
import com.ronzhin.tips.jdbc.core.sessionmanager.SessionManager;
import com.ronzhin.tips.jdbc.jdbc.executor.DBExecutor;
import com.ronzhin.tips.jdbc.jdbc.mapper.*;
import com.ronzhin.tips.jdbc.jdbc.sessionmanager.SessionManagerJdbc;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.util.Optional;

@Slf4j
public class UserDaoJdbc implements UserDao {

    private final SessionManagerJdbc sessionManager;
    private final JdbcRepository<User> mapper;

    public UserDaoJdbc(SessionManagerJdbc sessionManager, DBExecutor<User> dbExecutor) {
        this.sessionManager = sessionManager;
        EntityClassMetaData<User> classMetaData = new EntityClassMetaDataImpl<>(User.class);
        EntitySQLMetaDataImpl sqlMetaData = new EntitySQLMetaDataImpl(classMetaData);
        this.mapper = new JdbcRepositoryImpl<>(sqlMetaData, sessionManager, dbExecutor, classMetaData);
    }

    @Override
    public Optional<User> findById(long id) {
        try {
            return Optional.ofNullable(mapper.findById(id, User.class));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return Optional.empty();
    }

    @Override
    public long insertUser(User user) {
        try {
            mapper.insert(user);
            return user.getId();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new UserDaoException(e);
        }

    }

    @Override
    public SessionManager getSessionManager() {
        return sessionManager;
    }

    private Connection getConnection() {
        return sessionManager.getCurrentSession().getConnection();
    }
}
