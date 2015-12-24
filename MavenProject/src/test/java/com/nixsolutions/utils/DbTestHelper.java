package com.nixsolutions.utils;

import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;

import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.h2.H2DataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.h2.tools.RunScript;

import com.nixsolutions.pool.JdbcConnectionPool;

public class DbTestHelper {
    protected static final String SCHEMA = "TRAINEESHIP_DB";
    protected static final String SQL_CREATE_CHEMA = "src/test/resources/sql/Create scheme.sql";
    protected static final String SQL_CREATE_TABLE_ROLE = "src/test/resources/sql/Create table ROLE.sql";
    protected static final String SQL_CREATE_TABLE_USER = "src/test/resources/sql/Create table USER.sql";
    private final JdbcConnectionPool connectionPool;

    public DbTestHelper() {
        connectionPool = JdbcConnectionPool.getInstance();
    }

    public void initDb() throws Exception {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            connection.setAutoCommit(false);
            RunScript.execute(connection,
                    new FileReader(new File(SQL_CREATE_CHEMA)));
            RunScript.execute(connection,
                    new FileReader(new File(SQL_CREATE_TABLE_ROLE)));
            RunScript.execute(connection,
                    new FileReader(new File(SQL_CREATE_TABLE_USER)));
            connection.commit();
        } catch (SQLException sqlEx) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    throw e1;
                }
            }
            throw sqlEx;
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void fill(String dataSetPath) throws Exception {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            connection.setAutoCommit(false);
            IDataSet dataSet = new FlatXmlDataSetBuilder()
                    .build(DbTestHelper.class.getClassLoader()
                            .getResourceAsStream(dataSetPath));
            DatabaseOperation.CLEAN_INSERT.execute(getIDBConnection(connection),
                    dataSet);
            connection.commit();
        } catch (SQLException sqlEx) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    throw e1;
                }
            }
            throw sqlEx;
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public ITable getTableFromSchema(String tableName) throws Exception {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            connection.setAutoCommit(false);
            IDataSet dataSet = getIDBConnection(connection).createDataSet();
            connection.commit();
            return dataSet.getTable(tableName);
        } catch (SQLException sqlEx) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    throw e1;
                }
            }
            throw sqlEx;
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public ITable getTableFromFile(String tableName, String xmlFilePath)
            throws Exception {
        FlatXmlDataSet dataSet = new FlatXmlDataSetBuilder()
                .build(DbTestHelper.class.getClassLoader()
                        .getResourceAsStream(xmlFilePath));
        return dataSet.getTable(tableName);
    }

    private IDatabaseConnection getIDBConnection(Connection connection)
            throws Exception {
        DatabaseConnection databaseConnection = new DatabaseConnection(
                connection, SCHEMA);
        DatabaseConfig databaseConfig = databaseConnection.getConfig();
        databaseConfig.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY,
                new H2DataTypeFactory());
        return databaseConnection;
    }
}