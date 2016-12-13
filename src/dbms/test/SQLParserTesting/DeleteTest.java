package dbms.test.SQLParserTesting;

import dbms.exception.SyntaxErrorException;
import dbms.sqlparser.SQLParser;
import dbms.sqlparser.sqlInterpreter.BooleanOperator;
import dbms.sqlparser.sqlInterpreter.SQLPredicate;
import dbms.sqlparser.sqlInterpreter.rules.Delete;
import dbms.sqlparser.sqlInterpreter.rules.Expression;
import dbms.util.Operator;
import org.junit.Test;

import java.sql.Date;
import java.util.Queue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class DeleteTest extends SqlParserRef {

	private final SQLParser sqlParserObjTest = super.getSqlParserReference();
	private Expression deleteObjAct;

	/*
	 * these tests check the multiple forms the DELETE SQL command can take.
	 */

	@Test
	public void testDeleteTableSyntaxValidateOne() {

		try {
			sqlParserObjTest.parse("DELETE FROM TABLE_NAME;");
		} catch (SyntaxErrorException e) {
			e.printStackTrace();
			fail("SyntaxErrorException thrown or AssertionError occurred!");
		}

		try {
			sqlParserObjTest.parse("DELETE * FROM TABLE_NAME ;");
		} catch (SyntaxErrorException e) {
			e.printStackTrace();
			fail("SyntaxErrorException thrown or AssertionError occurred!");
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
			e.printStackTrace();
			fail("SyntaxErrorException thrown or AssertionError occurred!");
		}

		/*
		 * The column here represents data from data type Integer.
		 */

		try {
			sqlParserObjTest.parse("DELETE FROM TABLE_NAME WHERE SOME_COL=999;");
		} catch (SyntaxErrorException e) {
			e.printStackTrace();
			fail("SyntaxErrorException thrown or AssertionError occurred!");
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
			fail("SyntaxErrorException thrown or AssertionError occurred!");
		} catch (SyntaxErrorException e) {
			e.printStackTrace();
		}

		/*
		 * This test checks the SyntaxErrorException with invalid SQL command.
		 */

		try {
			sqlParserObjTest.parse(";EMAN_ELBAT MORF ETELED");
			fail("SyntaxErrorException thrown or AssertionError occurred!");
		} catch (SyntaxErrorException e) {
			e.printStackTrace();
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
			fail("SyntaxErrorException thrown or AssertionError occurred!");
		} catch (SyntaxErrorException e) {
			e.printStackTrace();
		}

		/*
		 * This test checks the SyntaxErrorException with invalid SQL command.
		 */

		try {
			sqlParserObjTest.parse(";EMAN_ELBAT MORF * ETELED");
			fail("SyntaxErrorException thrown or AssertionError occurred!");
		} catch (SyntaxErrorException e) {
			e.printStackTrace();
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
			fail("SyntaxErrorException thrown or AssertionError occurred!");
		} catch (SyntaxErrorException e) {
			e.printStackTrace();
		}

		/*
		 * This test checks the SyntaxErrorException with invalid SQL command.
		 */

		try {
			sqlParserObjTest.parse("; LOC EREHW EMAN_ELBAT MORF ETELED");
			fail("SyntaxErrorException thrown or AssertionError occurred!");
		} catch (SyntaxErrorException e) {
			e.printStackTrace();
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
			e.printStackTrace();
			fail("SyntaxErrorException thrown or AssertionError occurred!");
		}

		try {
			sqlParserObjTest.parse("       DELETE         *       FROM         TABLE_NAME      ;         ");
		} catch (SyntaxErrorException e) {
			e.printStackTrace();
			fail("SyntaxErrorException thrown or AssertionError occurred!");
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
			e.printStackTrace();
			fail("SyntaxErrorException thrown or AssertionError occurred!");
		}

		/*
		 * The column here represents data from data type Integer.
		 */

		try {
			sqlParserObjTest
					.parse("       DELETE        FROM      TABLE_NAME      WHERE   (     SOME_COL   =   999)  ;     ");
		} catch (SyntaxErrorException e) {
			e.printStackTrace();
			fail("SyntaxErrorException thrown or AssertionError occurred!");
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
			e.printStackTrace();
			fail("SyntaxErrorException thrown or AssertionError occurred!");
		}

		try {
			sqlParserObjTest.parse("DelETe * FrOm TaBlE_NAmE;");
		} catch (SyntaxErrorException e) {
			e.printStackTrace();
			fail("SyntaxErrorException thrown or AssertionError occurred!");
		}
	}

	@Test
	public void testDeleteTableSyntaxValidateNine() {
		try {
			sqlParserObjTest.parse("DElEte FrOm taBlE_NaME WherE NaMecol1 = 'NamE';");
		} catch (SyntaxErrorException e) {
			e.printStackTrace();
			fail("SyntaxErrorException thrown or AssertionError occurred!");

		}

		try {
			sqlParserObjTest.parse("DelETe FrOm TaBlE_NAmE wheRE namecOL2 = 55555;");
		} catch (SyntaxErrorException e) {
			e.printStackTrace();
			fail("SyntaxErrorException thrown or AssertionError occurred!");
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
			e.printStackTrace();
			fail("SyntaxErrorException thrown or AssertionError occurred!");
		}

		try {
			deleteObjAct = sqlParserObjTest.parse("DELETE * FROM TABLE_NAME;");
			assertEquals("table_name", ((Delete) deleteObjAct).getTableName().toLowerCase());
		} catch (SyntaxErrorException e) {
			e.printStackTrace();
			fail("SyntaxErrorException thrown or AssertionError occurred!");
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
			assertEquals("vALUe_1", ((SQLPredicate) sqlPredicateQ.peek()).getValue().getValue());
			assertEquals(Operator.Equal, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());
		} catch (SyntaxErrorException e) {
			e.printStackTrace();
			fail("SyntaxErrorException thrown or AssertionError occurred!");
		}

		try {
			deleteObjAct = sqlParserObjTest.parse("DELETE * FROM TABLE_NAME_88 wHeRe AGE = 5856263;");
			assertEquals("table_name_88", ((Delete) deleteObjAct).getTableName().toLowerCase());
			Queue<Object> sqlPredicateQ = ((Delete) deleteObjAct).getWhere().getPostfix();
			assertEquals("age", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals(5856263, ((SQLPredicate) sqlPredicateQ.peek()).getValue().getValue());
			assertEquals(Operator.Equal, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());

		} catch (SyntaxErrorException e) {
			e.printStackTrace();
			fail("SyntaxErrorException thrown or AssertionError occurred!");
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
					"         DELETE         FROM       TABLE_NAME     " +
							"   Where     col1     =         'valUE_1'           ;               ");
			assertEquals("table_name", ((Delete) deleteObjAct).getTableName().toLowerCase());
			Queue<Object> sqlPredicateQ = ((Delete) deleteObjAct).getWhere().getPostfix();
			assertEquals("col1", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals("valUE_1", ((SQLPredicate) sqlPredicateQ.peek()).getValue().getValue());
			assertEquals(Operator.Equal, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());
		} catch (SyntaxErrorException e) {
			e.printStackTrace();
			fail("SyntaxErrorException thrown or AssertionError occurred!");
		}

		try {
			deleteObjAct = sqlParserObjTest.parse(
					"       DELETE         * FROM             TABLE_NAME_88         " +
							" wHeRe    (  AGE =       5856263     ) ;     ");
			assertEquals("table_name_88", ((Delete) deleteObjAct).getTableName().toLowerCase());
			Queue<Object> sqlPredicateQ = ((Delete) deleteObjAct).getWhere().getPostfix();
			assertEquals("age", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals(5856263, ((SQLPredicate) sqlPredicateQ.peek()).getValue().getValue());
			assertEquals(Operator.Equal, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());

		} catch (SyntaxErrorException e) {
			e.printStackTrace();
			fail("SyntaxErrorException thrown or AssertionError occurred!");
		}

	}

	@Test
	public void testDeleteTableSyntaxParsingFour() {
		try {
			deleteObjAct = sqlParserObjTest.parse(
					"       DELETE         * FROM             TABLE_NAME_88      " +
							"    wHeRe      AGE >       MONEY      ;     ");
			assertEquals("table_name_88", ((Delete) deleteObjAct).getTableName().toLowerCase());
			Queue<Object> sqlPredicateQ = ((Delete) deleteObjAct).getWhere().getPostfix();
			assertEquals("age", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals("money", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName2().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicateQ.peek()).getValue());
			assertEquals(Operator.GreaterThan, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());

		} catch (SyntaxErrorException e) {
			e.printStackTrace();
			fail("SyntaxErrorException thrown or AssertionError occurred!");
		}
	}

	@Test
	public void testDeleteTableSyntaxParsingFive() {
		try {
			deleteObjAct = sqlParserObjTest.parse(
					"       DELETE         * FROM             TABLE_NAME_88       " +
							"   wHeRe      AGE >       'MONEY'      ;     ");
			assertEquals("table_name_88", ((Delete) deleteObjAct).getTableName().toLowerCase());
			Queue<Object> sqlPredicateQ = ((Delete) deleteObjAct).getWhere().getPostfix();
			assertEquals("age", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicateQ.peek()).getColumnName2());
			assertEquals("MONEY", ((SQLPredicate) sqlPredicateQ.peek()).getValue().getValue());
			assertEquals(Operator.GreaterThan, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());

		} catch (SyntaxErrorException e) {
			e.printStackTrace();
			fail("SyntaxErrorException thrown or AssertionError occurred!");
		}
	}

	@Test
	public void testDeleteTableSyntaxParsingSix() {
		try {
			deleteObjAct = sqlParserObjTest.parse(
					"       DELETE         * FROM             TABLE_NAME_88       " +
							"   wHeRe      AGE >       \"MONEY\"      ;     ");
			assertEquals("table_name_88", ((Delete) deleteObjAct).getTableName().toLowerCase());
			Queue<Object> sqlPredicateQ = ((Delete) deleteObjAct).getWhere().getPostfix();
			assertEquals("age", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicateQ.peek()).getColumnName2());
			assertEquals("MONEY", ((SQLPredicate) sqlPredicateQ.peek()).getValue().getValue());
			assertEquals(Operator.GreaterThan, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());

		} catch (SyntaxErrorException e) {
			e.printStackTrace();
			fail("SyntaxErrorException thrown or AssertionError occurred!");
		}
	}

	@Test
	public void testDeleteTableSyntaxParsingSeven() {
		try {
			deleteObjAct = sqlParserObjTest.parse(
					"       DELETE         * FROM             TABLE_NAME_88       " +
							"   wHeRe      AGE >=       \"MONEY\"      ;     ");
			assertEquals("table_name_88", ((Delete) deleteObjAct).getTableName().toLowerCase());
			Queue<Object> sqlPredicateQ = ((Delete) deleteObjAct).getWhere().getPostfix();
			assertEquals("age", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicateQ.peek()).getColumnName2());
			assertEquals("MONEY", ((SQLPredicate) sqlPredicateQ.peek()).getValue().getValue());
			assertEquals(Operator.GreaterThanOrEqual, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());

		} catch (SyntaxErrorException e) {
			e.printStackTrace();
			fail("SyntaxErrorException thrown or AssertionError occurred!");
		}
	}

	@Test
	public void testDeleteTableSyntaxParsingEight() {
		try {
			deleteObjAct = sqlParserObjTest.parse(
					"       DELETE         * FROM             TABLE_NAME_88       " +
							"   wHeRe      AGE <=       \"MONEY\"      ;     ");
			assertEquals("table_name_88", ((Delete) deleteObjAct).getTableName().toLowerCase());
			Queue<Object> sqlPredicateQ = ((Delete) deleteObjAct).getWhere().getPostfix();
			assertEquals("age", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicateQ.peek()).getColumnName2());
			assertEquals("MONEY", ((SQLPredicate) sqlPredicateQ.peek()).getValue().getValue());
			assertEquals(Operator.SmallerThanOrEqual, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());

		} catch (SyntaxErrorException e) {
			e.printStackTrace();
			fail("SyntaxErrorException thrown or AssertionError occurred!");
		}
	}

	@Test
	public void testDeleteTableSyntaxParsingNine() {
		try {
			deleteObjAct = sqlParserObjTest.parse(
					"       DELETE         * FROM             TABLE_NAME_88        " +
							"  wHeRe      AGE <=       MONEY     ;     ");
			assertEquals("table_name_88", ((Delete) deleteObjAct).getTableName().toLowerCase());
			Queue<Object> sqlPredicateQ = ((Delete) deleteObjAct).getWhere().getPostfix();
			assertEquals("age", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals("money", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName2().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicateQ.peek()).getValue());
			assertEquals(Operator.SmallerThanOrEqual, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());

		} catch (SyntaxErrorException e) {
			e.printStackTrace();
			fail("SyntaxErrorException thrown or AssertionError occurred!");
		}
	}

	@Test
	public void testDeleteTableSyntaxParsingTen() {
		try {
			deleteObjAct = sqlParserObjTest.parse(
					"       DELETE         * FROM             TABLE_NAME_88         " +
							" wHeRe      AGE =       MONEY     ;     ");
			assertEquals("table_name_88", ((Delete) deleteObjAct).getTableName().toLowerCase());
			Queue<Object> sqlPredicateQ = ((Delete) deleteObjAct).getWhere().getPostfix();
			assertEquals("age", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals("money", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName2().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicateQ.peek()).getValue());
			assertEquals(Operator.Equal, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());

		} catch (SyntaxErrorException e) {
			e.printStackTrace();
			fail("SyntaxErrorException thrown or AssertionError occurred!");
		}
	}

	@Test
	public void testDeleteTableSyntaxParsingEleven() {
		try {
			deleteObjAct = sqlParserObjTest.parse(
					"       DELETE         * FROM             TABLE_NAME_88         " +
							" wHeRe      AGE !=       MONEY     ;     ");
			assertEquals("table_name_88", ((Delete) deleteObjAct).getTableName().toLowerCase());
			Queue<Object> sqlPredicateQ = ((Delete) deleteObjAct).getWhere().getPostfix();
			assertEquals("age", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals("money", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName2().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicateQ.peek()).getValue());
			assertEquals(Operator.NotEqual, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());

		} catch (SyntaxErrorException e) {
			e.printStackTrace();
			fail("SyntaxErrorException thrown or AssertionError occurred!");
		}
	}

	@Test
	public void testDeleteTableSyntaxParsingTwelve() {
		try {
			deleteObjAct = sqlParserObjTest.parse(
					"       DELETE         * FROM             TABLE_NAME_88        " +
							"  wHeRe      ((col = 5 ) and (col2 = 6))     ;     ");
			assertEquals("table_name_88", ((Delete) deleteObjAct).getTableName().toLowerCase());
			Queue<Object> sqlPredicateQ = ((Delete) deleteObjAct).getWhere().getPostfix();

			assertEquals("col", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicateQ.peek()).getColumnName2());
			assertEquals(5, ((SQLPredicate) sqlPredicateQ.peek()).getValue().getValue());
			assertEquals(Operator.Equal, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());

			sqlPredicateQ.poll();

			assertEquals("col2", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicateQ.peek()).getColumnName2());
			assertEquals(6, ((SQLPredicate) sqlPredicateQ.peek()).getValue().getValue());
			assertEquals(Operator.Equal, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());

			sqlPredicateQ.poll();

			assertEquals(BooleanOperator.Operator.And, ((BooleanOperator) sqlPredicateQ.peek()).getOperator());

		} catch (SyntaxErrorException e) {
			e.printStackTrace();
			fail("SyntaxErrorException thrown or AssertionError occurred!");
		}
	}

	@Test
	public void testDeleteTableSyntaxParsingThirteen() {
		try {
			deleteObjAct = sqlParserObjTest.parse(
					"       DELETE         * FROM             TABLE_NAME_88         " +
							" wHeRe      (   (   col  !=   5   )  and    (  col2 =      6      )          )     ;     ");
			assertEquals("table_name_88", ((Delete) deleteObjAct).getTableName().toLowerCase());
			Queue<Object> sqlPredicateQ = ((Delete) deleteObjAct).getWhere().getPostfix();

			assertEquals("col", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicateQ.peek()).getColumnName2());
			assertEquals(5, ((SQLPredicate) sqlPredicateQ.peek()).getValue().getValue());
			assertEquals(Operator.NotEqual, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());

			sqlPredicateQ.poll();

			assertEquals("col2", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicateQ.peek()).getColumnName2());
			assertEquals(6, ((SQLPredicate) sqlPredicateQ.peek()).getValue().getValue());
			assertEquals(Operator.Equal, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());

			sqlPredicateQ.poll();

			assertEquals(BooleanOperator.Operator.And, ((BooleanOperator) sqlPredicateQ.peek()).getOperator());

		} catch (SyntaxErrorException e) {
			e.printStackTrace();
			fail("SyntaxErrorException thrown or AssertionError occurred!");

		}
	}

	@Test
	public void testDeleteTableSyntaxParsingFourteen() {
		try {
			deleteObjAct = sqlParserObjTest.parse(
					"       DELETE         * FROM             TABLE_NAME_88        " +
							"  wHeRe      (   (   col  !=   5   )  and    (  col2 =      6      )          )     ;     ");
			assertEquals("table_name_88", ((Delete) deleteObjAct).getTableName().toLowerCase());
			Queue<Object> sqlPredicateQ = ((Delete) deleteObjAct).getWhere().getPostfix();

			assertEquals("col", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicateQ.peek()).getColumnName2());
			assertEquals(5, ((SQLPredicate) sqlPredicateQ.peek()).getValue().getValue());
			assertEquals(Operator.NotEqual, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());

			sqlPredicateQ.poll();

			assertEquals("col2", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicateQ.peek()).getColumnName2());
			assertEquals(6, ((SQLPredicate) sqlPredicateQ.peek()).getValue().getValue());
			assertEquals(Operator.Equal, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());

			sqlPredicateQ.poll();

			assertEquals(BooleanOperator.Operator.And, ((BooleanOperator) sqlPredicateQ.peek()).getOperator());

		} catch (SyntaxErrorException e) {
			e.printStackTrace();
			fail("SyntaxErrorException thrown or AssertionError occurred!");

		}
	}


	@Test
	public void testDeleteTableSyntaxParsingFifteen() {
		try {
			deleteObjAct = sqlParserObjTest.parse(
					"       DELETE         * FROM             TABLE_NAME_88         " +
							" wHeRe      (   (   col  !=   5.223   )  and    (  col2 =      6.255      )          )     ;     ");
			assertEquals("table_name_88", ((Delete) deleteObjAct).getTableName().toLowerCase());
			Queue<Object> sqlPredicateQ = ((Delete) deleteObjAct).getWhere().getPostfix();

			assertEquals("col", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicateQ.peek()).getColumnName2());
			assertEquals((float)5.223, ((SQLPredicate) sqlPredicateQ.peek()).getValue().getValue());
			assertEquals(Operator.NotEqual, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());

			sqlPredicateQ.poll();

			assertEquals("col2", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicateQ.peek()).getColumnName2());
			assertEquals((float)6.255, ((SQLPredicate) sqlPredicateQ.peek()).getValue().getValue());
			assertEquals(Operator.Equal, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());

			sqlPredicateQ.poll();

			assertEquals(BooleanOperator.Operator.And, ((BooleanOperator) sqlPredicateQ.peek()).getOperator());

		} catch (SyntaxErrorException e) {
			e.printStackTrace();
			fail("SyntaxErrorException thrown or AssertionError occurred!");

		}
	}

	@Test
	public void testDeleteTableSyntaxParsingSixteen() {
		try {
			deleteObjAct = sqlParserObjTest.parse(
					"       DELETE         * FROM             TABLE_NAME_88         " +
							" wHeRe      (   (   col  !=   -5.223   )  and    (  col2 =      -6.255      )          )     ;     ");
			assertEquals("table_name_88", ((Delete) deleteObjAct).getTableName().toLowerCase());
			Queue<Object> sqlPredicateQ = ((Delete) deleteObjAct).getWhere().getPostfix();

			assertEquals("col", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicateQ.peek()).getColumnName2());
			assertEquals((float)-5.223, ((SQLPredicate) sqlPredicateQ.peek()).getValue().getValue());
			assertEquals(Operator.NotEqual, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());

			sqlPredicateQ.poll();

			assertEquals("col2", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicateQ.peek()).getColumnName2());
			assertEquals((float)-6.255, ((SQLPredicate) sqlPredicateQ.peek()).getValue().getValue());
			assertEquals(Operator.Equal, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());

			sqlPredicateQ.poll();

			assertEquals(BooleanOperator.Operator.And, ((BooleanOperator) sqlPredicateQ.peek()).getOperator());

		} catch (SyntaxErrorException e) {
			e.printStackTrace();
			fail("SyntaxErrorException thrown or AssertionError occurred!");

		}
	}

	@Test
	public void testDeleteTableSyntaxParsingSeventeen() {
		try {
			deleteObjAct = sqlParserObjTest.parse(
					"       DELETE         * FROM             TABLE_NAME_88         " +
							" wHeRe      (   (   col  !=   5.223333333333333   )  and  " +
							"  (  col2 =      6.255788787878787      )          )       ;   ");
			assertEquals("table_name_88", ((Delete) deleteObjAct).getTableName().toLowerCase());
			Queue<Object> sqlPredicateQ = ((Delete) deleteObjAct).getWhere().getPostfix();

			assertEquals("col", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicateQ.peek()).getColumnName2());
			assertEquals((float)5.223333333333333, ((SQLPredicate) sqlPredicateQ.peek()).getValue().getValue());
			assertEquals(Operator.NotEqual, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());

			sqlPredicateQ.poll();

			assertEquals("col2", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicateQ.peek()).getColumnName2());
			assertEquals((float)6.255788787878787, ((SQLPredicate) sqlPredicateQ.peek()).getValue().getValue());
			assertEquals(Operator.Equal, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());

			sqlPredicateQ.poll();

			assertEquals(BooleanOperator.Operator.And, ((BooleanOperator) sqlPredicateQ.peek()).getOperator());

		} catch (SyntaxErrorException e) {
			e.printStackTrace();
			fail("SyntaxErrorException thrown or AssertionError occurred!");

		}
	}

	@Test
	public void testDeleteTableSyntaxParsingEighteen() {
		try {
			deleteObjAct = sqlParserObjTest.parse(
					"       DELETE         * FROM             TABLE_NAME_88         " +
							" wHeRe      (   (   col  !=   -5.223333333333333   )  and  " +
							"  (  col2 =      -6.255788787878787      )          )     ;     ");
			assertEquals("table_name_88", ((Delete) deleteObjAct).getTableName().toLowerCase());
			Queue<Object> sqlPredicateQ = ((Delete) deleteObjAct).getWhere().getPostfix();

			assertEquals("col", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicateQ.peek()).getColumnName2());
			assertEquals((float)-5.223333333333333, ((SQLPredicate) sqlPredicateQ.peek()).getValue().getValue());
			assertEquals(Operator.NotEqual, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());

			sqlPredicateQ.poll();

			assertEquals("col2", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicateQ.peek()).getColumnName2());
			assertEquals((float)-6.255788787878787, ((SQLPredicate) sqlPredicateQ.peek()).getValue().getValue());
			assertEquals(Operator.Equal, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());

			sqlPredicateQ.poll();

			assertEquals(BooleanOperator.Operator.And, ((BooleanOperator) sqlPredicateQ.peek()).getOperator());

		} catch (SyntaxErrorException e) {
			e.printStackTrace();
			fail("SyntaxErrorException thrown or AssertionError occurred!");

		}
	}

	@Test
	public void testDeleteTableSyntaxParsingNineteen() {
		try {
			deleteObjAct = sqlParserObjTest.parse(
					"       DELETE         * FROM             TABLE_NAME_88         " +
							" wHeRe      (   (   col  !=   5.223333333333333   )  and  " +
							"  (  col2 =      6.255788787878787      )          )     ;    ");
			assertEquals("table_name_88", ((Delete) deleteObjAct).getTableName().toLowerCase());
			Queue<Object> sqlPredicateQ = ((Delete) deleteObjAct).getWhere().getPostfix();

			assertEquals("col", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicateQ.peek()).getColumnName2());
			assertEquals((float)5.223333333333333, ((SQLPredicate) sqlPredicateQ.peek()).getValue().getValue());
			assertEquals(Operator.NotEqual, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());

			sqlPredicateQ.poll();

			assertEquals("col2", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicateQ.peek()).getColumnName2());
			assertEquals((float)6.255788787878787, ((SQLPredicate) sqlPredicateQ.peek()).getValue().getValue());
			assertEquals(Operator.Equal, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());

			sqlPredicateQ.poll();

			assertEquals(BooleanOperator.Operator.And, ((BooleanOperator) sqlPredicateQ.peek()).getOperator());

		} catch (SyntaxErrorException e) {
			e.printStackTrace();
			fail("SyntaxErrorException thrown or AssertionError occurred!");

		}
	}

	@Test
	public void testDeleteTableSyntaxParsingTwenty() {
		try {
			deleteObjAct = sqlParserObjTest.parse(
					"       DELETE         * FROM             TABLE_NAME_88         " +
							" wHeRe      (   (   col  !=   --5.223333333333333   )  and  " +
							"  (  col2 =      --6.255788787878787      )          )     ;    ");
			assertEquals("table_name_88", ((Delete) deleteObjAct).getTableName().toLowerCase());
			Queue<Object> sqlPredicateQ = ((Delete) deleteObjAct).getWhere().getPostfix();

			assertEquals("col", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicateQ.peek()).getColumnName2());
			assertEquals((float)5.223333333333333, ((SQLPredicate) sqlPredicateQ.peek()).getValue().getValue());
			assertEquals(Operator.NotEqual, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());

			sqlPredicateQ.poll();

			assertEquals("col2", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicateQ.peek()).getColumnName2());
			assertEquals((float)6.255788787878787, ((SQLPredicate) sqlPredicateQ.peek()).getValue().getValue());
			assertEquals(Operator.Equal, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());

			sqlPredicateQ.poll();

			assertEquals(BooleanOperator.Operator.And, ((BooleanOperator) sqlPredicateQ.peek()).getOperator());
			fail("SyntaxErrorException thrown or AssertionError occurred!");

		} catch (SyntaxErrorException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testDeleteTableSyntaxParsingTwentyOne() {
		try {
			deleteObjAct = sqlParserObjTest.parse(
					"       DELETE         * FROM             TABLE_NAME_88         " +
							" wHeRe      (   (   col  !=   0.223333333333333   )  and  " +
							"  (  col2 =      -0.255788787878787      )          )     ;    ");
			assertEquals("table_name_88", ((Delete) deleteObjAct).getTableName().toLowerCase());
			Queue<Object> sqlPredicateQ = ((Delete) deleteObjAct).getWhere().getPostfix();

			assertEquals("col", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicateQ.peek()).getColumnName2());
			assertEquals((float)0.223333333333333, ((SQLPredicate) sqlPredicateQ.peek()).getValue().getValue());
			assertEquals(Operator.NotEqual, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());

			sqlPredicateQ.poll();

			assertEquals("col2", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicateQ.peek()).getColumnName2());
			assertEquals((float)-0.255788787878787, ((SQLPredicate) sqlPredicateQ.peek()).getValue().getValue());
			assertEquals(Operator.Equal, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());

			sqlPredicateQ.poll();

			assertEquals(BooleanOperator.Operator.And, ((BooleanOperator) sqlPredicateQ.peek()).getOperator());

		} catch (SyntaxErrorException e) {
			e.printStackTrace();
			fail("SyntaxErrorException thrown or AssertionError occurred!");

		}
	}

	@Test
	public void testDeleteTableSyntaxParsingTwentyTwo() {
		try {
			deleteObjAct = sqlParserObjTest.parse(
					"       DELETE         * FROM             TABLE_NAME_88         " +
							" wHeRe      (   (   col  !=   -1500   )  and  " +
							"  (  col2 =      -1000000000      )          )     ;    ");
			assertEquals("table_name_88", ((Delete) deleteObjAct).getTableName().toLowerCase());
			Queue<Object> sqlPredicateQ = ((Delete) deleteObjAct).getWhere().getPostfix();

			assertEquals("col", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicateQ.peek()).getColumnName2());
			assertEquals(-1500, ((SQLPredicate) sqlPredicateQ.peek()).getValue().getValue());
			assertEquals(Operator.NotEqual, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());

			sqlPredicateQ.poll();

			assertEquals("col2", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicateQ.peek()).getColumnName2());
			assertEquals(-1000000000, ((SQLPredicate) sqlPredicateQ.peek()).getValue().getValue());
			assertEquals(Operator.Equal, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());

			sqlPredicateQ.poll();

			assertEquals(BooleanOperator.Operator.And, ((BooleanOperator) sqlPredicateQ.peek()).getOperator());

		} catch (SyntaxErrorException e) {
			e.printStackTrace();
			fail("SyntaxErrorException thrown or AssertionError occurred!");

		}
	}

	@Test
	public void testDeleteTableSyntaxParsingTwentyThree() {
		try {
			deleteObjAct = sqlParserObjTest.parse(
					"       DELETE         * FROM             TABLE_NAME_88         " +
							" wHeRe      (   (   col  !=   -1500   )  and  " +
							"  (  col2 =      'ANAShARBY'     )  or " +
							"( col33 >= 100.50082602600000454     )    )     ;    ");
			assertEquals("table_name_88", ((Delete) deleteObjAct).getTableName().toLowerCase());
			Queue<Object> sqlPredicateQ = ((Delete) deleteObjAct).getWhere().getPostfix();

			assertEquals("col", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicateQ.peek()).getColumnName2());
			assertEquals(-1500, ((SQLPredicate) sqlPredicateQ.peek()).getValue().getValue());
			assertEquals(Operator.NotEqual, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());

			sqlPredicateQ.poll();

			assertEquals("col2", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicateQ.peek()).getColumnName2());
			assertEquals("ANAShARBY", ((SQLPredicate) sqlPredicateQ.peek()).getValue().getValue());
			assertEquals(Operator.Equal, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());

			sqlPredicateQ.poll();

			assertEquals(BooleanOperator.Operator.And, ((BooleanOperator) sqlPredicateQ.peek()).getOperator());

			sqlPredicateQ.poll();

			assertEquals("col33", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicateQ.peek()).getColumnName2());
			assertEquals((float)100.50082602600000454, ((SQLPredicate) sqlPredicateQ.peek()).getValue().getValue());
			assertEquals(Operator.GreaterThanOrEqual, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());

			sqlPredicateQ.poll();

			assertEquals(BooleanOperator.Operator.Or, ((BooleanOperator) sqlPredicateQ.peek()).getOperator());



		} catch (SyntaxErrorException e) {
			e.printStackTrace();
			fail("SyntaxErrorException thrown or AssertionError occurred!");

		}
	}

	@Test
	public void testDeleteTableSyntaxParsingTwentyFour() {
		try {
			deleteObjAct = sqlParserObjTest.parse(
					"       DELETE         * FROM             TABLE_NAME_88         " +
							" wHeRe      (   (   col  !=   -1500   )  and  " +
							"  (  col2 <      \"TOLBA\"     )  or " +
							"( col33 <= 0.50082602600000454     )    )     ;    ");
			assertEquals("table_name_88", ((Delete) deleteObjAct).getTableName().toLowerCase());
			Queue<Object> sqlPredicateQ = ((Delete) deleteObjAct).getWhere().getPostfix();

			assertEquals("col", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicateQ.peek()).getColumnName2());
			assertEquals(-1500, ((SQLPredicate) sqlPredicateQ.peek()).getValue().getValue());
			assertEquals(Operator.NotEqual, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());

			sqlPredicateQ.poll();

			assertEquals("col2", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicateQ.peek()).getColumnName2());
			assertEquals("TOLBA", ((SQLPredicate) sqlPredicateQ.peek()).getValue().getValue());
			assertEquals(Operator.SmallerThan, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());

			sqlPredicateQ.poll();

			assertEquals(BooleanOperator.Operator.And, ((BooleanOperator) sqlPredicateQ.peek()).getOperator());

			sqlPredicateQ.poll();

			assertEquals("col33", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicateQ.peek()).getColumnName2());
			assertEquals((float)0.50082602600000454, ((SQLPredicate) sqlPredicateQ.peek()).getValue().getValue());
			assertEquals(Operator.SmallerThanOrEqual, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());

			sqlPredicateQ.poll();

			assertEquals(BooleanOperator.Operator.Or, ((BooleanOperator) sqlPredicateQ.peek()).getOperator());



		} catch (SyntaxErrorException e) {
			e.printStackTrace();
			fail("SyntaxErrorException thrown or AssertionError occurred!");

		}
	}

	@Test
	public void testDeleteTableSyntaxParsingTwentyFive() {
		try {
			deleteObjAct = sqlParserObjTest.parse(
					"       DELETE         * FROM             TABLE_NAME_88         " +
							" wHeRe      (   (   col  !=   -1500   )  and  " +
							"  (  col2 <      \"TOLBA\"     )  or " +
							"( col33 <= 0.50082602600000454     )  and " +
							"(  col55_NAME    >      'IYaKouTY'  ) )     ;    ");
			assertEquals("table_name_88", ((Delete) deleteObjAct).getTableName().toLowerCase());
			Queue<Object> sqlPredicateQ = ((Delete) deleteObjAct).getWhere().getPostfix();

			assertEquals("col", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicateQ.peek()).getColumnName2());
			assertEquals(-1500, ((SQLPredicate) sqlPredicateQ.peek()).getValue().getValue());
			assertEquals(Operator.NotEqual, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());

			sqlPredicateQ.poll();

			assertEquals("col2", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicateQ.peek()).getColumnName2());
			assertEquals("TOLBA", ((SQLPredicate) sqlPredicateQ.peek()).getValue().getValue());
			assertEquals(Operator.SmallerThan, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());

			sqlPredicateQ.poll();

			assertEquals(BooleanOperator.Operator.And, ((BooleanOperator) sqlPredicateQ.peek()).getOperator());

			sqlPredicateQ.poll();

			assertEquals("col33", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicateQ.peek()).getColumnName2());
			assertEquals((float)0.50082602600000454, ((SQLPredicate) sqlPredicateQ.peek()).getValue().getValue());
			assertEquals(Operator.SmallerThanOrEqual, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());

			sqlPredicateQ.poll();

			assertEquals("col55_name", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicateQ.peek()).getColumnName2());
			assertEquals("IYaKouTY", ((SQLPredicate) sqlPredicateQ.peek()).getValue().getValue());
			assertEquals(Operator.GreaterThan, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());

			sqlPredicateQ.poll();

			assertEquals(BooleanOperator.Operator.And, ((BooleanOperator) sqlPredicateQ.peek()).getOperator());

			sqlPredicateQ.poll();

			assertEquals(BooleanOperator.Operator.Or, ((BooleanOperator) sqlPredicateQ.peek()).getOperator());

		} catch (SyntaxErrorException e) {
			e.printStackTrace();
			fail("SyntaxErrorException thrown or AssertionError occurred!");

		}
	}

	@Test
	public void testDeleteTableSyntaxParsingTwentySix() {
		try {
			deleteObjAct = sqlParserObjTest.parse(
					"       DELETE         * FROM             TABLE_NAME_88         " +
							" wHeRe      AGE != '1996-01-10'           ;     ");
			assertEquals("table_name_88", ((Delete) deleteObjAct).getTableName().toLowerCase());
			Queue<Object> sqlPredicateQ = ((Delete) deleteObjAct).getWhere().getPostfix();
			assertEquals("age", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicateQ.peek()).getColumnName2());
			assertEquals("1996-01-10", ((SQLPredicate) sqlPredicateQ.peek()).getValue().getValue().toString());
			assertEquals(Operator.NotEqual, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());


		} catch (SyntaxErrorException e) {
			e.printStackTrace();
			fail("SyntaxErrorException thrown or AssertionError occurred!");
		}
	}

	@Test
	public void testDeleteTableSyntaxParsingTwentySeven() {
		try {
			deleteObjAct = sqlParserObjTest.parse(
					"       DELETE         * FROM             TABLE_NAME_88         " +
							" wHeRe      (   (   col  !=   -1500   )  and  " +
							"  (  col2 <      \"TOLBA\"     )  or " +
							"( col33 <= 0.50082602600000454     )  and " +
							"(  col55_NAME    >      '1988-1-17'  ) )     ;    ");
			assertEquals("table_name_88", ((Delete) deleteObjAct).getTableName().toLowerCase());
			Queue<Object> sqlPredicateQ = ((Delete) deleteObjAct).getWhere().getPostfix();

			assertEquals("col", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicateQ.peek()).getColumnName2());
			assertEquals(-1500, ((SQLPredicate) sqlPredicateQ.peek()).getValue().getValue());
			assertEquals(Operator.NotEqual, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());

			sqlPredicateQ.poll();

			assertEquals("col2", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicateQ.peek()).getColumnName2());
			assertEquals("TOLBA", ((SQLPredicate) sqlPredicateQ.peek()).getValue().getValue());
			assertEquals(Operator.SmallerThan, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());

			sqlPredicateQ.poll();

			assertEquals(BooleanOperator.Operator.And, ((BooleanOperator) sqlPredicateQ.peek()).getOperator());

			sqlPredicateQ.poll();

			assertEquals("col33", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicateQ.peek()).getColumnName2());
			assertEquals((float)0.50082602600000454, ((SQLPredicate) sqlPredicateQ.peek()).getValue().getValue());
			assertEquals(Operator.SmallerThanOrEqual, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());

			sqlPredicateQ.poll();

			assertEquals("col55_name", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicateQ.peek()).getColumnName2());
			assertEquals("1988-01-17", ((SQLPredicate) sqlPredicateQ.peek()).getValue().getValue().toString());
			assertEquals(Operator.GreaterThan, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());

			sqlPredicateQ.poll();

			assertEquals(BooleanOperator.Operator.And, ((BooleanOperator) sqlPredicateQ.peek()).getOperator());

			sqlPredicateQ.poll();

			assertEquals(BooleanOperator.Operator.Or, ((BooleanOperator) sqlPredicateQ.peek()).getOperator());

		} catch (SyntaxErrorException e) {
			e.printStackTrace();
			fail("SyntaxErrorException thrown or AssertionError occurred!");

		}
	}

	@Test
	public void testDeleteTableSyntaxParsingTwentyEight() {
		try {
			deleteObjAct = sqlParserObjTest.parse(
					"       DELETE         * FROM             TABLE_NAME_88         " +
							" wHeRe      AGE != 1996-01-10           ;     ");
			assertEquals("table_name_88", ((Delete) deleteObjAct).getTableName().toLowerCase());
			Queue<Object> sqlPredicateQ = ((Delete) deleteObjAct).getWhere().getPostfix();
			assertEquals("age", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicateQ.peek()).getColumnName2());
			assertEquals("1996-01-10", ((SQLPredicate) sqlPredicateQ.peek()).getValue().getValue().toString());
			assertEquals(Operator.NotEqual, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());
			fail("SyntaxErrorException thrown or AssertionError occurred!");

		} catch (SyntaxErrorException e) {
			e.printStackTrace();
		}
	}

}
