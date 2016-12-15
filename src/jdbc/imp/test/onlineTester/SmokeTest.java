package jdbc.imp.test.onlineTester;

import jdbc.imp.driver.DriverAdapter;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.sql.*;
import java.util.Properties;

/**
 * Notes:
 1- Any test with "xmldb" can be replaced with "jsondb".

 2- You can manipulate this file as much as you can, add more tests, change these tests ... etc.

 3- This file does not cover all the required test cases but that does not mean that these test cases will be forgotten, the SanityTest
 will have many of them.
 **/
public class SmokeTest {

    private String protocol = "xmldb";
    private String tmp = System.getProperty("java.io.tmpdir");

    public static Class<?> getSpecifications() {
        return Driver.class;
    }

    private Connection createUseDatabase(String databaseName) throws SQLException {
        Driver driver = new DriverAdapter();
        Properties info = new Properties();
        File dbDir = new File(tmp + "/jdbc/" + Math.round((((float) Math.random()) * 100000)));
        info.put("path", dbDir.getAbsoluteFile());

        Connection connection = driver.connect("jdbc:" + protocol + "://localhost", info); // Establish
        // connection
        // (really,
        // just
        // make
        // sure
        // that the dbDir exists, and create it if it
        // doesn't), and just record the protocol.

        Statement statement = connection.createStatement(); // create a
        // statement object
        // to execute next
        // statements.

        statement.execute("CREATE DATABASE " + databaseName); // you should now
        // create a
        // folder for
        // that database
        // within dbDir.
        statement.execute("USE " + databaseName); // Set the state of your
        // connection to use
        // "databaseName", all next
        // created statements
        // (like selects and inserts) should be applied to this database.
        statement.close();
        return connection;
    }

    @Test //
    public void testCreateTable() throws SQLException {
        Connection connection = createUseDatabase("TestDB_Create");
        System.getProperty("java.io.tmpdir");
        try {
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE table_name1(column_name1 varchar, column_name2 int, column_name3 date)");
            statement.close();
        } catch (Throwable e) {
            TestRunner.fail("Failed to create table", e);
        }
        try {
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE table_name1(column_name1 varchar, column_name2 int, column_name3 date)");
            Assert.fail("Created existing table successfully!");
        } catch (SQLException e) {

        } catch (Throwable e) {
            TestRunner.fail("Invalid Exception thrown", e);
        }

        try {
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE incomplete_table_name1");
            Assert.fail("Create invalid table succeed");
        } catch (SQLException e) {
        } catch (Throwable e) {
            TestRunner.fail("Invalid Exception thrown", e);
        }
        connection.close();
    }

//    @Test //
//    public void testInsertWithoutColumnNames() throws SQLException {
//        Connection connection = createUseDatabase("TestDB_Create");
//        try {
//            Statement statement = connection.createStatement();
//            statement.execute("CREATE TABLE table_name3(column_name1 varchar, column_name2 int, column_name3 float)");
//            int count = statement.executeUpdate("INSERT INTO table_name3 VALUES ('value1', 3, 1.3)");
//            Assert.assertEquals("Insert returned a number != 1", 1, count);
//            statement.close();
//        } catch (Throwable e) {
//            TestRunner.fail("Failed to insert into table", e);
//        }
//        connection.close();
//    }

    @Test //
    public void testInsertWithColumnNames() throws SQLException {
        Connection connection = createUseDatabase("TestDB_Create");
        try {
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE table_name12(column_name1 varchar, column_name2 int, column_name3 varchar)");
            statement.execute("INSERT INTO table_name12(column_NAME1, COLUMN_name3, column_name2) VALUES ('value1', 'value3', 4)");
            statement.execute("INSERT INTO table_name12(column_NAME1, COLUMN_name3, column_name2) VALUES ('value1', 'value3', 4)");
            statement.execute("INSERT INTO table_name12(column_name1, COLUMN_NAME3, column_NAME2) VALUES ('value2', 'value4', 5)");
            statement.execute("INSERT INTO table_name12(column_name1, COLUMN_NAME3, column_NAME2) VALUES ('value5', 'value6', 6)");
            ResultSet rs = statement.executeQuery("SELECT * FROM table_name12");
            statement.close();
        } catch (Throwable e) {
            TestRunner.fail("Failed to insert into table", e);
        }
        connection.close();
    }

    @Test //
    public void testInsertWithWrongColumnNames() throws SQLException {
        Connection connection = createUseDatabase("TestDB_Create");
        try {
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE table_name5(column_name1 varchar, column_name2 int, column_name3 varchar)");
            statement.executeUpdate(
                    "INSERT INTO table_name5(invalid_column_name1, column_name3, column_name2) VALUES ('value1', 'value3', 4)");
            Assert.fail("Inserted with invalid column name!!");
            statement.close();
        } catch (SQLException e) {
        } catch (Throwable e) {
            TestRunner.fail("Invalid Exception thrown", e);
        }
        connection.close();
    }

    @Test //
    public void testInsertWithWrongColumnCount() throws SQLException {
//        Connection connection = createUseDatabase("TestDB_Create");
//        try {
//            Statement statement = connection.createStatement();
//            statement.execute("CREATE TABLE table_name6(column_name1 varchar, column_name2 int, column_name3 varchar)");
//            statement.executeUpdate("INSERT INTO table_name6 VALUES ('value1', 4)");
//            Assert.fail("Inserted with invalid column count!!");
//            statement.close();
//        } catch (SQLException e) {
//        } catch (Throwable e) {
//            TestRunner.fail("Invalid Exception thrown", e);
//        }
//        connection.close();
    }


    @Test //
    public void testUpdate() throws SQLException {
        Connection connection = createUseDatabase("TestDB_Create");
        try {
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE table_name7(column_name1 varchar, column_name2 int, column_name3 varchar)");
            int count1 = statement.executeUpdate(
                    "INSERT INTO table_name7(column_NAME1, COLUMN_name3, column_name2) VALUES ('value1', 'value3', 4)");
            Assert.assertEquals("Insert returned a number != 1", 1, count1);
            int count2 = statement.executeUpdate(
                    "INSERT INTO table_name7(column_NAME1, COLUMN_name3, column_name2) VALUES ('value1', 'value3', 4)");
            Assert.assertEquals("Insert returned a number != 1", 1, count2);
            int count3 = statement.executeUpdate(
                    "INSERT INTO table_name7(column_name1, COLUMN_NAME3, column_NAME2) VALUES ('value2', 'value4', 5)");
            Assert.assertEquals("Insert returned a number != 1", 1, count3);
            int count4 = statement.executeUpdate(
                    "UPDATE table_name7 SET column_name1='1111111111', COLUMN_NAME2=2222222, column_name3='333333333'");
            Assert.assertEquals("Updated returned wrong number", count1 + count2 + count3, count4);
            statement.close();
        } catch (Throwable e) {
            TestRunner.fail("Failed to update table", e);
        }
        connection.close();
    }

    @Test //
    public void testConditionalUpdate() throws SQLException {
        Connection connection = createUseDatabase("TestDB_Create");
        try {
            Statement statement = connection.createStatement();
            statement.execute(
                    "CREATE TABLE table_name8(column_name1 varchar, column_name2 int, column_name3 date, column_name4 float)");

            int count1 = statement.executeUpdate(
                    "INSERT INTO table_name8(column_NAME1, COLUMN_name3, column_name2, column_name4) VALUES ('value1', '2011-01-25', 3, 1.3)");
            Assert.assertEquals("Insert returned a number != 1", 1, count1);

            int count2 = statement.executeUpdate(
                    "INSERT INTO table_name8(column_NAME1, COLUMN_name3, column_name2, column_name4) VALUES ('value1', '2011-01-28', 3456, 1.01)");
            Assert.assertEquals("Insert returned a number != 1", 1, count2);

            int count3 = statement.executeUpdate(
                    "INSERT INTO table_name8(column_NAME1, COLUMN_name3, column_name2, column_name4) VALUES ('value2', '2011-02-11', -123, 3.14159)");
            Assert.assertEquals("Insert returned a number != 1", 1, count3);

            int count4 = statement.executeUpdate(
                    "UPDATE table_name8 SET COLUMN_NAME2=222222, column_name3='1993-10-03' WHERE coLUmn_NAME1='value1'");
            Assert.assertEquals("Updated returned wrong number", count1 + count2, count4);

            statement.close();
        } catch (Throwable e) {
            TestRunner.fail("Failed to update table", e);
        }
        connection.close();
    }

    @Test //
    public void testUpdateEmptyOrInvalidTable() throws SQLException {
        Connection connection = createUseDatabase("TestDB_Create");
        try {
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE table_name9(column_name1 varchar, column_name2 int, column_name3 varchar)");
            int count = statement.executeUpdate(
                    "UPDATE table_name9 SET column_name1='value1', column_name2=15, column_name3='value2'");
            Assert.assertEquals("Updated empty table retruned non-zero count!", 0, count);
            statement.close();
        } catch (Throwable e) {
            TestRunner.fail("Failed to update table", e);
        }

        try {
            Statement statement = connection.createStatement();
//            statement.executeUpdate(
//                    "UPDATE wrong_table_name9 SET column_name1='value1', column_name2=15, column_name3='value2'");
//            Assert.fail("Updated empty table retruned non-zero count!");
            statement.close();
        } catch (SQLException e) {
        } catch (Throwable e) {
            TestRunner.fail("Invalid exception was thrown", e);
        }
        connection.close();
    }

    @Test //
    public void testDelete() throws SQLException {
        Connection connection = createUseDatabase("TestDB_Create");
        try {
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE table_name10(column_name1 varchar, column_name2 int, column_name3 date)");
            int count1 = statement.executeUpdate(
                    "INSERT INTO table_name10(column_NAME1, COLUMN_name3, column_name2) VALUES ('value1', '2011-01-25', 4)");
            Assert.assertEquals("Insert returned a number != 1", 1, count1);
            int count2 = statement.executeUpdate(
                    "INSERT INTO table_name10(column_NAME1, COLUMN_name3, column_name2) VALUES ('value1', '2011-01-28', 4)");
            Assert.assertEquals("Insert returned a number != 1", 1, count2);
            int count3 = statement.executeUpdate(
                    "INSERT INTO table_name10(column_name1, COLUMN_NAME3, column_NAME2) VALUES ('value2', '2011-02-11', 5)");
            Assert.assertEquals("Insert returned a number != 1", 1, count3);
            int count4 = statement.executeUpdate("DELETE From table_name10");
            Assert.assertEquals("Delete returned wrong number", 3, count4);
            statement.close();
        } catch (Throwable e) {
            TestRunner.fail("Failed to delete from table", e);
        }
        connection.close();
    }

    @Test //
    public void testConditionalDelete() throws SQLException {
        Connection connection = createUseDatabase("TestDB_Create");
        try {
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE table_name11(column_name1 varchar, column_name2 int, column_name3 DATE)");
            int count1 = statement.executeUpdate(
                    "INSERT INTO table_name11(column_NAME1, COLUMN_name3, column_name2) VALUES ('value1', '2011-01-25', 4)");
            Assert.assertEquals("Insert returned a number != 1", 1, count1);
            int count2 = statement.executeUpdate(
                    "INSERT INTO table_name11(column_NAME1, COLUMN_name3, column_name2) VALUES ('value1', '2013-06-30', 4)");
            Assert.assertEquals("Insert returned a number != 1", 1, count2);
            int count3 = statement.executeUpdate(
                    "INSERT INTO table_name11(column_name1, COLUMN_NAME3, column_NAME2) VALUES ('value2', '2013-07-03', 5)");
            Assert.assertEquals("Insert returned a number != 1", 1, count3);
            int count4 = statement.executeUpdate("DELETE From table_name11  WHERE coLUmn_NAME3>'2011-01-25'");
            Assert.assertEquals("Delete returned wrong number", 2, count4);
            statement.close();
        } catch (Throwable e) {
            TestRunner.fail("Failed to delete from table", e);
        }
        connection.close();
    }

    @Test //
    public void testSelect() throws SQLException {
        Connection connection = createUseDatabase("TestDB_Create");
        try {
            Statement statement = connection.createStatement();
            statement
                    .execute("CREATE TABLE table_name12(column_name1 varchar, column_name2 int, column_name3 varchar)");
            int count1 = statement.executeUpdate(
                    "INSERT INTO table_name12(column_NAME1, COLUMN_name3, column_name2) VALUES ('value1', 'value3', 4)");
            Assert.assertEquals("Insert returned a number != 1", 1, count1);
            int count2 = statement.executeUpdate(
                    "INSERT INTO table_name12(column_NAME1, COLUMN_name3, column_name2) VALUES ('value1', 'value3', 4)");
            Assert.assertEquals("Insert returned a number != 1", 1, count2);
            int count3 = statement.executeUpdate(
                    "INSERT INTO table_name12(column_name1, COLUMN_NAME3, column_NAME2) VALUES ('value2', 'value4', 5)");
            Assert.assertEquals("Insert returned a number != 1", 1, count3);
            int count4 = statement.executeUpdate(
                    "INSERT INTO table_name12(column_name1, COLUMN_NAME3, column_NAME2) VALUES ('value5', 'value6', 6)");
            Assert.assertEquals("Insert returned a number != 1", 1, count4);
            ResultSet result = statement.executeQuery("SELECT * From table_name12");
            int rows = 0;
            while (result.next())
                rows++;
            Assert.assertNotNull("Null result retruned", result);
            Assert.assertEquals("Wrong number of rows", 4, rows);
            Assert.assertEquals("Wrong number of columns", 3, result.getMetaData().getColumnCount());
            statement.close();
        } catch (Throwable e) {
            TestRunner.fail("Failed to select from table", e);
        }
        connection.close();
    }

    @Test //
    public void testConditionalSelect() throws SQLException {
        Connection connection = createUseDatabase("TestDB_Create");
        try {
            Statement statement = connection.createStatement();
            statement
                    .execute("CREATE TABLE table_name13(column_name1 varchar, column_name2 int, column_name3 varchar)");
            int count1 = statement.executeUpdate(
                    "INSERT INTO table_name13(column_NAME1, COLUMN_name3, column_name2) VALUES ('value1', 'value3', 4)");
            Assert.assertEquals("Insert returned a number != 1", 1, count1);
            int count2 = statement.executeUpdate(
                    "INSERT INTO table_name13(column_NAME1, column_name2, COLUMN_name3) VALUES ('value1', 4, 'value3')");
            Assert.assertEquals("Insert returned a number != 1", 1, count2);
            int count3 = statement.executeUpdate(
                    "INSERT INTO table_name13(column_name1, COLUMN_NAME3, column_NAME2) VALUES ('value2', 'value4', 5)");
            Assert.assertEquals("Insert returned a number != 1", 1, count3);
            int count4 = statement.executeUpdate(
                    "INSERT INTO table_name13(column_name1, COLUMN_NAME3, column_NAME2) VALUES ('value5', 'value6', 6)");
            Assert.assertEquals("Insert returned a number != 1", 1, count4);
            ResultSet result = statement.executeQuery("SELECT column_name1 FROM table_name13 WHERE coluMN_NAME2 < 5");
            int rows = 0;
            while (result.next())
                rows++;
            Assert.assertNotNull("Null result retruned", result);
            Assert.assertEquals("Wrong number of rows", 2, rows);
            Assert.assertEquals("Wrong number of columns", 1, result.getMetaData().getColumnCount());
            statement.close();
        } catch (Throwable e) {
            TestRunner.fail("Failed to select from table", e);
        }
        connection.close();
    }

    @Test //
    public void testExecute() throws SQLException {
        Connection connection = createUseDatabase("TestDB_Create");
        try {
            Statement statement = connection.createStatement();
            statement
                    .execute("CREATE TABLE table_name13(column_name1 varchar, column_name2 int, column_name3 varchar)");
            int count1 = statement.executeUpdate(
                    "INSERT INTO table_name13(column_NAME1, COLUMN_name3, column_name2) VALUES ('value1', 'value3', 4)");
            Assert.assertEquals("Insert returned a number != 1", 1, count1);
            boolean result1 = statement.execute(
                    "INSERT INTO table_name13(column_NAME1, column_name2, COLUMN_name3) VALUES ('value1', 8, 'value3')");
            Assert.assertFalse("Wrong return from 'execute' for insert record", result1);
            int count3 = statement.executeUpdate(
                    "INSERT INTO table_name13(column_name1, COLUMN_NAME3, column_NAME2) VALUES ('value2', 'value4', 5)");
            Assert.assertEquals("Insert returned a number != 1", 1, count3);
            int count4 = statement.executeUpdate(
                    "INSERT INTO table_name13(column_name1, COLUMN_NAME3, column_NAME2) VALUES ('value5', 'value6', 6)");
            Assert.assertEquals("Insert returned a number != 1", 1, count4);

            boolean result2 = statement.execute("SELECT column_name1 FROM table_name13 WHERE coluMN_NAME2 = 8");
            Assert.assertTrue("Wrong return for select existing records", result2);

            boolean result3 = statement.execute("SELECT column_name1 FROM table_name13 WHERE coluMN_NAME2 > 100");
            Assert.assertFalse("Wrong return for select non existing records", result3);

            statement.close();
        } catch (Throwable e) {
            TestRunner.fail("Failed to select from table", e);
        }
        connection.close();
    }

    @Test //
    public void testDistinct() throws SQLException {
        Connection connection = createUseDatabase("TestDB_Create");
        try {
            Statement statement = connection.createStatement();
            statement
                    .execute("CREATE TABLE table_name13(column_name1 varchar, column_name2 int, column_name3 varchar)");
            int count1 = statement.executeUpdate(
                    "INSERT INTO table_name13(column_NAME1, COLUMN_name3, column_name2) VALUES ('value1', 'value3', 4)");
            Assert.assertEquals("Insert returned a number != 1", 1, count1);
            boolean result1 = statement.execute(
                    "INSERT INTO table_name13(column_NAME1, column_name2, COLUMN_name3) VALUES ('value1', 4, 'value5')");
            Assert.assertFalse("Wrong return for insert record", result1);
            int count3 = statement.executeUpdate(
                    "INSERT INTO table_name13(column_name1, COLUMN_NAME3, column_NAME2) VALUES ('value2', 'value4', 5)");
            Assert.assertEquals("Insert returned a number != 1", 1, count3);
            int count4 = statement.executeUpdate(
                    "INSERT INTO table_name13(column_name1, COLUMN_NAME3, column_NAME2) VALUES ('value5', 'value6', 6)");
            Assert.assertEquals("Insert returned a number != 1", 1, count4);

            boolean result2 = statement.execute("SELECT DISTINCT column_name2 FROM table_name13");
            Assert.assertTrue("Wrong return for select existing records", result2);
            ResultSet res1 = statement.getResultSet();

            int rows = 0;
            while (res1.next())
                rows++;
            Assert.assertEquals("Wrong number of rows", 3, rows);

            boolean result3 = statement
                    .execute("SELECT DISTINCT column_name2, column_name3 FROM table_name13 WHERE coluMN_NAME2 < 5");
            Assert.assertTrue("Wrong return for select existing records", result3);
            ResultSet res2 = statement.getResultSet();

            int rows2 = 0;
            while (res2.next())
                rows2++;
            Assert.assertEquals("Wrong number of rows", 2, rows2);

            statement.close();
        } catch (Throwable e) {
            TestRunner.fail("Failed to select distinct from table", e);
        }
        connection.close();
    }

    @Test //
    public void testAlterTable() throws SQLException {
        Connection connection = createUseDatabase("TestDB_Create");
        try {
            Statement statement = connection.createStatement();
            statement
                    .execute("CREATE TABLE table_name13(column_name1 varchar, column_name2 int, column_name3 varchar)");
            int count1 = statement.executeUpdate(
                    "INSERT INTO table_name13(column_NAME1, COLUMN_name3, column_name2) VALUES ('value1', 'value3', 4)");
            Assert.assertEquals("Insert returned a number != 1", 1, count1);
            boolean result1 = statement.execute(
                    "INSERT INTO table_name13(column_NAME1, column_name2, COLUMN_name3) VALUES ('value1', 4, 'value5')");
            Assert.assertFalse("Wrong return for insert record", result1);
            int count3 = statement.executeUpdate(
                    "INSERT INTO table_name13(column_name1, COLUMN_NAME3, column_NAME2) VALUES ('value2', 'value4', 5)");
            Assert.assertEquals("Insert returned a number != 1", 1, count3);
            int count4 = statement.executeUpdate(
                    "INSERT INTO table_name13(column_name1, COLUMN_NAME3, column_NAME2) VALUES ('value5', 'value6', 6)");
            Assert.assertEquals("Insert returned a number != 1", 1, count4);

            boolean result2 = statement.execute("ALTER TABLE table_name13 ADD column_name4 date");
            Assert.assertFalse("Wrong return for ALTER TABLE", result2);

            boolean result3 = statement.execute("SELECT column_name4 FROM table_name13 WHERE coluMN_NAME2 = 5");
            Assert.assertTrue("Wrong return for select existing records", result3);
            ResultSet res2 = statement.getResultSet();
            int rows2 = 0;
            while (res2.next())
                rows2++;
            Assert.assertEquals("Wrong number of rows", 1, rows2);

            while (res2.previous());
            res2.next();

            Assert.assertNull("Retrieved date is not null", res2.getDate("column_name4"));

            statement.close();
        } catch (Throwable e) {
            TestRunner.fail("Failed to test ALTER TABLE from table", e);
        }
        connection.close();
    }

    @Test //
    public void testUnion() throws SQLException {
        Connection connection = createUseDatabase("TestDB_Create");
        try {
            Statement statement = connection.createStatement();
            statement
                    .execute("CREATE TABLE table_name13(column_name1 varchar, column_name2 int, column_name3 varchar)");
            int count1 = statement.executeUpdate(
                    "INSERT INTO table_name13(column_NAME1, COLUMN_name3, column_name2) VALUES ('value1', 'value3', 4)");
            Assert.assertEquals("Insert returned a number != 1", 1, count1);
            boolean result1 = statement.execute(
                    "INSERT INTO table_name13(column_NAME1, column_name2, COLUMN_name3) VALUES ('value1', 4, 'value5')");
            Assert.assertFalse("Wrong return for insert record", result1);
            int count3 = statement.executeUpdate(
                    "INSERT INTO table_name13(column_name1, COLUMN_NAME3, column_NAME2) VALUES ('value2', 'value4', 5)");
            Assert.assertEquals("Insert returned a number != 1", 1, count3);
            int count4 = statement.executeUpdate(
                    "INSERT INTO table_name13(column_name1, COLUMN_NAME3, column_NAME2) VALUES ('value5', 'value6', 6)");
            Assert.assertEquals("Insert returned a number != 1", 1, count4);

            boolean result3 = statement.execute(
                    "SELECT * FROM table_name13 WHERE coluMN_NAME2 = 4 UNION SELECT * FROM table_name13 WHERE coluMN_NAME3 < 'value6'");
            Assert.assertTrue("Wrong return for select UNION existing records", result3);
            ResultSet res2 = statement.getResultSet();
            int rows2 = 0;
            while (res2.next())
                rows2++;
            Assert.assertEquals("Wrong number of rows", 3, rows2);

            statement.close();
        } catch (Throwable e) {
            TestRunner.fail("Failed to test SELECT from table UNION", e);
        }
        connection.close();
    }

    @Test
    public void testOrderBy() throws SQLException {
        Connection connection = createUseDatabase("TestDB_Create");
        try {
            Statement statement = connection.createStatement();
            statement
                    .execute("CREATE TABLE table_name13(column_name1 varchar, column_name2 int, column_name3 varchar)");
            int count1 = statement.executeUpdate(
                    "INSERT INTO table_name13(column_NAME1, COLUMN_name3, column_name2) VALUES ('value1', 'value3', 4)");
            Assert.assertEquals("Insert returned a number != 1", 1, count1);
            boolean result1 = statement.execute(
                    "INSERT INTO table_name13(column_NAME1, column_name2, COLUMN_name3) VALUES ('value1', 4, 'value5')");
            Assert.assertFalse("Wrong return for insert record", result1);
            int count3 = statement.executeUpdate(
                    "INSERT INTO table_name13(column_name1, COLUMN_NAME3, column_NAME2) VALUES ('value2', 'value4', 5)");
            Assert.assertEquals("Insert returned a number != 1", 1, count3);
            int count4 = statement.executeUpdate(
                    "INSERT INTO table_name13(column_name1, COLUMN_NAME3, column_NAME2) VALUES ('value5', 'value6', 6)");
            Assert.assertEquals("Insert returned a number != 1", 1, count4);
            boolean result3 = statement
                    .execute("SELECT * FROM table_name13 ORDER BY column_name2 ASC, COLUMN_name3 DESC");
            Assert.assertTrue("Wrong return for select UNION existing records", result3);
            ResultSet res2 = statement.getResultSet();
            while (res2.next());
            while (res2.previous());
            Assert.assertTrue(res2.isBeforeFirst());
            res2.next();
            Assert.assertTrue(res2.isFirst());
            Assert.assertEquals("value1", res2.getString("colUmn_Name1"));
            Assert.assertEquals(4, res2.getInt("colUmn_Name2"));
            Assert.assertEquals("value5", res2.getString("colUmn_Name3"));
            res2.next();
            Assert.assertEquals("value1", res2.getString("colUmn_Name1"));
            Assert.assertEquals(4, res2.getInt("colUmn_Name2"));
            Assert.assertEquals("value3", res2.getString("colUmn_Name3"));
            res2.next();
            Assert.assertEquals("value2", res2.getString("colUmn_Name1"));
            Assert.assertEquals(5, res2.getInt("colUmn_Name2"));
            Assert.assertEquals("value4", res2.getString("colUmn_Name3"));
            res2.next();
            Assert.assertTrue(res2.isLast());
            Assert.assertEquals("value5", res2.getString("colUmn_Name1"));
            Assert.assertEquals(6, res2.getInt("colUmn_Name2"));
            Assert.assertEquals("value6", res2.getString("colUmn_Name3"));
            res2.next();
            Assert.assertTrue(res2.isAfterLast());
            while (res2.previous());
            Assert.assertTrue(res2.isBeforeFirst());
            res2.next();
            Assert.assertTrue(res2.isFirst());
            Assert.assertEquals("value1", res2.getString(1));
            Assert.assertEquals(4, res2.getInt(2));
            Assert.assertEquals("value5", res2.getString(3));
            res2.next();
            Assert.assertEquals("value1", res2.getString(1));
            Assert.assertEquals(4, res2.getInt(2));
            Assert.assertEquals("value3", res2.getString(3));
            res2.next();
            Assert.assertEquals("value2", res2.getString(1));
            Assert.assertEquals(5, res2.getInt(2));
            Assert.assertEquals("value4", res2.getString(3));
            res2.next();
            Assert.assertTrue(res2.isLast());
            Assert.assertEquals("value5", res2.getString(1));
            Assert.assertEquals(6, res2.getInt(2));
            Assert.assertEquals("value6", res2.getString(3));
            res2.next();
            Assert.assertTrue(res2.isAfterLast());
            while (res2.previous());
            while (res2.next());
            while (res2.previous());
            Assert.assertTrue(res2.isBeforeFirst());
            res2.next();
            Assert.assertTrue(res2.isFirst());
            res2.first();
            Assert.assertEquals("value1", res2.getObject(res2.findColumn("colUmn_Name1")));
            Assert.assertEquals(4, res2.getObject(res2.findColumn("colUmn_Name2")));
            Assert.assertEquals("value5", res2.getObject(res2.findColumn("colUmn_Name3")));
            res2.next();
            Assert.assertEquals("value1", res2.getObject(res2.findColumn("colUmn_Name1")));
            Assert.assertEquals(4, res2.getObject(res2.findColumn("colUmn_Name2")));
            Assert.assertEquals("value3", res2.getObject("colUmn_Name3"));
            res2.next();
            Assert.assertEquals("value2", res2.getObject("colUmn_Name1"));
            Assert.assertEquals(5, res2.getObject("colUmn_Name2"));
            Assert.assertEquals("value4", res2.getObject("colUmn_Name3"));
            res2.next();
            Assert.assertTrue(res2.isLast());
            Assert.assertEquals("value5", res2.getObject("colUmn_Name1"));
            Assert.assertEquals(6, res2.getObject("colUmn_Name2"));
            Assert.assertEquals("value6", res2.getObject("colUmn_Name3"));
            res2.next();
            Assert.assertTrue(res2.isAfterLast());
            res2.previous();
            Assert.assertEquals("value5", res2.getObject("colUmn_Name1"));
            Assert.assertEquals(6, res2.getObject("colUmn_Name2"));
            res2.absolute(1);
            Assert.assertEquals("value1", res2.getString(1));
            Assert.assertEquals(4, res2.getInt(2));
            Assert.assertEquals("value5", res2.getString(3));
            res2.next();
            Assert.assertEquals("value1", res2.getString(1));
            Assert.assertEquals(4, res2.getInt(2));
            Assert.assertEquals("value3", res2.getString(3));
            res2.next();
            Assert.assertEquals("value2", res2.getString(1));
            Assert.assertEquals(5, res2.getInt(2));
            Assert.assertEquals("value4", res2.getString(3));
            res2.next();
            Assert.assertTrue(res2.isLast());
            Assert.assertEquals("value5", res2.getString(1));
            Assert.assertEquals(6, res2.getInt(2));
            Assert.assertEquals("value6", res2.getString(3));
            res2.next();
            Assert.assertTrue(res2.isAfterLast());

            statement.close();
        } catch (Throwable e) {
            TestRunner.fail("Failed to test ORDER By", e);
        }
        connection.close();
    }
}
