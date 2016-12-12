package dbms.test;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.GsonBuilder;
import dbms.backend.BackendController;
import dbms.backend.parsers.xml.XMLParser;
import dbms.exception.*;
import dbms.sqlparser.SQLParser;
import dbms.sqlparser.sqlInterpreter.rules.Expression;
import dbms.util.RecordSet;

import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;

public class Test {
	public static void main(String[] args) throws FileNotFoundException, IncorrectDataEntryException, TableNotFoundException, DatabaseNotFoundException, SyntaxErrorException, TableAlreadyCreatedException {
		try {
			SQLParser.getInstance().parse("CREATE DATABASE db1;").execute();
			SQLParser.getInstance().parse("CREATE TABLE table1 (ID int, Name varchar, Gender varchar, Date date);").execute();

			SQLParser.getInstance().parse("INSERT INTO table1 (ID, name, Gender, Date) VALUES (15, 'hamada14', 'Male', '1996-08-17');").execute();
			SQLParser.getInstance().parse("INSERT INTO table1 (ID, Name, Gender, Date) VALUES (16, 'what_ever', 'Female', '1996-12-15');").execute();
			SQLParser.getInstance().parse("INSERT INTO table1 (ID, Name, Gender, Date) VALUES (17, 'Yakouts GF', 'Male', '1995-08-08');").execute();
			SQLParser.getInstance().parse("INSERT INTO table1 (ID, Name, Gender) VALUES (18, 'tolbas', 'Female');").execute();
			Collection<String> columns = new ArrayList<>();
			columns.add("ID");
			columns.add("name");
			columns.add("gender");
			RecordSet rs = BackendController.getInstance().select("table1", columns, null);
			System.out.print("x");
		} catch (DatabaseNotFoundException | TableNotFoundException | SyntaxErrorException | DataTypeNotSupportedException | TableAlreadyCreatedException | DatabaseAlreadyCreatedException | IncorrectDataEntryException e) {
			e.printStackTrace();
		}
	}
}
