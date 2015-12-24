package com.nixsolutions.angelin.jdbc;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.nixsolutions.angelin.jdbc.pojo.User;

public class TestUserDao extends DbUnitConfig {
    
    @Before
    public void setUp() throws Exception {
        beforeData = new FlatXmlDataSetBuilder()
                .build(new File(DATASET_INIT_TABLE));
        DatabaseOperation.CLEAN_INSERT.execute(getConnection(), getDataSet());
    }

    @Test(expected = NullPointerException.class)
    public void testCreateByNull() throws Exception {
        userDao.create(null);
    }

    @Test(expected = RuntimeException.class)
    public void testCreateDublicate() throws Exception {
        userDao.create(users[0]);
    }

    @Test
    public void testCreate() throws Exception {
        userDao.create(users[2]);
        IDataSet expectedData = new FlatXmlDataSetBuilder()
                .build(new File(DATASET_INSERT));
        IDataSet actualData = getConnection().createDataSet();
        ITable expectedTable = expectedData.getTable("user");
        ITable actualTable = actualData.getTable("user");
        Assertion.assertEquals(expectedTable, actualTable);
    }

    @Test(expected = NullPointerException.class)
    public void testUpdateByNull() throws Exception {
        userDao.update(null);
    }

    @Test
    public void testUpdate() throws Exception {
        userDao.update(users[3]);
        IDataSet expectedData = new FlatXmlDataSetBuilder()
                .build(new File(DATASET_UPDATE));
        IDataSet actualData = getConnection().createDataSet();
        ITable expectedTable = expectedData.getTable("user");
        ITable actualTable = actualData.getTable("user");
        Assertion.assertEquals(expectedTable, actualTable);
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveByNull() throws Exception {
        userDao.remove(null);
    }

    @Test
    public void testRemove() throws Exception {
        userDao.remove(users[1]);
        IDataSet expectedData = new FlatXmlDataSetBuilder()
                .build(new File(DATASET_REMOVE));
        IDataSet actualData = getConnection().createDataSet();
        ITable expectedTable = expectedData.getTable("user");
        ITable actualTable = actualData.getTable("user");
        Assertion.assertEquals(expectedTable, actualTable);
    }

    @Test(expected = NullPointerException.class)
    public void testFindByLoginNull() throws Exception {
        userDao.findByLogin(null);
    }

    @Test
    public void testFindByLogin() throws Exception {
        Assert.assertEquals("Admin",
                userDao.findByLogin("admin").getFirstName());
        Assert.assertEquals("User", userDao.findByLogin("user").getFirstName());
    }

    @Test(expected = NullPointerException.class)
    public void testFindByEmailNull() throws Exception {
        userDao.findByEmail(null);
    }

    @Test
    public void testFindByEmail() throws Exception {
        Assert.assertEquals("Adminovich",
                userDao.findByEmail("admin@nixsolutions.com").getLastName());
        Assert.assertEquals("Userovich",
                userDao.findByEmail("user@nixsolutions.com").getLastName());
    }

    @Test
    public void testFindAll() throws Exception {
        List<User> expectedList = new ArrayList<User>();
        expectedList.add(users[0]);
        expectedList.add(users[1]);
        List<User> actualList = userDao.findAll();
        Assert.assertEquals("Actual List must returned two User object",
                expectedList.size(), actualList.size());
        Assert.assertArrayEquals("Arrays must be equals",
                expectedList.toArray(), actualList.toArray());
    }

    @After
    public void tearDown() throws Exception {
        beforeData = new FlatXmlDataSetBuilder()
                .build(new File(DATASET_INIT_TABLE));
        DatabaseOperation.DELETE_ALL.execute(getConnection(), getDataSet());
    }
}
