package dbms.test;

import java.io.FileNotFoundException;
import java.util.List;

import dbms.exception.DatabaseNotFoundException;
import dbms.exception.TableNotFoundException;
import dbms.util.Column;
import dbms.util.Table;

public class Test {
	public static void main(String[] args) throws FileNotFoundException {
//		try {
//			SQLParser.getInstance().parse("CREATE DATABASE db1;").execute();
//			SQLParser.getInstance().parse("CREATE TABLE table1 (ID int, Name varchar, Gender varchar);").execute();
//
//
//			SQLParser.getInstance().parse("INSERT INTO table1 (ID, Name, Gender) VALUES (15, 'hamada14', 'Male');").execute();
//			SQLParser.getInstance().parse("INSERT INTO table1 (ID, Name, Gender) VALUES (16, 'what_ever', 'Female');").execute();
//			SQLParser.getInstance().parse("INSERT INTO table1 (ID, Name, Gender) VALUES (17, 'awalid', 'Male');").execute();
//			SQLParser.getInstance().parse("INSERT INTO table1 (ID, Name, Gender) VALUES (18, 'tolbas', 'Female');").execute();
//			SQLParser.getInstance().parse("INSERT INTO table1 (ID, Name, Gender) VALUES (19, 'khaled', 'Male');").execute();
//
//		} catch (DatabaseNotFoundException | TableNotFoundException | SyntaxErrorException | DataTypeNotSupportedException | TableAlreadyCreatedException | DatabaseAlreadyCreatedException | IncorrectDataEntryException e) {
//			e.printStackTrace();
//		}
//		Integer x = (Integer) DatatypeFactory.getFactory().toObj("15", "Integer");
//		System.out.println(x.toString());
		Table table = new Table("db1", "table1");
		try {
			table.loadTable();
		} catch (TableNotFoundException | DatabaseNotFoundException e) {
			e.printStackTrace();
		}
		System.out.print(table.getDBName() + ": ");
		System.out.println(table.getName());
		System.out.println();
		List<Column> cols = table.getColumns();
		for (Column col : cols) {
			System.out.print(col.getName() + ": ");
			for (Object o : col.getEntries()) {
				System.out.print(String.valueOf(o) + ", ");
			}
			System.out.println();
		}
	}
}