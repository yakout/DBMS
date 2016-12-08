package dbms.test.XMLParserTesting;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

import dbms.backend.BackendController;
import dbms.sqlparser.sqlInterpreter.Condition;
import dbms.sqlparser.sqlInterpreter.Where;
import dbms.util.Result;
import dbms.util.ResultSet;

public class XMLTestingDelete {
	private final BackendController xmlParserConc = BackendController.getInstance();

	@Test
	public void testOne() {

		try {
			xmlParserConc.createDatabase("database_delete_1");

			Map<String, Class> passMap = new LinkedHashMap<String, Class>();
			passMap.put("column_1", Integer.class);
			passMap.put("column_2", String.class);

			xmlParserConc.createTable("table_name", passMap);

			Map<String, Object> entriesMap = new LinkedHashMap<String, Object>();
			entriesMap.put("column_1", 550);
			entriesMap.put("column_2", "KHalED");
			xmlParserConc.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<String, Object>();
			entriesMap.put("column_1", 200);
			entriesMap.put("column_2", "waLiD");
			xmlParserConc.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<String, Object>();
			entriesMap.put("column_1", 7500);
			entriesMap.put("column_2", "AnAs");
			xmlParserConc.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<String, Object>();
			entriesMap.put("column_1", 852);
			entriesMap.put("column_2", "YaKoUt");
			xmlParserConc.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<String, Object>();
			entriesMap.put("column_1", 189);
			entriesMap.put("column_2", "BaRRy");
			xmlParserConc.insertIntoTable("table_name", entriesMap);

			Condition whereCondition = new Where("    column_1   >=    300 ");
			ResultSet result = new ResultSet();
			Map<String, Object> resExpected = new LinkedHashMap<String, Object>();
			resExpected.put("column_1", 550);
			resExpected.put("column_2", "KHalED");
			Result expResult = new Result(resExpected);
			result.add(expResult);

			resExpected = new LinkedHashMap<String, Object>();
			resExpected.put("column_1", 7500);
			resExpected.put("column_2", "AnAs");
			expResult = new Result(resExpected);
			result.add(expResult);

			resExpected = new LinkedHashMap<String, Object>();
			resExpected.put("column_1", 852);
			resExpected.put("column_2", "YaKoUt");
			expResult = new Result(resExpected);
			result.add(expResult);

			xmlParserConc.delete("table_name", whereCondition);

			ResultSet actualRes = xmlParserConc.select("table_name", null, whereCondition);

			Iterator<Result> resultSetItr = result.iterator();
			Iterator<Result> actualResItr = actualRes.iterator();
			while (resultSetItr.hasNext() && actualResItr.hasNext()) {

				assertTrue(resultSetItr.next().getResult().equals(actualResItr.next().getResult()));

			}
		} catch (Exception e) {
			e.printStackTrace();
			fail("Error occured!");

		}
	}

	@Test
	public void testTwo() {

		try {
			xmlParserConc.createDatabase("database_delete_2");

			Map<String, Class> passMap = new LinkedHashMap<String, Class>();
			passMap.put("column_1", Integer.class);
			passMap.put("column_2", String.class);

			xmlParserConc.createTable("table_name", passMap);

			Map<String, Object> entriesMap = new LinkedHashMap<String, Object>();
			entriesMap.put("column_1", 550);
			entriesMap.put("column_2", "KHalED");
			xmlParserConc.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<String, Object>();
			entriesMap.put("column_1", 200);
			entriesMap.put("column_2", "waLiD");
			xmlParserConc.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<String, Object>();
			entriesMap.put("column_1", 7500);
			entriesMap.put("column_2", "AnAs");
			xmlParserConc.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<String, Object>();
			entriesMap.put("column_1", 852);
			entriesMap.put("column_2", "YaKoUt");
			xmlParserConc.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<String, Object>();
			entriesMap.put("column_1", 189);
			entriesMap.put("column_2", "BaRRy");
			xmlParserConc.insertIntoTable("table_name", entriesMap);

			Condition whereCondition = new Where("   column_1  >=  300  ");
			ResultSet result = new ResultSet();

			Map<String, Object> resExpected = new LinkedHashMap<String, Object>();
			resExpected.put("column_1", 550);
			Result expResult = new Result(resExpected);
			result.add(expResult);

			resExpected = new LinkedHashMap<String, Object>();
			resExpected.put("column_1", 7500);
			expResult = new Result(resExpected);
			result.add(expResult);

			resExpected = new LinkedHashMap<String, Object>();
			resExpected.put("column_1", 852);
			expResult = new Result(resExpected);
			result.add(expResult);

			Set<String> columns = new TreeSet<String>();

			columns.add("column_1");

			xmlParserConc.delete("table_name", whereCondition);

			ResultSet actualRes = xmlParserConc.select("table_name", columns, whereCondition);

			Iterator<Result> resultSetItr = result.iterator();
			Iterator<Result> actualResItr = actualRes.iterator();
			while (resultSetItr.hasNext() && actualResItr.hasNext()) {

				assertTrue(resultSetItr.next().getResult().equals(actualResItr.next().getResult()));

			}
		} catch (Exception e) {
			e.printStackTrace();
			fail("Error occured!");

		}
	}

	@Test
	public void testThree() {

		try {
			xmlParserConc.createDatabase("database_delete_3");

			Map<String, Class> passMap = new LinkedHashMap<String, Class>();
			passMap.put("column_1", Integer.class);
			passMap.put("column_2", String.class);

			xmlParserConc.createTable("table_name", passMap);

			Map<String, Object> entriesMap = new LinkedHashMap<String, Object>();
			entriesMap.put("column_1", 550);
			entriesMap.put("column_2", "KHalED");
			xmlParserConc.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<String, Object>();
			entriesMap.put("column_1", 200);
			entriesMap.put("column_2", "waLiD");
			xmlParserConc.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<String, Object>();
			entriesMap.put("column_1", 7500);
			entriesMap.put("column_2", "AnAs");
			xmlParserConc.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<String, Object>();
			entriesMap.put("column_1", 852);
			entriesMap.put("column_2", "YaKoUt");
			xmlParserConc.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<String, Object>();
			entriesMap.put("column_1", 189);
			entriesMap.put("column_2", "BaRRy");
			xmlParserConc.insertIntoTable("table_name", entriesMap);

			Condition whereConditionDel = new Where("   column_1  >  300  ");
			Condition whereConditionSel = new Where("   column_1  >  600  ");
			ResultSet result = new ResultSet();

			Map<String, Object> resExpected = new LinkedHashMap<String, Object>();
			resExpected.put("column_1", 7500);
			Result expResult = new Result(resExpected);
			result.add(expResult);

			resExpected = new LinkedHashMap<String, Object>();
			resExpected.put("column_1", 852);
			expResult = new Result(resExpected);
			result.add(expResult);

			Set<String> columns = new TreeSet<String>();

			columns.add("column_1");

			xmlParserConc.delete("table_name", whereConditionDel);

			ResultSet actualRes = xmlParserConc.select("table_name", columns, whereConditionSel);

			Iterator<Result> resultSetItr = result.iterator();
			Iterator<Result> actualResItr = actualRes.iterator();
			while (resultSetItr.hasNext() && actualResItr.hasNext()) {

				assertTrue(resultSetItr.next().getResult().equals(actualResItr.next().getResult()));

			}
		} catch (Exception e) {
			e.printStackTrace();
			fail("Error occured!");

		}
	}

	@Test
	public void testFour() {

		try {
			xmlParserConc.createDatabase("database_delete_4");

			Map<String, Class> passMap = new LinkedHashMap<String, Class>();
			passMap.put("column_1", Integer.class);
			passMap.put("column_2", String.class);

			xmlParserConc.createTable("table_name", passMap);

			Map<String, Object> entriesMap = new LinkedHashMap<String, Object>();
			entriesMap.put("column_1", 550);
			entriesMap.put("column_2", "KHalED");
			xmlParserConc.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<String, Object>();
			entriesMap.put("column_1", 200);
			entriesMap.put("column_2", "waLiD");
			xmlParserConc.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<String, Object>();
			entriesMap.put("column_1", 7500);
			entriesMap.put("column_2", "AnAs");
			xmlParserConc.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<String, Object>();
			entriesMap.put("column_1", 852);
			entriesMap.put("column_2", "YaKoUt");
			xmlParserConc.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<String, Object>();
			entriesMap.put("column_1", 189);
			entriesMap.put("column_2", "BaRRy");
			xmlParserConc.insertIntoTable("table_name", entriesMap);

			Condition whereConditionDel = new Where("   column_1  <  300  ");
			Condition whereConditionSel = new Where("   column_1  <  500  ");
			ResultSet result = new ResultSet();

			Set<String> columns = new TreeSet<String>();

			columns.add("column_1");

			xmlParserConc.delete("table_name", whereConditionDel);
			ResultSet actualRes = xmlParserConc.select("table_name", columns, whereConditionSel);
			Iterator<Result> actualResItr = actualRes.iterator();
			while (actualResItr.hasNext()) {

				assertTrue(null == actualResItr.next());

			}
		} catch (Exception e) {
			e.printStackTrace();
			fail("Error occured!");

		}
	}
   
	@Test
	public void testFive() {

		try {
			xmlParserConc.createDatabase("database_delete_5");

			Map<String, Class> passMap = new LinkedHashMap<String, Class>();
			passMap.put("column_1", Integer.class);
			passMap.put("column_2", String.class);

			xmlParserConc.createTable("table_name", passMap);

			Map<String, Object> entriesMap = new LinkedHashMap<String, Object>();
			entriesMap.put("column_1", 550);
			entriesMap.put("column_2", "KHalED");
			xmlParserConc.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<String, Object>();
			entriesMap.put("column_1", 200);
			entriesMap.put("column_2", "waLiD");
			xmlParserConc.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<String, Object>();
			entriesMap.put("column_1", 7500);
			entriesMap.put("column_2", "AnAs");
			xmlParserConc.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<String, Object>();
			entriesMap.put("column_1", 852);
			entriesMap.put("column_2", "YaKoUt");
			xmlParserConc.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<String, Object>();
			entriesMap.put("column_1", 189);
			entriesMap.put("column_2", "BaRRy");
			xmlParserConc.insertIntoTable("table_name", entriesMap);

			Condition whereConditionDel = new Where("   column_1  !=  300  ");
			
			ResultSet result = new ResultSet();

			Set<String> columns = new TreeSet<String>();

			columns.add("column_1");

			xmlParserConc.delete("table_name", whereConditionDel);
			ResultSet actualRes = xmlParserConc.select("table_name", null , null);
			Iterator<Result> actualResItr = actualRes.iterator();
			while (actualResItr.hasNext()) {

				assertTrue(null == actualResItr.next());

			}
		} catch (Exception e) {
			e.printStackTrace();
			fail("Error occured!");

		}
	}

}
