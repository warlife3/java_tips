package com.ronzhin.tips.jdbc;

import com.ronzhin.tips.jdbc.core.model.User;
import com.ronzhin.tips.jdbc.jdbc.executor.DBExecutor;
import com.ronzhin.tips.jdbc.jdbc.executor.DBExecutorImpl;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Optional;

@Slf4j
public class ExecutorDemo {
    private static final String URL = "jdbc:h2:mem:";

    public static void main(String[] args) throws SQLException {
        try (Connection connection = getConnection()) {
            createTable(connection);

            DBExecutor<User> executor = new DBExecutorImpl<>();
            long userId = executor.executeInsert(connection, "insert into user(name) values (?)",
                    Collections.singletonList("testUserName"));
            log.info("created user:{}", userId);
            connection.commit();

            Optional<User> user = executor.executeSelect(connection, "select id, name from user where id  = ?",
                    userId, rs -> {
                        try {
                            if (rs.next()) {
                                return new User(rs.getLong("id"), rs.getString("name"));
                            }
                        } catch (SQLException e) {
                            log.error(e.getMessage(), e);
                        }
                        return null;
                    });
            log.info("user:{}", user);
        }
    }

    private static Connection getConnection() throws SQLException {
        var connection = DriverManager.getConnection(URL);
        connection.setAutoCommit(false);
        return connection;
    }

    private static void createTable(Connection connection) throws SQLException {
        try (PreparedStatement pst = connection.prepareStatement("create table user(id long auto_increment, name varchar(50))")) {
            pst.executeUpdate();
        }
    }
}
