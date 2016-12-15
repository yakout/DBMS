package dbms.test;

import java.util.LinkedHashMap;

import dbms.backend.BackendController;
import dbms.datatypes.DBDatatype;
import dbms.datatypes.DBInteger;
import dbms.datatypes.DBString;
import dbms.datatypes.DatatypeFactory;
import dbms.sqlparser.SQLParser;

public class OrderbyTesting {
	private final static BackendController xmlParser = BackendController.getInstance();
	
	public static void main(String[] args) {
		try {
			xmlParser.createDatabase("test1");
			LinkedHashMap<String, Class<? extends DBDatatype>> passMap = new LinkedHashMap<>();
			passMap.put("col_1", DBString.class);
			passMap.put("col_2", DBString.class);
			passMap.put("col_3", DBString.class);
			passMap.put("col_4", DBInteger.class);
			xmlParser.createTable("table_1", passMap);
			LinkedHashMap<String, DBDatatype> entriesMap = new LinkedHashMap<>();
			entriesMap.put("col_1", DatatypeFactory.convertToDataType("Sea"));
			entriesMap.put("col_2", DatatypeFactory.convertToDataType("SQL"));
			entriesMap.put("col_3", DatatypeFactory.convertToDataType("SkY"));
			entriesMap.put("col_4", DatatypeFactory.convertToDataType(1));
			xmlParser.insertIntoTable("table_1", entriesMap);
			entriesMap.clear();
			entriesMap.put("col_1", DatatypeFactory.convertToDataType("high"));
			entriesMap.put("col_2", DatatypeFactory.convertToDataType("CFFF"));
			entriesMap.put("col_3", DatatypeFactory.convertToDataType("wyyyyy"));
			entriesMap.put("col_4", DatatypeFactory.convertToDataType(10));
			xmlParser.insertIntoTable("table_1", entriesMap);
			entriesMap.clear();
			entriesMap.put("col_1", DatatypeFactory.convertToDataType("key"));
			entriesMap.put("col_2", DatatypeFactory.convertToDataType("steadyyy"));
			entriesMap.put("col_3", DatatypeFactory.convertToDataType("andddd"));
			entriesMap.put("col_4", DatatypeFactory.convertToDataType(100));
			xmlParser.insertIntoTable("table_1", entriesMap);
			entriesMap.clear();
			SQLParser.getInstance().parse("SELECT * FROM table_1 ORDER BY col_1").execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
