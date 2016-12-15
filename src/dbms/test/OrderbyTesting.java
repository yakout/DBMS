package dbms.test;

import dbms.sqlparser.SQLParser;

public class OrderbyTesting {
	
	public static void main(String[] args) {
		try {
            SQLParser.getInstance().parse("CREATE DATABASE db1").execute();

			SQLParser.getInstance().parse("CREATE TABLE table1 (ID int, Name varchar, Gender varchar, Date date)").execute(); // FAIL
			SQLParser.getInstance().parse("INSERT INTO table1 VALUES (15, 'Whatever', 'Male', '1996-08-17')").execute();
			SQLParser.getInstance().parse("INSERT INTO table1 (ID, Name, Gender, Date) VALUES (16, 'Yakout', 'Female', '1996-12-15')").execute();
			SQLParser.getInstance().parse("INSERT INTO table1 (ID, Name, Gender, Date) VALUES (01, 'hamada14', 'Male', '1995-08-08')").execute();
			SQLParser.getInstance().parse("INSERT INTO table1 (ID, Name, Gender, Date) VALUES (02, 'ahmed', 'Male', '1995-08-08')").execute();
			SQLParser.getInstance().parse("INSERT INTO table1 (ID, Name, Gender, Date) VALUES (17, 'ahmed1', 'Male', '2001-08-08')").execute();
			SQLParser.getInstance().parse("INSERT INTO table1 (ID, Name, Gender, Date) VALUES (15, 'tolba15', 'Male', '2005-08-06')").execute();
			SQLParser.getInstance().parse("INSERT INTO table1 (ID, Name, Gender, Date) VALUES (17, 'sssss', 'Female', '1999-15-13')").execute();
			SQLParser.getInstance().parse("INSERT INTO table1 (ID, Name, Gender) VALUES (18, 'tolbas', 'Female')").execute();
			SQLParser.getInstance().parse("SELECT * FROM table1 ORDER BY Name").execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
