package dbms.test.SQLParserTesting;

import static org.junit.Assert.*;

import org.junit.Test;

import dbms.exception.SyntaxErrorException;
import dbms.sqlparser.SQLParser;
import dbms.sqlparser.sqlInterpreter.Expression;
import dbms.sqlparser.sqlInterpreter.SQLPredicate;
import dbms.sqlparser.sqlInterpreter.rules.Select;
import dbms.util.Operator;

public class SelectTest extends SqlParserRef {

	private final SQLParser sqlParserObjTest = super.getSqlParserReference();
	private Expression selectObjAct;

	/*
	 * these tests check the multiple forms the SELECT SQL command can take.
	 */

	@Test
	public void testSelectSyntaxValidateOne() {
		try {
			sqlParserObjTest.parse("SELECT COLUMN_NAME FROM TABLE_NAME ;");
		} catch (SyntaxErrorException e) {
			fail("Syntax error exception thrown!");
			e.printStackTrace();
		}
		try {
			sqlParserObjTest.parse("SELECT * FROM TABLE_NAME ;");
		} catch (SyntaxErrorException e) {
			fail("Syntax error exception thrown!");
			e.printStackTrace();
		}
		try {
			sqlParserObjTest.parse("SELECT * FROM TABLE_NAME WHERE country = 'Mexico' ;");
		} catch (SyntaxErrorException e) {
			fail("Syntax error exception thrown!");
			e.printStackTrace();
		}
		try {
			sqlParserObjTest.parse("SELECT COL_NAME FROM TABLE_NAME WHERE country = 'Brazil' ;");
		} catch (SyntaxErrorException e) {
			fail("Syntax error exception thrown!");
			e.printStackTrace();
		}
	}

	@Test
	public void testSelectSyntaxValidateTwo() {
		try {
			sqlParserObjTest.parse("SELECT COLUMN_NAME , COLUMN_NAME1, COLUMN_NAME2 FROM TABLE_NAME ;");
		} catch (SyntaxErrorException e) {
			fail("Syntax error exception thrown!");
			e.printStackTrace();
		}
		try {
			sqlParserObjTest.parse("SELECT * FROM TABLE_NAME ;");
		} catch (SyntaxErrorException e) {
			fail("Syntax error exception thrown!");
			e.printStackTrace();
		}
		try {
			sqlParserObjTest.parse("SELECT * FROM TABLE_NAME WHERE country = 'Mexico' ;");
		} catch (SyntaxErrorException e) {
			fail("Syntax error exception thrown!");
			e.printStackTrace();
		}
		try {
			sqlParserObjTest.parse("SELECT COL_NAME, COL_NAME1 ,COL_NAME2 FROM TABLE_NAME WHERE country = 'Brazil' ;");
		} catch (SyntaxErrorException e) {
			fail("Syntax error exception thrown!");
			e.printStackTrace();
		}
	}

	/*
	 * These tests check that the catch clause will get the
	 * SyntaxErrorException.
	 */

	@Test
	public void testSelectSyntaxValidateThree() {
		try {
			sqlParserObjTest.parse("SELECT COLUMN_NAME FROM TABLE_NAME ");
			fail("Syntax error exception thrown!");
		} catch (SyntaxErrorException e) {
			e.printStackTrace();
		}
		try {
			sqlParserObjTest.parse("SELECT  FROM TABLE_NAME ;");
			fail("Syntax error exception thrown!");
		} catch (SyntaxErrorException e) {
			e.printStackTrace();
		}
		try {
			sqlParserObjTest.parse("SELECT * FROM TABLE_NAME WHERE country  'Mexico' ;");
			fail("Syntax error exception thrown!");
		} catch (SyntaxErrorException e) {
			e.printStackTrace();
		}
		try {
			sqlParserObjTest.parse("SELECT COL_NAME FROM TABLE_NAME WHERE country = Brazil' ;");
			fail("Syntax error exception thrown!");
		} catch (SyntaxErrorException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSelectSyntaxValidateFour() {
		try {
			sqlParserObjTest.parse("SELECT  FROM TABLE_NAME ;");
			fail("Syntax error exception thrown!");
		} catch (SyntaxErrorException e) {
			e.printStackTrace();
		}
		try {
			sqlParserObjTest.parse("SEL * FROM TABLE_NAME ;");
			fail("Syntax error exception thrown!");
		} catch (SyntaxErrorException e) {
			e.printStackTrace();
		}
		try {
			sqlParserObjTest.parse("SELECT * FROM TABLE_NAME WHRE country = 'Mexico' ;");
			fail("Syntax error exception thrown!");
		} catch (SyntaxErrorException e) {
			e.printStackTrace();
		}
		try {
			sqlParserObjTest.parse("SELECT COL_NAME FROM  WHERE country = 'Brazil' ;");
			fail("Syntax error exception thrown!");
		} catch (SyntaxErrorException e) {
			e.printStackTrace();
		}
	}

	/*
	 * This test checks the case insensitivity of the SELECT SQL command plus
	 * the validity of multiple white spaces in the command.
	 */
	@Test
	public void testSelectSyntaxValidateFive() {
		try {
			sqlParserObjTest.parse("SeLEcT cOLuMn_NAmE FrOm TAbLE_NamE ;");
		} catch (SyntaxErrorException e) {
			fail("Syntax error exception thrown!");
			e.printStackTrace();
		}
		try {
			sqlParserObjTest.parse("           SELECT           *           FROM            TABLE_NAME     ;      ");
		} catch (SyntaxErrorException e) {
			fail("Syntax error exception thrown!");
			e.printStackTrace();
		}
		try {
			sqlParserObjTest.parse(
					"     sElEcT     *       FrOM      TaBLe_nAMe     wHeRe      cOuNtrY     =       'Mexico'    ;    ");
		} catch (SyntaxErrorException e) {
			fail("Syntax error exception thrown!");
			e.printStackTrace();
		}
		try {
			sqlParserObjTest.parse(
					"           SeLeCT         CoL_nAMe         FrOm     TaBlE_NaMe WheRe couNtry = 'Brazil' ;     ");
		} catch (SyntaxErrorException e) {
			fail("Syntax error exception thrown!");
			e.printStackTrace();
		}
	}

	/*
	 * This test checks the correctness of the parsed data from SELECT SQL
	 * command.
	 */

	/*
	 * SELECT * FROM TABLE_NAME;
	 */
	@Test
	public void testSelectParsingValidateOne() {
		try {
			selectObjAct = sqlParserObjTest.parse("SELECT * FROM TABLE_NAME ;");
			assertEquals("table_name", ((Select) selectObjAct).getTableName());
			assertEquals(true, ((Select) selectObjAct).getSelectAll());
		} catch (SyntaxErrorException e) {
			fail("Syntax error exception thrown!");
			e.printStackTrace();
		}
		try {
			selectObjAct = sqlParserObjTest.parse(
					"        SELECT               *          FROM          CUrr_915634            ;            ");
			assertEquals("curr_915634", ((Select) selectObjAct).getTableName());
			assertEquals(true, ((Select) selectObjAct).getSelectAll());
		} catch (SyntaxErrorException e) {
			fail("Syntax error exception thrown!");
			e.printStackTrace();
		}
		try {
			selectObjAct = sqlParserObjTest.parse("SELECT * FROM 121515438 ;");
			assertEquals("121515438", ((Select) selectObjAct).getTableName());
			assertEquals(true, ((Select) selectObjAct).getSelectAll());
		} catch (SyntaxErrorException e) {
			fail("Syntax error exception thrown!");
			e.printStackTrace();
		}
	}

	/*
	 * SELECT COL_NAME FROM TABLE_NAME;
	 */

	@Test
	public void testSelectParsingValidateTwo() {
		try {
			selectObjAct = sqlParserObjTest.parse("SELECT COL_NAME FROM TABLE_NAME ;");
			assertEquals("table_name", ((Select) selectObjAct).getTableName());
			String[] strArray = { "col_name" };
			assertArrayEquals(strArray, ((Select) selectObjAct).getColumns().toArray());
		} catch (SyntaxErrorException e) {
			fail("Syntax error exception thrown!");
			e.printStackTrace();
		}
		try {
			selectObjAct = sqlParserObjTest.parse(
					"        SELECT               col_name, col_naMe2 , COLname8          FROM          CUrr_915634            ;            ");
			assertEquals("curr_915634", ((Select) selectObjAct).getTableName());
			String[] strArray = { "col_name", "col_name2", "colname8" };
			assertArrayEquals(strArray, ((Select) selectObjAct).getColumns().toArray());
		} catch (SyntaxErrorException e) {
			fail("Syntax error exception thrown!");
			e.printStackTrace();
		}
	}

	/*
	 * SELECT COL_NAME FROM TABLE_NAME WHERE CERTAIN CONDITION;
	 */

	@Test
	public void testSelectParsingValidateThree() {
		try {
			selectObjAct = sqlParserObjTest.parse("SELECT COL_NAME FROM TABLE_NAME WHERE COL1 = COL2;");
			assertEquals("table_name", ((Select) selectObjAct).getTableName());
			String[] strArray = { "col_name" };
			assertArrayEquals(strArray, ((Select) selectObjAct).getColumns().toArray());
			Object[] sqlPredicate = ((Select) selectObjAct).getWhere().getPredicates().toArray();
			assertEquals("col1", ((SQLPredicate) sqlPredicate[0]).getColumnName());
			assertEquals("col2", ((SQLPredicate) sqlPredicate[0]).getColumnName2());
			assertEquals(null, ((SQLPredicate) sqlPredicate[0]).getValue());
			assertEquals(Operator.Equal, ((SQLPredicate) sqlPredicate[0]).getOperator());
		} catch (SyntaxErrorException e) {
			fail("Syntax error exception thrown!");
			e.printStackTrace();
		}
	}

	@Test
	public void testSelectParsingValidateFour() {
		try {
			selectObjAct = sqlParserObjTest.parse(
					"        SELECT               col_name, col_naMe2 , COLname8          FROM          CUrr_915634    WheRe Col1 = Col2        ;            ");
			assertEquals("curr_915634", ((Select) selectObjAct).getTableName());
			String[] strArray = { "col_name", "col_name2", "colname8" };
			assertArrayEquals(strArray, ((Select) selectObjAct).getColumns().toArray());
			Object[] sqlPredicate = ((Select) selectObjAct).getWhere().getPredicates().toArray();
			assertEquals("col1", ((SQLPredicate) sqlPredicate[0]).getColumnName());
			assertEquals("col2", ((SQLPredicate) sqlPredicate[0]).getColumnName2());
			assertEquals(null, ((SQLPredicate) sqlPredicate[0]).getValue());
			assertEquals(Operator.Equal, ((SQLPredicate) sqlPredicate[0]).getOperator());
		} catch (SyntaxErrorException e) {
			fail("Syntax error exception thrown!");
			e.printStackTrace();
		}
	}

	@Test
	public void testSelectParsingValidateFive() {
		try {
			selectObjAct = sqlParserObjTest.parse(
					"        SELECT               col_name, col_naMe2 , COLname8          FROM          CUrr_915634    WheRe Col1 = 'value'        ;            ");
			assertEquals("curr_915634", ((Select) selectObjAct).getTableName());
			String[] strArray = { "col_name", "col_name2", "colname8" };
			assertArrayEquals(strArray, ((Select) selectObjAct).getColumns().toArray());
			Object[] sqlPredicate = ((Select) selectObjAct).getWhere().getPredicates().toArray();
			assertEquals("col1", ((SQLPredicate) sqlPredicate[0]).getColumnName());
			assertEquals(null, ((SQLPredicate) sqlPredicate[0]).getColumnName2());
			assertEquals("value", ((SQLPredicate) sqlPredicate[0]).getValue());
			assertEquals(Operator.Equal, ((SQLPredicate) sqlPredicate[0]).getOperator());
		} catch (SyntaxErrorException e) {
			fail("Syntax error exception thrown!");
			e.printStackTrace();
		}
	}

	@Test
	public void testSelectParsingValidateSix() {
		try {
			selectObjAct = sqlParserObjTest.parse(
					"        SELECT               col_name, col_naMe2 , COLname8          FROM          CUrr_915634    WheRe Col1 > 5        ;            ");
			assertEquals("curr_915634", ((Select) selectObjAct).getTableName());
			String[] strArray = { "col_name", "col_name2", "colname8" };
			assertArrayEquals(strArray, ((Select) selectObjAct).getColumns().toArray());
			Object[] sqlPredicate = ((Select) selectObjAct).getWhere().getPredicates().toArray();
			assertEquals("col1", ((SQLPredicate) sqlPredicate[0]).getColumnName());
			assertEquals(null, ((SQLPredicate) sqlPredicate[0]).getColumnName2());
			assertEquals(5, ((SQLPredicate) sqlPredicate[0]).getValue());
			assertEquals(Operator.GreaterThan, ((SQLPredicate) sqlPredicate[0]).getOperator());
		} catch (SyntaxErrorException e) {
			fail("Syntax error exception thrown!");
			e.printStackTrace();
		}
	}

	@Test
	public void testSelectParsingValidateSeven() {
		try {
			selectObjAct = sqlParserObjTest.parse(
					"        SELECT               col_name, col_naMe2 , COLname8          FROM          CUrr_915634    WheRe Col1 < 5        ;            ");
			assertEquals("curr_915634", ((Select) selectObjAct).getTableName());
			String[] strArray = { "col_name", "col_name2", "colname8" };
			assertArrayEquals(strArray, ((Select) selectObjAct).getColumns().toArray());
			Object[] sqlPredicate = ((Select) selectObjAct).getWhere().getPredicates().toArray();
			assertEquals("col1", ((SQLPredicate) sqlPredicate[0]).getColumnName());
			assertEquals(null, ((SQLPredicate) sqlPredicate[0]).getColumnName2());
			assertEquals(5, ((SQLPredicate) sqlPredicate[0]).getValue());
			assertEquals(Operator.SmallerThan, ((SQLPredicate) sqlPredicate[0]).getOperator());
		} catch (SyntaxErrorException e) {
			fail("Syntax error exception thrown!");
			e.printStackTrace();
		}
	}

	/*
	 * SELECT * FROM TABLE_NAME WHERE CERTAIN CONDITION;
	 */

	@Test
	public void testSelectParsingValidateEight() {
		try {
			selectObjAct = sqlParserObjTest.parse("SELECT * FROM TABLE_NAME WHERE COL1 = COL2;");
			assertEquals("table_name", ((Select) selectObjAct).getTableName());
			assertEquals(true, ((Select) selectObjAct).getSelectAll());
			Object[] sqlPredicate = ((Select) selectObjAct).getWhere().getPredicates().toArray();
			assertEquals("col1", ((SQLPredicate) sqlPredicate[0]).getColumnName());
			assertEquals("col2", ((SQLPredicate) sqlPredicate[0]).getColumnName2());
			assertEquals(null, ((SQLPredicate) sqlPredicate[0]).getValue());
			assertEquals(Operator.Equal, ((SQLPredicate) sqlPredicate[0]).getOperator());
		} catch (SyntaxErrorException e) {
			fail("Syntax error exception thrown!");
			e.printStackTrace();
		}
	}

	@Test
	public void testSelectParsingValidateNine() {
		try {
			selectObjAct = sqlParserObjTest.parse("SELECT * FROM TABLE_NAME WHERE COL1 = 'value';");
			assertEquals("table_name", ((Select) selectObjAct).getTableName());
			assertEquals(true, ((Select) selectObjAct).getSelectAll());
			Object[] sqlPredicate = ((Select) selectObjAct).getWhere().getPredicates().toArray();
			assertEquals("col1", ((SQLPredicate) sqlPredicate[0]).getColumnName());
			assertEquals(null, ((SQLPredicate) sqlPredicate[0]).getColumnName2());
			assertEquals("value", ((SQLPredicate) sqlPredicate[0]).getValue());
			assertEquals(Operator.Equal, ((SQLPredicate) sqlPredicate[0]).getOperator());
		} catch (SyntaxErrorException e) {
			fail("Syntax error exception thrown!");
			e.printStackTrace();
		}
	}

	@Test
	public void testSelectParsingValidateTen() {
		try {
			selectObjAct = sqlParserObjTest.parse("       SELECT       *                      FROM       TABLE_NAME      WHERE    COL1    =     153           ;     ");
			assertEquals("table_name", ((Select) selectObjAct).getTableName());
			assertEquals(true, ((Select) selectObjAct).getSelectAll());
			Object[] sqlPredicate = ((Select) selectObjAct).getWhere().getPredicates().toArray();
			assertEquals("col1", ((SQLPredicate) sqlPredicate[0]).getColumnName());
			assertEquals(null, ((SQLPredicate) sqlPredicate[0]).getColumnName2());
			assertEquals(153, ((SQLPredicate) sqlPredicate[0]).getValue());
			assertEquals(Operator.Equal, ((SQLPredicate) sqlPredicate[0]).getOperator());
		} catch (SyntaxErrorException e) {
			fail("Syntax error exception thrown!");
			e.printStackTrace();
		}
	}

	@Test
	public void testSelectParsingValidateEleven() {
		try {
			selectObjAct = sqlParserObjTest.parse(
					"        SElEct         *          FrOm               TaBLe_NaMe       wHeRe      CoL1     >        153       ;     ");
			assertEquals("table_name", ((Select) selectObjAct).getTableName());
			assertEquals(true, ((Select) selectObjAct).getSelectAll());
			Object[] sqlPredicate = ((Select) selectObjAct).getWhere().getPredicates().toArray();
			assertEquals("col1", ((SQLPredicate) sqlPredicate[0]).getColumnName());
			assertEquals(null, ((SQLPredicate) sqlPredicate[0]).getColumnName2());
			assertEquals(153, ((SQLPredicate) sqlPredicate[0]).getValue());
			assertEquals(Operator.GreaterThan, ((SQLPredicate) sqlPredicate[0]).getOperator());
		} catch (SyntaxErrorException e) {
			fail("Syntax error exception thrown!");
			e.printStackTrace();
		}
	}

}
