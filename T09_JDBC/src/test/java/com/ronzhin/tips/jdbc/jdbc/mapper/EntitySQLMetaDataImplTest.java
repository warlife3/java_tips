package com.ronzhin.tips.jdbc.jdbc.mapper;

import com.ronzhin.tips.jdbc.UserForTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EntitySQLMetaDataImplTest {

    private EntitySQLMetaData sqlMetaData;

    @BeforeEach
    public void setUp() {
        EntityClassMetaData<?> classMetaData = new EntityClassMetaDataImpl<>(UserForTest.class);
        sqlMetaData = new EntitySQLMetaDataImpl(classMetaData);
    }

    @Test
    public void checkInsertSql() {
        var insertSql = sqlMetaData.getInsertSql();
        var expectedResult = "INSERT INTO userfortest (name) values(?)";
        assertThat(insertSql).isEqualTo(expectedResult);
    }

    @Test
    public void checkUpdateSql() {
        var insertSql = sqlMetaData.getUpdateSql();
        var expectedResult = "UPDATE userfortest SET name = ? WHERE id = ?";
        assertThat(insertSql).isEqualTo(expectedResult);
    }

    @Test
    public void checkSelectByIdSql() {
        var insertSql = sqlMetaData.getSelectByIdSql();
        var expectedResult = "SELECT * FROM userfortest WHERE id = ?";
        assertThat(insertSql).isEqualTo(expectedResult);
    }

    @Test
    public void checkSelectAllSql() {
        var insertSql = sqlMetaData.getSelectAllSql();
        var expectedResult = "SELECT * FROM userfortest";
        assertThat(insertSql).isEqualTo(expectedResult);
    }

}