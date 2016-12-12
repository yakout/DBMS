package dbms.test.SQLParserTesting;

import dbms.exception.SyntaxErrorException;
import dbms.sqlparser.SQLParser;
import dbms.sqlparser.sqlInterpreter.rules.DropDatabase;
import dbms.sqlparser.sqlInterpreter.rules.DropTable;
import dbms.sqlparser.sqlInterpreter.rules.Expression;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class DropTest extends SqlParserRef {

	private final SQLParser sqlParserObjTest = super.getSqlParserReference();
	private Expression dropDatabaseObjAct;
	private Expression dropTableObjAct;
	
	/*
	 * The time taken by this tests include the time consumed by the Validate method.
	 */

	/*
	 * This test checks the DROP DATABASE SQL command validation
	 */

	@Test
	public void testDropDatabaseSyntaxValidateOne() {

		try {
			sqlParserObjTest.parse("DROP DATABASE DATABASE_NAME;");
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occurred!");
		}

		/*
		 * This test checks the SyntaxErrorException with invalid SQL command
		 */

		try {
			sqlParserObjTest.parse(";EMAN_ESABATAD ESABATAD PORD");
			fail("Syntax error exception hasn't been thrown!");
		} catch (SyntaxErrorException e) {
		}

	}

	@Test
	public void testDropDatabaseSyntaxValidateTwo() {

		/*
		 * This test checks that the catch clause will get the
		 * SyntaxErrorException not IndexOutOfBoundsException due to validate
		 * method in SQLParser class
		 */

		try {
			sqlParserObjTest.parse("d;");
			fail("Syntax error exception hasn't been thrown!");
		} catch (SyntaxErrorException e) {
		}

		/*
		 * This test checks the case insensitivity of the SQL command
		 */
		try {
			sqlParserObjTest.parse("dRoP dATaBasE sAxA9BA5E_NmE;");
		} catch (Exception e) {
			fail("SyntaxErrorException thrown or AssertionError occurred!");
		}

	}

	@Test
	public void testDropDatabaseSyntaxValidateThree() {

		/*
		 * This test checks the SyntaxErrorException with deleting the semicolon
		 */

		try {
			sqlParserObjTest.parse("dRoP dATaBasE sAxA9BA5E_NmE one_two_three");
			fail("Syntax error exception hasn't been thrown!");
		} catch (SyntaxErrorException e) { 
		}

		/*
		 * This test checks the SyntaxErrorException without supporting
		 * meaningful DROP DATABASE SQL command
		 */

		try {
			sqlParserObjTest.parse("drop database;");
			fail("Syntax error exception hasn't been thrown!");
		} catch (SyntaxErrorException e) { 
		}
	}

	@Test
	public void testDropDatabaseSyntaxValidateFour() {
		/*
		 * This test checks the SyntaxErrorException with invalid DROP DATABASE
		 * SQL command
		 */

		try {
			sqlParserObjTest.parse("drop database database_one database_two;");
			fail("Syntax error exception hasn't been thrown!");
		} catch (SyntaxErrorException e) {
		}

		/*
		 * This test checks the validity of DROP DATABASE SQL command with
		 * multiple white spaces
		 */

		try {
			sqlParserObjTest.parse("       DROP            " + "DATABASE            " + "DATABASE96_NAME     ;    ");
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occurred!");
		}

	}

	/*
	 * These tests check the DROP TABLE SQL command validation
	 */

	@Test
	public void testDropTableSyntaxValidateOne() {

		try {
			sqlParserObjTest.parse("DROP TABLE TABLE_NAME;");
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occurred!");
		}

		/*
		 * This test checks the SyntaxErrorException with invalid SQL command.
		 */

		try {
			sqlParserObjTest.parse(";EMAN_ELBAT ELBAT PORD");
			fail("Syntax error exception hasn't been thrown!");
		} catch (SyntaxErrorException e) { 
		}
	}

	@Test
	public void testDropTableSyntaxValidateTwo() {
		/*
		 * This test checks that the catch clause will get the
		 * SyntaxErrorException not IndexOutOfBoundsException due to validate
		 * method in SQLParser class.
		 */

		try {
			sqlParserObjTest.parse("t;");
			fail("Syntax error exception hasn't been thrown!");
		} catch (SyntaxErrorException e) {
		}

		/*
		 * This test checks the case insensitivity of the SQL command.
		 */
		try {
			sqlParserObjTest.parse("dRoP TaBlE sAxA9BA5E_NmE;");
		} catch (Exception e) {
			fail("SyntaxErrorException thrown or AssertionError occurred!");
		}
	}

	@Test
	public void testDropTableSyntaxValidateThree() {

		/*
		 * This test checks the SyntaxErrorException with deleting the
		 * semicolon.
		 */

		try {
			sqlParserObjTest.parse("dRoP TaBlE sAxA9BA5E_NmE");
			fail("Syntax error exception hasn't been thrown!");
		} catch (SyntaxErrorException e) {
		}

		/*
		 * This test checks the SyntaxErrorException without supporting
		 * meaningful DROP TABLE SQL command.
		 */

		try {
			sqlParserObjTest.parse("drop table;");
			fail("Syntax error exception hasn't been thrown!");
		} catch (SyntaxErrorException e) {
		}
	}

	@Test
	public void testDropTableSyntaxValidateFour() {

		/*
		 * This test checks the SyntaxErrorException with invalid DROP TABLE SQL
		 * command.
		 */

		try {
			sqlParserObjTest.parse("drop database database_one database_two;");
			fail("Syntax error exception hasn't been thrown!");
		} catch (SyntaxErrorException e) {
		}

		/*
		 * This test checks the validity of DROP TABLE SQL command with multiple
		 * white spaces.
		 */

		try {
			sqlParserObjTest.parse("       DROP            " + "TABLE            " + "TABLE96_NAME     ;    ");
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occurred!");
		}

	}

	/*
	 * This test checks the DROP DATABASE SQL command parsed data validation
	 */

	@Test
	public void testDropDatabaseSyntaxParsingOne() {

		/*
		 * This test checks the name of database required to be dropped is the
		 * same as expected.
		 */

		try {
			dropDatabaseObjAct = sqlParserObjTest.parse("DROP DATABASE DATABASE_NAME;");
			assertEquals("DATABASE_NAME", ((DropDatabase) dropDatabaseObjAct).getDbName());
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occurred!");
		}

	}

	@Test
	public void testDropDatabaseSyntaxParsingTwo() {
		/*
		 * This test checks that the name of the database required could be
		 * anything even numbers except meta-characters of Regular expressions
		 * and case insensitivity of the SQL command.
		 */

		try {
			dropDatabaseObjAct = sqlParserObjTest.parse("dRoP DaTAbASe 99999666;");
			assertEquals("99999666", ((DropDatabase) dropDatabaseObjAct).getDbName());
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occurred!");
		}

		/*
		 * This test checks the validity of DROP DATABASE SQL command with
		 * multiple white spaces with checking the parsed data from the command.
		 */

		try {
			dropDatabaseObjAct = sqlParserObjTest
					.parse("    dRoP          DaTAbASe           database_One11111111     ;        ");
			assertEquals("database_one11111111", ((DropDatabase) dropDatabaseObjAct).getDbName().toLowerCase());
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occurred!");
		}

	}
	/*
	 * This test checks the DROP TABLE SQL command parsed data validation
	 */

	@Test
	public void testDropTableSyntaxParsingOne() {

		/*
		 * This test checks the name of table required to be dropped is the same
		 * as expected.
		 */

		try {
			dropTableObjAct = sqlParserObjTest.parse("DROP TABLE TABLE_NAME;");
			assertEquals("table_name", ((DropTable) dropTableObjAct).getTableName().toLowerCase());
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occurred!");			 
		}
	}

	@Test
	public void testDropTableSyntaxParsingTwo() {

		/*
		 * This test checks that the name of the table required could be
		 * anything even numbers except meta-characters of Regular expressions
		 * and case insensitivity of the SQL command.
		 */

		try	{
			dropTableObjAct = sqlParserObjTest.parse("dRoP TaBlE 99999666;");
			assertEquals("99999666", ((DropTable) dropTableObjAct).getTableName());
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occurred!");
		}

		/*
		 * This test checks the validity of DROP TABLE SQL command with multiple
		 * white spaces with checking the parsed data from the command.
		 */

		try {
			dropTableObjAct = sqlParserObjTest
					.parse("    dRoP          TaBlE           table_One11111111     ;        ");
			assertEquals("table_one11111111", ((DropTable) dropTableObjAct).getTableName().toLowerCase());
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occurred!");
		}

	}

}
