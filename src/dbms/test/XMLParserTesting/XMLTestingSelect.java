package dbms.test.XMLParserTesting;

import dbms.backend.BackendController;
import dbms.sqlparser.sqlInterpreter.Condition;
import dbms.sqlparser.sqlInterpreter.Where;
import dbms.util.Record;
import dbms.util.RecordSet;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class XMLTestingSelect {

	private final BackendController xmlParserConc = BackendController.getInstance();

	@Test
	public void testOne() {

		try {
			xmlParserConc.createDatabase("database_select_1");

			Map<String, Class> passMap = new LinkedHashMap<String, Class>();
			passMap.put("column_1", Integer.class);
			passMap.put("column_2", String.class);

			xmlParserConc.createTable("table_name", passMap);

			Map<String, Object> entriesMap = new LinkedHashMap<String, Object>();
			entriesMap.put("column_1", 550);
			entriesMap.put("column_2", "KHalED");
			xmlParserConc.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<String, Object>();
			entriesMap.put("column_1", 14);
			entriesMap.put("column_2", "hamada14");
			xmlParserConc.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<String, Object>();
			entriesMap.put("column_1", 17);
			entriesMap.put("column_2", "Sea");
			xmlParserConc.insertIntoTable("table_name", entriesMap);
			Set<String> columns = new TreeSet<String>();

			columns.add("column_1");
			columns.add("column_2");
			RecordSet result = new RecordSet();
			Map<String, Object> resExpected = new LinkedHashMap<String, Object>();
			resExpected.put("column_1", 550);
			resExpected.put("column_2", "KHalED");
			xmlParserConc.alterAdd("table_name", "column_3", String.class);
			xmlParserConc.alterDrop("table_name", "column_3");
			Record expRecord = new Record(resExpected);
			result.add(expRecord);

			RecordSet actualRes = xmlParserConc.select("table_name", columns, null);
			assertTrue(result.next().getRecord().equals(actualRes.next().getRecord()));
		} catch (Exception e) {
			e.printStackTrace();
			fail("Error occured!");

		}
	}

	@Test
	public void testTwo() {
		/*
		 * Test checks creating database and table then inserting two columns
		 * with different datatypes and then selecting them. SELECT * FROM
		 * TABLE_NAME;
		 */

		try {
			xmlParserConc.createDatabase("database_select_2");

			Map<String, Class> passMap = new LinkedHashMap<String, Class>();
			passMap.put("column_1", Integer.class);
			passMap.put("column_2", String.class);

			xmlParserConc.createTable("table_name", passMap);

			Map<String, Object> entriesMap = new LinkedHashMap<String, Object>();
			entriesMap.put("column_1", 550);
			entriesMap.put("column_2", "KHalED");
			entriesMap.put("column_1", 14);
			entriesMap.put("column_2", "TOLBa");
			xmlParserConc.insertIntoTable("table_name", entriesMap);

			Set<String> columns = new HashSet<String>();

			columns.add("column_1");
			columns.add("column_2");
			RecordSet result = new RecordSet();
			Map<String, Object> resExpected = new LinkedHashMap<String, Object>();
			resExpected.put("column_1", 550);
			resExpected.put("column_2", "KHalED");
			resExpected.put("column_1", 14);
			resExpected.put("column_2", "TOLBa");
			Record expRecord = new Record(resExpected);
			result.add(expRecord);

			RecordSet actualRes = xmlParserConc.select("table_name", columns, null);
			assertTrue(result.next().getRecord().equals(actualRes.next().getRecord()));
		} catch (Exception e) {
			e.printStackTrace();
			fail("Error occured!");

		}

	}

	@Test
	public void testThree() {
		/*
		 * Test checks creating database and table then inserting two columns
		 * with different data types and then selecting them. SELECT * FROM
		 * TABLE_NAME;
		 */

		try {
			xmlParserConc.createDatabase("database_select_3");

			Map<String, Class> passMap = new LinkedHashMap<String, Class>();
			passMap.put("column_1", Integer.class);
			passMap.put("column_2", String.class);
			passMap.put("column_3", String.class);
			xmlParserConc.createTable("table_name", passMap);

			Map<String, Object> entriesMap = new LinkedHashMap<String, Object>();
			entriesMap.put("column_1", 550);
			entriesMap.put("column_2", "KHalED");
			entriesMap.put("column_3", "ANAS");
			xmlParserConc.insertIntoTable("table_name", entriesMap);
			entriesMap.clear();
			entriesMap.put("column_1", 14);
			entriesMap.put("column_2", "TOLBa");
			entriesMap.put("column_3", "YAKoUT");
			xmlParserConc.insertIntoTable("table_name", entriesMap);
			entriesMap.clear();
			entriesMap.put("column_1", 1512);
			entriesMap.put("column_2", "Merci");
			entriesMap.put("column_3", "School");
			xmlParserConc.insertIntoTable("table_name", entriesMap);

			Set<String> columns = new TreeSet<String>();

			columns.add("column_1");
			columns.add("column_2");
			columns.add("column_3");
			RecordSet recordSet = new RecordSet();
			Map<String, Object> resExpected = new LinkedHashMap<String, Object>();

			resExpected.put("column_1", 550);
			resExpected.put("column_2", "KHalED");
			resExpected.put("column_3", "ANAS");
			recordSet.add(new Record(resExpected));
			resExpected = new LinkedHashMap<String, Object>();
			resExpected.put("column_1", 14);
			resExpected.put("column_2", "TOLBa");
			resExpected.put("column_3", "YAKoUT");
			recordSet.add(new Record(resExpected));
			resExpected = new LinkedHashMap<String, Object>();
			resExpected.put("column_1", 1512);
			resExpected.put("column_2", "Merci");
			resExpected.put("column_3", "School");
			recordSet.add(new Record(resExpected));

			RecordSet actualRes = xmlParserConc.select("table_name", columns, null);

			Iterator<Record> resultSetItr = recordSet.iterator();
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
			xmlParserConc.createDatabase("database_select_4");

			Map<String, Class> passMap = new LinkedHashMap<String, Class>();
			passMap.put("column_1", Integer.class);
			passMap.put("column_2", String.class);
			passMap.put("column_3", String.class);
			xmlParserConc.createTable("table_name", passMap);

			Map<String, Object> entriesMap = new LinkedHashMap<String, Object>();
			entriesMap.put("column_1", 550);
			entriesMap.put("column_2", "KHalED");
			entriesMap.put("column_3", "ANAS");
			xmlParserConc.insertIntoTable("table_name", entriesMap);
			entriesMap.clear();
			entriesMap.put("column_1", 14);
			entriesMap.put("column_2", "TOLBa");
			entriesMap.put("column_3", "YAKoUT");
			xmlParserConc.insertIntoTable("table_name", entriesMap);
			entriesMap.clear();
			entriesMap.put("column_1", 1512);
			entriesMap.put("column_2", "Merci");
			entriesMap.put("column_3", "School");
			xmlParserConc.insertIntoTable("table_name", entriesMap);
			entriesMap.clear();
			entriesMap.put("column_1", 56277);
			entriesMap.put("column_2", "Merci");
			entriesMap.put("column_3", "Wal3aa");
			xmlParserConc.insertIntoTable("table_name", entriesMap);
			entriesMap.clear();
			entriesMap.put("column_1", 889884);
			entriesMap.put("column_2", "yes");
			entriesMap.put("column_3", "no");
			xmlParserConc.insertIntoTable("table_name", entriesMap);
			entriesMap.clear();
			entriesMap.put("column_1", 7777);
			entriesMap.put("column_2", "HelloFromTheOtherSide");
			entriesMap.put("column_3", "noAgain");
			xmlParserConc.insertIntoTable("table_name", entriesMap);
			entriesMap.clear();
			entriesMap.put("column_1", 7897);
			entriesMap.put("column_2", "HelloFromTheOtherSideFam");
			entriesMap.put("column_3", "noAgainNo");
			xmlParserConc.insertIntoTable("table_name", entriesMap);
			entriesMap.clear();
			entriesMap.put("column_2",
					"HelloFromTheOtherSideFamHHHHhhhHHHHhhhhjkvh sdkjdhlvhczxhvsdhnufhs , oidnoiwhadshkfnh [fsnanhdx");
			entriesMap.put("column_3", "noAgainNohnifyueirefucnyweifyuewuciuuighrmoxarignriegxfiwhiufhzr");
			xmlParserConc.insertIntoTable("table_name", entriesMap);

			Set<String> columns = new TreeSet<String>();

			columns.add("column_1");
			columns.add("column_2");
			columns.add("column_3");
			RecordSet recordSet = new RecordSet();
			Map<String, Object> resExpected = new LinkedHashMap<String, Object>();

			resExpected.put("column_1", 550);
			resExpected.put("column_2", "KHalED");
			resExpected.put("column_3", "ANAS");
			recordSet.add(new Record(resExpected));
			resExpected = new LinkedHashMap<String, Object>();
			resExpected.put("column_1", 14);
			resExpected.put("column_2", "TOLBa");
			resExpected.put("column_3", "YAKoUT");
			recordSet.add(new Record(resExpected));
			resExpected = new LinkedHashMap<String, Object>();
			resExpected.put("column_1", 1512);
			resExpected.put("column_2", "Merci");
			resExpected.put("column_3", "School");
			recordSet.add(new Record(resExpected));
			resExpected = new LinkedHashMap<String, Object>();
			resExpected.put("column_1", 56277);
			resExpected.put("column_2", "Merci");
			resExpected.put("column_3", "Wal3aa");
			recordSet.add(new Record(resExpected));
			resExpected = new LinkedHashMap<String, Object>();
			resExpected.put("column_1", 889884);
			resExpected.put("column_2", "yes");
			resExpected.put("column_3", "no");
			recordSet.add(new Record(resExpected));
			resExpected = new LinkedHashMap<String, Object>();
			resExpected.put("column_1", 7777);
			resExpected.put("column_2", "HelloFromTheOtherSide");
			resExpected.put("column_3", "noAgain");
			recordSet.add(new Record(resExpected));
			resExpected = new LinkedHashMap<String, Object>();
			resExpected.put("column_1", 7897);
			resExpected.put("column_2", "HelloFromTheOtherSideFam");
			resExpected.put("column_3", "noAgainNo");
			recordSet.add(new Record(resExpected));
			resExpected = new LinkedHashMap<String, Object>();
			resExpected.put("column_1", null);
			resExpected.put("column_2",
					"HelloFromTheOtherSideFamHHHHhhhHHHHhhhhjkvh sdkjdhlvhczxhvsdhnufhs , oidnoiwhadshkfnh [fsnanhdx");
			resExpected.put("column_3", "noAgainNohnifyueirefucnyweifyuewuciuuighrmoxarignriegxfiwhiufhzr");
			recordSet.add(new Record(resExpected));

			RecordSet actualRes = xmlParserConc.select("table_name", columns, null);

			Iterator<Record> resultSetItr = recordSet.iterator();
			Iterator<Record> actualResItr = actualRes.iterator();

			while (resultSetItr.hasNext() && actualResItr.hasNext()) {
				assertTrue(resultSetItr.next().getRecord().equals(actualResItr.next().getRecord()));
			}
			try {
				testFive();
			} catch (Exception e) {
				e.printStackTrace();
				fail("Error occured!");
			}

		} catch (Exception e) {
			e.printStackTrace();
			fail("Error occured!");

		}

	}

	public void testFive() {
		try {

			xmlParserConc.useDatabase("database_select_4");
			Set<String> columns = new TreeSet<String>();

			columns.add("column_1");
			columns.add("column_2");
			columns.add("column_3");
			RecordSet recordSet = new RecordSet();
			Map<String, Object> resExpected = new LinkedHashMap<String, Object>();

			resExpected.put("column_1", 550);
			resExpected.put("column_2", "KHalED");
			resExpected.put("column_3", "ANAS");
			recordSet.add(new Record(resExpected));
			resExpected = new LinkedHashMap<String, Object>();
			resExpected.put("column_1", 14);
			resExpected.put("column_2", "TOLBa");
			resExpected.put("column_3", "YAKoUT");
			recordSet.add(new Record(resExpected));
			resExpected = new LinkedHashMap<String, Object>();
			resExpected.put("column_1", 1512);
			resExpected.put("column_2", "Merci");
			resExpected.put("column_3", "School");
			recordSet.add(new Record(resExpected));
			resExpected = new LinkedHashMap<String, Object>();
			resExpected.put("column_1", 56277);
			resExpected.put("column_2", "Merci");
			resExpected.put("column_3", "Wal3aa");
			recordSet.add(new Record(resExpected));
			resExpected = new LinkedHashMap<String, Object>();
			resExpected.put("column_1", 889884);
			resExpected.put("column_2", "yes");
			resExpected.put("column_3", "no");
			recordSet.add(new Record(resExpected));
			resExpected = new LinkedHashMap<String, Object>();
			resExpected.put("column_1", 7777);
			resExpected.put("column_2", "HelloFromTheOtherSide");
			resExpected.put("column_3", "noAgain");
			recordSet.add(new Record(resExpected));
			resExpected = new LinkedHashMap<String, Object>();
			resExpected.put("column_1", 7897);
			resExpected.put("column_2", "HelloFromTheOtherSideFam");
			resExpected.put("column_3", "noAgainNo");
			recordSet.add(new Record(resExpected));
			resExpected = new LinkedHashMap<String, Object>();
			resExpected.put("column_1", null);
			resExpected.put("column_2",
					"HelloFromTheOtherSideFamHHHHhhhHHHHhhhhjkvh sdkjdhlvhczxhvsdhnufhs , oidnoiwhadshkfnh [fsnanhdx");
			resExpected.put("column_3", "noAgainNohnifyueirefucnyweifyuewuciuuighrmoxarignriegxfiwhiufhzr");
			recordSet.add(new Record(resExpected));

			RecordSet actualRes = xmlParserConc.select("table_name", columns, null);

			Iterator<Record> resultSetItr = recordSet.iterator();
			Iterator<Record> actualResItr = actualRes.iterator();

			while (resultSetItr.hasNext() && actualResItr.hasNext()) {
				try {
					assertTrue(resultSetItr.next().getRecord().equals(actualResItr.next().getRecord()));
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			fail("Error occured!");

		}

	}

	@Test
	public void testSix() {

		try {
			xmlParserConc.createDatabase("database_select_5");

			Map<String, Class> passMap = new LinkedHashMap<String, Class>();
			passMap.put("column_1", Integer.class);
			passMap.put("column_2", Integer.class);

			xmlParserConc.createTable("table_name", passMap);

			Map<String, Object> entriesMap = new LinkedHashMap<String, Object>();
			entriesMap.put("column_1", 550);
			xmlParserConc.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<String, Object>();
			entriesMap.put("column_1", 80);
			xmlParserConc.insertIntoTable("table_name", entriesMap);
			Set<String> columns = new TreeSet<String>();
			columns.add("column_1");
			Condition conditionQ = new Where("column_1 > 100");
			Map<String, Object> resExpected = new LinkedHashMap<String, Object>();
			resExpected.put("column_1", 550);
			resExpected.put("column_2", null);
			Record expRecord = new Record(resExpected);
			RecordSet result = new RecordSet();
			result.add(expRecord);
			RecordSet actualRes = xmlParserConc.select("table_name", null, conditionQ);
			assertTrue(result.next().getRecord().equals(actualRes.next().getRecord()));
		} catch (Exception e) {
			e.printStackTrace();
			fail("Error occured!");

		}
	}
}
