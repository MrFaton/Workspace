package com.nixsolutions.angelin.jdbc;

import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.util.Properties;

import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.ext.h2.H2DataTypeFactory;
import org.h2.tools.RunScript;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nixsolutions.angelin.jdbc.dao.JdbcRoleDao;
import com.nixsolutions.angelin.jdbc.dao.JdbcUserDao;
import com.nixsolutions.angelin.jdbc.interfaces.RoleDao;
import com.nixsolutions.angelin.jdbc.interfaces.UserDao;
import com.nixsolutions.angelin.jdbc.pojo.Role;
import com.nixsolutions.angelin.jdbc.pojo.User;

public class DbUnitConfig {
    protected String URL;
    protected String USER;
    protected String PASSWORD;
    protected String DRIVER;
    protected Logger LOG = LoggerFactory.getLogger(this.getClass());
    protected final String SQL_CREATE_ROLE_TABLE = "src/test/resources/create_role_table.sql";
    protected final String SQL_CREATE_USER_TABLE = "src/test/resources/create_user_table.sql";
    protected final String DATASET_INIT_TABLE = "src/test/resources/dataset_init_table.xml";
    protected final String DATASET_INSERT = "src/test/resources/dataset_insert.xml";
    protected final String DATASET_REMOVE = "src/test/resources/dataset_remove.xml";
    protected final String DATASET_UPDATE = "src/test/resources/dataset_update.xml";
    protected final String SQL_DROP_ROLE_TABLE = "src/test/resources/drop_role_table.sql";
    protected final String SQL_DROP_USER_TABLE = "src/test/resources/drop_user_table.sql";
    protected IDataSet beforeData;
    protected Role[] roles;
    protected User[] users;
    protected UserDao userDao;
    protected RoleDao roleDao;
    protected Connection connection;

    public DbUnitConfig(){
        getPropertiesData();
        initTestData();
    }
    
    protected Connection getDatabaseConnection() throws Exception {
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
        FileReader reader = new FileReader(new File(SQL_CREATE_ROLE_TABLE));
        RunScript.execute(connection, reader);
        reader = new FileReader(new File(SQL_CREATE_USER_TABLE));
        RunScript.execute(connection, reader);
        return connection;
    }

    protected IDatabaseConnection getConnection() throws Exception {
        IDatabaseConnection connection = new DatabaseConnection(
                getDatabaseConnection());
        DatabaseConfig dbConfig = connection.getConfig();
        dbConfig.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY,
                new H2DataTypeFactory());
        return connection;
    }
    
    protected IDataSet getDataSet() throws Exception {
        return beforeData;
    }

    private void initTestData(){
        roles = new Role[4];
        users = new User[4];
        roleDao = new JdbcRoleDao(true);
        userDao = new JdbcUserDao(true);

        roles[0] = new Role();
        roles[1] = new Role();
        roles[2] = new Role();
        roles[3] = new Role();

        roles[0].setId(1);
        roles[0].setName("admin");

        roles[1].setId(2);
        roles[1].setName("user");

        roles[2].setId(3);
        roles[2].setName("test");

        roles[3].setId(2);
        roles[3].setName("user2");

        users[0] = new User();
        users[1] = new User();
        users[2] = new User();
        users[3] = new User();

        users[0].setId(1);
        users[0].setLogin("admin");
        users[0].setPassword("admin");
        users[0].setEmail("admin@nixsolutions.com");
        users[0].setFirstName("Admin");
        users[0].setLastName("Adminovich");
        users[0].setBirthday(Date.valueOf("1980-04-15"));
        users[0].setRole(roles[0]);

        users[1].setId(2);
        users[1].setLogin("user");
        users[1].setPassword("user");
        users[1].setEmail("user@nixsolutions.com");
        users[1].setFirstName("User");
        users[1].setLastName("Userovich");
        users[1].setBirthday(Date.valueOf("1988-10-05"));
        users[1].setRole(roles[1]);

        users[2].setId(3);
        users[2].setLogin("test");
        users[2].setPassword("test");
        users[2].setEmail("test@nixsolutions.com");
        users[2].setFirstName("Test");
        users[2].setLastName("Tester");
        users[2].setBirthday(Date.valueOf("1985-11-15"));
        users[2].setRole(roles[1]);

        users[3].setId(2);
        users[3].setLogin("newUser");
        users[3].setPassword("newUser");
        users[3].setEmail("new.user@nixsolutions.com");
        users[3].setFirstName("NewUser");
        users[3].setLastName("NewUserovich");
        users[3].setBirthday(Date.valueOf("1998-10-05"));
        users[3].setRole(roles[1]);
    }
    
    private void getPropertiesData() {
        Properties prop = new Properties();
        try (InputStreamReader in = new InputStreamReader(
                Thread.currentThread().getContextClassLoader()
                        .getResourceAsStream("database.properties"))) {
            prop.load(in);
            URL = prop.getProperty("db.test.url");
            USER = prop.getProperty("db.user");
            PASSWORD = prop.getProperty("db.password");
            DRIVER = prop.getProperty("db.driver");
            Class.forName(DRIVER);
        } catch (Exception e) {
            LOG.error("File with properties data was not found!");
            throw new RuntimeException(e);
        }
        LOG.info("Properties data to load successfully!");
    }
}
