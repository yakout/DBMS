package dbms.backend.parsers.json;

import dbms.backend.BackendController;
import dbms.exception.*;

import java.util.LinkedHashMap;
import java.util.Map;

public class test {
	private final static BackendController JSONParserConc = BackendController.getInstance();
	public static void main(String[] argv) {
		try {
			JSONParserConc.createDatabase("mine");
		} catch (DatabaseAlreadyCreatedException e) {
			e.printStackTrace();
		}
		Map<String, Class> passMap = new LinkedHashMap<String, Class>();
		passMap.put("column_1", Integer.class);
		passMap.put("column_2", String.class);
		try {
			JSONParserConc.createTable("table11", passMap);
		} catch (DatabaseNotFoundException | TableAlreadyCreatedException | IncorrectDataEntryException e) {
			e.printStackTrace();
		}
		Map<String, Object> entriesMap = new LinkedHashMap<String, Object>();
		entriesMap.put("column_1", 550);
		entriesMap.put("column_2", "KHalED");
		try {
			JSONParserConc.insertIntoTable("mine", entriesMap);
		} catch (DatabaseNotFoundException | TableNotFoundException | IncorrectDataEntryException e) {
			e.printStackTrace();
		}
	}
}
