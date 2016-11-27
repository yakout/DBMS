package dbms.xml;

import dbms.exception.SyntaxErrorException;
import dbms.sqlparser.SQLParser;

public class test {
	public static void main(String[] args) {
		try {
			System.out.println(SQLParser.getInstance().parse("SELECT * FROM table1 WHERE ((Gender == 'Male') AND (ID > 10));"));
		} catch (SyntaxErrorException e) {
			System.out.println(e.toString());
		}
	}
}
