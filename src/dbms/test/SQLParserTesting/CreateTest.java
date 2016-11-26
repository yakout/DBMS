package dbms.test.SQLParserTesting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import dbms.exception.SyntaxErrorException;
import dbms.sqlparser.SQLParser;
import dbms.sqlparser.sqlInterpreter.Expression;
import dbms.sqlparser.sqlInterpreter.rules.CreateDatabase;
import dbms.sqlparser.sqlInterpreter.rules.CreateTable;

public class CreateTest extends SqlParserRef {

	private final SQLParser sqlParserObjTest = super.getSqlParserReference();
	private Expression createDbObj;
	private Expression createTblObj;

	/*
	 * These tests check the validity of Syntax of CREATE DATABASE SQL command.
	 */
	@Test
	public void testCreateDbSyntaxValidateOne() {

		try {
			sqlParserObjTest.parse("CREATE DATABASE DATABASE_NAME;");
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}

		try {
			sqlParserObjTest.parse("CREATE DATABASE DATABASE_6565;");
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}

		try {
			sqlParserObjTest.parse("CREATE DATABASE 6565;");
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}

	}

	/*
	 * This test checks that the catch clause will get the SyntaxErrorException.
	 */

	@Test
	public void testCreateDbSyntaxValidateTwo() {

		try {
			sqlParserObjTest.parse("CREATE DATABASE DATABASE_NAME");
			fail("SyntaxErrorException thrown or AssertionError occured!");
		} catch (SyntaxErrorException e) {

		}

		try {
			sqlParserObjTest.parse("CREATE DATABASE ;");
			fail("SyntaxErrorException thrown or AssertionError occured!");
		} catch (SyntaxErrorException e) {

		}

		try {
			sqlParserObjTest.parse("CREATE  6565;");
			fail("SyntaxErrorException thrown or AssertionError occured!");
		} catch (SyntaxErrorException e) {
		}

	}

	@Test
	public void testCreateDbSyntaxValidateThree() {

		try {
			sqlParserObjTest.parse("CR DATABASE DATABASE_NAME");
			fail("SyntaxErrorException thrown or AssertionError occured!");
		} catch (SyntaxErrorException e) {
		}

		try {
			sqlParserObjTest.parse("CREATE DATABASE *****;");
			fail("SyntaxErrorException thrown or AssertionError occured!");
		} catch (SyntaxErrorException e) {
		}

		/*
		 * Check the validity of CREATE DATABASE SQL command with multiple white spaces.
		 */

		try {
			sqlParserObjTest.parse("        CREATE              DATABASE                   DATABASE_NAME          ;    ");
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}

	}
	/*
	 * These tests check the case insensitivity of the CREATE DATABASE SQL command.
	 */

	@Test
	public void testCreateDbSyntaxValidateFour() {

		try {
			sqlParserObjTest.parse("CREaTe DaTabASE DATaBAsE_NAmE;");
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}

		try {
			sqlParserObjTest.parse("          CREaTe          DaTabASE            DATaBAsE_NAmE;          ");
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}
	}

	/*
	 * These tests check the correctness of the parsed data from the SQL command.
	 */

	@Test
	public void testCreateDbParsingValidateOne() {

		try {
			createDbObj = sqlParserObjTest.parse("CREATE DATABASE DATABASE_NAME;");
			assertEquals("database_name", ((CreateDatabase) createDbObj).getDbName().toLowerCase());
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}

		try {
			createDbObj = sqlParserObjTest.parse("CREATE DATABASE DATABASE_6565;");
			assertEquals("database_6565", ((CreateDatabase) createDbObj).getDbName().toLowerCase());
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}

		try {
			createDbObj = sqlParserObjTest.parse("      CReaTe         DaTaBaSe         DATABASE_NAME885   ;     ");
			assertEquals("database_name885", ((CreateDatabase) createDbObj).getDbName().toLowerCase());
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}

	}

	/*
	 * These tests check the validity of Syntax of CREATE TABLE SQL command.
	 */

	@Test
	public void testCreateTblSyntaxValidateOne() {

		try {
			sqlParserObjTest.parse("CREATE TABLE TABLE_NAME (COL1 INT, COL2 VARCHAR);");
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}

		try {
			sqlParserObjTest.parse("          CREATE          TABLE    TABLE_NAME  (    COL1       INT     ,   COL2        VARCHAR       )      ;       ");
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}

		try {
			sqlParserObjTest.parse("          CrEaTE          TAbLe    TAblE_NAmE  (    bOL1       InT     ,   COl2        VARcHAr       )      ;       ");
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}

	}


	/*
	 * This test checks that the catch clause will get the SyntaxErrorException.
	 */

	@Test
	public void testCreateTblSyntaxValidateTwo() {

		try {
			sqlParserObjTest.parse("CREATE TABLE  (COL1 INT, COL2 VARCHAR);");
			fail("SyntaxErrorException thrown or AssertionError occured!");
		} catch (SyntaxErrorException e) {
		}

		try {
			sqlParserObjTest.parse("          CREATE          TABLE    TABLE_NAME  (    COL1       INT     ,   COL2        VARCHAR       )             ");
			fail("SyntaxErrorException thrown or AssertionError occured!");
		} catch (SyntaxErrorException e) {
		}

		try {
			sqlParserObjTest.parse("          CrEaTE          TAbLe    TAblE_NAmE  (    bOL1       InT     ,   COl2              )      ;       ");
			fail("SyntaxErrorException thrown or AssertionError occured!");
		} catch (SyntaxErrorException e) {
		}

	}

	/*
	 * These tests check the correctness of the parsed data from the SQL command.
	 */
	@Test
	public void testCreateTblParsingValidateOne() {

		try {
			createTblObj = sqlParserObjTest.parse("CREATE TABLE TABLE_NAME (COL1 INT, COL2 VARCHAR);");
			assertEquals("table_name", ((CreateTable) createTblObj).getTableName().toLowerCase());
			Map<String,Class> columnsCpy = new  HashMap<String, Class>();
			columnsCpy.put("COL1", Integer.class);
			columnsCpy.put("COL2", String.class);
			System.out.println("columns ;" + ((CreateTable)createTblObj).getColumns());
			assertTrue(columnsCpy.equals(((CreateTable)createTblObj).getColumns()));
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}

		try {
			createTblObj = sqlParserObjTest.parse("          CREATE          TABLE    TABLE_NAME999  (    COL1       VARCHAR     ,   COL2        VARCHAR       )      ;       ");
			assertEquals("table_name999", ((CreateTable) createTblObj).getTableName().toLowerCase());
			Map<String,Class> columnsCpy = new  HashMap<String, Class>();
			columnsCpy.put("COL1", String.class);
			columnsCpy.put("COL2", String.class);
			assertTrue(columnsCpy.equals(((CreateTable)createTblObj).getColumns()));
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}

		try {
			createTblObj = sqlParserObjTest.parse("          CrEaTE          TAbLe    COUNTRIES  (    bOL1       InT     ,   COl2        inT       )      ;       ");
			assertEquals("countries", ((CreateTable) createTblObj).getTableName().toLowerCase());
			Map<String,Class> columnsCpy = new  HashMap<String, Class>();
			columnsCpy.put("bOL1", Integer.class);
			columnsCpy.put("COl2", Integer.class);
			assertTrue(columnsCpy.equals(((CreateTable)createTblObj).getColumns()));
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}

	}
}
