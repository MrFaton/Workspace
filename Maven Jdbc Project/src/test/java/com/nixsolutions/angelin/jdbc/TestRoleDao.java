package com.nixsolutions.angelin.jdbc;

import java.io.File;

import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestRoleDao extends DbUnitConfig {
    
    @Before
    public void setUp() throws Exception {
        beforeData = new FlatXmlDataSetBuilder()
                .build(new File(DATASET_INIT_TABLE));
        DatabaseOperation.CLEAN_INSERT.execute(getConnection(), getDataSet());
    }

    @Test(expected = NullPointerException.class)
    public void testCreateByNull() throws Exception {
        roleDao.create(null);
    }

    @Test(expected = RuntimeException.class)
    public void testCreateDublicate() throws Exception {
        roleDao.create(roles[0]);
    }

    @Test
    public void testCreate() throws Exception {
        roleDao.create(roles[2]);
        IDataSet expectedData = new FlatXmlDataSetBuilder()
                .build(new File(DATASET_INSERT));
        IDataSet actualData = getConnection().createDataSet();
        ITable expectedTable = expectedData.getTable("role");
        ITable actualTable = actualData.getTable("role");
        Assertion.assertEquals(expectedTable, actualTable);
    }

    @Test(expected = NullPointerException.class)
    public void testUpdateByNull() throws Exception {
        roleDao.update(null);
    }

    @Test
    public void testUpdate() throws Exception {
        roleDao.update(roles[3]);
        IDataSet expectedData = new FlatXmlDataSetBuilder()
                .build(new File(DATASET_UPDATE));
        IDataSet actualData = getConnection().createDataSet();
        ITable expectedTable = expectedData.getTable("role");
        ITable actualTable = actualData.getTable("role");
        Assertion.assertEquals(expectedTable, actualTable);
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveByNull() throws Exception {
        roleDao.remove(null);
    }

    @Test
    public void testRemove() throws Exception {
        roleDao.remove(roles[1]);
        IDataSet expectedData = new FlatXmlDataSetBuilder()
                .build(new File(DATASET_REMOVE));
        IDataSet actualData = getConnection().createDataSet();
        ITable expectedTable = expectedData.getTable("role");
        ITable actualTable = actualData.getTable("role");
        Assertion.assertEquals(expectedTable, actualTable);
    }

    @Test(expected = NullPointerException.class)
    public void testFindByNameNull() throws Exception {
        roleDao.findByName(null);
    }

    @Test
    public void testFindByName() throws Exception {
        Assert.assertEquals("admin", roleDao.findByName("admin").getName());
        Assert.assertEquals("user", roleDao.findByName("user").getName());
    }

    @After
    public void tearDown() throws Exception {
        beforeData = new FlatXmlDataSetBuilder()
                .build(new File(DATASET_INIT_TABLE));
        DatabaseOperation.DELETE_ALL.execute(getConnection(), getDataSet());
    }
}
