package dbms.test.XMLParserTesting;

import dbms.backend.BackendController;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import dbms.datatypes.DBDatatype;
import dbms.datatypes.DBInteger;
import dbms.datatypes.DBString;
import dbms.datatypes.DatatypeFactory;
import dbms.util.Record;
import org.junit.Test;

import dbms.sqlparser.sqlInterpreter.Condition;
import dbms.sqlparser.sqlInterpreter.Where;
import dbms.util.RecordSet;

public class XMLTestingSelect {

	private final BackendController xmlParserConc = BackendController.getInstance();

	@Test
	public void testOne() {

		try {
			xmlParserConc.createDatabase("database_select_1");

			Map<String, Class<? extends DBDatatype>> passMap = new LinkedHashMap<>();
			passMap.put("column_1", DBInteger.class);
			passMap.put("column_2", DBString.class);

			xmlParserConc.createTable("table_name", passMap);

			Map<String, DBDatatype> entriesMap = new LinkedHashMap<String, DBDatatype>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(550));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("KHalED"));
			xmlParserConc.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<String, DBDatatype>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(14));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("hamada14"));
			xmlParserConc.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<String, DBDatatype>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(17));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("Sea"));
			xmlParserConc.insertIntoTable("table_name", entriesMap);
			Set<String> columns = new TreeSet<String>();

			columns.add("column_1");
			columns.add("column_2");
			RecordSet result = new RecordSet();
			LinkedHashMap<String, DBDatatype> resExpected = new LinkedHashMap<String, DBDatatype>();
			resExpected.put("column_1", DatatypeFactory.convertToDataType(550));
			resExpected.put("column_2", DatatypeFactory.convertToDataType("KHalED"));
			xmlParserConc.alterAdd("table_name", "column_3", DBString.class);
			xmlParserConc.alterDrop("table_name", "column_3");
			Record expRecord = new Record(resExpected);
			result.add(expRecord);

			RecordSet actualRes = xmlParserConc.select("table_name", columns, null);
			assertTrue(actualRes.getRecords().get(0).getRecord().equals(result.getRecords().get(0).getRecord()));
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

			Map<String, Class<? extends DBDatatype>> passMap = new LinkedHashMap<>();
			passMap.put("column_1", DBInteger.class);
			passMap.put("column_2", DBString.class);

			xmlParserConc.createTable("table_name", passMap);

			Map<String, DBDatatype> entriesMap = new LinkedHashMap<String, DBDatatype>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(550));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("KHalED"));
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(14));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("TOLBa"));
			xmlParserConc.insertIntoTable("table_name", entriesMap);

			Set<String> columns = new HashSet<String>();

			columns.add("column_1");
			columns.add("column_2");
			RecordSet result = new RecordSet();
			LinkedHashMap<String, DBDatatype> resExpected = new LinkedHashMap<String, DBDatatype>();
			resExpected.put("column_1", DatatypeFactory.convertToDataType(550));
			resExpected.put("column_2", DatatypeFactory.convertToDataType("KHalED"));
			resExpected.put("column_1", DatatypeFactory.convertToDataType(14));
			resExpected.put("column_2", DatatypeFactory.convertToDataType("TOLBa"));
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

			Map<String, Class<? extends DBDatatype>> passMap = new LinkedHashMap<>();
			passMap.put("column_1", DBInteger.class);
			passMap.put("column_2", DBString.class);
			passMap.put("column_3", DBString.class);
			xmlParserConc.createTable("table_name", passMap);

			Map<String, DBDatatype> entriesMap = new LinkedHashMap<String, DBDatatype>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(550));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("KHalED"));
			entriesMap.put("column_3", DatatypeFactory.convertToDataType("ANAS"));
			xmlParserConc.insertIntoTable("table_name", entriesMap);
			entriesMap.clear();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(14));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("TOLBa"));
			entriesMap.put("column_3", DatatypeFactory.convertToDataType("YAKoUT"));
			xmlParserConc.insertIntoTable("table_name", entriesMap);
			entriesMap.clear();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(1512));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("Merci"));
			entriesMap.put("column_3", DatatypeFactory.convertToDataType("School"));
			xmlParserConc.insertIntoTable("table_name", entriesMap);

			Set<String> columns = new TreeSet<String>();

			columns.add("column_1");
			columns.add("column_2");
			columns.add("column_3");
			RecordSet recordSet = new RecordSet();
			LinkedHashMap<String, DBDatatype> resExpected = new LinkedHashMap<String, DBDatatype>();

			resExpected.put("column_1", DatatypeFactory.convertToDataType(550));
			resExpected.put("column_2", DatatypeFactory.convertToDataType("KHalED"));
			resExpected.put("column_3", DatatypeFactory.convertToDataType("ANAS"));
			recordSet.add(new Record(resExpected));
			resExpected = new LinkedHashMap<String, DBDatatype>();
			resExpected.put("column_1", DatatypeFactory.convertToDataType(14));
			resExpected.put("column_2", DatatypeFactory.convertToDataType("TOLBa"));
			resExpected.put("column_3", DatatypeFactory.convertToDataType("YAKoUT"));
			recordSet.add(new Record(resExpected));
			resExpected = new LinkedHashMap<String, DBDatatype>();
			resExpected.put("column_1", DatatypeFactory.convertToDataType(1512));
			resExpected.put("column_2", DatatypeFactory.convertToDataType("Merci"));
			resExpected.put("column_3", DatatypeFactory.convertToDataType("School"));
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

			Map<String, Class<? extends DBDatatype>> passMap = new LinkedHashMap<String, Class<? extends DBDatatype>>();
			passMap.put("column_1", DBInteger.class);
			passMap.put("column_2", DBString.class);
			passMap.put("column_3", DBString.class);
			xmlParserConc.createTable("table_name", passMap);

			Map<String, DBDatatype> entriesMap = new LinkedHashMap<String, DBDatatype>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(550));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("KHalED"));
			entriesMap.put("column_3", DatatypeFactory.convertToDataType("ANAS"));
			xmlParserConc.insertIntoTable("table_name", entriesMap);
			entriesMap.clear();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(14));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("TOLBa"));
			entriesMap.put("column_3", DatatypeFactory.convertToDataType("YAKoUT"));
			xmlParserConc.insertIntoTable("table_name", entriesMap);
			entriesMap.clear();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(1512));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("Merci"));
			entriesMap.put("column_3", DatatypeFactory.convertToDataType("School"));
			xmlParserConc.insertIntoTable("table_name", entriesMap);
			entriesMap.clear();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(56277));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("Merci"));
			entriesMap.put("column_3", DatatypeFactory.convertToDataType("Wal3aa"));
			xmlParserConc.insertIntoTable("table_name", entriesMap);
			entriesMap.clear();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(889884));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("yes"));
			entriesMap.put("column_3", DatatypeFactory.convertToDataType("no"));
			xmlParserConc.insertIntoTable("table_name", entriesMap);
			entriesMap.clear();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(7777));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("HelloFromTheOtherSide"));
			entriesMap.put("column_3", DatatypeFactory.convertToDataType("noAgain"));
			xmlParserConc.insertIntoTable("table_name", entriesMap);
			entriesMap.clear();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(7897));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("HelloFromTheOtherSideFam"));
			entriesMap.put("column_3", DatatypeFactory.convertToDataType("noAgainNo"));
			xmlParserConc.insertIntoTable("table_name", entriesMap);
			entriesMap.clear();
			entriesMap.put("column_2",
					DatatypeFactory.convertToDataType("HelloFromTheOtherSideFamHHHHhhhHHHHhhhhjkvh sdkjdhlvhczxhvsdhnufhs , oidnoiwhadshkfnh [fsnanhdx"));
			entriesMap.put("column_3", DatatypeFactory.convertToDataType("noAgainNohnifyueirefucnyweifyuewuciuuighrmoxarignriegxfiwhiufhzr"));
			xmlParserConc.insertIntoTable("table_name", entriesMap);

			Set<String> columns = new TreeSet<String>();

			columns.add("column_1");
			columns.add("column_2");
			columns.add("column_3");
			RecordSet recordSet = new RecordSet();
			LinkedHashMap<String, DBDatatype> resExpected = new LinkedHashMap<String, DBDatatype>();

			resExpected.put("column_1", DatatypeFactory.convertToDataType(550));
			resExpected.put("column_2", DatatypeFactory.convertToDataType("KHalED"));
			resExpected.put("column_3", DatatypeFactory.convertToDataType("ANAS"));
			recordSet.add(new Record(resExpected));
			resExpected = new LinkedHashMap<String, DBDatatype>();
			resExpected.put("column_1", DatatypeFactory.convertToDataType(14));
			resExpected.put("column_2", DatatypeFactory.convertToDataType("TOLBa"));
			resExpected.put("column_3", DatatypeFactory.convertToDataType("YAKoUT"));
			recordSet.add(new Record(resExpected));
			resExpected = new LinkedHashMap<String, DBDatatype>();
			resExpected.put("column_1", DatatypeFactory.convertToDataType(1512));
			resExpected.put("column_2", DatatypeFactory.convertToDataType("Merci"));
			resExpected.put("column_3", DatatypeFactory.convertToDataType("School"));
			recordSet.add(new Record(resExpected));
			resExpected = new LinkedHashMap<String, DBDatatype>();
			resExpected.put("column_1", DatatypeFactory.convertToDataType(56277));
			resExpected.put("column_2", DatatypeFactory.convertToDataType("Merci"));
			resExpected.put("column_3", DatatypeFactory.convertToDataType("Wal3aa"));
			recordSet.add(new Record(resExpected));
			resExpected = new LinkedHashMap<String, DBDatatype>();
			resExpected.put("column_1",DatatypeFactory.convertToDataType(889884));
			resExpected.put("column_2", DatatypeFactory.convertToDataType("yes"));
			resExpected.put("column_3", DatatypeFactory.convertToDataType("no"));
			recordSet.add(new Record(resExpected));
			resExpected = new LinkedHashMap<String, DBDatatype>();
			resExpected.put("column_1", DatatypeFactory.convertToDataType(7777));
			resExpected.put("column_2", DatatypeFactory.convertToDataType("HelloFromTheOtherSide"));
			resExpected.put("column_3", DatatypeFactory.convertToDataType("noAgain"));
			recordSet.add(new Record(resExpected));
			resExpected = new LinkedHashMap<String, DBDatatype>();
			resExpected.put("column_1", DatatypeFactory.convertToDataType(7897));
			resExpected.put("column_2", DatatypeFactory.convertToDataType("HelloFromTheOtherSideFam"));
			resExpected.put("column_3", DatatypeFactory.convertToDataType("noAgainNo"));
			recordSet.add(new Record(resExpected));
			resExpected = new LinkedHashMap<String, DBDatatype>();
			resExpected.put("column_1", null);
			resExpected.put("column_2",
					DatatypeFactory.convertToDataType("HelloFromTheOtherSideFamHHHHhhhHHHHhhhhjkvh sdkjdhlvhczxhvsdhnufhs , oidnoiwhadshkfnh [fsnanhdx"));
			resExpected.put("column_3", DatatypeFactory.convertToDataType("noAgainNohnifyueirefucnyweifyuewuciuuighrmoxarignriegxfiwhiufhzr"));
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
			LinkedHashMap<String, DBDatatype> resExpected = new LinkedHashMap<String, DBDatatype>();

			resExpected.put("column_1", DatatypeFactory.convertToDataType(550));
			resExpected.put("column_2", DatatypeFactory.convertToDataType("KHalED"));
			resExpected.put("column_3", DatatypeFactory.convertToDataType("ANAS"));
			recordSet.add(new Record(resExpected));
			resExpected = new LinkedHashMap<String, DBDatatype>();
			resExpected.put("column_1", DatatypeFactory.convertToDataType(14));
			resExpected.put("column_2", DatatypeFactory.convertToDataType("TOLBa"));
			resExpected.put("column_3", DatatypeFactory.convertToDataType("YAKoUT"));
			recordSet.add(new Record(resExpected));
			resExpected = new LinkedHashMap<String, DBDatatype>();
			resExpected.put("column_1", DatatypeFactory.convertToDataType(1512));
			resExpected.put("column_2", DatatypeFactory.convertToDataType("Merci"));
			resExpected.put("column_3", DatatypeFactory.convertToDataType("School"));
			recordSet.add(new Record(resExpected));
			resExpected = new LinkedHashMap<String, DBDatatype>();
			resExpected.put("column_1", DatatypeFactory.convertToDataType(56277));
			resExpected.put("column_2", DatatypeFactory.convertToDataType("Merci"));
			resExpected.put("column_3", DatatypeFactory.convertToDataType("Wal3aa"));
			recordSet.add(new Record(resExpected));
			resExpected = new LinkedHashMap<String, DBDatatype>();
			resExpected.put("column_1", DatatypeFactory.convertToDataType(889884));
			resExpected.put("column_2", DatatypeFactory.convertToDataType("yes"));
			resExpected.put("column_3", DatatypeFactory.convertToDataType("no"));
			recordSet.add(new Record(resExpected));
			resExpected = new LinkedHashMap<String, DBDatatype>();
			resExpected.put("column_1", DatatypeFactory.convertToDataType(7777));
			resExpected.put("column_2", DatatypeFactory.convertToDataType("HelloFromTheOtherSide"));
			resExpected.put("column_3", DatatypeFactory.convertToDataType("noAgain"));
			recordSet.add(new Record(resExpected));
			resExpected = new LinkedHashMap<String, DBDatatype>();
			resExpected.put("column_1", DatatypeFactory.convertToDataType(7897));
			resExpected.put("column_2", DatatypeFactory.convertToDataType("HelloFromTheOtherSideFam"));
			resExpected.put("column_3", DatatypeFactory.convertToDataType("noAgainNo"));
			recordSet.add(new Record(resExpected));
			resExpected = new LinkedHashMap<String, DBDatatype>();
			resExpected.put("column_1", null);
			resExpected.put("column_2",
					DatatypeFactory.convertToDataType("HelloFromTheOtherSideFamHHHHhhhHHHHhhhhjkvh sdkjdhlvhczxhvsdhnufhs , oidnoiwhadshkfnh [fsnanhdx"));
			resExpected.put("column_3", DatatypeFactory.convertToDataType("noAgainNohnifyueirefucnyweifyuewuciuuighrmoxarignriegxfiwhiufhzr"));
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

			Map<String, Class<? extends DBDatatype>> passMap = new LinkedHashMap<String, Class<? extends DBDatatype>>();
			passMap.put("column_1", DBInteger.class);
			passMap.put("column_2", DBInteger.class);

			xmlParserConc.createTable("table_name", passMap);

			Map<String, DBDatatype> entriesMap = new LinkedHashMap<String, DBDatatype>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(550));
			xmlParserConc.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<String, DBDatatype>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(80));
			xmlParserConc.insertIntoTable("table_name", entriesMap);
			Set<String> columns = new TreeSet<String>();
			columns.add("column_1");
			Condition conditionQ = new Where("column_1 > 100");
			LinkedHashMap<String, DBDatatype> resExpected = new LinkedHashMap<String, DBDatatype>();
			resExpected.put("column_1", DatatypeFactory.convertToDataType(550));
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
