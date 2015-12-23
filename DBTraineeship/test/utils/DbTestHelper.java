package utils;

import java.io.File;
import java.io.FileReader;
import java.sql.Connection;

import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.h2.H2DataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.dbunit.util.TableFormatter;
import org.h2.tools.RunScript;

public class DbTestHelper {
    public static final String SQL_CREATE_CHEMA = "resources/sql/Create scheme.sql";
    public static final String SQL_CREATE_TABLE_ROLE = "resources/sql/Create table ROLE.sql";
    public static final String SQL_CREATE_TABLE_USER = "resources/sql/Create table USER.sql";

    public static void initDb(Connection connection) throws Exception {
        RunScript.execute(connection,
                new FileReader(new File(SQL_CREATE_CHEMA)));
        RunScript.execute(connection,
                new FileReader(new File(SQL_CREATE_TABLE_ROLE)));
        RunScript.execute(connection,
                new FileReader(new File(SQL_CREATE_TABLE_USER)));
    }

    public static void fill(String dataSetPath, Connection connection)
            throws Exception {
        IDataSet dataSet = new FlatXmlDataSetBuilder().build(DbTestHelper.class
                .getClassLoader().getResourceAsStream(dataSetPath));
        DatabaseOperation.CLEAN_INSERT.execute(getIDBConnection(connection),
                dataSet);
    }

    public static ITable getTableFromSchema(String tableName,
            Connection connection) throws Exception {
        IDataSet dataSet = getIDBConnection(connection).createDataSet();
        return dataSet.getTable(tableName);
    }

    public static ITable getTableFromFile(String tableName, String xmlFilePath)
            throws Exception {
        FlatXmlDataSet dataSet = new FlatXmlDataSetBuilder()
                .build(DbTestHelper.class.getClassLoader()
                        .getResourceAsStream(xmlFilePath));
        return dataSet.getTable(tableName);
    }

    public static String tableAsString(ITable table) throws Exception {
        return new TableFormatter().format(table);
    }

    private static IDatabaseConnection getIDBConnection(Connection connection)
            throws Exception {
        final String SCHEMA = "TRAINEESHIP_DB";
        DatabaseConnection databaseConnection = new DatabaseConnection(
                connection, SCHEMA);
        DatabaseConfig databaseConfig = databaseConnection.getConfig();
        databaseConfig.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY,
                new H2DataTypeFactory());
        return databaseConnection;
    }
}