package com.nixsolutions.pool;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

public class JdbcConnectionPool {
    private static final Logger logger = LoggerFactory
            .getLogger(JdbcConnectionPool.class);
    private static final String FILE_NAME = "db.properties";
    private static JdbcConnectionPool jdbcPool = null;
    private BoneCP pool = null;
    private Properties properties = null;

    private JdbcConnectionPool() {
        logger.trace("build connection pool");
        loadProperties();
        if (properties == null || properties.isEmpty()) {
            String message = "properties are null or empty";
            logger.error(message);
            throw new RuntimeException(message);
        }

        boolean testMode = Boolean.valueOf(properties.getProperty("test.mode"));
        String jdbcDriver = properties.getProperty("jdbc.driver");
        String jdbcUser = properties.getProperty("jdbc.user");
        String jdbcPassword = properties.getProperty("jdbc.password");
        String jdbcUrl;
        if (testMode) {
            logger.debug("Pool runs in test mode");
            jdbcUrl = properties.getProperty("jdbc.test.url");
        } else {
            logger.debug("Pool runs in regular mode");
            jdbcUrl = properties.getProperty("jdbc.url");
        }

        try {
            Class.forName(jdbcDriver);
        } catch (ClassNotFoundException e) {
            logger.error("Can't find driver " + jdbcDriver, e);
            throw new RuntimeException(e);
        }

        BoneCPConfig config = new BoneCPConfig();
        config.setJdbcUrl(jdbcUrl);
        config.setUser(jdbcUser);
        config.setPassword(jdbcPassword);
        config.setPartitionCount(2);
        config.setMinConnectionsPerPartition(1);
        config.setMaxConnectionsPerPartition(4);
        config.setAcquireIncrement(2);
        config.setAcquireRetryAttempts(5);
        config.setAcquireRetryDelayInMs(300);
        config.setDefaultAutoCommit(false);
        config.setPoolAvailabilityThreshold(5);
        config.setCloseOpenStatements(true);
        config.setConnectionTestStatement("SELECT 1;");
        config.setIdleConnectionTestPeriodInMinutes(2);
        config.setIdleMaxAgeInMinutes(2);

        try {
            pool = new BoneCP(config);
        } catch (SQLException e) {
            logger.warn("can't init BoneCP Connection Pool", e);
            throw new RuntimeException(e);
        }
    }

    public static synchronized JdbcConnectionPool getInstance() {
        if (jdbcPool == null) {
            jdbcPool = new JdbcConnectionPool();
        }
        return jdbcPool;
    }

    public Connection getConnection() throws SQLException {
        return pool.getConnection();
    }

    private void loadProperties() {
        logger.trace("load file with DB properties");
        properties = new Properties();
        try {
            properties.load(Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream(FILE_NAME));
        } catch (IOException e) {
            logger.error("Exception while loadin DB properties file");
            throw new RuntimeException(e);
        }
    }
}
