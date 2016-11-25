package dbms.test.SQLParserTesting;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import dbms.exception.SyntaxErrorException;
import dbms.sqlparser.SQLParser;
import dbms.sqlparser.sqlInterpreter.Expression;
import dbms.sqlparser.sqlInterpreter.rules.InsertIntoTable;

public class InsertTest extends SqlParserRef {

	private final SQLParser sqlParserObjTest = super.getSqlParserReference();
	private Expression insertObjAct;

	/*
	 * This test checks the validity of the syntax of the SQL command in
	 * different forms.
	 */

	@Test
	public void testInsertSyntaxValidateOne() {
		try {
			sqlParserObjTest.parse("INSERT INTO TABLE_NAME (COL1,COL2) VALUES ('VAL1','VAL2');");
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");				 
		}
		try {
			sqlParserObjTest
					.parse("        INSERT            INTO        TABLE_NAME           (       COL1  ,            COL2       )             VALUES                   (          'VAL1'         ,      "
							+ "             'VAL2'             )             ;             ");
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");			 
		}
		try {
			sqlParserObjTest
					.parse("        InSeRT            InTo        TaBlE_NAmE           (       CoL1  ,            COl2       )             VaLueS                   (          'VaL1'         ,      "
							+ "             'VAl2'             )             ;             ");
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");				 
		}
	}

	/*
	 * These tests check that the catch clause will get the
	 * SyntaxErrorException.
	 */

	@Test
	public void testInsertSyntaxValidateTwo() {
		try {
			sqlParserObjTest.parse("INSERT INTO TABLE_NAME (COL1 COL2) VALUES ('VAL1','VAL2');");
			fail("SyntaxErrorException thrown or AssertionError occured!");	
		} catch (SyntaxErrorException e) {		 
		}
		try {
			sqlParserObjTest
					.parse("        INSERT            INTO        TABLE_NAME           (       COL1  ,            COL2       )             VALUES                   (          'VAL1'         ,      "
							+ "             'VAL2             )             ;             ");
			fail("SyntaxErrorException thrown or AssertionError occured!");	
		} catch (SyntaxErrorException e) {		 
		}
		try {
			sqlParserObjTest
					.parse("        InSeRT            InTo                 (       CoL1  ,            COl2       )             VaLueS                   (          'VaL1'         ,      "
							+ "             'VAl2'             )             ;             ");
			fail("SyntaxErrorException thrown or AssertionError occured!");	
		} catch (SyntaxErrorException e) {			 
		}
	}

	@Test
	public void testInsertSyntaxValidateThree() {
		try {
			sqlParserObjTest.parse("INSERT INTO TABLE_NAME (COL1 , COL2 VALUES ('VAL1','VAL2');");
			fail("SyntaxErrorException thrown or AssertionError occured!");	
		} catch (SyntaxErrorException e) {		 
		}
		try {
			sqlParserObjTest
					.parse("        INSERT            INTO        TABLE_NAME           (       COL1  ,            COL2       )                            (          'VAL1'         ,      "
							+ "             'VAL2'             )             ;             ");
			fail("SyntaxErrorException thrown or AssertionError occured!");	
		} catch (SyntaxErrorException e) {		 
		}
		try {
			sqlParserObjTest
					.parse("                    InTo        table_name         (       CoL1  ,            COl2       )             VaLueS                   (          'VaL1'         ,      "
							+ "             'VAl2'             )             ;             ");
			fail("SyntaxErrorException thrown or AssertionError occured!");	
		} catch (SyntaxErrorException e) {		 
		}
	}

	/*
	 * These tests check the correctness of the parsed data from the INSERT SQL
	 * command.
	 */

	@Test
	public void testInsertParsingValidateOne() {
		try {
			insertObjAct = sqlParserObjTest.parse("INSERT INTO TABLE_NAME (col1,col2) VALUES ('VAL1','VAL2');");
			assertEquals("table_name", ((InsertIntoTable) insertObjAct).getTableName().toLowerCase());
			Map<String, Object> entryMapCpy = new HashMap<String, Object>();
			entryMapCpy.put("col1", "VAL1");
			entryMapCpy.put("col2", "VAL2");
			assertTrue(entryMapCpy.equals(((InsertIntoTable) insertObjAct).getEntryMap()));
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");			 
		}
	}

	@Test
	public void testInsertParsingValidateTwo() {
		try {
			insertObjAct = sqlParserObjTest
					.parse("        INSERT            INTO        COUNTRIES_WORLD           (       COL1  ,            COL2       )             VALUES                   (          'VAL1'         ,      "
							+ "             'VAL2'             )             ;             ");
			assertEquals("countries_world", ((InsertIntoTable) insertObjAct).getTableName().toLowerCase());
			Map<String, Object> entryMapCpy = new HashMap<String, Object>();
			entryMapCpy.put("COL1", "VAL1");
			entryMapCpy.put("COL2", "VAL2");
			assertTrue(entryMapCpy.equals(((InsertIntoTable) insertObjAct).getEntryMap()));
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");			 
		}
	}

	@Test
	public void testInsertParsingValidateThree() {
		try {
			insertObjAct = sqlParserObjTest
					.parse("        InSeRT            InTo        TaBlE_NAmE           (       col1  ,            col2      )             VaLueS                   (         5        ,      "
							+ "             'VAl2'             )             ;             ");
			assertEquals("table_name", ((InsertIntoTable) insertObjAct).getTableName().toLowerCase());
			Map<String, Object> entryMapCpy = new HashMap<String, Object>();
			entryMapCpy.put("col1", 5);
			entryMapCpy.put("col2", "VAl2");
			assertTrue(entryMapCpy.equals(((InsertIntoTable) insertObjAct).getEntryMap()));
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");			 
		}
	}
	
	
	@Test
	public void testInsertParsingValidateFour() {
		try {
			insertObjAct = sqlParserObjTest
					.parse("        InSeRT            InTo        TaBlE_NAmE           (       CoL1  ,            num1       )             VaLueS                   (         5        ,      "
							+ "             888             )             ;             ");
			assertEquals("table_name", ((InsertIntoTable) insertObjAct).getTableName().toLowerCase());
			Map<String, Object> entryMapCpy = new HashMap<String, Object>();
			entryMapCpy.put("CoL1", 5);
			entryMapCpy.put("num1", 888);
			assertTrue(entryMapCpy.equals(((InsertIntoTable) insertObjAct).getEntryMap()));
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");	
		}
	}
}
