package com.ronzhin.tips.jdbc.jdbc.executor;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Slf4j
public class DBExecutorImpl<T> implements DBExecutor<T> {

    @Override
    public long executeInsert(Connection connection, String sql, List<Object> params) throws SQLException {
        try (final var pst = connection.prepareStatement(sql)) {
            for (int i = 0; i < params.size(); i++) {
                pst.setObject(i + 1, params.get(i));
            }
            final int rows = pst.executeUpdate();
            log.info("Updated rows: {}", rows);
            return rows;
        }
    }

    @Override
    public Optional<T> executeSelect(Connection connection, String sql, long id, Function<ResultSet, T> rsHandler) throws SQLException {
        try (final var pst = connection.prepareStatement(sql)) {
            pst.setLong(1, id);
            try (final ResultSet resultSet = pst.executeQuery()) {
                return Optional.ofNullable(rsHandler.apply(resultSet));
            }
        }
    }

}