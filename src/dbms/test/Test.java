package dbms.test;

import java.util.HashMap;

import javax.xml.transform.TransformerException;

public class Test {
	public static void main(String[] args) throws
		TransformerException {
//		try {
//			XMLParser.getInstance().createDatabase("db");
//		} catch (DatabaseAlreadyCreatedException e) {
//			System.err.println("DATABASE ALREADY CREATED");
//		}
//		try {
//			XMLParser.getInstance().createTable("db", "table1", null);
//		} catch (DatabaseNotFoundException e) {
//			System.err.println("DATABASE NOT FOUND");
//		} catch (TableAlreadyCreatedException e) {
//			System.err.println("TABLE ALREADY FOUND");
//		}
		HashMap<String, Object> x = new HashMap<>();
		x.put("col1", new Integer(5));
		get(x.get("col2"));
	}

	private static void get(Object n) {
		System.out.println();
	}
}