package com.ronzhin.tips.jdbc.jdbc.dao;


import com.ronzhin.tips.jdbc.core.dao.UserDao;
import com.ronzhin.tips.jdbc.core.dao.UserDaoException;
import com.ronzhin.tips.jdbc.core.model.User;
import com.ronzhin.tips.jdbc.core.sessionmanager.SessionManager;
import com.ronzhin.tips.jdbc.jdbc.executor.DBExecutor;
import com.ronzhin.tips.jdbc.jdbc.sessionmanager.SessionManagerJdbc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Optional;

public class UserDaoPlainJdbc implements UserDao {
    private static final Logger logger = LoggerFactory.getLogger(UserDaoPlainJdbc.class);

    private final SessionManagerJdbc sessionManager;
    private final DBExecutor<User> dbExecutor;

    public UserDaoPlainJdbc(SessionManagerJdbc sessionManager, DBExecutor<User> dbExecutor) {
        this.sessionManager = sessionManager;
        this.dbExecutor = dbExecutor;
    }

    @Override
    public Optional<User> findById(long id) {
        try {
            return dbExecutor.executeSelect(getConnection(), "select id, name from user where id  = ?",
                    id, rs -> {
                        try {
                            if (rs.next()) {
                                return new User(rs.getLong("id"), rs.getString("name"));
                            }
                        } catch (SQLException e) {
                            logger.error(e.getMessage(), e);
                        }
                        return null;
                    });
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return Optional.empty();
    }

    @Override
    public long insertUser(User user) {
        try {
            return dbExecutor.executeInsert(getConnection(), "insert into user(name) values (?)",
                    Collections.singletonList(user.getName()));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
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
