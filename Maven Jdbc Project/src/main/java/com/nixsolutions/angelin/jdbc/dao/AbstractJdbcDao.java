package com.nixsolutions.angelin.jdbc.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractJdbcDao {
    protected String URL;
    protected String USER;
    protected String PASSWORD;
    protected String DRIVER;
    protected Logger LOG = LoggerFactory.getLogger(this.getClass());

    protected Connection createConnection() {
        Connection connection = null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            LOG.error("Driver class was not found!");
            throw new RuntimeException(e);
        } catch (SQLException e) {
            LOG.error("Could not create connection!");
            throw new RuntimeException(e);
        }
        return connection;
    }

    protected void getPropertiesData() {
        Properties prop = new Properties();
        try (InputStreamReader in = new InputStreamReader(
                Thread.currentThread().getContextClassLoader()
                        .getResourceAsStream("database.properties"))) {
            prop.load(in);
            URL = prop.getProperty("db.url");
            USER = prop.getProperty("db.user");
            PASSWORD = prop.getProperty("db.password");
            DRIVER = prop.getProperty("db.driver");
        } catch (FileNotFoundException e) {
            LOG.error("File with properties data was not found!");
            throw new RuntimeException(e);
        } catch (IOException e) {
            LOG.error("Input data exception!");
            throw new RuntimeException(e);
        }
        LOG.info("Properties data to load successfully!");
    }
}
