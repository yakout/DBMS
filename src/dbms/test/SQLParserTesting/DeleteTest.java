package dbms.test.SQLParserTesting;

import dbms.exception.SyntaxErrorException;
import dbms.sqlparser.SQLParser;
import dbms.sqlparser.sqlInterpreter.SQLPredicate;
import dbms.sqlparser.sqlInterpreter.rules.BooleanOperator;
import dbms.sqlparser.sqlInterpreter.rules.Delete;
import dbms.sqlparser.sqlInterpreter.rules.Expression;
import dbms.util.Operator;
import org.junit.Test;

import java.util.Queue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class DeleteTest extends SqlParserRef {

	private final SQLParser sqlParserObjTest = super.getSqlParserReference();
	private Expression deleteObjAct;
	
	/*
	 * The time taken by this tests include the time consumed by the Validate method.
	 */

	/*
	 * these tests check the multiple forms the DELETE SQL command can take.
	 */

	@Test
	public void testDeleteTableSyntaxValidateOne() {

		try {
			sqlParserObjTest.parse("DELETE FROM TABLE_NAME;");
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}

		try {
			sqlParserObjTest.parse("DELETE * FROM TABLE_NAME ;");
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}

	}

	@Test
	public void testDeleteTableSyntaxValidateTwo() {

		/*
		 * The column here represents data from data type String.
		 */

		try {
			sqlParserObjTest.parse("DELETE FROM TABLE_NAME WHERE SOME_COL='SOME_VALUE';");
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}

		/*
		 * The column here represents data from data type Integer.
		 */

		try {
			sqlParserObjTest.parse("DELETE FROM TABLE_NAME WHERE SOME_COL=999;");
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}
	}

	/*
	 * This test checks that the catch clause will get the SyntaxErrorException.
	 */

	@Test
	public void testDeleteTableSyntaxValidateThree() {

		/*
		 * This test checks the SyntaxErrorException with deleting the
		 * semicolon.
		 */

		try {
			sqlParserObjTest.parse("DELETE FROM TABLE_NAME");
			fail("SyntaxErrorException thrown or AssertionError occured!");
		} catch (SyntaxErrorException e) {
		}

		/*
		 * This test checks the SyntaxErrorException with invalid SQL command.
		 */

		try {
			sqlParserObjTest.parse(";EMAN_ELBAT MORF ETELED");
			fail("SyntaxErrorException thrown or AssertionError occured!");
		} catch (SyntaxErrorException e) {
		}

	}

	@Test
	public void testDeleteTableSyntaxValidateFour() {

		/*
		 * This test checks the SyntaxErrorException with deleting the
		 * semicolon.
		 */

		try {
			sqlParserObjTest.parse("DELETE * FROM TABLE_NAME");
			fail("SyntaxErrorException thrown or AssertionError occured!");
		} catch (SyntaxErrorException e) {
		}

		/*
		 * This test checks the SyntaxErrorException with invalid SQL command.
		 */

		try {
			sqlParserObjTest.parse(";EMAN_ELBAT MORF * ETELED");
			fail("SyntaxErrorException thrown or AssertionError occured!");
		} catch (SyntaxErrorException e) {
		}

	}

	@Test
	public void testDeleteTableSyntaxValidateFive() {

		/*
		 * This test checks the SyntaxErrorException with deleting the
		 * semicolon.
		 */

		try {
			sqlParserObjTest.parse("DELETE FROM TABLE_NAME WHERE COL=='VAL'");
			fail("SyntaxErrorException thrown or AssertionError occured!");
		} catch (SyntaxErrorException e) {
		}

		/*
		 * This test checks the SyntaxErrorException with invalid SQL command.
		 */

		try {
			sqlParserObjTest.parse("; LOC EREHW EMAN_ELBAT MORF ETELED");
			fail("SyntaxErrorException thrown or AssertionError occured!");
		} catch (SyntaxErrorException e) {
		}

	}

	/*
	 * these tests check the validity of the multiple white spaces within DELETE
	 * SQL command in different forms.
	 */

	@Test
	public void testDeleteTableSyntaxValidateSix() {

		try {
			sqlParserObjTest.parse("                 DELETE                  FROM          TABLE_NAME     ;      ");
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}

		try {
			sqlParserObjTest.parse("       DELETE         *       FROM         TABLE_NAME      ;         ");
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}

	}

	/*
	 * these tests check the validity of the multiple white spaces within DELETE
	 * SQL command in different forms.
	 */

	@Test
	public void testDeleteTableSyntaxValidateSeven() {

		/*
		 * The column here represents data from data type String.
		 */

		try {
			sqlParserObjTest.parse(
					"          DELETE            FROM            TABLE_NAME           WHERE     SOME_COL     =  'SOME_VALUE'    ;       ");
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}

		/*
		 * The column here represents data from data type Integer.
		 */

		try {
			sqlParserObjTest
					.parse("       DELETE        FROM      TABLE_NAME      WHERE   (     SOME_COL   =   999)  ;     ");
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}
	}

	/*
	 * These tests check the case insensitivity of the DELETE SQL command in
	 * different forms.
	 */

	@Test
	public void testDeleteTableSyntaxValidateEight() {
		try {
			sqlParserObjTest.parse("DElEte FrOm taBlE_NaME;");
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}

		try {
			sqlParserObjTest.parse("DelETe * FrOm TaBlE_NAmE;");
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}
	}

	@Test
	public void testDeleteTableSyntaxValidateNine() {
		try {
			sqlParserObjTest.parse("DElEte FrOm taBlE_NaME WherE NaMecol1 = 'NamE';");
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");

		}

		try {
			sqlParserObjTest.parse("DelETe FrOm TaBlE_NAmE wheRE namecOL2 = 55555;");
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}
	}

	/*
	 * these tests check the multiple forms the DELETE SQL command can take and
	 * the parsed data from the SQL command.
	 */

	@Test
	public void testDeleteTableSyntaxParsingOne() {

		try {
			deleteObjAct = sqlParserObjTest.parse("DELETE FROM TABLE_NAME;");
			assertEquals("table_name", ((Delete) deleteObjAct).getTableName().toLowerCase());
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}

		try {
			deleteObjAct = sqlParserObjTest.parse("DELETE * FROM TABLE_NAME;");
			assertEquals("table_name", ((Delete) deleteObjAct).getTableName().toLowerCase());
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}

	}

	/*
	 * these tests check the multiple forms the DELETE SQL command can take plus
	 * checking the parsed data from the commands.
	 */

	@Test
	public void testDeleteTableSyntaxParsingTwo() {

		try {
			deleteObjAct = sqlParserObjTest.parse("DELETE FROM TABLE_NAME Where coL1 = 'vALUe_1';");
			assertEquals("table_name", ((Delete) deleteObjAct).getTableName().toLowerCase());
			Queue<Object> sqlPredicateQ = ((Delete) deleteObjAct).getWhere().getPostfix();
			assertEquals("col1", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals("vALUe_1", ((SQLPredicate) sqlPredicateQ.peek()).getValue());
			assertEquals(Operator.Equal, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}

		try {
			deleteObjAct = sqlParserObjTest.parse("DELETE * FROM TABLE_NAME_88 wHeRe AGE = 5856263;");
			assertEquals("table_name_88", ((Delete) deleteObjAct).getTableName().toLowerCase());
			Queue<Object> sqlPredicateQ = ((Delete) deleteObjAct).getWhere().getPostfix();
			assertEquals("age", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals(5856263, ((SQLPredicate) sqlPredicateQ.peek()).getValue());
			assertEquals(Operator.Equal, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());

		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}

	}

	/*
	 * these tests check the multiple forms the DELETE SQL command can take plus
	 * checking the parsed data from the commands with multiple white spaces.
	 */

	@Test
	public void testDeleteTableSyntaxParsingThree() {

		try {
			deleteObjAct = sqlParserObjTest.parse(
					"         DELETE         FROM       TABLE_NAME        Where     col1     =         'valUE_1'           ;               ");
			assertEquals("table_name", ((Delete) deleteObjAct).getTableName().toLowerCase());
			Queue<Object> sqlPredicateQ = ((Delete) deleteObjAct).getWhere().getPostfix();
			assertEquals("col1", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals("valUE_1", ((SQLPredicate) sqlPredicateQ.peek()).getValue());
			assertEquals(Operator.Equal, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}

		try {
			deleteObjAct = sqlParserObjTest.parse(
					"       DELETE         * FROM             TABLE_NAME_88          wHeRe    (  AGE =       5856263     ) ;     ");
			assertEquals("table_name_88", ((Delete) deleteObjAct).getTableName().toLowerCase());
			Queue<Object> sqlPredicateQ = ((Delete) deleteObjAct).getWhere().getPostfix();
			assertEquals("age", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals(5856263, ((SQLPredicate) sqlPredicateQ.peek()).getValue());
			assertEquals(Operator.Equal, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());

		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}

	}

	@Test
	public void testDeleteTableSyntaxParsingFour() {
		try {
			deleteObjAct = sqlParserObjTest.parse(
					"       DELETE         * FROM             TABLE_NAME_88          wHeRe      AGE >       MONEY      ;     ");
			assertEquals("table_name_88", ((Delete) deleteObjAct).getTableName().toLowerCase());
			Queue<Object> sqlPredicateQ = ((Delete) deleteObjAct).getWhere().getPostfix();
			assertEquals("age", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals("money", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName2().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicateQ.peek()).getValue());
			assertEquals(Operator.GreaterThan, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());

		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}
	}

	@Test
	public void testDeleteTableSyntaxParsingFive() {
		try {
			deleteObjAct = sqlParserObjTest.parse(
					"       DELETE         * FROM             TABLE_NAME_88          wHeRe      AGE >       'MONEY'      ;     ");
			assertEquals("table_name_88", ((Delete) deleteObjAct).getTableName().toLowerCase());
			Queue<Object> sqlPredicateQ = ((Delete) deleteObjAct).getWhere().getPostfix();
			assertEquals("age", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicateQ.peek()).getColumnName2());
			assertEquals("MONEY", ((SQLPredicate) sqlPredicateQ.peek()).getValue());
			assertEquals(Operator.GreaterThan, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());

		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}
	}

	@Test
	public void testDeleteTableSyntaxParsingSix() {
		try {
			deleteObjAct = sqlParserObjTest.parse(
					"       DELETE         * FROM             TABLE_NAME_88          wHeRe      AGE >       \"MONEY\"      ;     ");
			assertEquals("table_name_88", ((Delete) deleteObjAct).getTableName().toLowerCase());
			Queue<Object> sqlPredicateQ = ((Delete) deleteObjAct).getWhere().getPostfix();
			assertEquals("age", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicateQ.peek()).getColumnName2());
			assertEquals("MONEY", ((SQLPredicate) sqlPredicateQ.peek()).getValue());
			assertEquals(Operator.GreaterThan, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());

		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}
	}

	@Test
	public void testDeleteTableSyntaxParsingSeven() {
		try {
			deleteObjAct = sqlParserObjTest.parse(
					"       DELETE         * FROM             TABLE_NAME_88          wHeRe      AGE >=       \"MONEY\"      ;     ");
			assertEquals("table_name_88", ((Delete) deleteObjAct).getTableName().toLowerCase());
			Queue<Object> sqlPredicateQ = ((Delete) deleteObjAct).getWhere().getPostfix();
			assertEquals("age", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicateQ.peek()).getColumnName2());
			assertEquals("MONEY", ((SQLPredicate) sqlPredicateQ.peek()).getValue());
			assertEquals(Operator.GreaterThanOrEqual, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());

		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}
	}

	@Test
	public void testDeleteTableSyntaxParsingEight() {
		try {
			deleteObjAct = sqlParserObjTest.parse(
					"       DELETE         * FROM             TABLE_NAME_88          wHeRe      AGE <=       \"MONEY\"      ;     ");
			assertEquals("table_name_88", ((Delete) deleteObjAct).getTableName().toLowerCase());
			Queue<Object> sqlPredicateQ = ((Delete) deleteObjAct).getWhere().getPostfix();
			assertEquals("age", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicateQ.peek()).getColumnName2());
			assertEquals("MONEY", ((SQLPredicate) sqlPredicateQ.peek()).getValue());
			assertEquals(Operator.SmallerThanOrEqual, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());

		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}
	}

	@Test
	public void testDeleteTableSyntaxParsingNine() {
		try {
			deleteObjAct = sqlParserObjTest.parse(
					"       DELETE         * FROM             TABLE_NAME_88          wHeRe      AGE <=       MONEY     ;     ");
			assertEquals("table_name_88", ((Delete) deleteObjAct).getTableName().toLowerCase());
			Queue<Object> sqlPredicateQ = ((Delete) deleteObjAct).getWhere().getPostfix();
			assertEquals("age", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals("money", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName2().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicateQ.peek()).getValue());
			assertEquals(Operator.SmallerThanOrEqual, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());

		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}
	}

	@Test
	public void testDeleteTableSyntaxParsingTen() {
		try {
			deleteObjAct = sqlParserObjTest.parse(
					"       DELETE         * FROM             TABLE_NAME_88          wHeRe      AGE =       MONEY     ;     ");
			assertEquals("table_name_88", ((Delete) deleteObjAct).getTableName().toLowerCase());
			Queue<Object> sqlPredicateQ = ((Delete) deleteObjAct).getWhere().getPostfix();
			assertEquals("age", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals("money", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName2().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicateQ.peek()).getValue());
			assertEquals(Operator.Equal, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());

		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}
	}

	@Test
	public void testDeleteTableSyntaxParsingEleven() {
		try {
			deleteObjAct = sqlParserObjTest.parse(
					"       DELETE         * FROM             TABLE_NAME_88          wHeRe      AGE !=       MONEY     ;     ");
			assertEquals("table_name_88", ((Delete) deleteObjAct).getTableName().toLowerCase());
			Queue<Object> sqlPredicateQ = ((Delete) deleteObjAct).getWhere().getPostfix();
			assertEquals("age", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals("money", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName2().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicateQ.peek()).getValue());
			assertEquals(Operator.NotEqual, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());

		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}
	}

	@Test
	public void testDeleteTableSyntaxParsingTwelve() {
		try {
			deleteObjAct = sqlParserObjTest.parse(
					"       DELETE         * FROM             TABLE_NAME_88          wHeRe      ((col = 5 ) and (col2 = 6))     ;     ");
			assertEquals("table_name_88", ((Delete) deleteObjAct).getTableName().toLowerCase());
			Queue<Object> sqlPredicateQ = ((Delete) deleteObjAct).getWhere().getPostfix();

			assertEquals("col", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicateQ.peek()).getColumnName2());
			assertEquals(5, ((SQLPredicate) sqlPredicateQ.peek()).getValue());
			assertEquals(Operator.Equal, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());

			sqlPredicateQ.poll();

			assertEquals("col2", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicateQ.peek()).getColumnName2());
			assertEquals(6, ((SQLPredicate) sqlPredicateQ.peek()).getValue());
			assertEquals(Operator.Equal, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());

			sqlPredicateQ.poll();

			assertEquals(BooleanOperator.Operator.And, ((BooleanOperator) sqlPredicateQ.peek()).getOperator());

		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}
	}

	@Test
	public void testDeleteTableSyntaxParsingThirteen() {
		try {
			deleteObjAct = sqlParserObjTest.parse(
					"       DELETE         * FROM             TABLE_NAME_88          wHeRe      (   (   col  !=   5   )  and    (  col2 =      6      )          )     ;     ");
			assertEquals("table_name_88", ((Delete) deleteObjAct).getTableName().toLowerCase());
			Queue<Object> sqlPredicateQ = ((Delete) deleteObjAct).getWhere().getPostfix();

			assertEquals("col", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicateQ.peek()).getColumnName2());
			assertEquals(5, ((SQLPredicate) sqlPredicateQ.peek()).getValue());
			assertEquals(Operator.NotEqual, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());

			sqlPredicateQ.poll();

			assertEquals("col2", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicateQ.peek()).getColumnName2());
			assertEquals(6, ((SQLPredicate) sqlPredicateQ.peek()).getValue());
			assertEquals(Operator.Equal, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());

			sqlPredicateQ.poll();

			assertEquals(BooleanOperator.Operator.And, ((BooleanOperator) sqlPredicateQ.peek()).getOperator());

		} catch (SyntaxErrorException e) {
			e.printStackTrace();
			fail("SyntaxErrorException thrown or AssertionError occured!");

		}
	}

	@Test
	public void testDeleteTableSyntaxParsingFourteen() {
		try {
			deleteObjAct = sqlParserObjTest.parse(
					"       DELETE         * FROM             TABLE_NAME_88          wHeRe      (   (   col  !=   5   )  and    (  col2 =      6      )          )     ;     ");
			assertEquals("table_name_88", ((Delete) deleteObjAct).getTableName().toLowerCase());
			Queue<Object> sqlPredicateQ = ((Delete) deleteObjAct).getWhere().getPostfix();

			assertEquals("col", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicateQ.peek()).getColumnName2());
			assertEquals(5, ((SQLPredicate) sqlPredicateQ.peek()).getValue());
			assertEquals(Operator.NotEqual, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());

			sqlPredicateQ.poll();

			assertEquals("col2", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicateQ.peek()).getColumnName2());
			assertEquals(6, ((SQLPredicate) sqlPredicateQ.peek()).getValue());
			assertEquals(Operator.Equal, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());

			sqlPredicateQ.poll();

			assertEquals(BooleanOperator.Operator.And, ((BooleanOperator) sqlPredicateQ.peek()).getOperator());

		} catch (SyntaxErrorException e) {
			e.printStackTrace();
			fail("SyntaxErrorException thrown or AssertionError occured!");

		}
	}

}
