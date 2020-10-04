package com.ronzhin.tips.jdbc.jdbc.mapper;

import com.ronzhin.tips.jdbc.core.dao.UserDaoException;
import com.ronzhin.tips.jdbc.core.sessionmanager.SessionManager;
import com.ronzhin.tips.jdbc.jdbc.executor.DBExecutor;
import com.ronzhin.tips.jdbc.reflection.ReflectionUtil;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Slf4j
public class JdbcRepositoryImpl<T> implements JdbcRepository<T> {

    private final EntitySQLMetaData sqlMetaData;
    private final SessionManager sessionManager;
    private final DBExecutor<T> dbExecutor;
    private final EntityClassMetaData<T> metaData;

    public JdbcRepositoryImpl(EntitySQLMetaData sqlMetaData, SessionManager sessionManager, DBExecutor<T> dbExecutor, EntityClassMetaData<T> metaData) {
        this.sqlMetaData = sqlMetaData;
        this.sessionManager = sessionManager;
        this.dbExecutor = dbExecutor;
        this.metaData = metaData;
    }

    @Override
    public void insert(T object) {
        final String insertSql = sqlMetaData.getInsertSql();
        List<Object> values = ReflectionUtil.getValues(metaData.getFieldsWithoutId(), object);
        try {
            long id = dbExecutor.executeInsert(sessionManager.getCurrentSession().getConnection(), insertSql, values);
            updateId(metaData.getIdField(), object, id);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new UserDaoException(e);
        }
    }

    private void updateId(Field idField, T object, long id) {
        idField.setAccessible(true);
        try {
            idField.set(object, id);
        } catch (IllegalAccessException e) {
            throw new UserDaoException(new RuntimeException("Cannot modify id"));
        }
    }


    @Override
    public void update(T objectData) {
        final String updateSql = sqlMetaData.getUpdateSql();
        List<Object> values = ReflectionUtil.getValues(metaData.getFieldsWithoutId(), objectData);
        Object value = ReflectionUtil.getValue(metaData.getIdField(), objectData);
        values.add(value);
        try {
            dbExecutor.executeInsert(sessionManager.getCurrentSession().getConnection(), updateSql, values);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new UserDaoException(e);
        }
    }

    @Override
    public void insertOrUpdate(T objectData) {
        Object idObject = ReflectionUtil.getValue(metaData.getIdField(), objectData);
        boolean isInsert = false;
        if (idObject instanceof Number)
            isInsert = ((Number) idObject).longValue() <= 0;
        if (isInsert) insert(objectData);
        else update(objectData);
    }

    @Override
    public T findById(long id, Class<T> clazz) {
        String selectByIdSql = sqlMetaData.getSelectByIdSql();
        try {
            return dbExecutor.executeSelect(sessionManager.getCurrentSession().getConnection(), selectByIdSql, id, generateMapper()).get();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new UserDaoException(e);
        }
    }

    private Function<ResultSet, T> generateMapper() {
        return rs -> {
            try {
                if (!rs.next()) return null;
                List<Object> params = new ArrayList<>();
                for (Field field : metaData.getAllFields()) {
                    String name = field.getName();
                    Object value = rs.getObject(name);
                    params.add(value);
                }
                return metaData.getConstructor().newInstance(params.toArray());
            } catch (SQLException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
                e.printStackTrace();
                return null;
            }
        };
    }

}