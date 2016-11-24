package dbms.test;

import java.util.HashMap;
import java.util.Map;

import javax.xml.transform.TransformerException;

import dbms.exception.DatabaseAlreadyCreatedException;
import dbms.exception.DatabaseNotFoundException;
import dbms.exception.SyntaxErrorException;
import dbms.exception.TableAlreadyCreatedException;
import dbms.exception.TableNotFoundException;
import dbms.xml.XMLParser;

public class Test {
	public static void main(String[] args) {
		try {
			XMLParser.getInstance().createDatabase("testDB");
		} catch (DatabaseAlreadyCreatedException e) {
			e.printStackTrace();
		}
		Map<String, Class> columns = new HashMap<>();
		columns.put("ID", Integer.class);
		columns.put("Name", String.class);
		columns.put("Organization", String.class);
		try {
			XMLParser.getInstance().createTable("testDB", "table1", columns);
		} catch (DatabaseNotFoundException | TableAlreadyCreatedException | TransformerException | SyntaxErrorException e) {
			e.printStackTrace();
		}
		Map<String, Object> rows = new HashMap<>();
		rows.put("ID", 125);
		rows.put("Name", "hamada14");
		rows.put("Organization", "AlexU");
		try {
			XMLParser.getInstance().insertIntoTable("testDB", "table1", rows);
		} catch (DatabaseNotFoundException | TableNotFoundException | SyntaxErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}