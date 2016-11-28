package dbms.test.XMLParserTesting;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import dbms.connection.XMLConnection;
import dbms.sqlparser.sqlInterpreter.BooleanExpression;
import dbms.sqlparser.sqlInterpreter.Condition;
import dbms.util.Result;
import dbms.util.ResultSet;

public class XMLTestingSelect {
	private final XMLConnection xmlParserConc = XMLConnection.getInstance();
	/*
	 * These tests check the correctness of the XML Parser module.
	 */

	@Test
	public void testOne() {
		/*
		 * Test checks creating database and table then inserting two columns with different datatypes
		 * and then selecting them. SELECT * FROM TABLE_NAME;
		 */
		
		try {
			xmlParserConc.createDatabase("database_1");
			
			Map<String,Class> passMap = new  HashMap<String,Class>();
			passMap.put("column_1", Integer.class);
			passMap.put("column_2", String.class);
			
			xmlParserConc.createTable("table_name", passMap);
			
			Map<String,Object> entriesMap = new HashMap<String,Object>();
			entriesMap.put("column_1", 550);
			entriesMap.put("column_2","KHalED");
			
			xmlParserConc.insertIntoTable("table_name", entriesMap);
			
			Set<String> columns = new HashSet<String>();
			
			columns.add("column_1");
			columns.add("column_2");
			ResultSet result = new ResultSet();
			Map<String, Object> resExpected = new LinkedHashMap<String, Object>();
			resExpected.put("column_1", 550);
			resExpected.put("column_2", "KHalED");
			Result expResult = new Result(resExpected);
			result.add(expResult);
			
			ResultSet actualRes = xmlParserConc.select("table_name", columns, null);
		    assertTrue(result.next().getResult().equals(actualRes.next().getResult()));
		} catch(Exception e) {
			e.printStackTrace();
			fail("Error occured!");
			
		}	
	}
	
	@Test
	public void testTwo() {
		/*
		 * Test checks creating database and table then inserting two columns with different datatypes
		 * and then selecting them. SELECT * FROM TABLE_NAME;
		 */
		
		try {
			xmlParserConc.createDatabase("database_2");
			
			Map<String,Class> passMap = new  HashMap<String,Class>();
			passMap.put("column_1", Integer.class);
			passMap.put("column_2", String.class);
			
			xmlParserConc.createTable("table_name", passMap);
			
			Map<String,Object> entriesMap = new HashMap<String,Object>();
			entriesMap.put("column_1", 550);
			entriesMap.put("column_2","KHalED");
			entriesMap.put("column_1", 14);
			entriesMap.put("column_2", "TOLBa");
			xmlParserConc.insertIntoTable("table_name", entriesMap);
			
			Set<String> columns = new HashSet<String>();
			
			columns.add("column_1");
			columns.add("column_2");
			ResultSet result = new ResultSet();
			Map<String, Object> resExpected = new LinkedHashMap<String, Object>();
			resExpected.put("column_1", 550);
			resExpected.put("column_2", "KHalED");
			resExpected.put("column_1", 14);
			resExpected.put("column_2", "TOLBa");
			Result expResult = new Result(resExpected);
			result.add(expResult);
			
			ResultSet actualRes = xmlParserConc.select("table_name", columns, null);
		    assertTrue(result.next().getResult().equals(actualRes.next().getResult()));
		} catch(Exception e) {
			e.printStackTrace();
			fail("Error occured!");
			
		}
		
		
	}
	
	@Test
	public void testThree() {
		/*
		 * Test checks creating database and table then inserting two columns with different datatypes
		 * and then selecting them. SELECT * FROM TABLE_NAME;
		 */
		
		try {
			xmlParserConc.createDatabase("database_3");
			
			Map<String,Class> passMap = new  HashMap<String,Class>();
			passMap.put("column_1", Integer.class);
			passMap.put("column_2", String.class);
			passMap.put("column_3", String.class);
			xmlParserConc.createTable("table_name", passMap);
			
			Map<String,Object> entriesMap = new HashMap<String,Object>();
			entriesMap.put("column_1", 550);
			entriesMap.put("column_2","KHalED");
			entriesMap.put("column_3","ANAS");
			xmlParserConc.insertIntoTable("table_name", entriesMap);
			entriesMap.clear();
			entriesMap.put("column_1", 14);
			entriesMap.put("column_2", "TOLBa");
			entriesMap.put("column_3","YAKoUT");
			xmlParserConc.insertIntoTable("table_name", entriesMap);
			entriesMap.clear();
			entriesMap.put("column_1", 1512);
			entriesMap.put("column_2", "Merci");
			entriesMap.put("column_3","School");
			xmlParserConc.insertIntoTable("table_name", entriesMap);
			
			Set<String> columns = new HashSet<String>();
			
			columns.add("column_1");
			columns.add("column_2");
			columns.add("column_3");
			ResultSet result = new ResultSet();
			Map<String, Object> resExpected = new LinkedHashMap<String, Object>();
			resExpected.put("column_1", 550);
			resExpected.put("column_2","KHalED");
			resExpected.put("column_3","ANAS");
			resExpected.put("column_1", 14);
			resExpected.put("column_2", "TOLBa");
			resExpected.put("column_3","YAKoUT");
			resExpected.put("column_1", 1512);
			resExpected.put("column_2", "Merci");
			resExpected.put("column_3","School");
			Result expResult = new Result(resExpected);
			result.add(expResult);
			
			ResultSet actualRes = xmlParserConc.select("table_name", columns, null);
		    assertTrue(result.next().getResult().equals(actualRes.next().getResult()));
		} catch(Exception e) {
			e.printStackTrace();
			fail("Error occured!");
			
		}	
	}
	
	@Test
	public void testFour() {
		/*
		 * Test checks creating database and table then inserting two columns with different datatypes
		 * and then selecting them. SELECT * FROM TABLE_NAME;
		 */
		
		try {
			xmlParserConc.createDatabase("database_4");
			
			Map<String,Class> passMap = new  HashMap<String,Class>();
			passMap.put("column_1", Integer.class);
			passMap.put("column_2", String.class);
			passMap.put("column_3", String.class);
			xmlParserConc.createTable("table_name", passMap);
			
			Map<String,Object> entriesMap = new HashMap<String,Object>();
			entriesMap.put("column_1", 550);
			entriesMap.put("column_2","KHalED");
			entriesMap.put("column_3","ANAS");
			xmlParserConc.insertIntoTable("table_name", entriesMap);
			entriesMap.clear();
			entriesMap.put("column_1", 0);
			entriesMap.put("column_2", "TOLBa");
			entriesMap.put("column_3","YAKoUT");
			xmlParserConc.insertIntoTable("table_name", entriesMap);
			entriesMap.clear();
			entriesMap.put("column_1", 1512);
			entriesMap.put("column_2", "Merci");
			entriesMap.put("column_3","School");
			xmlParserConc.insertIntoTable("table_name", entriesMap);
			
			Set<String> columns = new HashSet<String>();
			
			columns.add("column_1");
			columns.add("column_2");
			columns.add("column_3");
			ResultSet result = new ResultSet();
			Map<String, Object> resExpected = new LinkedHashMap<String, Object>();
			resExpected.put("column_1", 550);
			resExpected.put("column_2","KHalED");
			resExpected.put("column_3","ANAS");
			result.add(new Result(resExpected));
			resExpected.clear();
			resExpected.put("column_1", 0);
			resExpected.put("column_2", "TOLBa");
			resExpected.put("column_3","YAKoUT");
			result.add(new Result(resExpected));
			resExpected.clear();
			resExpected.put("column_1", 1512);
			resExpected.put("column_2", "Merci");
			resExpected.put("column_3","School");
			result.add(new Result(resExpected));
			ResultSet actualRes = xmlParserConc.select("table_name", columns, null);
//			Result res = result.next();
//			Result actRes = actualRes.next();
			for (int i = 0; i < 3; i++) {
//				assertTrue(res.getResult().equals(actRes.getResult()));
			}
			} catch(Exception e) {
			e.printStackTrace();
			fail("Error occured!");
			
		}
		
		
	}
	
	
}
