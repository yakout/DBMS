package dbms.test;

import java.io.FileNotFoundException;

import dbms.exception.DataTypeNotSupportedException;
import dbms.exception.DatabaseAlreadyCreatedException;
import dbms.exception.DatabaseNotFoundException;
import dbms.exception.SyntaxErrorException;
import dbms.exception.TableAlreadyCreatedException;
import dbms.exception.TableNotFoundException;
import dbms.sqlparser.SQLParser;

public class Test {
	public static void main(String[] args) throws FileNotFoundException {
//		try {
//			XMLParser.getInstance().insertIntoTable("testDB", "table1", rows);
//		} catch (DatabaseNotFoundException | TableNotFoundException | SyntaxErrorException e) {
//			e.printStackTrace();
//		}
//		rows.clear();
//		rows.put("ID", 126);
//		ResultSet x = null;
//		try {
//			x = XMLParser.getInstance().select("testDB", "table1", null);
//		} catch (DatabaseNotFoundException | TableNotFoundException e) {
//			e.printStackTrace();
//		}
//		for (Result res : x) {
//			System.out.println(res.getInt("ID"));
//			System.out.println(res.getString("Name"));
//			System.out.println(res.getString("Organization"));
//		}
//
//		try {
//			XMLParser.getInstance().createDatabase("testDB");
//		} catch (DatabaseAlreadyCreatedException e) {
//			e.printStackTrace();
//		}
//		Map<String, Class> columns = new HashMap<>();
//		columns.put("ID", Integer.class);
//		columns.put("Name", String.class);
//		columns.put("Organization", String.class);
//		columns.put("Team", String.class);
//		try {
//			XMLParser.getInstance().createTable("testDB", "table1", columns);
//		} catch (DatabaseNotFoundException | TableAlreadyCreatedException | TransformerException | SyntaxErrorException e) {
//			e.printStackTrace();
//		}
//		Map<String, Object> rows = new HashMap<>();
//		rows.put("ID", 125);
//		rows.put("Name", "hamada14");
//		rows.put("Organization", "AlexU");
//		rows.put("Team", "SQL");
//		try {
//			XMLParser.getInstance().insertIntoTable("testDB", "table1", rows);
//		} catch (DatabaseNotFoundException | TableNotFoundException | SyntaxErrorException e) {
//			e.printStackTrace();
//		}
//		rows.put("ID", 99);
//		rows.put("Name", "Ahmed");
//		rows.put("Organization", "myHome");
//		rows.put("Team", "XML");
//		try {
//			XMLParser.getInstance().insertIntoTable("testDB", "table1", rows);
//		} catch (DatabaseNotFoundException | TableNotFoundException | SyntaxErrorException e) {
//			e.printStackTrace();
//		}
//		rows.put("ID", 35);
//		rows.put("Name", "x");
//		rows.put("Organization", "AlexU");
//		rows.put("Team", "UI");
//		try {
//			XMLParser.getInstance().insertIntoTable("testDB", "table1", rows);
//		} catch (DatabaseNotFoundException | TableNotFoundException | SyntaxErrorException e) {
//			e.printStackTrace();
//		}

		try {
			SQLParser.getInstance().parse("CREATE DATABASE testDB;").execute();
			SQLParser.getInstance().parse("USE DATABASE testDB;").execute();
			SQLParser.getInstance().parse("CREATE TABLE table1 (ID int, Name varchar, Gender varchar);").execute();
			SQLParser.getInstance().parse("INSERT INTO table1 (ID, Name, Gender) VALUES (15, 'hamada14', 'Male');").execute();
			SQLParser.getInstance().parse("INSERT INTO table1 (ID, Name, Gender) VALUES (16, 'what_ever', 'Male');").execute();
			SQLParser.getInstance().parse("INSERT INTO table1 (ID, Name, Gender) VALUES (17, 'awalid', 'Male');").execute();
			SQLParser.getInstance().parse("INSERT INTO table1 (ID, Name, Gender) VALUES (18, 'tolba', 'Female');").execute();
			SQLParser.getInstance().parse("SELECT ID FROM table1;").execute();
		} catch (DatabaseNotFoundException | TableNotFoundException | SyntaxErrorException | DataTypeNotSupportedException | TableAlreadyCreatedException | DatabaseAlreadyCreatedException e) {
			e.printStackTrace();
		}
//		Map<String, Object> values = new HashMap<String, Object>();
//		values.put("ID", "s");
//		values.put("Organization", "mine");
//		values.put("Name", "XMLPARSERSSSSSS");
//		Map<String, String> columns1 = new HashMap<String, String>();
//		columns1.put("Organization", "Team");
//		try {
//			XMLParser.getInstance().update("testDB", "table1", values, columns1, null);
//		} catch (DatabaseNotFoundException | TableNotFoundException | SyntaxErrorException e) {
//			e.printStackTrace();
//		} catch (DataTypeNotSupportedException e) {
//			e.printStackTrace();
//		}

	}
}