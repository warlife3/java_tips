package com.ronzhin.tips.jdbc;

import com.ronzhin.tips.jdbc.core.model.User;
import com.ronzhin.tips.jdbc.core.service.DbServiceUserImpl;
import com.ronzhin.tips.jdbc.h2.DataSourceH2;
import com.ronzhin.tips.jdbc.jdbc.dao.UserDaoJdbc;
import com.ronzhin.tips.jdbc.jdbc.executor.DBExecutor;
import com.ronzhin.tips.jdbc.jdbc.executor.DBExecutorImpl;
import com.ronzhin.tips.jdbc.jdbc.sessionmanager.SessionManagerJdbc;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Optional;

@Slf4j
public class DbServiceDemo {
    private static final String URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";

    public static void main(String[] args) throws Exception {
        var dataSource = new DataSourceH2(URL);
        var demo = new DbServiceDemo();

        demo.createTable(dataSource);

        var sessionManager = new SessionManagerJdbc(dataSource);
        DBExecutor<User> dbExecutor = new DBExecutorImpl<>();
        // you can use UserDaoJdbc or UserDaoPlainJdbc
        // UserDaoJdbc - custom class with automatic mapping, SQL generation, class analyzer and DBExecutor
        // UserDaoPlainJdbc - old JDBC class with custom DBExecutor (SQL and mapper writes by hands)
        var userDao = new UserDaoJdbc(sessionManager, dbExecutor);

        var dbServiceUser = new DbServiceUserImpl(userDao);
        var id = dbServiceUser.saveUser(new User(0, "dbServiceUser"));
        Optional<User> user = dbServiceUser.getUser(id);

        user.ifPresentOrElse(
                crUser -> log.info("created user, name:{}", crUser.getName()),
                () -> log.info("user was not created")
        );

    }

    private void createTable(DataSource dataSource) throws SQLException {
        try (var connection = dataSource.getConnection();
             var pst = connection.prepareStatement("create table user(id long auto_increment, name varchar(50))")) {
            pst.executeUpdate();
        }
        log.info("table created");
    }
}