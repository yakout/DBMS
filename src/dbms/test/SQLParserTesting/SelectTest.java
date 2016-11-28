package dbms.test.SQLParserTesting;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Queue;

import org.junit.Test;

import dbms.exception.SyntaxErrorException;
import dbms.sqlparser.SQLParser;
import dbms.sqlparser.sqlInterpreter.SQLPredicate;
import dbms.sqlparser.sqlInterpreter.rules.BooleanOperator;
import dbms.sqlparser.sqlInterpreter.rules.Expression;
import dbms.sqlparser.sqlInterpreter.rules.Select;
import dbms.util.Operator;

public class SelectTest extends SqlParserRef {

	private final SQLParser sqlParserObjTest = super.getSqlParserReference();
	private Expression selectObjAct;

	/*
	 * The time taken by this tests include the time consumed by the Validate method.
	 */

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
			sqlParserObjTest.parse("SELECT * FROM TABLE_NAME WHERE country == 'Mexico' ;");
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}
		try {
			sqlParserObjTest.parse("SELECT COL_NAME FROM TABLE_NAME WHERE country == 'Brazil' ;");
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
			sqlParserObjTest.parse("SELECT * FROM TABLE_NAME WHERE country == 'Mexico' ;");
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}
		try {
			sqlParserObjTest.parse("SELECT COL_NAME, COL_NAME1 ,COL_NAME2 FROM TABLE_NAME WHERE country == 'Brazil' ;");
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
					"     sElEcT     *       FrOM      TaBLe_nAMe     wHeRe      cOuNtrY     ==       'Mexico'    ;    ");
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}
		try {
			sqlParserObjTest.parse(
					"           SeLeCT         CoL_nAMe         FrOm     TaBlE_NaMe WheRe couNtry == 'Brazil' ;     ");
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}
	}

	@Test
	public void testSelectSyntaxValidateSix() {
		try {
			sqlParserObjTest.parse("SeLEcT cOLuMn_NAmE FrOm TAbLE_NamE  where col == 5266;");
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}
		try {
			sqlParserObjTest.parse("           SELECT           *           FROM            TABLE_NAME   where col >= 55523  ;      ");
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}
		try {
			sqlParserObjTest.parse(
					"     sElEcT     *       FrOM      TaBLe_nAMe     wHeRe      cOuNtrY     ==       \"Mexico\"    ;    ");
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}
		try {
			sqlParserObjTest.parse(
					"           SeLeCT         CoL_nAMe         FrOm     TaBlE_NaMe WheRe couNtry >= 'Brazil' ;     ");
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}
	}

	@Test
	public void testSelectSyntaxValidateSeven() {
		try {
			sqlParserObjTest.parse("SeLEcT cOLuMn_NAmE FrOm TAbLE_NamE  where col >= 5266;");
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}
		try {
			sqlParserObjTest.parse("           SELECT           *           FROM            TABLE_NAME   where col <= 55523  ;      ");
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}
		try {
			sqlParserObjTest.parse(
					"     sElEcT     *       FrOM      TaBLe_nAMe     wHeRe      cOuNtrY     <=       \"Mexico\"    ;    ");
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}
		try {
			sqlParserObjTest.parse(
					"           SeLeCT         CoL_nAMe         FrOm     TaBlE_NaMe WheRe couNtry != \"Brazil\" ;     ");
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}
	}


	@Test
	public void testSelectSyntaxValidateEight() {
		try {
			sqlParserObjTest.parse("SeLEcT cOLuMn_NAmE FrOm TAbLE_NamE  where col == col2;");
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}
		try {
			sqlParserObjTest.parse("           SELECT           *           FROM            TABLE_NAME   where col <= countries2  ;      ");
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}
		try {
			sqlParserObjTest.parse(
					"     sElEcT     *       FrOM      TaBLe_nAMe     wHeRe      cOuNtrY     >=     countries2      ;    ");
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}
		try {
			sqlParserObjTest.parse(
					"           SeLeCT         CoL_nAMe         FrOm     TaBlE_NaMe WheRe couNtry != countries2 ;     ");
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}
		try {
			sqlParserObjTest.parse(
					"           SeLeCT         Col_nAMe6546239         FrOm     TaBlE_NaMe WheRe couNtry > countries2 ;     ");
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}
		try {
			sqlParserObjTest.parse(
					"           SeLeCT         Col_nAMe6546239          FrOm          TaBlE_NaMe   WheRe couNtry < countries2 ;     ");
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}
	}


	/*
	 * Performance tests with heavy load.
	 */

	@Test
	public void testSelectSyntaxValidateNine() {
		try {
			selectObjAct = sqlParserObjTest.parse("select               col1                ,  "
					+ "  col2            ,      ol3     ,             col4              ,           "
					+ "  col5             ,          col6         ,       col7             ,        col8,              col9,        "
					+ "       col10           ,            col11         ,             col12      ,                    col13        , col14     , col15     , col16             , col17            ,  col18          ,         col19     ,        col20           , col21, col22, col23       "
					+ "        , col24               , col25,               col26,            col27     , col28       , col29     ,                 col30,                   col31               ,                col32            ,                                  col33             ,                 col34            , col35         ,       "
					+ "   col36                            ,        col37       , col38      ,  col39       ,   col40,           col41,         col42, col43         , col44           , col45 ,                 col46 ,      "
					+ "     col47 ,                col48         ,           col49             ,col50"
					+ ", col51, col52, col53, col54, col55, col56, col57, col58, col59, col60, col61,col62, "
					+ "col63, col64, col65, col66, col67, col68, col69, col70, col71, col72,col73, col74, col75 , col76,"
					+ " col77, col78, col79, col80, col81, col82, col83,col84,               "
					+ "col85, col86, col87, col88, col89, col90, col91, col92, col93, col94,  "
					+ " col95 ,col96 ,col97 ,col98 ,col99 ,col100"
					+ " from               table_name      where                     col1                    ==              col2;");
			assertEquals("table_name", ((Select) selectObjAct).getTableName().toLowerCase());
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}
	}

	@Test
	public void testSelectSyntaxValidateTen() {

		try {
			sqlParserObjTest.parse("select               col1                ,  "
					+ "  col2            ,      ol3     ,             col4              ,           "
					+ "  col5             ,          col6         ,       col7             ,        col8,              col9,        "
					+ "       col10           ,            col11         ,             col12      ,                    col13        , col14     , col15     , col16             , col17            ,  col18          ,         col19     ,        col20           , col21, col22, col23       "
					+ "        , col24               , col25,               col26,            col27     , col28       , col29     ,                 col30,                   col31               ,                col32            ,                                  col33             ,                 col34            , col35         ,       "
					+ "   col36                            ,        col37       , col38      ,  col39       ,   col40,           col41,         col42, col43         , col44           , col45 ,                 col46 ,      "
					+ "     col47 ,                col48         ,           col49             ,col50"
					+ " from               table_name             where               col1                    ==              col2;");
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
			Select select = (Select) selectObjAct;
			assertEquals("table_name", select.getTableName().toLowerCase());
			assertEquals(true, select.getColumns() == null);
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}
		try {
			selectObjAct = sqlParserObjTest.parse(
					"        SELECT               *          FROM          CUrr_915634            ;            ");

			Select select = (Select) selectObjAct;
			assertEquals("curr_915634", select.getTableName().toLowerCase());
			assertEquals(true, select.getColumns() == null);
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}
		try {
			selectObjAct = sqlParserObjTest.parse("SELECT * FROM 121515438 ;");
			assertEquals("121515438", ((Select) selectObjAct).getTableName());
			Select select = (Select) selectObjAct;
			assertEquals(true, select.getColumns() == null);
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
			selectObjAct = sqlParserObjTest.parse("SELECT COL_NAME FROM TABLE_NAME WHERE COL1 == COL2;");
			assertEquals("table_name", ((Select) selectObjAct).getTableName().toLowerCase());
			Object[] columnsCpy = {"COL_NAME"};
			assertArrayEquals(columnsCpy, ((Select) selectObjAct).getColumns().toArray());
			Queue <Object> sqlPredicateQ = ((Select) selectObjAct).getWhere().getPostfix();
			assertEquals("col1", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals("col2", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName2().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicateQ.peek()).getValue());
			assertEquals(Operator.Equal, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}
	}

	@Test
	public void testSelectParsingValidateFour() {
		try {
			selectObjAct = sqlParserObjTest.parse(
					"        SELECT               col_name, col_naMe2 , COLname8          FROM          CUrr_915634    WheRe Col1 == Col2        ;            ");
			assertEquals("curr_915634", ((Select) selectObjAct).getTableName().toLowerCase());
			String[] strArray = { "col_name", "col_naMe2", "COLname8" };
			assertArrayEquals(strArray, ((Select) selectObjAct).getColumns().toArray());
			Queue <Object> sqlPredicateQ = ((Select) selectObjAct).getWhere().getPostfix();
			assertEquals("col1", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals("col2", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName2().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicateQ.peek()).getValue());
			assertEquals(Operator.Equal, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}
	}

	@Test
	public void testSelectParsingValidateFive() {
		try {
			selectObjAct = sqlParserObjTest.parse(
					"        SELECT               col_name, col_naMe2 , COLname8          FROM          CUrr_915634    WheRe Col1 == 'ValUe'        ;            ");
			assertEquals("curr_915634", ((Select) selectObjAct).getTableName().toLowerCase());
			String[] strArray = { "col_name", "col_naMe2", "COLname8" };
			assertArrayEquals(strArray, ((Select) selectObjAct).getColumns().toArray());
			Queue <Object> sqlPredicateQ = ((Select) selectObjAct).getWhere().getPostfix();
			assertEquals("col1", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicateQ.peek()).getColumnName2());
			assertEquals("ValUe", ((SQLPredicate) sqlPredicateQ.peek()).getValue());
			assertEquals(Operator.Equal, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());
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
			Queue <Object> sqlPredicateQ = ((Select) selectObjAct).getWhere().getPostfix();
			assertEquals("col1", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicateQ.peek()).getColumnName2());
			assertEquals(5, ((SQLPredicate) sqlPredicateQ.peek()).getValue());
			assertEquals(Operator.GreaterThan, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());
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
			Queue <Object> sqlPredicateQ = ((Select) selectObjAct).getWhere().getPostfix();
			assertEquals("col1", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicateQ.peek()).getColumnName2());
			assertEquals(5, ((SQLPredicate) sqlPredicateQ.peek()).getValue());
			assertEquals(Operator.SmallerThan, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());
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
			selectObjAct = sqlParserObjTest.parse("select * FROM TABLE_NAME WHERE COL1 == COL2;");
			assertEquals("table_name", ((Select) selectObjAct).getTableName().toLowerCase());
			Select select = (Select) selectObjAct;
			assertEquals("table_name", select.getTableName().toLowerCase());
			assertEquals(true, select.getColumns() == null);
			Queue <Object> sqlPredicateQ = ((Select) selectObjAct).getWhere().getPostfix();
			assertEquals("col1", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals("col2", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName2().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicateQ.peek()).getValue());
			assertEquals(Operator.Equal, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}
	}

	@Test
	public void testSelectParsingValidateNine() {
		try {
			selectObjAct = sqlParserObjTest.parse("SELECT * FROM TABLE_NAME WHERE COL1 == 'vALue';");
			assertEquals("table_name", ((Select) selectObjAct).getTableName().toLowerCase());
			Select select = (Select) selectObjAct;
			assertEquals("table_name", select.getTableName().toLowerCase());
			assertEquals(true, select.getColumns() == null);
			Queue <Object> sqlPredicateQ = ((Select) selectObjAct).getWhere().getPostfix();
			assertEquals("col1", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicateQ.peek()).getColumnName2());
			assertEquals("vALue", ((SQLPredicate) sqlPredicateQ.peek()).getValue());
			assertEquals(Operator.Equal, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}
	}

	@Test
	public void testSelectParsingValidateTen() {
		try {
			selectObjAct = sqlParserObjTest.parse("       SELECT       *                      FROM       TABLE_NAME      WHERE    COL1    ==     153           ;     ");
			assertEquals("table_name", ((Select) selectObjAct).getTableName().toLowerCase());
			Select select = (Select) selectObjAct;
			assertEquals("table_name", select.getTableName().toLowerCase());
			assertEquals(true, select.getColumns() == null);
			Queue <Object> sqlPredicateQ = ((Select) selectObjAct).getWhere().getPostfix();
			assertEquals("col1", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicateQ.peek()).getColumnName2());
			assertEquals(153, ((SQLPredicate) sqlPredicateQ.peek()).getValue());
			assertEquals(Operator.Equal, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());
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
			Select select = (Select) selectObjAct;
			assertEquals("table_name", select.getTableName().toLowerCase());
			assertEquals(true, select.getColumns() == null);
			Queue <Object> sqlPredicateQ = ((Select) selectObjAct).getWhere().getPostfix();
			assertEquals("col1", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicateQ.peek()).getColumnName2());
			assertEquals(153, ((SQLPredicate) sqlPredicateQ.peek()).getValue());
			assertEquals(Operator.GreaterThan, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());
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
			Select select = (Select) selectObjAct;
			assertEquals("table_name", select.getTableName().toLowerCase());
			assertEquals(true, select.getColumns() == null);
			Queue <Object> sqlPredicateQ = ((Select) selectObjAct).getWhere().getPostfix();
			assertEquals("col1", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals("coll2", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName2().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicateQ.peek()).getValue());
			assertEquals(Operator.GreaterThan, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());
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
			Select select = (Select) selectObjAct;
			assertEquals("table_name", select.getTableName().toLowerCase());
			assertEquals(true, select.getColumns() == null);
			Queue <Object> sqlPredicateQ = ((Select) selectObjAct).getWhere().getPostfix();
			assertEquals("col1", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals("coll2", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName2().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicateQ.peek()).getValue());
			assertEquals(Operator.SmallerThan, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());
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
			Select select = (Select) selectObjAct;
			assertEquals("table_name", select.getTableName().toLowerCase());
			assertEquals(true, select.getColumns() == null);
			Queue <Object> sqlPredicateQ = ((Select) selectObjAct).getWhere().getPostfix();
			assertEquals("col1", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicateQ.peek()).getColumnName2());
			assertEquals("STrInG", ((SQLPredicate) sqlPredicateQ.peek()).getValue());
			assertEquals(Operator.SmallerThan, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());
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
			Select select = (Select) selectObjAct;
			assertEquals("table_name", select.getTableName().toLowerCase());
			assertEquals(true, select.getColumns() == null);
			Queue <Object> sqlPredicateQ = ((Select) selectObjAct).getWhere().getPostfix();
			assertEquals("col1", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicateQ.peek()).getColumnName2());
			assertEquals("STrInG", ((SQLPredicate) sqlPredicateQ.peek()).getValue());
			assertEquals(Operator.GreaterThan, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}
	}

	@Test
	public void testSelectParsingValidateSixteen() {
		try {
			selectObjAct = sqlParserObjTest.parse(
					"        SElEct         *          FrOm               TaBLe_NaMe       wHeRe      CoL1     >=        'STrInG'      ;     ");
			assertEquals("table_name", ((Select) selectObjAct).getTableName().toLowerCase());
			Select select = (Select) selectObjAct;
			assertEquals("table_name", select.getTableName().toLowerCase());
			assertEquals(true, select.getColumns() == null);
			Queue <Object> sqlPredicateQ = ((Select) selectObjAct).getWhere().getPostfix();
			assertEquals("col1", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicateQ.peek()).getColumnName2());
			assertEquals("STrInG", ((SQLPredicate) sqlPredicateQ.peek()).getValue());
			assertEquals(Operator.GreaterThanOrEqual, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}
	}

	@Test
	public void testSelectParsingValidateSeventeen() {
		try {
			selectObjAct = sqlParserObjTest.parse(
					"        SElEct         *          FrOm               TaBLe_NaMe       wHeRe      CoL1     <=        'STrInG'      ;     ");
			assertEquals("table_name", ((Select) selectObjAct).getTableName().toLowerCase());
			Select select = (Select) selectObjAct;
			assertEquals("table_name", select.getTableName().toLowerCase());
			assertEquals(true, select.getColumns() == null);
			Queue <Object> sqlPredicateQ = ((Select) selectObjAct).getWhere().getPostfix();
			assertEquals("col1", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicateQ.peek()).getColumnName2());
			assertEquals("STrInG", ((SQLPredicate) sqlPredicateQ.peek()).getValue());
			assertEquals(Operator.SmallerThanOrEqual, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}
	}

	@Test
	public void testSelectParsingValidateEighteen() {
		try {
			selectObjAct = sqlParserObjTest.parse(
					"        SElEct         *          FrOm               TaBLe_NaMe       wHeRe      CoL1     !=        'STrInG'      ;     ");
			assertEquals("table_name", ((Select) selectObjAct).getTableName().toLowerCase());
			Select select = (Select) selectObjAct;
			assertEquals("table_name", select.getTableName().toLowerCase());
			assertEquals(true, select.getColumns() == null);
			Queue <Object> sqlPredicateQ = ((Select) selectObjAct).getWhere().getPostfix();
			assertEquals("col1", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicateQ.peek()).getColumnName2());
			assertEquals("STrInG", ((SQLPredicate) sqlPredicateQ.peek()).getValue());
			assertEquals(Operator.NotEqual, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}
	}

	@Test
	public void testSelectParsingValidateNinteen() {
		try {
			selectObjAct = sqlParserObjTest.parse(
					"        SElEct         *          FrOm               TaBLe_NaMe       wHeRe      CoL1     !=        \"STrInG\"      ;     ");
			assertEquals("table_name", ((Select) selectObjAct).getTableName().toLowerCase());
			Select select = (Select) selectObjAct;
			assertEquals("table_name", select.getTableName().toLowerCase());
			assertEquals(true, select.getColumns() == null);
			Queue <Object> sqlPredicateQ = ((Select) selectObjAct).getWhere().getPostfix();
			assertEquals("col1", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicateQ.peek()).getColumnName2());
			assertEquals("STrInG", ((SQLPredicate) sqlPredicateQ.peek()).getValue());
			assertEquals(Operator.NotEqual, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}
	}

	@Test
	public void testSelectParsingValidateTwenty() {
		try {
			selectObjAct = sqlParserObjTest.parse(
					"        SElEct         *          FrOm               TaBLe_NaMe       wHeRe      CoL1     <=        \"STrInG\"      ;     ");
			assertEquals("table_name", ((Select) selectObjAct).getTableName().toLowerCase());
			Select select = (Select) selectObjAct;
			assertEquals("table_name", select.getTableName().toLowerCase());
			assertEquals(true, select.getColumns() == null);
			Queue <Object> sqlPredicateQ = ((Select) selectObjAct).getWhere().getPostfix();
			assertEquals("col1", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicateQ.peek()).getColumnName2());
			assertEquals("STrInG", ((SQLPredicate) sqlPredicateQ.peek()).getValue());
			assertEquals(Operator.SmallerThanOrEqual, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}
	}

	@Test
	public void testSelectParsingValidateTwentyOne() {
		try {
			selectObjAct = sqlParserObjTest.parse(
					"        SElEct         *          FrOm               TaBLe_NaMe       wHeRe      CoL1     >=        \"STrInG\"      ;     ");
			assertEquals("table_name", ((Select) selectObjAct).getTableName().toLowerCase());
			Select select = (Select) selectObjAct;
			assertEquals("table_name", select.getTableName().toLowerCase());
			assertEquals(true, select.getColumns() == null);
			Queue <Object> sqlPredicateQ = ((Select) selectObjAct).getWhere().getPostfix();
			assertEquals("col1", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicateQ.peek()).getColumnName2());
			assertEquals("STrInG", ((SQLPredicate) sqlPredicateQ.peek()).getValue());
			assertEquals(Operator.GreaterThanOrEqual, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}
	}

	@Test
	public void testSelectParsingValidateTwentyTwo() {
		try {
			selectObjAct = sqlParserObjTest.parse(
					"        SElEct         *          FrOm               TaBLe_NaMe       wHeRe      CoL1     >=        Col2      ;     ");
			assertEquals("table_name", ((Select) selectObjAct).getTableName().toLowerCase());
			Select select = (Select) selectObjAct;
			assertEquals("table_name", select.getTableName().toLowerCase());
			assertEquals(true, select.getColumns() == null);
			Queue <Object> sqlPredicateQ = ((Select) selectObjAct).getWhere().getPostfix();
			assertEquals("col1", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals("col2", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName2().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicateQ.peek()).getValue());
			assertEquals(Operator.GreaterThanOrEqual, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}
	}

	@Test
	public void testSelectParsingValidateTwentyThree() {
		try {
			selectObjAct = sqlParserObjTest.parse(
					"        SElEct         *          FrOm               TaBLe_NaMe       wHeRe      CoL1     <=        Col2      ;     ");
			assertEquals("table_name", ((Select) selectObjAct).getTableName().toLowerCase());
			Select select = (Select) selectObjAct;
			assertEquals("table_name", select.getTableName().toLowerCase());
			assertEquals(true, select.getColumns() == null);
			Queue <Object> sqlPredicateQ = ((Select) selectObjAct).getWhere().getPostfix();
			assertEquals("col1", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals("col2", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName2().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicateQ.peek()).getValue());
			assertEquals(Operator.SmallerThanOrEqual, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}
	}

	@Test
	public void testSelectParsingValidateTwentyFour() {
		try {
			selectObjAct = sqlParserObjTest.parse(
					"        SElEct         *          FrOm               TaBLe_NaMe       wHeRe      CoL1     !=        Col2      ;     ");
			assertEquals("table_name", ((Select) selectObjAct).getTableName().toLowerCase());
			Select select = (Select) selectObjAct;
			assertEquals("table_name", select.getTableName().toLowerCase());
			assertEquals(true, select.getColumns() == null);
			Queue <Object> sqlPredicateQ = ((Select) selectObjAct).getWhere().getPostfix();
			assertEquals("col1", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals("col2", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName2().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicateQ.peek()).getValue());
			assertEquals(Operator.NotEqual, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}
	}

	@Test
	public void testSelectParsingValidateTwentyFive() {
		try {
			selectObjAct = sqlParserObjTest.parse(
					"        SElEct         *          FrOm               TaBLe_NaMe       wHeRe      CoL1     !=        555633      ;     ");
			assertEquals("table_name", ((Select) selectObjAct).getTableName().toLowerCase());
			Select select = (Select) selectObjAct;
			assertEquals("table_name", select.getTableName().toLowerCase());
			assertEquals(true, select.getColumns() == null);
			Queue <Object> sqlPredicateQ = ((Select) selectObjAct).getWhere().getPostfix();
			assertEquals("col1", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicateQ.peek()).getColumnName2());
			assertEquals(555633, ((SQLPredicate) sqlPredicateQ.peek()).getValue());
			assertEquals(Operator.NotEqual, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}
	}

	@Test
	public void testSelectParsingValidateTwentySix() {
		try {
			selectObjAct = sqlParserObjTest.parse(
					"        SElEct         *          FrOm               TaBLe_NaMe       wHeRe      CoL1     >=        555633      ;     ");
			assertEquals("table_name", ((Select) selectObjAct).getTableName().toLowerCase());
			Select select = (Select) selectObjAct;
			assertEquals("table_name", select.getTableName().toLowerCase());
			assertEquals(true, select.getColumns() == null);
			Queue <Object> sqlPredicateQ = ((Select) selectObjAct).getWhere().getPostfix();
			assertEquals("col1", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicateQ.peek()).getColumnName2());
			assertEquals(555633, ((SQLPredicate) sqlPredicateQ.peek()).getValue());
			assertEquals(Operator.GreaterThanOrEqual, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}
	}

	@Test
	public void testSelectParsingValidateTwentySeven() {
		try {
			selectObjAct = sqlParserObjTest.parse(
					"        SElEct         *          FrOm               TaBLe_NaMe       wHeRe      CoL1     <=        555633      ;     ");
			assertEquals("table_name", ((Select) selectObjAct).getTableName().toLowerCase());
			Select select = (Select) selectObjAct;
			assertEquals("table_name", select.getTableName().toLowerCase());
			assertEquals(true, select.getColumns() == null);
			Queue <Object> sqlPredicateQ = ((Select) selectObjAct).getWhere().getPostfix();
			assertEquals("col1", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicateQ.peek()).getColumnName2());
			assertEquals(555633, ((SQLPredicate) sqlPredicateQ.peek()).getValue());
			assertEquals(Operator.SmallerThanOrEqual, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}
	}

	@Test
	public void testSelectParsingValidateTwentyEight() {
		try {
			selectObjAct = sqlParserObjTest.parse(
					"        SElEct         *          FrOm               TaBLe_NaMe       wHeRe      (CoL1     <=        555633)      ;     ");
			assertEquals("table_name", ((Select) selectObjAct).getTableName().toLowerCase());
			Select select = (Select) selectObjAct;
			assertEquals("table_name", select.getTableName().toLowerCase());
			assertEquals(true, select.getColumns() == null);
			Queue <Object> sqlPredicateQ = ((Select) selectObjAct).getWhere().getPostfix();
			assertEquals("col1", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicateQ.peek()).getColumnName2());
			assertEquals(555633, ((SQLPredicate) sqlPredicateQ.peek()).getValue());
			assertEquals(Operator.SmallerThanOrEqual, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}
	}


	@Test
	public void testSelectParsingValidateTwentyNine() {
		try {
			selectObjAct = sqlParserObjTest.parse(
					"        SElEct         *          FrOm               TaBLe_NaMe       wHeRe   (   (CoL1     <=        555633)  oR (col2 >= \"HelLO\"))    ;     ");
			assertEquals("table_name", ((Select) selectObjAct).getTableName().toLowerCase());
			Select select = (Select) selectObjAct;
			assertEquals("table_name", select.getTableName().toLowerCase());
			assertEquals(true, select.getColumns() == null);
			Queue <Object> sqlPredicateQ = ((Select) selectObjAct).getWhere().getPostfix();


			assertEquals("col1", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicateQ.peek()).getColumnName2());
			assertEquals(555633, ((SQLPredicate) sqlPredicateQ.peek()).getValue());
			assertEquals(Operator.SmallerThanOrEqual, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());

			sqlPredicateQ.poll();

			assertEquals("col2", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicateQ.peek()).getColumnName2());
			assertEquals("HelLO", ((SQLPredicate) sqlPredicateQ.peek()).getValue());
			assertEquals(Operator.GreaterThanOrEqual, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());

			sqlPredicateQ.poll();

			assertEquals(BooleanOperator.Operator.Or, ((BooleanOperator) sqlPredicateQ.peek()).getOperator());
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}
	}

	@Test
	public void testSelectParsingValidateThirty() {
		try {
			selectObjAct = sqlParserObjTest.parse(
					"        SElEct         *          FrOm               TaBLe_NaMe       wHeRe   (   (CoL1     <=        555633)  AnD (col2 >= \"HelLO\"))    ;     ");
			assertEquals("table_name", ((Select) selectObjAct).getTableName().toLowerCase());
			Select select = (Select) selectObjAct;
			assertEquals("table_name", select.getTableName().toLowerCase());
			assertEquals(true, select.getColumns() == null);
			Queue <Object> sqlPredicateQ = ((Select) selectObjAct).getWhere().getPostfix();

			assertEquals("col1", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicateQ.peek()).getColumnName2());
			assertEquals(555633, ((SQLPredicate) sqlPredicateQ.peek()).getValue());
			assertEquals(Operator.SmallerThanOrEqual, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());

			sqlPredicateQ.poll();

			assertEquals("col2", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicateQ.peek()).getColumnName2());
			assertEquals("HelLO", ((SQLPredicate) sqlPredicateQ.peek()).getValue());
			assertEquals(Operator.GreaterThanOrEqual, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());

			sqlPredicateQ.poll();

			assertEquals(BooleanOperator.Operator.And, ((BooleanOperator) sqlPredicateQ.peek()).getOperator());
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}
	}

	@Test
	public void testSelectParsingValidateThirtyOne() {
		try {
			selectObjAct = sqlParserObjTest.parse(
					"        SElEct         *          FrOm               TaBLe_NaMe       wHeRe   (   (CoL1     <=        column)  AnD (col2 >= \"HelLO\"))    ;     ");
			assertEquals("table_name", ((Select) selectObjAct).getTableName().toLowerCase());
			Select select = (Select) selectObjAct;
			assertEquals("table_name", select.getTableName().toLowerCase());
			assertEquals(true, select.getColumns() == null);
			Queue <Object> sqlPredicateQ = ((Select) selectObjAct).getWhere().getPostfix();

			assertEquals("col1", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals("column", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName2().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicateQ.peek()).getValue());
			assertEquals(Operator.SmallerThanOrEqual, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());

			sqlPredicateQ.poll();

			assertEquals("col2", ((SQLPredicate) sqlPredicateQ.peek()).getColumnName().toLowerCase());
			assertEquals(null, ((SQLPredicate) sqlPredicateQ.peek()).getColumnName2());
			assertEquals("HelLO", ((SQLPredicate) sqlPredicateQ.peek()).getValue());
			assertEquals(Operator.GreaterThanOrEqual, ((SQLPredicate) sqlPredicateQ.peek()).getOperator());

			sqlPredicateQ.poll();

			assertEquals(BooleanOperator.Operator.And, ((BooleanOperator) sqlPredicateQ.peek()).getOperator());
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}
	}
}
