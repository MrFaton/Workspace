package com.nixsolutions.pool;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jolbox.bonecp.BoneCP;

public class JdbcConnectionPool {
    private static final Logger logger = LoggerFactory.getLogger(JdbcConnectionPool.class);
    private static final String FILE_NAME = "db.properties";
    private static BoneCP boneCP = null;
    private static Properties properties = null;
    private static JdbcConnectionPool jdbcPool = null;
    
    private JdbcConnectionPool() {
        logger.trace("build connection pool");
        loadProperties();
        if (properties == null || properties.isEmpty()) {
            String message = "properties are null or empty";
            logger.error(message);
            throw new RuntimeException(message);
        }
        
        BoneCPConfig config = new BoneCPConfig();
        config.setJdbcUrl(properties.getProperty("jdbc.url"));
        config.setUser(properties.getProperty("jdbc.user"));
        config.setPassword("jdbc.password");
        config.setPartitionCount(2);
        config.setMinConnectionsPerPartition(1);
        config.setMaxConnectionsPerPartition(4);
        config.setAcquireIncrement(2);
        config.setAcquireRetryAttempts(5);
        config.setAcquireRetryDelayInMs(300);
        config.setDefaultAutoCommit(true);
        config.setPoolAvailabilityThreshold(5);
        config.setDetectUnclosedStatements(true);
        config.setCloseOpenStatements(true);
        config.setConnectionTestStatement("SELECT 1;");
        config.setIdleConnectionTestPeriodInMinutes(2);
        config.setIdleMaxAgeInMinutes(4);
    }
    
    public static synchronized JdbcConnectionPool init() {
        if (jdbcPool == null) {
            jdbcPool = new JdbcConnectionPool();
        }
        return jdbcPool;
    }
    
    public static Connection getConnection() throws SQLException {
        return boneCP.getConnection();
    }
    
    private static void loadProperties() {
        logger.trace("load file with DB properties");
        properties = new Properties();
        try(InputStream in = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream(FILE_NAME)) {
            properties.load(in);
        } catch (IOException e) {
             logger.error("Exception while loadin DB properties file");
             throw new RuntimeException(e);
        }
    }
}
