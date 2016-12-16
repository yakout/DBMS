package jdbc.imp.test.jdbcTesting;

import java.io.File;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.junit.Assert;
import org.junit.Test;

import jdbc.imp.driver.*;
import jdbc.imp.resultSet.DBResultSet;

public class JDBCTest {
    private final String protocol = "altdb";
    private final String tmp = System.getProperty("java.io.tmpdir");

    public static Class<?> getSpecifications() {
        return Driver.class;
    }

    private Connection createUseDatabase(final String databaseName) throws SQLException {
        final Driver driver = new DBDriver();
        final Properties info = new Properties();
        final File dbDir = new File(tmp + "/jdbc/" + Math.round((((float) Math.random()) * 100000)));
        info.put("path", dbDir.getAbsoluteFile());
        final Connection connection = driver.connect("jdbc:" + protocol + "://localhost", info);
        final Statement statement = connection.createStatement();
        statement.execute("CREATE DATABASE " + databaseName);
        statement.execute("USE " + databaseName);
        statement.close();
        return connection;
    }

    @Test
    public void testJDBCOne() throws SQLException {
        final Connection connection = createUseDatabase("School");
        try {
            final Statement statement = connection.createStatement();
            statement.execute("Create table Student (ID int, Name varchar, Grade float)");
            int count = statement.executeUpdate("INSERT INTO Student (ID, Name, Grade)"
                    + " VALUES (1 ,'Ahmed Khaled', 90.5)");
            Assert.assertEquals("Table Insertion did not return 1", 1, count);
            count  = statement.executeUpdate("INSERT INTO Student (ID, Name, Grade)"
                    + " VALUES (2 ,'Ahmed El Naggar', 90.2)");
            Assert.assertEquals("Table Insertion did not return 1", 1, count);
            final ResultSet resultSet = statement.executeQuery("SELECT * FROM Student");
            resultSet.next();
            Assert.assertEquals("Failed to get Correct Float Value",
                    90.5, resultSet.getFloat("Grade"), 0.0001);
        } catch (final SQLException e) {
            e.printStackTrace();
        }
        connection.close();
    }

    @Test
    public void testJDBCTwo() throws SQLException {
        final Connection connection = createUseDatabase("School");
        try {
            final Statement statement = connection.createStatement();
            statement.execute("Create table Student (ID int, Name varchar, Grade float)");
            int count = statement.executeUpdate("INSERT INTO Student (ID, Name, Grade)"
                    + " VALUES (1 ,'Ahmed Khaled', 90.5)");
            Assert.assertEquals("Table Insertion did not return 1", 1, count);
            count  = statement.executeUpdate("INSERT INTO Student (ID, Name, Grade)"
                    + " VALUES (2 ,'Ahmed El Naggar', 90.2)");
            Assert.assertEquals("Table Insertion did not return 1", 1, count);
            count  = statement.executeUpdate("INSERT INTO Student (ID, Name, Grade)"
                    + " VALUES (3 ,'Ahmed Walid', 90.5)");
            count  = statement.executeUpdate("INSERT INTO Student (ID, Name, Grade)"
                    + " VALUES (4 ,'Anas Harby', 90.5)");
            final DBResultSet resultSet = (DBResultSet)
                    statement.executeQuery("SELECT * FROM Student WHERE ID > 1");
            int numberOfMatches = 0;
            while (resultSet.next()) {
                numberOfMatches++;
            }
            Assert.assertEquals("Invalid Result Set Size", 3, numberOfMatches);
        } catch (final SQLException e) {
            e.printStackTrace();
        }
        connection.close();
    }

    @Test
    public void testJDBCThree() throws SQLException {
        final Connection connection = createUseDatabase("TestDB_Create");
        try {
            final Statement statement = connection.createStatement();
            statement
                    .execute("CREATE TABLE table_name13(column_name1 varchar, column_name2" +
                            " int, column_name3 varchar)");
            final int count1 = statement.executeUpdate(
                    "INSERT INTO table_name13(column_NAME1, COLUMN_name3, column_name2) " +
                            "VALUES ('value1', 'value3', 4)");
            Assert.assertEquals("Insert returned a number != 1", 1, count1);
            final boolean result1 = statement.execute(
                    "INSERT INTO table_name13(column_NAME1, column_name2, COLUMN_name3)" +
                            " VALUES ('value1', 4, 'value5')");
            Assert.assertFalse("Wrong return for insert record", result1);
            final int count3 = statement.executeUpdate(
                    "INSERT INTO table_name13(column_name1, COLUMN_NAME3, column_NAME2) " +
                            "VALUES ('value2', 'value4', 5)");
            Assert.assertEquals("Insert returned a number != 1", 1, count3);
            final int count4 = statement.executeUpdate(
                    "INSERT INTO table_name13(column_name1, COLUMN_NAME3, column_NAME2) " +
                            "VALUES ('value5', 'value6', 6)");
            Assert.assertEquals("Insert returned a number != 1", 1, count4);
            final boolean result3 = statement
                    .execute("SELECT * FROM table_name13" +
                            " ORDER BY column_name2 ASC, COLUMN_name3 DESC");
            Assert.assertTrue("Wrong return for select UNION existing records", result3);
            final ResultSet res2 = statement.getResultSet();

            res2.next();
            statement.close();
        } catch (final Throwable e) {
            e.printStackTrace();
        }
        connection.close();
    }

    @Test
    public void testJDBCFour() throws SQLException {
        final Connection connection = createUseDatabase("sqlDatabase");
        try {
            final Statement statement = connection.createStatement();
            statement.execute("Create table tb (ID int, Name varchar, Grade float, Birth date)");
            int count = statement.executeUpdate("INSERT INTO tb (ID, Name, Grade, birth)"
                    + " VALUES (-30, 'hello', -0.366, '2001-10-10')");
            Assert.assertEquals("Table Insertion did not return 1", 1, count);
            count  = statement.executeUpdate("INSERT INTO tb"
                    + " VALUES (-2 ,'A spaced string', 101.00002, '0001-01-01')");
            Assert.assertEquals("Table Insertion did not return 1", 1, count);
            count  = statement.executeUpdate("INSERT INTO tb"
                    + " VALUES (333 ,'a float is 003', 0.003, '8488-11-30')");
            Assert.assertEquals("Table Insertion did not return 1", 1, count);
            final ResultSet resultSet =
                    statement.executeQuery("SELECT * FROM tb WHERE birth != '1111-11-11'");
            int rows = 0;
            while (resultSet.next()) {
                rows++;
            }
            Assert.assertEquals("Invalid Result Set Size", 3, rows);
        } catch (final SQLException e) {
            e.printStackTrace();
        }
        connection.close();
    }

    @Test
    public void testJDBCFive() throws SQLException {
        final Connection connection = createUseDatabase("TestDB_Create");
        try {
            final Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE table_name3(column_name1 varchar, " +
                    "column_name2 int, column_name3 float)");
            final int count = statement.executeUpdate("INSERT INTO table_name3 " +
                    "VALUES ('value1', 3, 1.3)");
            Assert.assertEquals("Insert returned a number != 1", 1, count);
            statement.close();
        } catch (final Throwable e) {
            e.printStackTrace();
        }
        connection.close();
    }

    @Test
    public void testJDBCSix() throws SQLException {
        final Connection connection = createUseDatabase("TestDB_Create");
        try {
            final Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE table_name4(column_name1 varchar," +
                    " column_name2 int, column_name3 date)");
            final int count = statement.executeUpdate(
                    "INSERT INTO table_name4(column_NAME1, COLUMN_name3, column_name2)" +
                            " VALUES ('value1', '2011-01-25', 4)");
            Assert.assertEquals("Insert returned a number != 1", 1, count);
            statement.close();
        } catch (final Throwable e) {
            e.printStackTrace();
        }
        connection.close();
    }

    @Test
    public void testJDBCSeven() throws SQLException {
        final Connection connection = createUseDatabase("TestDB_Create");
        try {
            final Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE table_name5(column_name1 varchar, " +
                    "column_name2 int, column_name3 varchar)");
            statement.executeUpdate(
                    "INSERT INTO table_name5(invalid_column_name1, column_name3, " +
                            "column_name2) VALUES ('value1', 'value3', 4)");
            Assert.fail("Inserted with invalid column name!!");
            statement.close();
        } catch (final SQLException e) {
        } catch (final Throwable e) {
            e.printStackTrace();
        }
        connection.close();
    }

    @Test
    public void testJDBCEight() throws SQLException {
        final Connection connection = createUseDatabase("TestDB_Create");
        try {
            final Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE table_name7(column_name1 varchar, " +
                    "column_name2 int, column_name3 varchar)");
            final int count1 = statement.executeUpdate(
                    "INSERT INTO table_name7(column_NAME1, COLUMN_name3, column_name2)" +
                            " VALUES ('value1', 'value3', 4)");
            Assert.assertEquals("Insert returned a number != 1", 1, count1);
            final int count2 = statement.executeUpdate(
                    "INSERT INTO table_name7(column_NAME1, COLUMN_name3, column_name2)" +
                            " VALUES ('value1', 'value3', 4)");
            Assert.assertEquals("Insert returned a number != 1", 1, count2);
            final int count3 = statement.executeUpdate(
                    "INSERT INTO table_name7(column_name1, COLUMN_NAME3, column_NAME2)" +
                            " VALUES ('value2', 'value4', 5)");
            Assert.assertEquals("Insert returned a number != 1", 1, count3);
            final int count4 = statement.executeUpdate(
                    "UPDATE table_name7 SET column_name1='1111111111', " +
                            "COLUMN_NAME2=2222222, column_name3='333333333'");
            Assert.assertEquals("Updated returned wrong number", count1 + count2 + count3, count4);
            statement.close();
        } catch (final Throwable e) {
            e.printStackTrace();
        }
        connection.close();
    }

    @Test
    public void testJDBCNine() throws SQLException {
        final Connection connection = createUseDatabase("TestDB_Create");
        try {
            final Statement statement = connection.createStatement();
            statement.execute(
                    "CREATE TABLE table_name8(column_name1 varchar, column_name2 int," +
                            " column_name3 date, column_name4 float)");

            final int count1 = statement.executeUpdate(
                    "INSERT INTO table_name8(column_NAME1, COLUMN_name3, column_name2, " +
                            "column_name4) VALUES ('value1', '2011-01-25', 3, 1.3)");
            Assert.assertEquals("Insert returned a number != 1", 1, count1);

            final int count2 = statement.executeUpdate(
                    "INSERT INTO table_name8(column_NAME1, COLUMN_name3, column_name2," +
                            " column_name4) VALUES ('value1', '2011-01-28', 3456, 1.01)");
            Assert.assertEquals("Insert returned a number != 1", 1, count2);

            final int count3 = statement.executeUpdate(
                    "INSERT INTO table_name8(column_NAME1, COLUMN_name3, column_name2, " +
                            "column_name4) VALUES ('value2', '2011-02-11', -123, 3.14159)");
            Assert.assertEquals("Insert returned a number != 1", 1, count3);

            final int count4 = statement.executeUpdate(
                    "UPDATE table_name8 SET COLUMN_NAME2=222222, column_name3='1993-10-03'" +
                            " WHERE coLUmn_NAME1='value1'");
            Assert.assertEquals("Updated returned wrong number", count1 + count2, count4);

            statement.close();
        } catch (final Throwable e) {
            e.printStackTrace();
        }
        connection.close();
    }

    @Test
    public void testJDBCTen() throws SQLException {
        final Connection connection = createUseDatabase("TestDB_Create");
        try {
            final Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE table_name9(column_name1 varchar, column_name2 int, " +
                    "column_name3 varchar)");
            final int count = statement.executeUpdate(
                    "UPDATE table_name9 SET column_name1='value1', column_name2=15, " +
                            "column_name3='value2'");
            Assert.assertEquals("Updated empty table retruned non-zero count!", 0, count);
            statement.close();
        } catch (final Throwable e) {
            e.printStackTrace();
        }

        try {
            final Statement statement = connection.createStatement();
            statement.executeUpdate(
                    "UPDATE wrong_table_name9 SET column_name1='value1'," +
                            " column_name2=15, column_name3='value2'");
            Assert.fail("Updated empty table retruned non-zero count!");
            statement.close();
        } catch (final SQLException e) {
        } catch (final Throwable e) {
            e.printStackTrace();
        }
        connection.close();
    }

    @Test
    public void testJDBCEleven() throws SQLException {
        final Connection connection = createUseDatabase("TestDB_Create");
        try {
            final Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE table_name10(column_name1 varchar," +
                    " column_name2 int, column_name3 date)");
            final int count1 = statement.executeUpdate(
                    "INSERT INTO table_name10(column_NAME1, COLUMN_name3, column_name2)" +
                            " VALUES ('value1', '2011-01-25', 4)");
            Assert.assertEquals("Insert returned a number != 1", 1, count1);
            final int count2 = statement.executeUpdate(
                    "INSERT INTO table_name10(column_NAME1, COLUMN_name3, column_name2) " +
                            "VALUES ('value1', '2011-01-28', 4)");
            Assert.assertEquals("Insert returned a number != 1", 1, count2);
            final int count3 = statement.executeUpdate(
                    "INSERT INTO table_name10(column_name1, COLUMN_NAME3, column_NAME2)" +
                            " VALUES ('value2', '2011-02-11', 5)");
            Assert.assertEquals("Insert returned a number != 1", 1, count3);
            final int count4 = statement.executeUpdate("DELETE From table_name10");
            Assert.assertEquals("Delete returned wrong number", 3, count4);
            statement.close();
        } catch (final Throwable e) {
            e.printStackTrace();
        }
        connection.close();
    }

    @Test
    public void testJDBCTwelve() throws SQLException {
        final Connection connection = createUseDatabase("TestDB_Create");
        try {
            final Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE table_name11(column_name1 varchar, " +
                    "column_name2 int, column_name3 DATE)");
            final int count1 = statement.executeUpdate(
                    "INSERT INTO table_name11(column_NAME1, COLUMN_name3, column_name2) " +
                            "VALUES ('value1', '2011-01-25', 4)");
            Assert.assertEquals("Insert returned a number != 1", 1, count1);
            final int count2 = statement.executeUpdate(
                    "INSERT INTO table_name11(column_NAME1, COLUMN_name3, column_name2)" +
                            " VALUES ('value1', '2013-06-30', 4)");
            Assert.assertEquals("Insert returned a number != 1", 1, count2);
            final int count3 = statement.executeUpdate(
                    "INSERT INTO table_name11(column_name1, COLUMN_NAME3, column_NAME2) " +
                            "VALUES ('value2', '2013-07-03', 5)");
            Assert.assertEquals("Insert returned a number != 1", 1, count3);
            final int count4 = statement.executeUpdate("DELETE From table_name11 " +
                    " WHERE coLUmn_NAME3>'2011-01-25'");
            Assert.assertEquals("Delete returned wrong number", 2, count4);
            statement.close();
        } catch (final Throwable e) {
            e.printStackTrace();
        }
        connection.close();
    }

    @Test
    public void testJDBCThirteen() throws SQLException {
        final Connection connection = createUseDatabase("TestDB_Create");
        try {
            final Statement statement = connection.createStatement();
            statement
                    .execute("CREATE TABLE table_name12(column_name1 varchar, " +
                            "column_name2 int, column_name3 varchar)");
            final int count1 = statement.executeUpdate(
                    "INSERT INTO table_name12(column_NAME1, COLUMN_name3, column_name2)" +
                            " VALUES ('value1', 'value3', 4)");
            Assert.assertEquals("Insert returned a number != 1", 1, count1);
            final int count2 = statement.executeUpdate(
                    "INSERT INTO table_name12(column_NAME1, COLUMN_name3, column_name2)" +
                            " VALUES ('value1', 'value3', 4)");
            Assert.assertEquals("Insert returned a number != 1", 1, count2);
            final int count3 = statement.executeUpdate(
                    "INSERT INTO table_name12(column_name1, COLUMN_NAME3, column_NAME2) " +
                            "VALUES ('value2', 'value4', 5)");
            Assert.assertEquals("Insert returned a number != 1", 1, count3);
            final int count4 = statement.executeUpdate(
                    "INSERT INTO table_name12(column_name1, COLUMN_NAME3, column_NAME2) " +
                            "VALUES ('value5', 'value6', 6)");
            Assert.assertEquals("Insert returned a number != 1", 1, count4);
            final ResultSet result = statement.executeQuery("SELECT * From table_name12");
            int rows = 0;
            while (result.next())
                rows++;
            Assert.assertNotNull("Null result retruned", result);
            Assert.assertEquals("Wrong number of rows", 4, rows);
            Assert.assertEquals("Wrong number of columns", 3, result.getMetaData().getColumnCount());
            statement.close();
        } catch (final Throwable e) {
            e.printStackTrace();
        }
        connection.close();
    }

    @Test
    public void testJDBCFourteen() throws SQLException {
        final Connection connection = createUseDatabase("TestDB_Create");
        try {
            final Statement statement = connection.createStatement();
            statement
                    .execute("CREATE TABLE table_name13(column_name1 varchar, " +
                            "column_name2 int, column_name3 varchar)");
            final int count1 = statement.executeUpdate(
                    "INSERT INTO table_name13(column_NAME1, COLUMN_name3, column_name2)" +
                            " VALUES ('value1', 'value3', 4)");
            Assert.assertEquals("Insert returned a number != 1", 1, count1);
            final int count2 = statement.executeUpdate(
                    "INSERT INTO table_name13(column_NAME1, column_name2, COLUMN_name3) " +
                            "VALUES ('value1', 4, 'value3')");
            Assert.assertEquals("Insert returned a number != 1", 1, count2);
            final int count3 = statement.executeUpdate(
                    "INSERT INTO table_name13(column_name1, COLUMN_NAME3, column_NAME2) " +
                            "VALUES ('value2', 'value4', 5)");
            Assert.assertEquals("Insert returned a number != 1", 1, count3);
            final int count4 = statement.executeUpdate(
                    "INSERT INTO table_name13(column_name1, COLUMN_NAME3, column_NAME2) " +
                            "VALUES ('value5', 'value6', 6)");
            Assert.assertEquals("Insert returned a number != 1", 1, count4);
            final ResultSet result = statement.executeQuery("SELECT column_name1 FROM " +
                    "table_name13 WHERE coluMN_NAME2 < 5");
            int rows = 0;
            while (result.next())
                rows++;
            Assert.assertNotNull("Null result retruned", result);
            Assert.assertEquals("Wrong number of rows", 2, rows);
            Assert.assertEquals("Wrong number of columns", 1, result.getMetaData().getColumnCount());
            statement.close();
        } catch (final Throwable e) {
            e.printStackTrace();
        }
        connection.close();
    }

    @Test
    public void testJDBCFifteen() throws SQLException {
        final Connection connection = createUseDatabase("TestDB_Create");
        try {
            final Statement statement = connection.createStatement();
            statement
                    .execute("CREATE TABLE table_name13(column_name1 varchar, column_name2 int," +
                            " column_name3 varchar)");
            final int count1 = statement.executeUpdate(
                    "INSERT INTO table_name13(column_NAME1, COLUMN_name3, column_name2) " +
                            "VALUES ('value1', 'value3', 4)");
            Assert.assertEquals("Insert returned a number != 1", 1, count1);
            final boolean result1 = statement.execute(
                    "INSERT INTO table_name13(column_NAME1, column_name2, COLUMN_name3)" +
                            " VALUES ('value1', 8, 'value3')");
            Assert.assertFalse("Wrong return from 'execute' for insert record", result1);
            final int count3 = statement.executeUpdate(
                    "INSERT INTO table_name13(column_name1, COLUMN_NAME3, column_NAME2)" +
                            " VALUES ('value2', 'value4', 5)");
            Assert.assertEquals("Insert returned a number != 1", 1, count3);
            final int count4 = statement.executeUpdate(
                    "INSERT INTO table_name13(column_name1, COLUMN_NAME3, column_NAME2)" +
                            " VALUES ('value5', 'value6', 6)");
            Assert.assertEquals("Insert returned a number != 1", 1, count4);

            final boolean result2 = statement.execute("SELECT column_name1 FROM table_name13 " +
                    "WHERE coluMN_NAME2 = 8");
            Assert.assertTrue("Wrong return for select existing records", result2);

            final boolean result3 = statement.execute("SELECT column_name1 FROM table_name13" +
                    " WHERE coluMN_NAME2 > 100");
            Assert.assertFalse("Wrong return for select non existing records", result3);

            statement.close();
        } catch (final Throwable e) {
            e.printStackTrace();
        }
        connection.close();
    }

    @Test
    public void testJDBCSixteen() throws SQLException {
        final Connection connection = createUseDatabase("TestDB_Create");
        try {
            final Statement statement = connection.createStatement();
            statement
                    .execute("CREATE TABLE table_name13(column_name1 varchar, column_name2 " +
                            "int, column_name3 varchar)");
            final int count1 = statement.executeUpdate(
                    "INSERT INTO table_name13(column_NAME1, COLUMN_name3, column_name2) " +
                            "VALUES ('value1', 'value3', 4)");
            Assert.assertEquals("Insert returned a number != 1", 1, count1);
            final boolean result1 = statement.execute(
                    "INSERT INTO table_name13(column_NAME1, column_name2, COLUMN_name3) " +
                            "VALUES ('value1', 4, 'value5')");
            Assert.assertFalse("Wrong return for insert record", result1);
            final int count3 = statement.executeUpdate(
                    "INSERT INTO table_name13(column_name1, COLUMN_NAME3, column_NAME2) " +
                            "VALUES ('value2', 'value4', 5)");
            Assert.assertEquals("Insert returned a number != 1", 1, count3);
            final int count4 = statement.executeUpdate(
                    "INSERT INTO table_name13(column_name1, COLUMN_NAME3, column_NAME2) " +
                            "VALUES ('value5', 'value6', 6)");
            Assert.assertEquals("Insert returned a number != 1", 1, count4);

            final boolean result2 = statement.execute("SELECT DISTINCT column_name2 " +
                    "FROM table_name13");
            Assert.assertTrue("Wrong return for select existing records", result2);
            final ResultSet res1 = statement.getResultSet();

            int rows = 0;
            while (res1.next())
                rows++;
            Assert.assertEquals("Wrong number of rows", 3, rows);

            final boolean result3 = statement
                    .execute("SELECT DISTINCT column_name2, column_name3 FROM " +
                            "table_name13 WHERE coluMN_NAME2 < 5");
            Assert.assertTrue("Wrong return for select existing records", result3);
            final ResultSet res2 = statement.getResultSet();

            int rows2 = 0;
            while (res2.next())
                rows2++;
            Assert.assertEquals("Wrong number of rows", 2, rows2);

            statement.close();
        } catch (final Throwable e) {
            e.printStackTrace();
        }
        connection.close();
    }

    @Test
    public void testJDBCSeventeen() throws SQLException {
        final Connection connection = createUseDatabase("TestDB_Create");
        try {
            final Statement statement = connection.createStatement();
            statement
                    .execute("CREATE TABLE table_name13(column_name1 varchar, " +
                            "column_name2 int, column_name3 varchar)");
            final int count1 = statement.executeUpdate(
                    "INSERT INTO table_name13(column_NAME1, COLUMN_name3, column_name2)" +
                            " VALUES ('value1', 'value3', 4)");
            Assert.assertEquals("Insert returned a number != 1", 1, count1);
            final boolean result1 = statement.execute(
                    "INSERT INTO table_name13(column_NAME1, column_name2, COLUMN_name3)" +
                            " VALUES ('value1', 4, 'value5')");
            Assert.assertFalse("Wrong return for insert record", result1);
            final int count3 = statement.executeUpdate(
                    "INSERT INTO table_name13(column_name1, COLUMN_NAME3, column_NAME2)" +
                            " VALUES ('value2', 'value4', 5)");
            Assert.assertEquals("Insert returned a number != 1", 1, count3);
            final int count4 = statement.executeUpdate(
                    "INSERT INTO table_name13(column_name1, COLUMN_NAME3, column_NAME2)" +
                            " VALUES ('value5', 'value6', 6)");
            Assert.assertEquals("Insert returned a number != 1", 1, count4);

            final boolean result2 = statement.execute("ALTER TABLE table_name13 ADD " +
                    "column_name4 date");
            Assert.assertFalse("Wrong return for ALTER TABLE", result2);

            final boolean result3 = statement.execute("SELECT column_name4 FROM table_name13 " +
                    "WHERE coluMN_NAME2 = 5");
            Assert.assertTrue("Wrong return for select existing records", result3);
            final ResultSet res2 = statement.getResultSet();
            int rows2 = 0;
            while (res2.next())
                rows2++;
            Assert.assertEquals("Wrong number of rows", 1, rows2);

            while (res2.previous())
                ;
            res2.next();

            Assert.assertNull("Retrieved date is not null", res2.getDate("column_name4"));

            statement.close();
        } catch (final Throwable e) {
            e.printStackTrace();
        }
        connection.close();
    }

    @Test
    public void testJDBCEighteen() throws SQLException {
        final Connection connection = createUseDatabase("TestDB_Create");
        try {
            final Statement statement = connection.createStatement();
            statement
                    .execute("CREATE TABLE table_name13(column_name1 varchar, column_name2 int," +
                            " column_name3 varchar)");
            final int count1 = statement.executeUpdate(
                    "INSERT INTO table_name13(column_NAME1, COLUMN_name3, column_name2) VALUES " +
                            "('value1', 'value3', 4)");
            Assert.assertEquals("Insert returned a number != 1", 1, count1);
            final boolean result1 = statement.execute(
                    "INSERT INTO table_name13(column_NAME1, column_name2, COLUMN_name3) VALUES " +
                            "('value1', 4, 'value5')");
            Assert.assertFalse("Wrong return for insert record", result1);
            final int count3 = statement.executeUpdate(
                    "INSERT INTO table_name13(column_name1, COLUMN_NAME3, column_NAME2) VALUES " +
                            "('value2', 'value4', 5)");
            Assert.assertEquals("Insert returned a number != 1", 1, count3);
            final int count4 = statement.executeUpdate(
                    "INSERT INTO table_name13(column_name1, COLUMN_NAME3, column_NAME2) VALUES " +
                            "('value5', 'value6', 6)");
            Assert.assertEquals("Insert returned a number != 1", 1, count4);

            final boolean result3 = statement
                    .execute("SELECT * FROM table_name13 ORDER BY column_name2 ASC, COLUMN_name3 DESC");
            Assert.assertTrue("Wrong return for select UNION existing records", result3);
            final ResultSet res2 = statement.getResultSet();
            int rows2 = 0;
            while (res2.next())
                rows2++;
            Assert.assertEquals("Wrong number of rows", 4, rows2);
            while (res2.previous()) ;
            res2.next();
            Assert.assertEquals("Wrong order of rows", 4, res2.getInt("column_name2"));
            Assert.assertEquals("Wrong order of rows", "value5", res2.getString("column_name3"));

            res2.next();
            Assert.assertEquals("Wrong order of rows", 4, res2.getInt("column_name2"));
            Assert.assertEquals("Wrong order of rows", "value3", res2.getString("column_name3"));

            res2.next();
            Assert.assertEquals("Wrong order of rows", 5, res2.getInt("column_name2"));

            statement.close();
        } catch (final Throwable e) {
            e.printStackTrace();
        }
        connection.close();
    }

    @Test
    public void testJDBCNineteen() throws SQLException {
        final Connection connection = createUseDatabase("TestDB_Create");
        try {
            final Statement statement = connection.createStatement();
            statement
                    .execute("CREATE TABLE table_name13(column_name1 varchar, column_name2 " +
                            "int, column_name3 varchar)");
            final int count1 = statement.executeUpdate(
                    "INSERT INTO table_name13(column_NAME1, COLUMN_name3, column_name2) " +
                            "VALUES ('value1', 'value3', 4)");
            Assert.assertEquals("Insert returned a number != 1", 1, count1);
            final boolean result1 = statement.execute(
                    "INSERT INTO table_name13(column_NAME1, column_name2, COLUMN_name3) " +
                            "VALUES ('value1', 4, 'value5')");
            Assert.assertFalse("Wrong return for insert record", result1);
            final int count3 = statement.executeUpdate(
                    "INSERT INTO table_name13(column_name1, COLUMN_NAME3, column_NAME2)" +
                            " VALUES ('value2', 'value4', 5)");
            Assert.assertEquals("Insert returned a number != 1", 1, count3);
            final int count4 = statement.executeUpdate(
                    "INSERT INTO table_name13(column_name1, COLUMN_NAME3, column_NAME2) " +
                            "VALUES ('value5', 'value6', 6)");
            Assert.assertEquals("Insert returned a number != 1", 1, count4);
            final boolean result3 = statement
                    .execute("SELECT * FROM table_name13 ORDER BY column_name2 ASC, COLUMN_name3 DESC");
            Assert.assertTrue("Wrong return for select UNION existing records", result3);
            final ResultSet res2 = statement.getResultSet();
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
        } catch (final Throwable e) {
            e.printStackTrace();
        }
        connection.close();
    }


}