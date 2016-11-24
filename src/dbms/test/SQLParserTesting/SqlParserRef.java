package dbms.test.SQLParserTesting;

import dbms.sqlparser.SQLParser;

public class SqlParserRef {

	private final SQLParser sqlParserObjTest = SQLParser.getInstace();
	public SQLParser getSqlParserReference() {
		return this.sqlParserObjTest;
	}
}
