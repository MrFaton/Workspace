package com.nixsolutions.dao.impl;

import java.util.Date;
import java.util.GregorianCalendar;

import org.dbunit.dataset.ITable;
import static org.dbunit.Assertion.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.nixsolutions.dao.UserDao;
import com.nixsolutions.entity.Role;
import com.nixsolutions.entity.User;

import utils.DbTestHelper;

public class JdbcRoleDaoTest {
    private static final String DATASET_COMMON = "dataset/user/common.xml";
    private static final String TABLE_EMPTY = "dataset/user/empty.xml";
    private static final String TABLE_NAME = "USER";
    private static final String[] IGNORE_COLS = { "USER_ID" };
    private static UserDao userDao;

    @BeforeClass
    public static void generalSetUp() throws Exception {
        dbTestHelper.initDb();
        userDao = new JdbcUserDao();
    }

    @Before
    public void setUp() throws Exception {
        dbTestHelper.fill(DATASET_COMMON);
    }

    @Test
    public void testCreate() throws Exception {
        dbTestHelper.fill(TABLE_EMPTY);

        String afterCreate = "dataset/user/afterCreate.xml";

        User user = new User();

        user.setLogin("login1");
        user.setPassword("pass1");
        user.setEmail("email1");
        user.setFirstName("firstName1");
        user.setLastName("lastName1");

        GregorianCalendar calendar = new GregorianCalendar(2000, 3, 11);
        user.setBirthDay(new Date(calendar.getTimeInMillis()));

        Role role = new Role();
        role.setId(2);
        role.setName("role2");
        user.setRole(role);

        userDao.create(user);

        ITable expected = dbTestHelper.getTableFromFile(TABLE_NAME,
                afterCreate);
        ITable actual = dbTestHelper.getTableFromSchema(TABLE_NAME);

        assertEqualsIgnoreCols(expected, actual, IGNORE_COLS);
    }

    // Update user with id 1 from common.xml
    @Test
    public void testUpdate() throws Exception {
        String afterUpdate = "dataset/user/afterUpdate.xml";

        User user = new User();

        user.setId(1L);
        user.setLogin("login55");
        user.setPassword("pass55");
        user.setEmail("email55");
        user.setFirstName("firstName55");
        user.setLastName("lastName55");

        GregorianCalendar calendar = new GregorianCalendar(1950, 2, 7);
        user.setBirthDay(new Date(calendar.getTimeInMillis()));

        Role role = new Role();
        role.setId(3);
        role.setName("role3");
        user.setRole(role);

        userDao.update(user);

        ITable expected = dbTestHelper.getTableFromFile(TABLE_NAME,
                afterUpdate);
        ITable actual = dbTestHelper.getTableFromSchema(TABLE_NAME);

        assertEqualsIgnoreCols(expected, actual, IGNORE_COLS);

    }

    @Test
    public void testRemove() throws Exception {
        String afterRemove = "dataset/user/afterRemove.xml";

        User user = new User();

        user.setId(2L);
        user.setLogin("login2");
        user.setPassword("pass2");
        user.setEmail("email2");
        user.setFirstName("firstName2");
        user.setLastName("lastName2");

        GregorianCalendar calendar = new GregorianCalendar(1995, 8, 5);
        user.setBirthDay(new Date(calendar.getTimeInMillis()));

        Role role = new Role();
        role.setId(2);
        role.setName("role2");
        user.setRole(role);

        userDao.remove(user);

        ITable expected = dbTestHelper.getTableFromFile(TABLE_NAME,
                afterRemove);
        ITable actual = dbTestHelper.getTableFromSchema(TABLE_NAME);

        assertEqualsIgnoreCols(expected, actual, IGNORE_COLS);
    }
}
