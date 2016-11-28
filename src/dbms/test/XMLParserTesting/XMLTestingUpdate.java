package dbms.test.XMLParserTesting;

import static org.junit.Assert.*;

import java.util.*;
import org.junit.Test;

import dbms.connection.XMLConnection;
import dbms.sqlparser.sqlInterpreter.Condition;
import dbms.sqlparser.sqlInterpreter.rules.Where;
import dbms.util.Result;
import dbms.util.ResultSet;
import dbms.xml.XMLParser;

public class XMLTestingUpdate {
	private final XMLConnection xmlParserConc = XMLConnection.getInstance();
	/*
	 * These tests check the validity of XML Parser(UPDATE command) module
	 */
	@Test
	public void testOne() {
		try {
			xmlParserConc.createDatabase("test1");
			Map<String, Class> passMap = new LinkedHashMap<String, Class>();
			passMap.put("col_1", String.class);
			passMap.put("col_2", String.class);
			passMap.put("col_3", String.class);
			passMap.put("col_4", Integer.class);
			xmlParserConc.createTable("table_1", passMap);
			Map<String, Object> entriesMap = new LinkedHashMap<String, Object>();
			entriesMap.put("col_1", "Sea");
			entriesMap.put("col_2", "SQL");
			entriesMap.put("col_3", "SkY");
			entriesMap.put("col_4", 1);
			xmlParserConc.insertIntoTable("table_1", entriesMap);
			entriesMap.clear();
			entriesMap.put("col_1", "high");
			entriesMap.put("col_2", "CFFF");
			entriesMap.put("col_3", "wyyyyy");
			entriesMap.put("col_4", 10);
			xmlParserConc.insertIntoTable("table_1", entriesMap);
			entriesMap.clear();
			entriesMap.put("col_1", "keyyyy");
			entriesMap.put("col_2", "steadyyy");
			entriesMap.put("col_3", "andddd");
			entriesMap.put("col_4", 100);
			xmlParserConc.insertIntoTable("table_1", entriesMap);
			entriesMap.clear();
			
			Map<String, Object> values = new LinkedHashMap<String, Object>();
			values.put("col_1", "changed");
			values.put("col_2", "changed");
			values.put("col_3", "changed");
			values.put("col_4", 10000);
			xmlParserConc.update("table_1", values, null, null);
			
			Set<String> columns = new TreeSet<String>();
			columns.add("col_1");
			columns.add("col_2");
			columns.add("col_4");
			Map<String, Object> resExpected = new LinkedHashMap<String, Object>();
			values.clear();
			ResultSet resultSet = new ResultSet();
			resExpected.put("col_1", "changed");
			resExpected.put("col_2", "changed");
			resExpected.put("col_4", 10000);
			resultSet.add(new Result(resExpected));
			resExpected = new LinkedHashMap<String, Object>();
			resExpected.put("col_1", "changed");
			resExpected.put("col_2", "changed");
			resExpected.put("col_4", 10000);
			resultSet.add(new Result(resExpected));
			resExpected = new LinkedHashMap<String, Object>();
			resExpected.put("col_1", "changed");
			resExpected.put("col_2", "changed");
			resExpected.put("col_4", 10000);
			resultSet.add(new Result(resExpected));
			ResultSet actualRes = xmlParserConc.select("table_1", columns, null);
			Iterator<Result> resultSetIt = resultSet.iterator();
			Iterator<Result> actualResultIt = actualRes.iterator();
			while (resultSetIt.hasNext() && actualResultIt.hasNext()) {
				assertTrue(resultSetIt.next().getResult().equals(
						actualResultIt.next().getResult()));
			}
		} catch(Exception e) {
			e.printStackTrace();
			fail("Not yet implemented");			
		}
	}
	
	@Test
	public void testTwo() {
		try {
			xmlParserConc.createDatabase("test2");
			Map<String, Class> passMap = new LinkedHashMap<String, Class>();
			passMap.put("col_1", String.class);
			passMap.put("col_2", String.class);
			passMap.put("col_3", String.class);
			passMap.put("col_4", Integer.class);
			xmlParserConc.createTable("table_1", passMap);
			Map<String, Object> entriesMap = new LinkedHashMap<String, Object>();
			entriesMap.put("col_1", "Sea");
			entriesMap.put("col_2", "SQL");
			entriesMap.put("col_3", "SkY");
			entriesMap.put("col_4", 1);
			xmlParserConc.insertIntoTable("table_1", entriesMap);
			entriesMap.clear();
			entriesMap.put("col_1", "high");
			entriesMap.put("col_2", "CFFF");
			entriesMap.put("col_3", "wyyyyy");
			entriesMap.put("col_4", 10);
			xmlParserConc.insertIntoTable("table_1", entriesMap);
			entriesMap.clear();
			entriesMap.put("col_1", "keyyyy");
			entriesMap.put("col_2", "steadyyy");
			entriesMap.put("col_3", "andddd");
			entriesMap.put("col_4", 100);
			xmlParserConc.insertIntoTable("table_1", entriesMap);
			entriesMap.clear();
			
			Map<String, Object> values = new LinkedHashMap<String, Object>();
			values.put("col_1", "changed");
			values.put("col_2", "changed");
			values.put("col_3", "changed");
			values.put("col_4", 10000);
			Condition where = new Where("col_4 < 50");
			xmlParserConc.update("table_1", values, null, where);
			
			
			Set<String> columns = new TreeSet<String>();
			columns.add("col_1");
			columns.add("col_2");
			columns.add("col_4");
			Map<String, Object> resExpected = new LinkedHashMap<String, Object>();
			values.clear();
			ResultSet resultSet = new ResultSet();
			resExpected.put("col_1", "keyyyy");
			resExpected.put("col_2", "steadyyy");
			resExpected.put("col_4", 100);
			resultSet.add(new Result(resExpected));
			Condition where2 =  new Where("col_4 < 10000");
			ResultSet actualRes = xmlParserConc.select("table_1", columns, where2);
			Iterator<Result> resultSetIt = resultSet.iterator();
			Iterator<Result> actualResultIt = actualRes.iterator();
			while (resultSetIt.hasNext() && actualResultIt.hasNext()) {
//				System.out.println(resultSetIt.next().getResult() + "    " + actualResultIt.next().getResult());
				assertTrue(resultSetIt.next().getResult().equals(
						actualResultIt.next().getResult()));
			}
		} catch(Exception e) {
			e.printStackTrace();
			fail("Not yet implemented");			
		}
	}

}