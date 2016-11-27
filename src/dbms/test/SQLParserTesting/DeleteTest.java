//package dbms.test.SQLParserTesting;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.fail;
//
//import org.junit.Test;
//
//import dbms.exception.SyntaxErrorException;
//import dbms.sqlparser.SQLParser;
//import dbms.sqlparser.sqlInterpreter.SQLPredicate;
//import dbms.sqlparser.sqlInterpreter.rules.Delete;
//import dbms.sqlparser.sqlInterpreter.rules.Expression;
//import dbms.util.Operator;
//
//public class DeleteTest extends SqlParserRef {
//
//	private final SQLParser sqlParserObjTest = super.getSqlParserReference();
//	private Expression deleteObjAct;
//
//	/*
//	 * these tests check the multiple forms the DELETE SQL command can take.
//	 */
//
//	@Test
//	public void testDeleteTableSyntaxValidateOne() {
//
//		try {
//			sqlParserObjTest.parse("DELETE FROM TABLE_NAME;");
//		} catch (SyntaxErrorException e) {
//			fail("SyntaxErrorException thrown or AssertionError occured!");
//		}
//
//		try {
//			sqlParserObjTest.parse("DELETE * FROM TABLE_NAME;");
//		} catch (SyntaxErrorException e) {
//			fail("SyntaxErrorException thrown or AssertionError occured!");
//		}
//
//	}
//
//	@Test
//	public void testDeleteTableSyntaxValidateTwo() {
//
//		/*
//		 * The column here represents data from data type String.
//		 */
//
//		try {
//			sqlParserObjTest.parse("DELETE FROM TABLE_NAME WHERE SOME_COL='SOME_VALUE';");
//		} catch (SyntaxErrorException e) {
//			fail("SyntaxErrorException thrown or AssertionError occured!");
//		}
//
//		/*
//		 * The column here represents data from data type Integer.
//		 */
//
//		try {
//			sqlParserObjTest.parse("DELETE FROM TABLE_NAME WHERE SOME_COL=999;");
//		} catch (SyntaxErrorException e) {
//			fail("SyntaxErrorException thrown or AssertionError occured!");
//		}
//	}
//
//	/*
//	 * This test checks that the catch clause will get the SyntaxErrorException.
//	 */
//
//	@Test
//	public void testDeleteTableSyntaxValidateThree() {
//
//		/*
//		 * This test checks the SyntaxErrorException with deleting the
//		 * semicolon.
//		 */
//
//		try {
//			sqlParserObjTest.parse("DELETE FROM TABLE_NAME");
//			fail("SyntaxErrorException thrown or AssertionError occured!");
//		} catch (SyntaxErrorException e) {
//		}
//
//		/*
//		 * This test checks the SyntaxErrorException with invalid SQL command.
//		 */
//
//		try {
//			sqlParserObjTest.parse(";EMAN_ELBAT MORF ETELED");
//			fail("SyntaxErrorException thrown or AssertionError occured!");
//		} catch (SyntaxErrorException e) {
//		}
//
//	}
//
//	@Test
//	public void testDeleteTableSyntaxValidateFour() {
//
//		/*
//		 * This test checks the SyntaxErrorException with deleting the
//		 * semicolon.
//		 */
//
//		try {
//			sqlParserObjTest.parse("DELETE * FROM TABLE_NAME");
//			fail("SyntaxErrorException thrown or AssertionError occured!");
//		} catch (SyntaxErrorException e) {
//		}
//
//		/*
//		 * This test checks the SyntaxErrorException with invalid SQL command.
//		 */
//
//		try {
//			sqlParserObjTest.parse(";EMAN_ELBAT MORF * ETELED");
//			fail("SyntaxErrorException thrown or AssertionError occured!");
//		} catch (SyntaxErrorException e) {
//		}
//
//	}
//
//	@Test
//	public void testDeleteTableSyntaxValidateFive() {
//
//		/*
//		 * This test checks the SyntaxErrorException with deleting the
//		 * semicolon.
//		 */
//
//		try {
//			sqlParserObjTest.parse("DELETE FROM TABLE_NAME WHERE COL='VAL'");
//			fail("SyntaxErrorException thrown or AssertionError occured!");
//		} catch (SyntaxErrorException e) {
//		}
//
//		/*
//		 * This test checks the SyntaxErrorException with invalid SQL command.
//		 */
//
//		try {
//			sqlParserObjTest.parse("; LOC EREHW EMAN_ELBAT MORF ETELED");
//			fail("SyntaxErrorException thrown or AssertionError occured!");
//		} catch (SyntaxErrorException e) {
//		}
//
//	}
//
//	/*
//	 * these tests check the validity of the multiple white spaces within DELETE
//	 * SQL command in different forms.
//	 */
//
//	@Test
//	public void testDeleteTableSyntaxValidateSix() {
//
//		try {
//			sqlParserObjTest.parse("                 DELETE                  FROM          TABLE_NAME     ;      ");
//		} catch (SyntaxErrorException e) {
//			fail("SyntaxErrorException thrown or AssertionError occured!");
//		}
//
//		try {
//			sqlParserObjTest.parse("       DELETE         *       FROM         TABLE_NAME      ;         ");
//		} catch (SyntaxErrorException e) {
//			fail("SyntaxErrorException thrown or AssertionError occured!");
//		}
//
//	}
//
//	/*
//	 * these tests check the validity of the multiple white spaces within DELETE
//	 * SQL command in different forms.
//	 */
//
//	@Test
//	public void testDeleteTableSyntaxValidateSeven() {
//
//		/*
//		 * The column here represents data from data type String.
//		 */
//
//		try {
//			sqlParserObjTest.parse(
//					"          DELETE            FROM            TABLE_NAME           WHERE     SOME_COL     =  'SOME_VALUE'    ;       ");
//		} catch (SyntaxErrorException e) {
//			fail("SyntaxErrorException thrown or AssertionError occured!");
//		}
//
//		/*
//		 * The column here represents data from data type Integer.
//		 */
//
//		try {
//			sqlParserObjTest
//					.parse("       DELETE        FROM      TABLE_NAME      WHERE     SOME_COL   =   999  ;     ");
//		} catch (SyntaxErrorException e) {
//			fail("SyntaxErrorException thrown or AssertionError occured!");
//		}
//	}
//
//	/*
//	 * These tests check the case insensitivity of the DELETE SQL command in
//	 * different forms.
//	 */
//
//	@Test
//	public void testDeleteTableSyntaxValidateEight() {
//		try {
//			sqlParserObjTest.parse("DElEte FrOm taBlE_NaME;");
//		} catch (SyntaxErrorException e) {
//			fail("SyntaxErrorException thrown or AssertionError occured!");
//		}
//
//		try {
//			sqlParserObjTest.parse("DelETe * FrOm TaBlE_NAmE;");
//		} catch (SyntaxErrorException e) {
//			fail("SyntaxErrorException thrown or AssertionError occured!");
//		}
//	}
//
//	@Test
//	public void testDeleteTableSyntaxValidateNine() {
//		try {
//			sqlParserObjTest.parse("DElEte FrOm taBlE_NaME WherE NaMecol1 = 'NamE';");
//		} catch (SyntaxErrorException e) {
//			fail("SyntaxErrorException thrown or AssertionError occured!");
//
//		}
//
//		try {
//			sqlParserObjTest.parse("DelETe FrOm TaBlE_NAmE wheRE namecOL2 = 55555;");
//		} catch (SyntaxErrorException e) {
//			fail("SyntaxErrorException thrown or AssertionError occured!");
//		}
//	}
//
//	/*
//	 * these tests check the multiple forms the DELETE SQL command can take and
//	 * the parsed data from the SQL command.
//	 */
//
//	@Test
//	public void testDeleteTableSyntaxParsingOne() {
//
//		try {
//			deleteObjAct = sqlParserObjTest.parse("DELETE FROM TABLE_NAME;");
//			assertEquals("table_name", ((Delete) deleteObjAct).getTableName().toLowerCase());
//		} catch (SyntaxErrorException e) {
//			fail("SyntaxErrorException thrown or AssertionError occured!");
//		}
//
//		try {
//			deleteObjAct = sqlParserObjTest.parse("DELETE * FROM TABLE_NAME;");
//			assertEquals("table_name", ((Delete) deleteObjAct).getTableName().toLowerCase());
//		} catch (SyntaxErrorException e) {
//			fail("SyntaxErrorException thrown or AssertionError occured!");
//		}
//
//	}
//
//	/*
//	 * these tests check the multiple forms the DELETE SQL command can take plus
//	 * checking the parsed data from the commands.
//	 */
//
//	@Test
//	public void testDeleteTableSyntaxParsingTwo() {
//
//		try {
//			deleteObjAct = sqlParserObjTest.parse("DELETE FROM TABLE_NAME Where coL1 = 'vALUe_1';");
//			assertEquals("table_name", ((Delete) deleteObjAct).getTableName().toLowerCase());
//			Object[] sqlPredicateArray = ((Delete) deleteObjAct).getWhere().getPredicates().toArray();
//			assertEquals("col1", ((SQLPredicate) sqlPredicateArray[0]).getColumnName().toLowerCase());
//			assertEquals("vALUe_1", ((SQLPredicate) sqlPredicateArray[0]).getValue());
//			assertEquals(Operator.Equal, ((SQLPredicate) sqlPredicateArray[0]).getOperator());
//		} catch (SyntaxErrorException e) {
//			fail("SyntaxErrorException thrown or AssertionError occured!");
//		}
//
//		try {
//			deleteObjAct = sqlParserObjTest.parse("DELETE * FROM TABLE_NAME_88 wHeRe AGE = 5856263;");
//			assertEquals("table_name_88", ((Delete) deleteObjAct).getTableName().toLowerCase());
//			Object[] sqlPredicateArray = ((Delete) deleteObjAct).getWhere().getPredicates().toArray();
//			assertEquals("age", ((SQLPredicate) sqlPredicateArray[0]).getColumnName().toLowerCase());
//			assertEquals(5856263, ((SQLPredicate) sqlPredicateArray[0]).getValue());
//			assertEquals(Operator.Equal, ((SQLPredicate) sqlPredicateArray[0]).getOperator());
//
//		} catch (SyntaxErrorException e) {
//			fail("SyntaxErrorException thrown or AssertionError occured!");
//		}
//
//	}
//
//	/*
//	 * these tests check the multiple forms the DELETE SQL command can take plus
//	 * checking the parsed data from the commands with multiple white spaces.
//	 */
//
//	@Test
//	public void testDeleteTableSyntaxParsingThree() {
//
//		try {
//			deleteObjAct = sqlParserObjTest.parse(
//					"         DELETE         FROM       TABLE_NAME        Where     col1     =         'valUE_1'           ;               ");
//			assertEquals("table_name", ((Delete) deleteObjAct).getTableName().toLowerCase());
//			Object[] sqlPredicateArray = ((Delete) deleteObjAct).getWhere().getPredicates().toArray();
//			assertEquals("col1", ((SQLPredicate) sqlPredicateArray[0]).getColumnName().toLowerCase());
//			assertEquals("valUE_1", ((SQLPredicate) sqlPredicateArray[0]).getValue());
//			assertEquals(Operator.Equal, ((SQLPredicate) sqlPredicateArray[0]).getOperator());
//		} catch (SyntaxErrorException e) {
//			fail("SyntaxErrorException thrown or AssertionError occured!");
//		}
//
//		try {
//			deleteObjAct = sqlParserObjTest.parse(
//					"       DELETE         * FROM             TABLE_NAME_88          wHeRe      AGE =       5856263      ;     ");
//			assertEquals("table_name_88", ((Delete) deleteObjAct).getTableName().toLowerCase());
//			Object[] sqlPredicateArray = ((Delete) deleteObjAct).getWhere().getPredicates().toArray();
//			assertEquals("age", ((SQLPredicate) sqlPredicateArray[0]).getColumnName().toLowerCase());
//			assertEquals(5856263, ((SQLPredicate) sqlPredicateArray[0]).getValue());
//			assertEquals(Operator.Equal, ((SQLPredicate) sqlPredicateArray[0]).getOperator());
//
//		} catch (SyntaxErrorException e) {
//			fail("SyntaxErrorException thrown or AssertionError occured!");
//		}
//
//	}
//
//	@Test
//	public void testDeleteTableSyntaxParsingFour() {
//		try {
//			deleteObjAct = sqlParserObjTest.parse(
//					"       DELETE         * FROM             TABLE_NAME_88          wHeRe      AGE >       MONEY      ;     ");
//			assertEquals("table_name_88", ((Delete) deleteObjAct).getTableName().toLowerCase());
//			Object[] sqlPredicateArray = ((Delete) deleteObjAct).getWhere().getPredicates().toArray();
//			assertEquals("age", ((SQLPredicate) sqlPredicateArray[0]).getColumnName().toLowerCase());
//			assertEquals("money", ((SQLPredicate) sqlPredicateArray[0]).getColumnName2().toLowerCase());
//			assertEquals(null, ((SQLPredicate) sqlPredicateArray[0]).getValue());
//			assertEquals(Operator.GreaterThan, ((SQLPredicate) sqlPredicateArray[0]).getOperator());
//
//		} catch (SyntaxErrorException e) {
//			fail("SyntaxErrorException thrown or AssertionError occured!");
//		}
//	}
//
//	@Test
//	public void testDeleteTableSyntaxParsingFive() {
//		try {
//			deleteObjAct = sqlParserObjTest.parse(
//					"       DELETE         * FROM             TABLE_NAME_88          wHeRe      AGE >       'MONEY'      ;     ");
//			assertEquals("table_name_88", ((Delete) deleteObjAct).getTableName().toLowerCase());
//			Object[] sqlPredicateArray = ((Delete) deleteObjAct).getWhere().getPredicates().toArray();
//			assertEquals("age", ((SQLPredicate) sqlPredicateArray[0]).getColumnName().toLowerCase());
//			assertEquals(null, ((SQLPredicate) sqlPredicateArray[0]).getColumnName2());
//			assertEquals("MONEY", ((SQLPredicate) sqlPredicateArray[0]).getValue());
//			assertEquals(Operator.GreaterThan, ((SQLPredicate) sqlPredicateArray[0]).getOperator());
//
//		} catch (SyntaxErrorException e) {
//			fail("SyntaxErrorException thrown or AssertionError occured!");
//		}
//	}
//
//}
