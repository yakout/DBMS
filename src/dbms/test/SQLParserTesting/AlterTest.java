package dbms.test.SQLParserTesting;

import static org.junit.Assert.*;

import org.junit.Test;

import dbms.exception.SyntaxErrorException;
import dbms.sqlparser.SQLParser;

public class AlterTest extends SqlParserRef {

	private final SQLParser sqlParserObjTest = super.getSqlParserReference();

	@Test
	public void testAlterSyntaxValidateOne() {

		try {
			sqlParserObjTest.parse("alter table table_name drop column_name;");
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}
	}

	@Test
	public void testAlterSyntaxValidateTwo() {
		try {
			sqlParserObjTest.parse("alter table table_name add column_name int;");
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}
	}
	
	@Test
	public void testAlterSyntaxValidateThree() {
		try {
			sqlParserObjTest.parse("alter table table_name add column_name varchar;");
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}
	}
	
	@Test
	public void testAlterSyntaxValidateFour() {
		try {
			sqlParserObjTest.parse("alter table table_name add column_name varchar;");
		} catch (SyntaxErrorException e) {
			fail("SyntaxErrorException thrown or AssertionError occured!");
		}
	}
	
	
	

}
