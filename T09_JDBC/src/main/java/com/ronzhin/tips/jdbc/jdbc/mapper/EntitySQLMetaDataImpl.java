package com.ronzhin.tips.jdbc.jdbc.mapper;

import java.lang.reflect.Field;
import java.util.stream.Collectors;

public class EntitySQLMetaDataImpl implements EntitySQLMetaData {

    private final EntityClassMetaData<?> entityClassMetaData;
    private String selectAll;
    private String selectById;
    private String insert;
    private String update;

    public EntitySQLMetaDataImpl(EntityClassMetaData<?> entityClassMetaData) {
        this.entityClassMetaData = entityClassMetaData;
    }

    @Override
    public String getSelectAllSql() {
        if (selectAll == null) {
            String tableName = entityClassMetaData.getName();
            selectAll = String.format("SELECT * FROM %s", tableName);
        }
        return selectAll;
    }

    @Override
    public String getSelectByIdSql() {
        if (selectById == null) {
            String idName = entityClassMetaData.getIdField().getName();
            String tableName = entityClassMetaData.getName();
            selectById = String.format("SELECT * FROM %s WHERE %s = ?", tableName, idName);
        }
        return selectById;
    }

    @Override
    public String getInsertSql() {
        if (insert == null) {
            String columnNames = entityClassMetaData.getFieldsWithoutId().stream()
                    .map(Field::getName).collect(Collectors.joining(", "));
            String tableName = entityClassMetaData.getName();
            String values = columnNames.replaceAll("\\w+", "?");
            insert = String.format("INSERT INTO %s (%s) values(%s)", tableName, columnNames, values);
        }
        return insert;
    }

    @Override
    public String getUpdateSql() {
        if (update == null) {
            String setValues = entityClassMetaData.getFieldsWithoutId().stream()
                    .map(field -> String.join("", field.getName(), " = ?"))
                    .collect(Collectors.joining(", "));
            String tableName = entityClassMetaData.getName();
            String idName = entityClassMetaData.getIdField().getName();
            update = String.format("UPDATE %s SET %s WHERE %s = ?", tableName, setValues, idName);
        }
        return update;
    }
}