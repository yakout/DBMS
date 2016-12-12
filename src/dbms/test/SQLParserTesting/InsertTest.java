package dbms.test.SQLParserTesting;

import dbms.datatypes.DBDatatype;
import dbms.datatypes.DatatypeFactory;
import dbms.exception.SyntaxErrorException;
import dbms.sqlparser.SQLParser;
import dbms.sqlparser.sqlInterpreter.rules.Expression;
import dbms.sqlparser.sqlInterpreter.rules.InsertIntoTable;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class InsertTest extends SqlParserRef {

	private final SQLParser sqlParserObjTest = super.getSqlParserReference();
	private Expression insertObjAct;
	
	/*
	 * The time taken by this tests include the time consumed by the Validate method.
	 */

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
	
	@Test
	public void testInsertSyntaxValidateFour() {
		try {
			sqlParserObjTest.parse("INSERT INTO TABLE_NAME (COL1 , COL2) VALUES (\"VAL1',\"VAL2\");");
			fail("SyntaxErrorException thrown or AssertionError occured!");	
		} catch (SyntaxErrorException e) {		 
		}
		try {
			sqlParserObjTest
					.parse("        INSERT            INTO        TABLE_NAME           (       COL1  ,            COL2       )              values              (          'VAL1'         ,      "
							+ "             'VAL2\"             )             ;             ");
			fail("SyntaxErrorException thrown or AssertionError occured!");	
		} catch (SyntaxErrorException e) {		 
		}
		try {
			sqlParserObjTest
					.parse("         insert           InTo        table_name         (       CoL1  ,            COl2       )             VaLueS                   (          \"VaL1'         ,      "
							+ "             'VAl2'             )             ;             ");
			fail("SyntaxErrorException thrown or AssertionError occured!");	
		} catch (SyntaxErrorException e) {		 
		}
	}
	
	
	@Test
	public void testInsertSyntaxValidateFive() {
		try {
			sqlParserObjTest.parse("INSERT INTO TABLE_NAME (COL1,COL2) VALUES (\"VAL1\",'VAL2');");
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");				 
		}
		try {
			sqlParserObjTest
					.parse("        INSERT            INTO        TABLE_NAME           (       COL1  ,            COL2       )             VALUES                   (         \"VAL1\"         ,      "
							+ "             50000             )             ;             ");
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");			 
		}
		try {
			sqlParserObjTest
					.parse("        InSeRT            InTo        TaBlE_NAmE           (       CoL1  ,            COl2       )             VaLueS                   (          500         ,      "
							+ "             \"VAl2\"             )             ;             ");
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");				 
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
			Map<String, DBDatatype> entryMapCpy = new HashMap<>();
			entryMapCpy.put("col1", DatatypeFactory.convertToDataType("VAL1"));
			entryMapCpy.put("col2", DatatypeFactory.convertToDataType("VAL2"));
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
			Map<String, DBDatatype> entryMapCpy = new HashMap<>();
			entryMapCpy.put("col1", DatatypeFactory.convertToDataType("VAL1"));
			entryMapCpy.put("col2", DatatypeFactory.convertToDataType("VAL2"));
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
			Map<String, DBDatatype> entryMapCpy = new HashMap<>();
			entryMapCpy.put("col1", DatatypeFactory.convertToDataType(5));
			entryMapCpy.put("col2", DatatypeFactory.convertToDataType("VAl2"));
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
			Map<String, DBDatatype> entryMapCpy = new HashMap<>();
			entryMapCpy.put("col1", DatatypeFactory.convertToDataType(5));
			entryMapCpy.put("num1", DatatypeFactory.convertToDataType(888));
			assertTrue(entryMapCpy.equals(((InsertIntoTable) insertObjAct).getEntryMap()));
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");	
		}
	}
	
	@Test
	public void testInsertParsingValidateFive() {
		try {
			insertObjAct = sqlParserObjTest
					.parse("        InSeRT            InTo        TaBlE_NAmE           (       CoL1  ,            num1       )             VaLueS                   (         5        ,      "
							+ "             \"HELLO\"             )             ;             ");
			assertEquals("table_name", ((InsertIntoTable) insertObjAct).getTableName().toLowerCase());
			Map<String, DBDatatype> entryMapCpy = new HashMap<>();
			entryMapCpy.put("col1", DatatypeFactory.convertToDataType(5));
			entryMapCpy.put("num1", DatatypeFactory.convertToDataType("HELLO"));
			assertTrue(entryMapCpy.equals(((InsertIntoTable) insertObjAct).getEntryMap()));
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");	
		}
	}
	
	@Test
	public void testInsertParsingValidateSix() {
		try {
			insertObjAct = sqlParserObjTest
					.parse("        InSeRT            InTo        TaBlE_NAmE           (       CoL1  ,            num1       )             VaLueS                   (         'Hey'        ,      "
							+ "             \"HELLO\"             )             ;             ");
			assertEquals("table_name", ((InsertIntoTable) insertObjAct).getTableName().toLowerCase());
			Map<String, DBDatatype> entryMapCpy = new HashMap<>();
			entryMapCpy.put("col1", DatatypeFactory.convertToDataType("Hey"));
			entryMapCpy.put("num1", DatatypeFactory.convertToDataType("HELLO"));
			assertTrue(entryMapCpy.equals(((InsertIntoTable) insertObjAct).getEntryMap()));
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");	
		}
	}
}
