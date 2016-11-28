package dbms.test;

import java.io.FileNotFoundException;

import dbms.exception.DataTypeNotSupportedException;
import dbms.exception.DatabaseAlreadyCreatedException;
import dbms.exception.DatabaseNotFoundException;
import dbms.exception.IncorrectDataEntryException;
import dbms.exception.SyntaxErrorException;
import dbms.exception.TableAlreadyCreatedException;
import dbms.exception.TableNotFoundException;
import dbms.sqlparser.SQLParser;

public class Test {
	public static void main(String[] args) throws FileNotFoundException {
		try {
			SQLParser.getInstance().parse("CREATE DATABASE db1;").execute();
			SQLParser.getInstance().parse("CREATE TABLE table1 (ID int, Name varchar, Gender varchar);").execute();
			
			
			SQLParser.getInstance().parse("INSERT INTO table1 (ID, Name, Gender) VALUES (15, 'hamada14', 'Male');").execute();
			SQLParser.getInstance().parse("INSERT INTO table1 (ID, Name, Gender) VALUES (16, 'what_ever', 'Female');").execute();
			SQLParser.getInstance().parse("INSERT INTO table1 (ID, Name, Gender) VALUES (17, 'awalid', 'Male');").execute();
			SQLParser.getInstance().parse("INSERT INTO table1 (ID, Name, Gender) VALUES (18, 'tolbas', 'Female');").execute();
			SQLParser.getInstance().parse("INSERT INTO table1 (ID, Name, Gender) VALUES (19, 'khaled', 'Male');").execute();
			SQLParser.getInstance().parse("DELETE * FROM table1;").execute();
			SQLParser.getInstance().parse("INSERT INTO table1 (ID, Name, Gender) VALUES (15, 'hamada14', 'Male');").execute();
			SQLParser.getInstance().parse("INSERT INTO table1 (ID, Name, Gender) VALUES (16, 'what_ever', 'Female');").execute();
			SQLParser.getInstance().parse("INSERT INTO table1 (ID, Name, Gender) VALUES (17, 'awalid', 'Male');").execute();
			SQLParser.getInstance().parse("INSERT INTO table1 (ID, Name, Gender) VALUES (18, 'tolbas', 'Female');").execute();
			SQLParser.getInstance().parse("INSERT INTO table1 (ID, Name, Gender) VALUES (19, 'khaled', 'Male');").execute();
			SQLParser.getInstance().parse("SELECT * FROM table1;").execute();
			SQLParser.getInstance().parse("alter table table1 add col3 int;").execute();
			SQLParser.getInstance().parse("select * from table1;").execute();
			SQLParser.getInstance().parse("alter table table1 drop col3;").execute();
			SQLParser.getInstance().parse("select * from table1;").execute();
			SQLParser.getInstance().parse("alter table table1 add col3 varchar;").execute();
			SQLParser.getInstance().parse("select * from table1;").execute();
			
		} catch (DatabaseNotFoundException | TableNotFoundException | SyntaxErrorException | DataTypeNotSupportedException | TableAlreadyCreatedException | DatabaseAlreadyCreatedException | IncorrectDataEntryException e) {
			e.printStackTrace();
		}

	}
}