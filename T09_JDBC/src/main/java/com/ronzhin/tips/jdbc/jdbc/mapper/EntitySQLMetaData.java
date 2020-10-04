package com.ronzhin.tips.jdbc.jdbc.mapper;

public interface EntitySQLMetaData {
    String getSelectAllSql();

    String getSelectByIdSql();

    String getInsertSql();

    String getUpdateSql();
}
