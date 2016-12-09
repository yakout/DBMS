package dbms.test.XMLParserTesting;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import dbms.util.Record;
import org.junit.Test;

import dbms.backend.BackendController;
import dbms.sqlparser.sqlInterpreter.Condition;
import dbms.sqlparser.sqlInterpreter.Where;
import dbms.util.RecordSet;

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
			RecordSet result = new RecordSet();
			Map<String, Object> resExpected = new LinkedHashMap<String, Object>();
			resExpected.put("column_1", 550);
			resExpected.put("column_2", "KHalED");
			Record expRecord = new Record(resExpected);
			result.add(expRecord);

			resExpected = new LinkedHashMap<String, Object>();
			resExpected.put("column_1", 7500);
			resExpected.put("column_2", "AnAs");
			expRecord = new Record(resExpected);
			result.add(expRecord);

			resExpected = new LinkedHashMap<String, Object>();
			resExpected.put("column_1", 852);
			resExpected.put("column_2", "YaKoUt");
			expRecord = new Record(resExpected);
			result.add(expRecord);

			xmlParserConc.delete("table_name", whereCondition);

			RecordSet actualRes = xmlParserConc.select("table_name", null, whereCondition);

			Iterator<Record> resultSetItr = result.iterator();
			Iterator<Record> actualResItr = actualRes.iterator();
			while (resultSetItr.hasNext() && actualResItr.hasNext()) {

				assertTrue(resultSetItr.next().getRecord().equals(actualResItr.next().getRecord()));

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
			RecordSet result = new RecordSet();

			Map<String, Object> resExpected = new LinkedHashMap<String, Object>();
			resExpected.put("column_1", 550);
			Record expRecord = new Record(resExpected);
			result.add(expRecord);

			resExpected = new LinkedHashMap<String, Object>();
			resExpected.put("column_1", 7500);
			expRecord = new Record(resExpected);
			result.add(expRecord);

			resExpected = new LinkedHashMap<String, Object>();
			resExpected.put("column_1", 852);
			expRecord = new Record(resExpected);
			result.add(expRecord);

			Set<String> columns = new TreeSet<String>();

			columns.add("column_1");

			xmlParserConc.delete("table_name", whereCondition);

			RecordSet actualRes = xmlParserConc.select("table_name", columns, whereCondition);

			Iterator<Record> resultSetItr = result.iterator();
			Iterator<Record> actualResItr = actualRes.iterator();
			while (resultSetItr.hasNext() && actualResItr.hasNext()) {

				assertTrue(resultSetItr.next().getRecord().equals(actualResItr.next().getRecord()));

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
			RecordSet result = new RecordSet();

			Map<String, Object> resExpected = new LinkedHashMap<String, Object>();
			resExpected.put("column_1", 7500);
			Record expRecord = new Record(resExpected);
			result.add(expRecord);

			resExpected = new LinkedHashMap<String, Object>();
			resExpected.put("column_1", 852);
			expRecord = new Record(resExpected);
			result.add(expRecord);

			Set<String> columns = new TreeSet<String>();

			columns.add("column_1");

			xmlParserConc.delete("table_name", whereConditionDel);

			RecordSet actualRes = xmlParserConc.select("table_name", columns, whereConditionSel);

			Iterator<Record> resultSetItr = result.iterator();
			Iterator<Record> actualResItr = actualRes.iterator();
			while (resultSetItr.hasNext() && actualResItr.hasNext()) {

				assertTrue(resultSetItr.next().getRecord().equals(actualResItr.next().getRecord()));

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
			RecordSet result = new RecordSet();

			Set<String> columns = new TreeSet<String>();

			columns.add("column_1");

			xmlParserConc.delete("table_name", whereConditionDel);
			RecordSet actualRes = xmlParserConc.select("table_name", columns, whereConditionSel);
			Iterator<Record> actualResItr = actualRes.iterator();
			while (actualResItr.hasNext()) {

				assertTrue(actualResItr.next() == null);

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
			
			RecordSet result = new RecordSet();

			Set<String> columns = new TreeSet<String>();

			columns.add("column_1");

			xmlParserConc.delete("table_name", whereConditionDel);
			RecordSet actualRes = xmlParserConc.select("table_name", null , null);
			Iterator<Record> actualResItr = actualRes.iterator();
			while (actualResItr.hasNext()) {

				assertTrue(actualResItr.next() == null);

			}
		} catch (Exception e) {
			e.printStackTrace();
			fail("Error occured!");

		}
	}

}
