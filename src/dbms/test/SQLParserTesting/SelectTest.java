package dbms.test.SQLParserTesting;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import dbms.exception.SyntaxErrorException;
import dbms.sqlparser.SQLParser;
import dbms.sqlparser.sqlInterpreter.SQLPredicate;
import dbms.sqlparser.sqlInterpreter.rules.Expression;
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
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}
		try {
			sqlParserObjTest.parse("SELECT * FROM TABLE_NAME ;");
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}
		try {
			sqlParserObjTest.parse("SELECT * FROM TABLE_NAME WHERE country = 'Mexico' ;");
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}
		try {
			sqlParserObjTest.parse("SELECT COL_NAME FROM TABLE_NAME WHERE country = 'Brazil' ;");
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}
	}

	@Test
	public void testSelectSyntaxValidateTwo() {
		try {
			sqlParserObjTest.parse("SELECT COLUMN_NAME , COLUMN_NAME1, COLUMN_NAME2 FROM TABLE_NAME ;");
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}
		try {
			sqlParserObjTest.parse("SELECT * FROM TABLE_NAME ;");
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}
		try {
			sqlParserObjTest.parse("SELECT * FROM TABLE_NAME WHERE country = 'Mexico' ;");
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}
		try {
			sqlParserObjTest.parse("SELECT COL_NAME, COL_NAME1 ,COL_NAME2 FROM TABLE_NAME WHERE country = 'Brazil' ;");
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
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
			fail("SyntaxErrorException thrown or AssertionError occured!");
		} catch (SyntaxErrorException e) {
		}
		try {
			sqlParserObjTest.parse("SELECT  FROM TABLE_NAME ;");
			fail("SyntaxErrorException thrown or AssertionError occured!");
		} catch (SyntaxErrorException e) {
		}
		try {
			sqlParserObjTest.parse("SELECT * FROM TABLE_NAME WHERE country  'Mexico' ;");
			fail("SyntaxErrorException thrown or AssertionError occured!");
		} catch (SyntaxErrorException e) {
		}
		try {
			sqlParserObjTest.parse("SELECT COL_NAME FROM TABLE_NAME WHERE country = Brazil' ;");
			fail("SyntaxErrorException thrown or AssertionError occured!");
		} catch (SyntaxErrorException e) {
		}
	}

	@Test
	public void testSelectSyntaxValidateFour() {
		try {
			sqlParserObjTest.parse("SELECT  FROM TABLE_NAME ;");
			fail("SyntaxErrorException thrown or AssertionError occured!");
		} catch (SyntaxErrorException e) {
		}
		try {
			sqlParserObjTest.parse("SEL * FROM TABLE_NAME ;");
			fail("SyntaxErrorException thrown or AssertionError occured!");
		} catch (SyntaxErrorException e) {
		}
		try {
			sqlParserObjTest.parse("SELECT * FROM TABLE_NAME WHRE country = 'Mexico' ;");
			fail("SyntaxErrorException thrown or AssertionError occured!");
		} catch (SyntaxErrorException e) {
		}
		try {
			sqlParserObjTest.parse("SELECT COL_NAME FROM  WHERE country = 'Brazil' ;");
			fail("SyntaxErrorException thrown or AssertionError occured!");
		} catch (SyntaxErrorException e) {
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
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}
		try {
			sqlParserObjTest.parse("           SELECT           *           FROM            TABLE_NAME     ;      ");
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}
		try {
			sqlParserObjTest.parse(
					"     sElEcT     *       FrOM      TaBLe_nAMe     wHeRe      cOuNtrY     =       'Mexico'    ;    ");
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}
		try {
			sqlParserObjTest.parse(
					"           SeLeCT         CoL_nAMe         FrOm     TaBlE_NaMe WheRe couNtry = 'Brazil' ;     ");
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
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
			assertEquals("table_name", ((Select) selectObjAct).getTableName().toLowerCase());
			assertEquals(true, ((Select) selectObjAct).getSelectAll());
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}
		try {
			selectObjAct = sqlParserObjTest.parse(
					"        SELECT               *          FROM          CUrr_915634            ;            ");
			assertEquals("curr_915634", ((Select) selectObjAct).getTableName().toLowerCase());
			assertEquals(true, ((Select) selectObjAct).getSelectAll());
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}
		try {
			selectObjAct = sqlParserObjTest.parse("SELECT * FROM 121515438 ;");
			assertEquals("121515438", ((Select) selectObjAct).getTableName());
			assertEquals(true, ((Select) selectObjAct).getSelectAll());
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}
	}

	/*
	 * SELECT COL_NAME FROM TABLE_NAME;
	 */

	@Test
	public void testSelectParsingValidateTwo() {
		try {
			selectObjAct = sqlParserObjTest.parse("SELECT COL_NAME FROM TABLE_NAME ;");
			assertEquals("table_name", ((Select) selectObjAct).getTableName().toLowerCase());
			String[] strArray = { "COL_NAME" };
			assertArrayEquals(strArray, ((Select) selectObjAct).getColumns().toArray());
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}
		try {
			selectObjAct = sqlParserObjTest.parse(
					"        SELECT               col_name, col_naMe2 , COLname8          FROM          CUrr_915634            ;            ");
			assertEquals("curr_915634", ((Select) selectObjAct).getTableName().toLowerCase());
			String[] strArray = { "col_name", "col_naMe2", "COLname8" };
			assertArrayEquals(strArray, ((Select) selectObjAct).getColumns().toArray());
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}
	}

	/*
	 * SELECT COL_NAME FROM TABLE_NAME WHERE CERTAIN CONDITION;
	 */

	@Test
	public void testSelectParsingValidateThree() {
		try {
			selectObjAct = sqlParserObjTest.parse("SELECT COL_NAME FROM TABLE_NAME WHERE COL1 = COL2;");
			assertEquals("table_name", ((Select) selectObjAct).getTableName().toLowerCase());
			Object[] columnsCpy = {"COL_NAME"};
			assertArrayEquals(columnsCpy, ((Select) selectObjAct).getColumns().toArray());
			Object[] sqlPredicate = ((Select) selectObjAct).getWhere().getPredicates().toArray();
			assertEquals("col1", ((SQLPredicate) sqlPredicate[0]).getColumnName().toLowerCase());
			assertEquals("col2", ((SQLPredicate) sqlPredicate[0]).getColumnName2().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicate[0]).getValue());
			assertEquals(Operator.Equal, ((SQLPredicate) sqlPredicate[0]).getOperator());
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}
	}

	@Test
	public void testSelectParsingValidateFour() {
		try {
			selectObjAct = sqlParserObjTest.parse(
					"        SELECT               col_name, col_naMe2 , COLname8          FROM          CUrr_915634    WheRe Col1 = Col2        ;            ");
			assertEquals("curr_915634", ((Select) selectObjAct).getTableName().toLowerCase());
			String[] strArray = { "col_name", "col_naMe2", "COLname8" };
			assertArrayEquals(strArray, ((Select) selectObjAct).getColumns().toArray());
			Object[] sqlPredicate = ((Select) selectObjAct).getWhere().getPredicates().toArray();
			assertEquals("col1", ((SQLPredicate) sqlPredicate[0]).getColumnName().toLowerCase());
			assertEquals("col2", ((SQLPredicate) sqlPredicate[0]).getColumnName2().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicate[0]).getValue());
			assertEquals(Operator.Equal, ((SQLPredicate) sqlPredicate[0]).getOperator());
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}
	}

	@Test
	public void testSelectParsingValidateFive() {
		try {
			selectObjAct = sqlParserObjTest.parse(
					"        SELECT               col_name, col_naMe2 , COLname8          FROM          CUrr_915634    WheRe Col1 = 'ValUe'        ;            ");
			assertEquals("curr_915634", ((Select) selectObjAct).getTableName().toLowerCase());
			String[] strArray = { "col_name", "col_naMe2", "COLname8" };
			assertArrayEquals(strArray, ((Select) selectObjAct).getColumns().toArray());
			Object[] sqlPredicate = ((Select) selectObjAct).getWhere().getPredicates().toArray();
			assertEquals("col1", ((SQLPredicate) sqlPredicate[0]).getColumnName().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicate[0]).getColumnName2());
			assertEquals("ValUe", ((SQLPredicate) sqlPredicate[0]).getValue());
			assertEquals(Operator.Equal, ((SQLPredicate) sqlPredicate[0]).getOperator());
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}
	}

	@Test
	public void testSelectParsingValidateSix() {
		try {
			selectObjAct = sqlParserObjTest.parse(
					"        SELECT               col_name, col_naMe2 , COLname8          FROM          CUrr_915634    WheRe Col1 > 5        ;            ");
			assertEquals("curr_915634", ((Select) selectObjAct).getTableName().toLowerCase());
			String[] strArray = { "col_name", "col_naMe2", "COLname8" };
			assertArrayEquals(strArray, ((Select) selectObjAct).getColumns().toArray());
			Object[] sqlPredicate = ((Select) selectObjAct).getWhere().getPredicates().toArray();
			assertEquals("col1", ((SQLPredicate) sqlPredicate[0]).getColumnName().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicate[0]).getColumnName2());
			assertEquals(5, ((SQLPredicate) sqlPredicate[0]).getValue());
			assertEquals(Operator.GreaterThan, ((SQLPredicate) sqlPredicate[0]).getOperator());
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}
	}

	@Test
	public void testSelectParsingValidateSeven() {
		try {
			selectObjAct = sqlParserObjTest.parse(
					"        SELECT               col_name, col_naMe2 , COLname8          FROM          CUrr_915634    WheRe Col1 < 5        ;            ");
			assertEquals("curr_915634", ((Select) selectObjAct).getTableName().toLowerCase());
			String[] strArray = { "col_name", "col_naMe2", "COLname8" };
			assertArrayEquals(strArray, ((Select) selectObjAct).getColumns().toArray());
			Object[] sqlPredicate = ((Select) selectObjAct).getWhere().getPredicates().toArray();
			assertEquals("col1", ((SQLPredicate) sqlPredicate[0]).getColumnName().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicate[0]).getColumnName2());
			assertEquals(5, ((SQLPredicate) sqlPredicate[0]).getValue());
			assertEquals(Operator.SmallerThan, ((SQLPredicate) sqlPredicate[0]).getOperator());
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}
	}

	/*
	 * SELECT * FROM TABLE_NAME WHERE CERTAIN CONDITION;
	 */

	@Test
	public void testSelectParsingValidateEight() {
		try {
			selectObjAct = sqlParserObjTest.parse("select * FROM TABLE_NAME WHERE COL1 = COL2;");
			assertEquals("table_name", ((Select) selectObjAct).getTableName().toLowerCase());
			assertEquals(true, ((Select) selectObjAct).getSelectAll());
			Object[] sqlPredicate = ((Select) selectObjAct).getWhere().getPredicates().toArray();
			assertEquals("col1", ((SQLPredicate) sqlPredicate[0]).getColumnName().toLowerCase());
			assertEquals("col2", ((SQLPredicate) sqlPredicate[0]).getColumnName2().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicate[0]).getValue());
			assertEquals(Operator.Equal, ((SQLPredicate) sqlPredicate[0]).getOperator());
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}
	}

	@Test
	public void testSelectParsingValidateNine() {
		try {
			selectObjAct = sqlParserObjTest.parse("SELECT * FROM TABLE_NAME WHERE COL1 = 'vALue';");
			assertEquals("table_name", ((Select) selectObjAct).getTableName().toLowerCase());
			assertEquals(true, ((Select) selectObjAct).getSelectAll());
			Object[] sqlPredicate = ((Select) selectObjAct).getWhere().getPredicates().toArray();
			assertEquals("col1", ((SQLPredicate) sqlPredicate[0]).getColumnName().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicate[0]).getColumnName2());
			assertEquals("vALue", ((SQLPredicate) sqlPredicate[0]).getValue());
			assertEquals(Operator.Equal, ((SQLPredicate) sqlPredicate[0]).getOperator());
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}
	}

	@Test
	public void testSelectParsingValidateTen() {
		try {
			selectObjAct = sqlParserObjTest.parse("       SELECT       *                      FROM       TABLE_NAME      WHERE    COL1    =     153           ;     ");
			assertEquals("table_name", ((Select) selectObjAct).getTableName().toLowerCase());
			assertEquals(true, ((Select) selectObjAct).getSelectAll());
			Object[] sqlPredicate = ((Select) selectObjAct).getWhere().getPredicates().toArray();
			assertEquals("col1", ((SQLPredicate) sqlPredicate[0]).getColumnName().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicate[0]).getColumnName2());
			assertEquals(153, ((SQLPredicate) sqlPredicate[0]).getValue());
			assertEquals(Operator.Equal, ((SQLPredicate) sqlPredicate[0]).getOperator());
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}
	}

	@Test
	public void testSelectParsingValidateEleven() {
		try {
			selectObjAct = sqlParserObjTest.parse(
					"        SElEct         *          FrOm               TaBLe_NaMe       wHeRe      CoL1     >        153       ;     ");
			assertEquals("table_name", ((Select) selectObjAct).getTableName().toLowerCase());
			assertEquals(true, ((Select) selectObjAct).getSelectAll());
			Object[] sqlPredicate = ((Select) selectObjAct).getWhere().getPredicates().toArray();
			assertEquals("col1", ((SQLPredicate) sqlPredicate[0]).getColumnName().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicate[0]).getColumnName2());
			assertEquals(153, ((SQLPredicate) sqlPredicate[0]).getValue());
			assertEquals(Operator.GreaterThan, ((SQLPredicate) sqlPredicate[0]).getOperator());
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}
	}


	@Test
	public void testSelectParsingValidateTwelve() {
		try {
			selectObjAct = sqlParserObjTest.parse(
					"        SElEct         *          FrOm               TaBLe_NaMe       wHeRe      CoL1     >        COll2       ;     ");
			assertEquals("table_name", ((Select) selectObjAct).getTableName().toLowerCase());
			assertEquals(true, ((Select) selectObjAct).getSelectAll());
			Object[] sqlPredicate = ((Select) selectObjAct).getWhere().getPredicates().toArray();
			assertEquals("col1", ((SQLPredicate) sqlPredicate[0]).getColumnName().toLowerCase());
			assertEquals("coll2", ((SQLPredicate) sqlPredicate[0]).getColumnName2().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicate[0]).getValue());
			assertEquals(Operator.GreaterThan, ((SQLPredicate) sqlPredicate[0]).getOperator());
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}
	}

	@Test
	public void testSelectParsingValidateThirteen() {
		try {
			selectObjAct = sqlParserObjTest.parse(
					"        SElEct         *          FrOm               TaBLe_NaMe       wHeRe      CoL1     <        COll2       ;     ");
			assertEquals("table_name", ((Select) selectObjAct).getTableName().toLowerCase());
			assertEquals(true, ((Select) selectObjAct).getSelectAll());
			Object[] sqlPredicate = ((Select) selectObjAct).getWhere().getPredicates().toArray();
			assertEquals("col1", ((SQLPredicate) sqlPredicate[0]).getColumnName().toLowerCase());
			assertEquals("coll2", ((SQLPredicate) sqlPredicate[0]).getColumnName2().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicate[0]).getValue());
			assertEquals(Operator.SmallerThan, ((SQLPredicate) sqlPredicate[0]).getOperator());
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}
	}

	@Test
	public void testSelectParsingValidateFourteen() {
		try {
			selectObjAct = sqlParserObjTest.parse(
					"        SElEct         *          FrOm               TaBLe_NaMe       wHeRe      CoL1     <        'STrInG'      ;     ");
			assertEquals("table_name", ((Select) selectObjAct).getTableName().toLowerCase());
			assertEquals(true, ((Select) selectObjAct).getSelectAll());
			Object[] sqlPredicate = ((Select) selectObjAct).getWhere().getPredicates().toArray();
			assertEquals("col1", ((SQLPredicate) sqlPredicate[0]).getColumnName().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicate[0]).getColumnName2());
			assertEquals("STrInG", ((SQLPredicate) sqlPredicate[0]).getValue());
			assertEquals(Operator.SmallerThan, ((SQLPredicate) sqlPredicate[0]).getOperator());
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}
	}


	@Test
	public void testSelectParsingValidateFifteen() {
		try {
			selectObjAct = sqlParserObjTest.parse(
					"        SElEct         *          FrOm               TaBLe_NaMe       wHeRe      CoL1     >        'STrInG'      ;     ");
			assertEquals("table_name", ((Select) selectObjAct).getTableName().toLowerCase());
			assertEquals(true, ((Select) selectObjAct).getSelectAll());
			Object[] sqlPredicate = ((Select) selectObjAct).getWhere().getPredicates().toArray();
			assertEquals("col1", ((SQLPredicate) sqlPredicate[0]).getColumnName().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicate[0]).getColumnName2());
			assertEquals("STrInG", ((SQLPredicate) sqlPredicate[0]).getValue());
			assertEquals(Operator.GreaterThan, ((SQLPredicate) sqlPredicate[0]).getOperator());
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}
	}

}
