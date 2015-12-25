package com.nixsolutions.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.nixsolutions.pool.JdbcConnectionPool;

public abstract class AbstractJdbsDao {
    private JdbcConnectionPool connectionPool;

    public Connection createConnection() {
        if (connectionPool == null) {
            connectionPool = JdbcConnectionPool.getInstance();
        }
        try {
            Connection connection = connectionPool.getConnection();
            connection.setAutoCommit(false);
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
