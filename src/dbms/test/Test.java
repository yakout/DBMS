package dbms.test;

import dbms.xml.XMLParser;

public class Test {
	public static void main(String[] args) {
		XMLParser.getInstance().createTable("x", "tbl", null);;
	}
}