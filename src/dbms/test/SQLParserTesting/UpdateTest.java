//package dbms.test.SQLParserTesting;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
//import static org.junit.Assert.fail;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import org.junit.Test;
//
//import dbms.exception.SyntaxErrorException;
//import dbms.sqlparser.SQLParser;
//import dbms.sqlparser.sqlInterpreter.SQLPredicate;
//import dbms.sqlparser.sqlInterpreter.rules.Expression;
//import dbms.sqlparser.sqlInterpreter.rules.Update;
//import dbms.util.Operator;
//
//public class UpdateTest extends SqlParserRef {
//
//	private final SQLParser sqlParserObjTest = super.getSqlParserReference();
//	private Expression updateObjAct;
//
//	/*
//	 * This test checks the validity of syntax of UPDATE SQL command in
//	 * different forms.
//	 */
//	@Test
//	public void testUpdateTableSyntaxValidateOne() {
//		try {
//			sqlParserObjTest
//					.parse("UPDATE TABLE_NAME SET COLUMN1='VALUE1',COLUMN2='VALUE2' WHERE SOME_COL = 'SOME_VALUE';");
//		} catch (SyntaxErrorException e) {
//			fail("SyntaxErrorException thrown or AssertionError occured!");
//		}
//
//		try {
//			sqlParserObjTest.parse(
//					"        UPDATE      TABLE_NAME       SET       COLUMN1      =  'VALUE1'        ,      COLUMN2  =       'VALUE2'   WHERE      SOME_COL    =         'SOME_VALUE'         ;         ");
//		} catch (SyntaxErrorException e) {
//			fail("SyntaxErrorException thrown or AssertionError occured!");
//		}
//
//	}
//
//	@Test
//	public void testUpdateTableSyntaxValidateTwo() {
//		try {
//			sqlParserObjTest
//					.parse("UPDATE TABLE_NAME SET COLUMN1='VALUE1',COLUMN2='VALUE2' WHERE SOME_COL > 'SOME_VALUE';");
//		} catch (SyntaxErrorException e) {
//			fail("SyntaxErrorException thrown or AssertionError occured!");
//		}
//
//		try {
//			sqlParserObjTest.parse(
//					"       UPDATE      TABLE_NAME       SET       COLUMN1      =  'VALUE1'        ,      COLUMN2  =       'VALUE2'   WHERE      SOME_COL    >        'SOME_VALUE'         ;         ");
//		} catch (SyntaxErrorException e) {
//			fail("SyntaxErrorException thrown or AssertionError occured!");
//		}
//
//	}
//
//	@Test
//	public void testUpdateTableSyntaxValidateThree() {
//		try {
//			sqlParserObjTest
//					.parse("UPDATE TABLE_NAME SET COLUMN1='VALUE1',COLUMN2='VALUE2' WHERE SOME_COL < 'SOME_VALUE';");
//		} catch (SyntaxErrorException e) {
//			fail("SyntaxErrorException thrown or AssertionError occured!");
//		}
//
//		try {
//			sqlParserObjTest.parse(
//					"      UPDATE      TABLE_NAME       SET       COLUMN1      =  'VALUE1'        ,      COLUMN2  =       'VALUE2'   WHERE      SOME_COL    <         'SOME_VALUE'         ;         ");
//		} catch (SyntaxErrorException e) {
//			fail("SyntaxErrorException thrown or AssertionError occured!");
//		}
//
//	}
//
//	/*
//	 * This test checks that the catch clause will get the SyntaxErrorException
//	 */
//
//	@Test
//	public void testUpdateTableSyntaxValidateFour() {
//		try {
//			sqlParserObjTest
//					.parse("UPDATE TABLE_NAME SET COLUMN1='VALUE1',COLUMN2='VALUE2' WHERE SOME_COL  'SOME_VALUE';");
//			fail("SyntaxErrorException thrown or AssertionError occured!");
//		} catch (SyntaxErrorException e) {
//		}
//
//		try {
//			sqlParserObjTest.parse(
//					"        UPDATE      TABLE_NAME       SET       COLUMN1      =  'VALUE1'        ,      COLUMN2         'VALUE2'   WHERE      SOME_COL    =         'SOME_VALUE'         ;         ");
//			fail("SyntaxErrorException thrown or AssertionError occured!");
//		} catch (SyntaxErrorException e) {
//		}
//
//	}
//
//	@Test
//	public void testUpdateTableSyntaxValidateFive() {
//		try {
//			sqlParserObjTest
//					.parse("UPDATE TABLE_NAME  COLUMN1='VALUE1',COLUMN2='VALUE2' WHERE SOME_COL  'SOME_VALUE';");
//			fail("SyntaxErrorException thrown or AssertionError occured!");
//		} catch (SyntaxErrorException e) {
//		}
//
//		try {
//			sqlParserObjTest.parse(
//					"        UPDATE      TABLE_NAME       SET       COLUMN1      =  'VALUE1'        ,      COLUMN2         'VALUE2'         SOME_COL    =         'SOME_VALUE'         ;         ");
//			fail("SyntaxErrorException thrown or AssertionError occured!");
//		} catch (SyntaxErrorException e) {
//		}
//
//	}
//
//	@Test
//	public void testUpdateTableSyntaxValidateSix() {
//		try {
//			sqlParserObjTest.parse("UPDATE TABLE_NAME  WHERE SOME_COL = 'SOME_VALUE';");
//			fail("SyntaxErrorException thrown or AssertionError occured!");
//		} catch (SyntaxErrorException e) {
//		}
//
//		try {
//			sqlParserObjTest.parse(
//					"        UPDATE      TABLE_NAME           WHERE      SOME_COL    =         'SOME_VALUE'         ;         ");
//			fail("SyntaxErrorException thrown or AssertionError occured!");
//		} catch (SyntaxErrorException e) {
//		}
//
//	}
//
//	/*
//	 * This test checks the case insensitivity in the UPDATE SQL command.
//	 */
//
//	@Test
//	public void testUpdateTableSyntaxValidateSeven() {
//		try {
//			sqlParserObjTest
//					.parse("UPDaTe tAbLE_nAMe SeT CoLUMn1='VAlUe1',CoLUmN2='VaLUe2' WhErE SOME_col = 'SOME_VALUE';");
//		} catch (SyntaxErrorException e) {
//			fail("SyntaxErrorException thrown or AssertionError occured!");
//
//		}
//
//		try {
//			sqlParserObjTest.parse(
//					"          UPDaTe        tAbLE_nAMe            SeT          CoLUMn1='VAlUe1'         , CoLUmN2='VaLUe2'        WhErE          SOME_col          =         'SOME_VALUE'       ;   ");
//		} catch (SyntaxErrorException e) {
//			fail("SyntaxErrorException thrown or AssertionError occured!");
//		}
//	}
//
//	/*
//	 * These test checks the correctness of the parsed data from the SQL command
//	 * on different conditions.
//	 */
//
//	@Test
//	public void testUpdateTableParsingValidateOne() {
//		try {
//			updateObjAct = sqlParserObjTest
//					.parse("UPDATE TABLE_NAME SET COLUMN1='VALUE1',COLUMN2='VALUE2' WHERE SOME_COL = 'SOME_VALUE';");
//			assertEquals("table_name", ((Update) updateObjAct).getTableName().toLowerCase());
//			Map<String, Object> entryMapCpy = ((Update) updateObjAct).getValues();
//			Map<String, Object> expectedEntryMap = new HashMap<String, Object>();
//			expectedEntryMap.put("COLUMN1", "VALUE1");
//			expectedEntryMap.put("COLUMN2", "VALUE2");
//			assertTrue(expectedEntryMap.equals(entryMapCpy));
//			Object[] sqlPredicateArray = ((Update) updateObjAct).getWhere().getPredicates().toArray();
//			assertEquals("some_col", ((SQLPredicate) sqlPredicateArray[0]).getColumnName().toLowerCase());
//			assertEquals("SOME_VALUE", ((SQLPredicate) sqlPredicateArray[0]).getValue());
//			assertEquals(Operator.Equal, ((SQLPredicate) sqlPredicateArray[0]).getOperator());
//		} catch (SyntaxErrorException e) {
//			fail("SyntaxErrorException thrown or AssertionError occured!");
//		}
//	}
//
//	@Test
//	public void testUpdateTableParsingValidateTwo() {
//		try {
//			updateObjAct = sqlParserObjTest.parse(
//					"        UPDATE      TABLE_NAME       SET       COLUMN1      =  'VALUE1'        ,      COLUMN2  =       'VALUE2'   WHERE      SOME_COL    =         'SOME_vaLUE'         ;         ");
//			assertEquals("table_name", ((Update) updateObjAct).getTableName().toLowerCase());
//			Map<String, Object> entryMapCpy = ((Update) updateObjAct).getValues();
//			Map<String, Object> expectedEntryMap = new HashMap<String, Object>();
//			expectedEntryMap.put("COLUMN1", "VALUE1");
//			expectedEntryMap.put("COLUMN2", "VALUE2");
//			assertTrue(expectedEntryMap.equals(entryMapCpy));
//			Object[] sqlPredicateArray = ((Update) updateObjAct).getWhere().getPredicates().toArray();
//			assertEquals("some_col", ((SQLPredicate) sqlPredicateArray[0]).getColumnName().toLowerCase());
//			assertEquals("SOME_vaLUE", ((SQLPredicate) sqlPredicateArray[0]).getValue());
//			assertEquals(Operator.Equal, ((SQLPredicate) sqlPredicateArray[0]).getOperator());
//		} catch (SyntaxErrorException e) {
//			fail("SyntaxErrorException thrown or AssertionError occured!");
//		}
//
//	}
//
//	@Test
//	public void testUpdateTableParsingValidateThree() {
//		try {
//			updateObjAct = sqlParserObjTest
//					.parse("UPDATE TABLE_NAME SET COLUMN1='VALUE1',COLUMN2='VALUE2' WHERE SOME_COL = SOME_COL2;");
//			assertEquals("table_name", ((Update) updateObjAct).getTableName().toLowerCase());
//			Map<String, Object> entryMapCpy = ((Update) updateObjAct).getValues();
//			Map<String, Object> expectedEntryMap = new HashMap<String, Object>();
//			expectedEntryMap.put("COLUMN1", "VALUE1");
//			expectedEntryMap.put("COLUMN2", "VALUE2");
//			assertTrue(expectedEntryMap.equals(entryMapCpy));
//			Object[] sqlPredicateArray = ((Update) updateObjAct).getWhere().getPredicates().toArray();
//			assertEquals("some_col", ((SQLPredicate) sqlPredicateArray[0]).getColumnName().toLowerCase());
//			assertEquals("some_col2", ((SQLPredicate) sqlPredicateArray[0]).getColumnName2().toLowerCase());
//			assertEquals(Operator.Equal, ((SQLPredicate) sqlPredicateArray[0]).getOperator());
//		} catch (SyntaxErrorException e) {
//			fail("SyntaxErrorException thrown or AssertionError occured!");
//		}
//	}
//
//	@Test
//	public void testUpdateTableParsingValidateFour() {
//		try {
//			updateObjAct = sqlParserObjTest
//					.parse("UPDATE TABLE_NAME SET COLUMN1='VALUE1',COLUMN2='VALUE2' WHERE SOME_COL < 5;");
//			assertEquals("table_name", ((Update) updateObjAct).getTableName().toLowerCase());
//			Map<String, Object> entryMapCpy = ((Update) updateObjAct).getValues();
//			Map<String, Object> expectedEntryMap = new HashMap<String, Object>();
//			expectedEntryMap.put("COLUMN1", "VALUE1");
//			expectedEntryMap.put("COLUMN2", "VALUE2");
//			assertTrue(expectedEntryMap.equals(entryMapCpy));
//			Object[] sqlPredicateArray = ((Update) updateObjAct).getWhere().getPredicates().toArray();
//			assertEquals("some_col", ((SQLPredicate) sqlPredicateArray[0]).getColumnName().toLowerCase());
//			assertEquals(5, ((SQLPredicate) sqlPredicateArray[0]).getValue());
//			assertEquals(Operator.SmallerThan, ((SQLPredicate) sqlPredicateArray[0]).getOperator());
//		} catch (SyntaxErrorException e) {
//			fail("SyntaxErrorException thrown or AssertionError occured!");
//		}
//	}
//
//	@Test
//	public void testUpdateTableParsingValidateFive() {
//		try {
//			updateObjAct = sqlParserObjTest
//					.parse("UPDATE TABLE_NAME SET COLUMN1='value1',column2='VALUE2' WHERE SOME_COL > 5;");
//			assertEquals("table_name", ((Update) updateObjAct).getTableName().toLowerCase());
//			Map<String, Object> entryMapCpy = ((Update) updateObjAct).getValues();
//			Map<String, Object> expectedEntryMap = new HashMap<String, Object>();
//			expectedEntryMap.put("COLUMN1", "value1");
//			expectedEntryMap.put("column2", "VALUE2");
//			assertTrue(expectedEntryMap.equals(entryMapCpy));
//			Object[] sqlPredicateArray = ((Update) updateObjAct).getWhere().getPredicates().toArray();
//			assertEquals("some_col", ((SQLPredicate) sqlPredicateArray[0]).getColumnName().toLowerCase());
//			assertEquals(5, ((SQLPredicate) sqlPredicateArray[0]).getValue());
//			assertEquals(Operator.GreaterThan, ((SQLPredicate) sqlPredicateArray[0]).getOperator());
//		} catch (SyntaxErrorException e) {
//			fail("SyntaxErrorException thrown or AssertionError occured!");
//		}
//	}
//
//	@Test
//	public void testUpdateTableParsingValidateSix() {
//		try {
//			updateObjAct = sqlParserObjTest
//					.parse("UPDATE TABLE_NAME SET column1='value1',column2='value2' WHERE SOME_COL = 5;");
//			assertEquals("table_name", ((Update) updateObjAct).getTableName().toLowerCase());
//			Map<String, Object> entryMapCpy = ((Update) updateObjAct).getValues();
//			Map<String, Object> expectedEntryMap = new HashMap<String, Object>();
//			expectedEntryMap.put("column1", "value1");
//			expectedEntryMap.put("column2", "value2");
//			assertTrue(expectedEntryMap.equals(entryMapCpy));
//			Object[] sqlPredicateArray = ((Update) updateObjAct).getWhere().getPredicates().toArray();
//			assertEquals("some_col", ((SQLPredicate) sqlPredicateArray[0]).getColumnName().toLowerCase());
//			assertEquals(5, ((SQLPredicate) sqlPredicateArray[0]).getValue());
//			assertEquals(Operator.Equal, ((SQLPredicate) sqlPredicateArray[0]).getOperator());
//		} catch (SyntaxErrorException e) {
//			fail("SyntaxErrorException thrown or AssertionError occured!");
//		}
//	}
//
//	@Test
//	public void testUpdateTableParsingValidateSeven() {
//		try {
//			updateObjAct = sqlParserObjTest.parse("UPDATE TABLE_NAME SET COLUMN1 =     COLUMN2;");
//			assertEquals("table_name", ((Update) updateObjAct).getTableName().toLowerCase());
//			Map<String, String> entryMapCpy = ((Update) updateObjAct).getColumns();
//			Map<String, String> expectedEntryMap = new HashMap<String, String>();
//			expectedEntryMap.put("COLUMN1", "COLUMN2");
//			assertTrue(expectedEntryMap.equals(entryMapCpy));
//		} catch (SyntaxErrorException e) {
//			fail("SyntaxErrorException thrown or AssertionError occured!");
//		}
//	}
//
//	@Test
//	public void testUpdateTableParsingValidateEight() {
//		try {
//			updateObjAct = sqlParserObjTest
//					.parse("UPDATE TABLE_NAME SET COLUMN1='value1',COLUMN2='value2' WHERE SOME_COL > 5;");
//			assertEquals("table_name", ((Update) updateObjAct).getTableName().toLowerCase());
//			Map<String, Object> entryMapCpy = ((Update) updateObjAct).getValues();
//			Map<String, Object> expectedEntryMap = new HashMap<String, Object>();
//			expectedEntryMap.put("COLUMN1", "value1");
//			expectedEntryMap.put("COLUMN2", "value2");
//			assertTrue(expectedEntryMap.equals(entryMapCpy));
//			Object[] sqlPredicateArray = ((Update) updateObjAct).getWhere().getPredicates().toArray();
//			assertEquals("some_col", ((SQLPredicate) sqlPredicateArray[0]).getColumnName().toLowerCase());
//			assertEquals(5, ((SQLPredicate) sqlPredicateArray[0]).getValue());
//			assertEquals(Operator.GreaterThan, ((SQLPredicate) sqlPredicateArray[0]).getOperator());
//		} catch (SyntaxErrorException e) {
//			fail("SyntaxErrorException thrown or AssertionError occured!");
//		}
//	}
//
//	@Test
//	public void testUpdateTableParsingValidateNine() {
//		try {
//			updateObjAct = sqlParserObjTest
//					.parse("UPDATE TABLE_NAME SET COLUMN1='value1',COLUMN2='value2' WHERE SOME_COL > 'HEYYY';");
//			assertEquals("table_name", ((Update) updateObjAct).getTableName().toLowerCase());
//			Map<String, Object> entryMapCpy = ((Update) updateObjAct).getValues();
//			Map<String, Object> expectedEntryMap = new HashMap<String, Object>();
//			expectedEntryMap.put("COLUMN1", "value1");
//			expectedEntryMap.put("COLUMN2", "value2");
//			assertTrue(expectedEntryMap.equals(entryMapCpy));
//			Object[] sqlPredicateArray = ((Update) updateObjAct).getWhere().getPredicates().toArray();
//			assertEquals("some_col", ((SQLPredicate) sqlPredicateArray[0]).getColumnName().toLowerCase());
//			assertEquals("HEYYY", ((SQLPredicate) sqlPredicateArray[0]).getValue());
//			assertEquals(Operator.GreaterThan, ((SQLPredicate) sqlPredicateArray[0]).getOperator());
//		} catch (SyntaxErrorException e) {
//			fail("SyntaxErrorException thrown or AssertionError occured!");
//		}
//	}
//
//}