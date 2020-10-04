package com.ronzhin.tips.jdbc;

import com.ronzhin.tips.jdbc.h2.DataSourceH2;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.sql.Connection;

@Slf4j
public class H2Demo {
    private static final String URL = "jdbc:h2:mem:";

    @SneakyThrows
    public static void main(String[] args) {
        DataSource dataSource = new DataSourceH2(URL);
        try (var connection = dataSource.getConnection()) {
            createTable(connection);
            saveUsers(connection, 1, "smoke test");
            saveUsers(connection, 2, "new smoke test");
            selectUser(connection);
        }
    }

    @SneakyThrows
    private static void selectUser(Connection connection) {
        try (var pst = connection.createStatement()) {
            try (var resultSet = pst.executeQuery("select * from users")) {
                while (resultSet.next()) {
                    final int userId = resultSet.getInt(1);
                    final String userName = resultSet.getString("name");
                    log.info("User id: {}, name: {}", userId, userName);
                }
            }
        }
    }

    @SneakyThrows
    private static void saveUsers(Connection connection, int id, String name) {
        try (var pst = connection.prepareStatement("insert into users(id, name) values(?, ?)")) {
            pst.setInt(1, id);
            pst.setString(2, name);
            int rowCount = pst.executeUpdate();
            log.info("Inserted rows: {}", rowCount);
        }
    }

    @SneakyThrows
    private static void createTable(Connection connection) {
        try (var st = connection.createStatement()) {
            st.execute("create table users(id int, name varchar(55))");
        }
    }
}
