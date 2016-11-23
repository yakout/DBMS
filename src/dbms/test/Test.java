package dbms.test;

import dbms.exception.DatabaseAlreadyCreatedException;
import dbms.exception.DatabaseNotFoundException;
import dbms.exception.TableAlreadyCreatedException;
import dbms.xml.XMLParser;

public class Test {
	public static void main(String[] args) {

		try {
			XMLParser.getInstance().createDatabase("db");
		} catch (DatabaseAlreadyCreatedException e) {
			System.err.println("DATABASE ALREADY CREATED");
		}
		try {
			XMLParser.getInstance().createTable("db", "table1", null);
		} catch (DatabaseNotFoundException e) {
			System.err.println("DATABASE NOT FOUND");
		} catch (TableAlreadyCreatedException e) {
			System.err.println("TABLE ALREADY FOUND");
		}
	}
}