package dbms.test;

import dbms.sqlparser.SQLParser;

public class OrderbyTesting {
	
	public static void main(String[] args) {
		try {
			SQLParser.getInstance().parse("drop DATABASE db1").execute();
            SQLParser.getInstance().parse("CREATE DATABASE db1").execute();

			SQLParser.getInstance().parse("CREATE TABLE table1 (ID int, Name varchar, Gender varchar, Date date)").execute(); // FAIL
			SQLParser.getInstance().parse("INSERT INTO table1 VALUES (15, 'whatever', 'Male', '1996-08-17')").execute();
			SQLParser.getInstance().parse("INSERT INTO table1 (ID, Name, Gender, Date) VALUES (16, 'yakout', 'Female', '1996-12-15')").execute();
			SQLParser.getInstance().parse("INSERT INTO table1 (ID, Name, Gender, Date) VALUES (17, 'hamada14', 'Male', '1995-08-08')").execute();
			SQLParser.getInstance().parse("INSERT INTO table1 (ID, Name, Gender) VALUES (18, 'tolbas', 'Female')").execute();
//			SQLParser.getInstance().parse("INSERT INTO table1 (ID, Name, Gender) VALUES (18, 'tolbas', 'Female')").execute();
//			SQLParser.getInstance().parse("INSERT INTO table1 (ID, Name, Gender) VALUES (18, 'tolbas', 'Female')").execute();
//			SQLParser.getInstance().parse("INSERT INTO table1 (ID, Name, Gender) VALUES (18, 'tolbas', 'Female')").execute();
			SQLParser.getInstance().parse("SELECT * FROM table1 ORDER BY id").execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
