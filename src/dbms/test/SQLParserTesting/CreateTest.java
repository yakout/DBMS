package dbms.test.SQLParserTesting;

import dbms.datatypes.*;
import dbms.exception.SyntaxErrorException;
import dbms.sqlparser.SQLParser;
import dbms.sqlparser.sqlInterpreter.rules.CreateDatabase;
import dbms.sqlparser.sqlInterpreter.rules.CreateTable;
import dbms.sqlparser.sqlInterpreter.rules.Expression;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * JUnit for create testing.
 */
public class CreateTest extends SqlParserRef {
    private final SQLParser sqlParserObjTest = super.getSqlParserReference();
    private Expression createDbObj;
    private Expression createTblObj;

	/*
     * The time taken by this tests include the time consumed by the Validate
      * method.
	 */

    /*
     * These tests check the validity of Syntax of CREATE DATABASE SQL command.
     */
    @Test
    public void testCreateDbSyntaxValidateOne() {

        try {
            sqlParserObjTest.parse("CREATE DATABASE DATABASE_NAME;");
        } catch (SyntaxErrorException e) {
            e.printStackTrace();
            fail("SyntaxErrorException thrown or AssertionError occurred!");
        }

        try {
            sqlParserObjTest.parse("CREATE DATABASE DATABASE_6565;");
        } catch (SyntaxErrorException e) {
            e.printStackTrace();
            fail("SyntaxErrorException thrown or AssertionError occurred!");
        }

        try {
            sqlParserObjTest.parse("CREATE DATABASE 6565;");
        } catch (SyntaxErrorException e) {
            e.printStackTrace();
            fail("SyntaxErrorException thrown or AssertionError occurred!");
        }

    }

	/*
     * This test checks that the catch clause will get the
	 * SyntaxErrorException.
	 */

    @Test
    public void testCreateDbSyntaxValidateTwo() {

        try {
            sqlParserObjTest.parse("CREATE DATABASE DATABASE_NAME");
            fail("SyntaxErrorException thrown or AssertionError occurred!");
        } catch (SyntaxErrorException e) {
            e.printStackTrace();
        }

        try {
            sqlParserObjTest.parse("CREATE DATABASE ;");
            fail("SyntaxErrorException thrown or AssertionError occurred!");
        } catch (SyntaxErrorException e) {
            e.printStackTrace();
        }

        try {
            sqlParserObjTest.parse("CREATE  6565;");
            fail("SyntaxErrorException thrown or AssertionError occurred!");
        } catch (SyntaxErrorException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testCreateDbSyntaxValidateThree() {

        try {
            sqlParserObjTest.parse("CR DATABASE DATABASE_NAME");
            fail("SyntaxErrorException thrown or AssertionError occurred!");
        } catch (SyntaxErrorException e) {
            e.printStackTrace();
        }

        try {
            sqlParserObjTest.parse("CREATE DATABASE *****;");
            fail("SyntaxErrorException thrown or AssertionError occurred!");
        } catch (SyntaxErrorException e) {
            e.printStackTrace();
        }
		
		/*
		 * Check the validity of CREATE DATABASE SQL command with multiple
		 * white spaces.
		 */

        try {
            sqlParserObjTest.parse("        CREATE              DATABASE     "
                    + "       "
                    + "       DATABASE_NAME          ;    ");
        } catch (SyntaxErrorException e) {
            e.printStackTrace();
            fail("SyntaxErrorException thrown or AssertionError occurred!");
        }

    }
	/*
	 * These tests check the case insensitivity of the CREATE DATABASE SQL
	 * command.
	 */

    @Test
    public void testCreateDbSyntaxValidateFour() {

        try {
            sqlParserObjTest.parse("CREaTe DaTabASE DATaBAsE_NAmE;");
        } catch (SyntaxErrorException e) {
            e.printStackTrace();
            fail("SyntaxErrorException thrown or AssertionError occurred!");
        }

        try {
            sqlParserObjTest.parse("          CREaTe          DaTabASE       "
                    + "     DATaBAsE_NAmE;          ");
        } catch (SyntaxErrorException e) {
            e.printStackTrace();
            fail("SyntaxErrorException thrown or AssertionError occurred!");
        }
    }
	
	/*
	 * These tests check the correctness of the parsed data from the SQL
	 * command.
	 */

    @Test
    public void testCreateDbParsingValidateOne() {

        try {
            createDbObj = sqlParserObjTest.parse("CREATE DATABASE "
                    + "DATABASE_NAME;");
            assertEquals("database_name", ((CreateDatabase) createDbObj)
                    .getDbName().toLowerCase());
        } catch (SyntaxErrorException e) {
            e.printStackTrace();
            fail("SyntaxErrorException thrown or AssertionError occurred!");
        }

        try {
            createDbObj = sqlParserObjTest.parse("CREATE DATABASE "
                    + "DATABASE_6565;");
            assertEquals("database_6565", ((CreateDatabase) createDbObj)
                    .getDbName().toLowerCase());
        } catch (SyntaxErrorException e) {
            e.printStackTrace();
            fail("SyntaxErrorException thrown or AssertionError occurred!");
        }

        try {
            createDbObj = sqlParserObjTest.parse("      CReaTe         "
                    + "DaTaBaSe         DATABASE_NAME885   ;     ");
            assertEquals("database_name885", ((CreateDatabase) createDbObj)
                    .getDbName().toLowerCase());
        } catch (SyntaxErrorException e) {
            e.printStackTrace();
            fail("SyntaxErrorException thrown or AssertionError occurred!");
        }

    }
	
	/*
	 * These tests check the validity of Syntax of CREATE TABLE SQL command.
	 */

    @Test
    public void testCreateTblSyntaxValidateOne() {

        try {
            sqlParserObjTest.parse("CREATE TABLE TABLE_NAME (COL1 INT, COL2 "
                    + "VARCHAR);");
        } catch (SyntaxErrorException e) {
            e.printStackTrace();
            fail("SyntaxErrorException thrown or AssertionError occurred!");
        }

        try {
            sqlParserObjTest.parse("          CREATE          TABLE    "
                    + "TABLE_NAME  (    COL1       INT     ,   COL2        "
                    + "VARCHAR       )      ;       ");
        } catch (SyntaxErrorException e) {
            e.printStackTrace();
            fail("SyntaxErrorException thrown or AssertionError occurred!");
        }

        try {
            sqlParserObjTest.parse("          CrEaTE          TAbLe    "
                    + "TAblE_NAmE  (    bOL1       InT     ,   COl2        "
                    + "VARcHAr       )      ;       ");
        } catch (SyntaxErrorException e) {
            e.printStackTrace();
            fail("SyntaxErrorException thrown or AssertionError occurred!");
        }

    }
	
	
	/*
	 * This test checks that the catch clause will get the
	 * SyntaxErrorException.
	 */

    @Test
    public void testCreateTblSyntaxValidateTwo() {

        try {
            sqlParserObjTest.parse("CREATE TABLE  (COL1 INT, COL2 VARCHAR);");
            fail("SyntaxErrorException thrown or AssertionError occurred!");
        } catch (SyntaxErrorException e) {
            e.printStackTrace();
        }

        try {
            sqlParserObjTest.parse("          CREATE          TABLE    "
                    + "TABLE_NAME  (    COL1       INT     ,   COL2        "
                    + "VARCHAR       )             ");
            fail("SyntaxErrorException thrown or AssertionError occurred!");
        } catch (SyntaxErrorException e) {
            e.printStackTrace();
        }

        try {
            sqlParserObjTest.parse("          CrEaTE          TAbLe    "
                    + "TAblE_NAmE  (    bOL1       InT     ,   COl2          "
                    + "    )      ;       ");
            fail("SyntaxErrorException thrown or AssertionError occurred!");
        } catch (SyntaxErrorException e) {
            e.printStackTrace();
        }

    }

    /*
     * These tests check the correctness of the parsed data from the SQL
     * command.
     */
    @Test
    public void testCreateTblParsingValidateOne() {

        try {
            createTblObj = sqlParserObjTest.parse("CREATE TABLE TABLE_NAME "
                    + "(COL1 INT, COL2 VARCHAR);");
            assertEquals("table_name", ((CreateTable) createTblObj)
                    .getTableName().toLowerCase());
            Map<String, Class<? extends DBDatatype>> columnsCpy = new
                    LinkedHashMap<>();
            columnsCpy.put("col1", DBInteger.class);
            columnsCpy.put("col2", DBString.class);
            assertTrue(columnsCpy.equals(((CreateTable) createTblObj)
                    .getColumns()));
        } catch (SyntaxErrorException e) {
            e.printStackTrace();
            fail("SyntaxErrorException thrown or AssertionError occurred!");
        }

        try {
            createTblObj = sqlParserObjTest.parse("          CREATE          "
                    + "TABLE   "
                    + " TABLE_NAME999  (    COL1       VARCHAR     ,   COL2  "
                    + "      VARCHAR       )      ;       ");
            assertEquals("table_name999", ((CreateTable) createTblObj)
                    .getTableName().toLowerCase());
            Map<String, Class<? extends DBDatatype>> columnsCpy = new
                    HashMap<>();
            columnsCpy.put("col1", DBString.class);
            columnsCpy.put("col2", DBString.class);
            assertTrue(columnsCpy.equals(((CreateTable) createTblObj)
                    .getColumns()));
        } catch (SyntaxErrorException e) {
            e.printStackTrace();
            fail("SyntaxErrorException thrown or AssertionError occurred!");
        }

        try {
            createTblObj = sqlParserObjTest.parse("          CrEaTE          "
                    + "TAbLe    "
                    + "COUNTRIES  (    bOL1       InT     ,   COl2        inT"
                    + "       )      ;       ");
            assertEquals("countries", ((CreateTable) createTblObj)
                    .getTableName().toLowerCase());
            Map<String, Class> columnsCpy = new HashMap<>();
            columnsCpy.put("bol1", DBInteger.class);
            columnsCpy.put("col2", DBInteger.class);
            assertTrue(columnsCpy.equals(((CreateTable) createTblObj)
                    .getColumns()));
        } catch (SyntaxErrorException e) {
            e.printStackTrace();
            fail("SyntaxErrorException thrown or AssertionError occurred!");
        }

    }

    @Test
    public void testCreateTblParsingValidateTwo() {

        try {
            createTblObj = sqlParserObjTest.parse("CREATE TABLE TABLE_NAME "
                    + "(COL1 DATE, COL2 VARCHAR);");
            assertEquals("table_name", ((CreateTable) createTblObj)
                    .getTableName().toLowerCase());
            Map<String, Class<? extends DBDatatype>> columnsCpy = new
                    LinkedHashMap<>();
            columnsCpy.put("col1", DBDate.class);
            columnsCpy.put("col2", DBString.class);
            assertTrue(columnsCpy.equals(((CreateTable) createTblObj)
                    .getColumns()));
        } catch (SyntaxErrorException e) {
            e.printStackTrace();
            fail("SyntaxErrorException thrown or AssertionError occurred!");
        }

        try {
            createTblObj = sqlParserObjTest.parse("          CREATE          "
                    + "TABLE   "
                    + " TABLE_NAME999  (    COL1       DaTE     ,   COL2     "
                    + "   FLoAt       )      ;       ");
            assertEquals("table_name999", ((CreateTable) createTblObj)
                    .getTableName().toLowerCase());
            Map<String, Class<? extends DBDatatype>> columnsCpy = new
                    HashMap<>();
            columnsCpy.put("col1", DBDate.class);
            columnsCpy.put("col2", DBFloat.class);
            assertTrue(columnsCpy.equals(((CreateTable) createTblObj)
                    .getColumns()));
        } catch (SyntaxErrorException e) {
            e.printStackTrace();
            fail("SyntaxErrorException thrown or AssertionError occurred!");
        }

        try {
            createTblObj = sqlParserObjTest.parse("          CrEaTE          "
                    + "TAbLe    "
                    + "COUNTRIES  (    bOL1       FloAt     ,   COl2        "
                    + "varchar       )      ;       ");
            assertEquals("countries", ((CreateTable) createTblObj)
                    .getTableName().toLowerCase());
            Map<String, Class> columnsCpy = new HashMap<>();
            columnsCpy.put("bol1", DBFloat.class);
            columnsCpy.put("col2", DBString.class);
            assertTrue(columnsCpy.equals(((CreateTable) createTblObj)
                    .getColumns()));
        } catch (SyntaxErrorException e) {
            e.printStackTrace();
            fail("SyntaxErrorException thrown or AssertionError occurred!");
        }
    }
}
