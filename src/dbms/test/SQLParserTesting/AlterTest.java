package dbms.test.SQLParserTesting;

import dbms.datatypes.DBInteger;
import dbms.exception.SyntaxErrorException;
import dbms.sqlparser.SQLParser;
import dbms.sqlparser.sqlInterpreter.rules.AlterAdd;
import dbms.sqlparser.sqlInterpreter.rules.AlterDrop;
import dbms.sqlparser.sqlInterpreter.rules.Expression;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class AlterTest extends SqlParserRef {

	private final SQLParser sqlParserObjTest = super.getSqlParserReference();
	private Expression alterObj;

	@Test
	public void testAlterSyntaxValidateOne() {

		try {
			sqlParserObjTest.parse("alter table table_name drop column column_name;");
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occurred!");
		}
	}

	@Test
	public void testAlterSyntaxValidateTwo() {
		try {
			sqlParserObjTest.parse("alter table table_name add column_name int;");
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occurred!");
		}
	}

	@Test
	public void testAlterSyntaxValidateThree() {
		try {
			sqlParserObjTest.parse("alter table table_name add column_name varchar;");
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occurred!");
		}
	}

	@Test
	public void testAlterSyntaxValidateFour() {
		try {
			sqlParserObjTest.parse("alter table table_name add column_name varchar;");
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occurred!");
		}
	}

	@Test
	public void testAlterSyntaxValidateFive() {
		try {
			sqlParserObjTest.parse("alter table table_name column_name varchar;");
			fail("SyntaxErrorException thrown or AssertionError occurred!");
		} catch (SyntaxErrorException e) {
		}
	}

	@Test
	public void testAlterSyntaxValidateSix() {
		try {
			sqlParserObjTest.parse("alter table table_name add varchar;");
			fail("SyntaxErrorException thrown or AssertionError occurred!");
		} catch (SyntaxErrorException e) {
		}
	}

	@Test
	public void testAlterSyntaxValidateSeven() {
		try {
			sqlParserObjTest.parse("alter table table_name add  column_name ;");
			fail("SyntaxErrorException thrown or AssertionError occurred!");
		} catch (SyntaxErrorException e) {
		}
	}

	@Test
	public void testAlterSyntaxValidateEight() {
		try {
			sqlParserObjTest.parse("alter table table_name drop column column_name ;");
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occurred!");
		}
	}
	
	@Test
	public void testAlterSyntaxValidateNine() {
		try {
			sqlParserObjTest.parse("   alter     table     table_name       drop    column     column_name      ;              ");
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occurred!");
		}
	}
	
	@Test
	public void testAlterSyntaxValidateTen() {
		try {
			sqlParserObjTest.parse("   alter     table     table_name       add         column_name    int  ;              ");
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occurred!");
		}
	}
	
	@Test
	public void testAlterSyntaxValidateEleven() {
		try {
			sqlParserObjTest.parse("   aLtEr     TaBlE     tAbLe_nAMe       aDd         colUMn_nAMe    int  ;              ");
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occurred!");
		}
	}
	
	@Test
	public void testAlterSyntaxValidateTwelve() {
		try {
			sqlParserObjTest.parse("   aLtEr     TaBlE     tAbLe_nAMe       DrOP     column    colUMn_nAMe     ;              ");
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occurred!");
		}
	}
	
	@Test
	public void testAlterSyntaxParsingOne() {

		try {
			alterObj = sqlParserObjTest.parse("alter table table_name drop column column_name;");
			assertEquals(((AlterDrop)alterObj).getTableName().toLowerCase(), "table_name");
			assertEquals(((AlterDrop)alterObj).getColumnName().toLowerCase(), "column_name");
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occurred!");
		}
	}
	
	@Test
	public void testAlterSyntaxParsingTwo() {

		try {
			alterObj = sqlParserObjTest.parse("alter table table_name add column_name int;");
			assertEquals(((AlterAdd)alterObj).getTableName().toLowerCase(), "table_name");
			assertEquals(((AlterAdd)alterObj).getColumnName().toLowerCase(), "column_name");
			assertEquals(((AlterAdd)alterObj).getDataType(), DBInteger.class);
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occurred!");
		}
	}
}
