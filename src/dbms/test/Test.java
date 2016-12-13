package dbms.test;

import dbms.backend.BackendController;
import dbms.backend.parsers.xml.XMLParser;
import dbms.datatypes.DBDatatype;
import dbms.datatypes.DBDate;
import dbms.datatypes.DBInteger;
import dbms.datatypes.DBString;
import dbms.exception.*;
import dbms.sqlparser.SQLParser;
import dbms.util.RecordSet;

import java.io.FileNotFoundException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Test {
	public static void main(String[] args) throws FileNotFoundException, IncorrectDataEntryException, TableNotFoundException, DatabaseNotFoundException, SyntaxErrorException, TableAlreadyCreatedException {
		try {
            SQLParser.getInstance().parse("drop DATABASE db1;").execute();
            SQLParser.getInstance().parse("CREATE DATABASE db1;").execute();

			SQLParser.getInstance().parse("INSERT INTO table1 (ID, name, Gender, Date) VALUES (15, 'hamada14', 'Male', '1996-08-17')").execute();
			SQLParser.getInstance().parse("INSERT INTO table1 (ID, Name, Gender, Date) VALUES (16, 'what_ever', 'Female', '1996-12-15')").execute();
			SQLParser.getInstance().parse("INSERT INTO table1 (ID, Name, Gender, Date) VALUES (17, 'Yakouts GF', 'Male', '1995-08-08')").execute();
			SQLParser.getInstance().parse("INSERT INTO table1 (ID, Name, Gender) VALUES (18, 'tolbas', 'Female')").execute();
			Map<String, DBDatatype> entries = new HashMap<>();

            SQLParser.getInstance().parse("SELECT * FROM table1 UNION SELECT * FROM table1 WHERE ID < 18").execute();


		} catch (DatabaseNotFoundException | TableNotFoundException | SyntaxErrorException | DataTypeNotSupportedException | TableAlreadyCreatedException | DatabaseAlreadyCreatedException | IncorrectDataEntryException e) {
			e.printStackTrace();
		}
	}
}
