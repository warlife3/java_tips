package com.ronzhin.tips.jdbc.core.sessionmanager;

import java.sql.Connection;

public interface DatabaseSession {
    Connection getConnection();
}
