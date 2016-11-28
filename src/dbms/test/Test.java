package dbms.test;

import java.io.FileNotFoundException;

import dbms.exception.DataTypeNotSupportedException;
import dbms.exception.DatabaseAlreadyCreatedException;
import dbms.exception.DatabaseNotFoundException;
import dbms.exception.SyntaxErrorException;
import dbms.exception.TableAlreadyCreatedException;
import dbms.exception.TableNotFoundException;
import dbms.sqlparser.SQLParser;
import dbms.xml.XMLParser;

public class Test {
	public static void main(String[] args) throws FileNotFoundException {
		try {
			SQLParser.getInstance().parse("CREATE DATABASE testDB;").execute();
			SQLParser.getInstance().parse("USE DATABASE testDB;").execute();
			SQLParser.getInstance().parse("CREATE TABLE table1 (ID int, Name varchar, Gender varchar);").execute();
			SQLParser.getInstance().parse("INSERT INTO table1 (ID, Name, Gender) VALUES (15, 'hamada14', 'Male');").execute();
			SQLParser.getInstance().parse("INSERT INTO table1 (ID, Name, Gender) VALUES (16, 'what_ever', 'Male');").execute();
			SQLParser.getInstance().parse("INSERT INTO table1 (ID, Name, Gender) VALUES (17, 'awalid', 'Male');").execute();
			SQLParser.getInstance().parse("INSERT INTO table1 (ID, Name, Gender) VALUES (18, 'tolbas', 'Female');").execute();
			SQLParser.getInstance().parse("SELECT * FROM table1;").execute();
			System.out.print("\n\n");
			SQLParser.getInstance().parse("UPDATE table1 SET ID = 9, Name = Gender WHERE Name == 'Female';").execute();
			SQLParser.getInstance().parse("SELECT * FROM table1;").execute();
//			XMLParser.getInstance().dropDataBase("testDB");
		} catch (DatabaseNotFoundException | TableNotFoundException | SyntaxErrorException | DataTypeNotSupportedException | TableAlreadyCreatedException | DatabaseAlreadyCreatedException e) {
			e.printStackTrace();
		}

	}
}