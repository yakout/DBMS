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
		HashMap<String, String> x = new HashMap<>();
		x.put("a", "b");
		System.out.print(x.get("x"));
	}
}