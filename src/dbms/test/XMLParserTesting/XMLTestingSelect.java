package dbms.test.XMLParserTesting;

import dbms.backend.BackendController;
import dbms.backend.BackendParserFactory;
import dbms.datatypes.DBDatatype;
import dbms.datatypes.DBInteger;
import dbms.datatypes.DBString;
import dbms.datatypes.DatatypeFactory;
import dbms.sqlparser.sqlInterpreter.Condition;
import dbms.sqlparser.sqlInterpreter.Where;
import dbms.util.Record;
import dbms.util.RecordSet;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class XMLTestingSelect {

	private final BackendController xmlParser = BackendController.getInstance();

	@Test
	public void testOne() {

		BackendParserFactory.getFactory().setCurrentParser("xml");
		try {
			xmlParser.createDatabase("database_select_1");

			Map<String, Class<? extends DBDatatype>> passMap = new LinkedHashMap<>();
			passMap.put("column_1", DBInteger.class);
			passMap.put("column_2", DBString.class);

			xmlParser.createTable("table_name", passMap);

			Map<String, DBDatatype> entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(550));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("KHalED"));
			xmlParser.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(14));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("hamada14"));
			xmlParser.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(17));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("Sea"));
			xmlParser.insertIntoTable("table_name", entriesMap);
			Set<String> columns = new TreeSet<>();

			columns.add("column_1");
			columns.add("column_2");
			RecordSet result = new RecordSet();
			LinkedHashMap<String, DBDatatype> resExpected = new LinkedHashMap<>();
			resExpected.put("column_1", DatatypeFactory.convertToDataType(550));
			resExpected.put("column_2", DatatypeFactory.convertToDataType("KHalED"));
			xmlParser.alterAdd("table_name", "column_3", DBString.class);
			xmlParser.alterDrop("table_name", "column_3");
			Record expRecord = new Record(resExpected);
			result.add(expRecord);

			RecordSet actualRes = xmlParser.select("table_name", columns, null);
			assertTrue(actualRes.getRecords().get(0).getRecord().equals(result.getRecords().get(0).getRecord()));
		} catch (Exception e) {
			e.printStackTrace();
			fail("Error occurred!");
		}
	}

	@Test
	public void testTwo() {

		BackendParserFactory.getFactory().setCurrentParser("xml");
		try {
			xmlParser.createDatabase("database_select_2");

			Map<String, Class<? extends DBDatatype>> passMap = new LinkedHashMap<>();
			passMap.put("column_1", DBInteger.class);
			passMap.put("column_2", DBString.class);

			xmlParser.createTable("table_name", passMap);

			Map<String, DBDatatype> entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(550));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("KHalED"));
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(14));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("TOLBa"));
			xmlParser.insertIntoTable("table_name", entriesMap);

			Set<String> columns = new HashSet<>();

			columns.add("column_1");
			columns.add("column_2");
			RecordSet result = new RecordSet();
			LinkedHashMap<String, DBDatatype> resExpected = new LinkedHashMap<>();
			resExpected.put("column_1", DatatypeFactory.convertToDataType(550));
			resExpected.put("column_2", DatatypeFactory.convertToDataType("KHalED"));
			resExpected.put("column_1", DatatypeFactory.convertToDataType(14));
			resExpected.put("column_2", DatatypeFactory.convertToDataType("TOLBa"));
			Record expRecord = new Record(resExpected);
			result.add(expRecord);

			RecordSet actualRes = xmlParser.select("table_name", columns, null);
			assertTrue(result.next().getRecord().equals(actualRes.next().getRecord()));
		} catch (Exception e) {
			e.printStackTrace();
			fail("Error occurred!");

		}

	}

	@Test
	public void testThree() {

		BackendParserFactory.getFactory().setCurrentParser("xml");
		try {
			xmlParser.createDatabase("database_select_3");

			Map<String, Class<? extends DBDatatype>> passMap = new LinkedHashMap<>();
			passMap.put("column_1", DBInteger.class);
			passMap.put("column_2", DBString.class);
			passMap.put("column_3", DBString.class);
			xmlParser.createTable("table_name", passMap);

			Map<String, DBDatatype> entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(550));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("KHalED"));
			entriesMap.put("column_3", DatatypeFactory.convertToDataType("ANAS"));
			xmlParser.insertIntoTable("table_name", entriesMap);
			entriesMap.clear();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(14));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("TOLBa"));
			entriesMap.put("column_3", DatatypeFactory.convertToDataType("YAKoUT"));
			xmlParser.insertIntoTable("table_name", entriesMap);
			entriesMap.clear();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(1512));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("Merci"));
			entriesMap.put("column_3", DatatypeFactory.convertToDataType("School"));
			xmlParser.insertIntoTable("table_name", entriesMap);

			Set<String> columns = new TreeSet<>();

			columns.add("column_1");
			columns.add("column_2");
			columns.add("column_3");
			RecordSet recordSet = new RecordSet();
			LinkedHashMap<String, DBDatatype> resExpected = new LinkedHashMap<>();

			resExpected.put("column_1", DatatypeFactory.convertToDataType(550));
			resExpected.put("column_2", DatatypeFactory.convertToDataType("KHalED"));
			resExpected.put("column_3", DatatypeFactory.convertToDataType("ANAS"));
			recordSet.add(new Record(resExpected));
			resExpected = new LinkedHashMap<>();
			resExpected.put("column_1", DatatypeFactory.convertToDataType(14));
			resExpected.put("column_2", DatatypeFactory.convertToDataType("TOLBa"));
			resExpected.put("column_3", DatatypeFactory.convertToDataType("YAKoUT"));
			recordSet.add(new Record(resExpected));
			resExpected = new LinkedHashMap<>();
			resExpected.put("column_1", DatatypeFactory.convertToDataType(1512));
			resExpected.put("column_2", DatatypeFactory.convertToDataType("Merci"));
			resExpected.put("column_3", DatatypeFactory.convertToDataType("School"));
			recordSet.add(new Record(resExpected));

			RecordSet actualRes = xmlParser.select("table_name", columns, null);

			Iterator<Record> resultSetItr = recordSet.iterator();
			Iterator<Record> actualResItr = actualRes.iterator();
			while (resultSetItr.hasNext() && actualResItr.hasNext()) {

				assertTrue(resultSetItr.next().getRecord().equals(actualResItr.next().getRecord()));

			}
		} catch (Exception e) {
			e.printStackTrace();
			fail("Error occurred!");

		}
	}

	@Test
	public void testFour() {

		BackendParserFactory.getFactory().setCurrentParser("xml");
		try {
			xmlParser.createDatabase("database_select_4");

			Map<String, Class<? extends DBDatatype>> passMap = new LinkedHashMap<>();
			passMap.put("column_1", DBInteger.class);
			passMap.put("column_2", DBString.class);
			passMap.put("column_3", DBString.class);
			xmlParser.createTable("table_name", passMap);

			Map<String, DBDatatype> entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(550));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("KHalED"));
			entriesMap.put("column_3", DatatypeFactory.convertToDataType("ANAS"));
			xmlParser.insertIntoTable("table_name", entriesMap);
			entriesMap.clear();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(14));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("TOLBa"));
			entriesMap.put("column_3", DatatypeFactory.convertToDataType("YAKoUT"));
			xmlParser.insertIntoTable("table_name", entriesMap);
			entriesMap.clear();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(1512));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("Merci"));
			entriesMap.put("column_3", DatatypeFactory.convertToDataType("School"));
			xmlParser.insertIntoTable("table_name", entriesMap);
			entriesMap.clear();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(56277));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("Merci"));
			entriesMap.put("column_3", DatatypeFactory.convertToDataType("Wal3aa"));
			xmlParser.insertIntoTable("table_name", entriesMap);
			entriesMap.clear();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(889884));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("yes"));
			entriesMap.put("column_3", DatatypeFactory.convertToDataType("no"));
			xmlParser.insertIntoTable("table_name", entriesMap);
			entriesMap.clear();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(7777));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("HelloFromTheOtherSide"));
			entriesMap.put("column_3", DatatypeFactory.convertToDataType("noAgain"));
			xmlParser.insertIntoTable("table_name", entriesMap);
			entriesMap.clear();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(7897));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("HelloFromTheOtherSideFam"));
			entriesMap.put("column_3", DatatypeFactory.convertToDataType("noAgainNo"));
			xmlParser.insertIntoTable("table_name", entriesMap);
			entriesMap.clear();
			entriesMap.put("column_2",
					DatatypeFactory.convertToDataType("HelloFromTheOtherSideFamHHHHhhhHHHHhhhhjkvh sdkjdhlvhczxhvsdhnufhs , oidnoiwhadshkfnh [fsnanhdx"));
			entriesMap.put("column_3", DatatypeFactory.convertToDataType("noAgainNohnifyueirefucnyweifyuewuciuuighrmoxarignriegxfiwhiufhzr"));
			xmlParser.insertIntoTable("table_name", entriesMap);

			Set<String> columns = new TreeSet<>();

			columns.add("column_1");
			columns.add("column_2");
			columns.add("column_3");
			RecordSet recordSet = new RecordSet();
			LinkedHashMap<String, DBDatatype> resExpected = new LinkedHashMap<>();

			resExpected.put("column_1", DatatypeFactory.convertToDataType(550));
			resExpected.put("column_2", DatatypeFactory.convertToDataType("KHalED"));
			resExpected.put("column_3", DatatypeFactory.convertToDataType("ANAS"));
			recordSet.add(new Record(resExpected));
			resExpected = new LinkedHashMap<>();
			resExpected.put("column_1", DatatypeFactory.convertToDataType(14));
			resExpected.put("column_2", DatatypeFactory.convertToDataType("TOLBa"));
			resExpected.put("column_3", DatatypeFactory.convertToDataType("YAKoUT"));
			recordSet.add(new Record(resExpected));
			resExpected = new LinkedHashMap<>();
			resExpected.put("column_1", DatatypeFactory.convertToDataType(1512));
			resExpected.put("column_2", DatatypeFactory.convertToDataType("Merci"));
			resExpected.put("column_3", DatatypeFactory.convertToDataType("School"));
			recordSet.add(new Record(resExpected));
			resExpected = new LinkedHashMap<>();
			resExpected.put("column_1", DatatypeFactory.convertToDataType(56277));
			resExpected.put("column_2", DatatypeFactory.convertToDataType("Merci"));
			resExpected.put("column_3", DatatypeFactory.convertToDataType("Wal3aa"));
			recordSet.add(new Record(resExpected));
			resExpected = new LinkedHashMap<>();
			resExpected.put("column_1",DatatypeFactory.convertToDataType(889884));
			resExpected.put("column_2", DatatypeFactory.convertToDataType("yes"));
			resExpected.put("column_3", DatatypeFactory.convertToDataType("no"));
			recordSet.add(new Record(resExpected));
			resExpected = new LinkedHashMap<>();
			resExpected.put("column_1", DatatypeFactory.convertToDataType(7777));
			resExpected.put("column_2", DatatypeFactory.convertToDataType("HelloFromTheOtherSide"));
			resExpected.put("column_3", DatatypeFactory.convertToDataType("noAgain"));
			recordSet.add(new Record(resExpected));
			resExpected = new LinkedHashMap<>();
			resExpected.put("column_1", DatatypeFactory.convertToDataType(7897));
			resExpected.put("column_2", DatatypeFactory.convertToDataType("HelloFromTheOtherSideFam"));
			resExpected.put("column_3", DatatypeFactory.convertToDataType("noAgainNo"));
			recordSet.add(new Record(resExpected));
			resExpected = new LinkedHashMap<>();
			resExpected.put("column_1", null);
			resExpected.put("column_2",
					DatatypeFactory.convertToDataType("HelloFromTheOtherSideFamHHHHhhhHHHHhhhhjkvh sdkjdhlvhczxhvsdhnufhs , oidnoiwhadshkfnh [fsnanhdx"));
			resExpected.put("column_3", DatatypeFactory.convertToDataType("noAgainNohnifyueirefucnyweifyuewuciuuighrmoxarignriegxfiwhiufhzr"));
			recordSet.add(new Record(resExpected));

			RecordSet actualRes = xmlParser.select("table_name", columns, null);

			Iterator<Record> resultSetItr = recordSet.iterator();
			Iterator<Record> actualResItr = actualRes.iterator();

			while (resultSetItr.hasNext() && actualResItr.hasNext()) {
				assertTrue(resultSetItr.next().getRecord().equals(actualResItr.next().getRecord()));
			}
			try {
				testFive();
			} catch (Exception e) {
				e.printStackTrace();
				fail("Error occurred!");
			}

		} catch (Exception e) {
			e.printStackTrace();
			fail("Error occurred!");

		}

	}

	public void testFive() {

		BackendParserFactory.getFactory().setCurrentParser("xml");
		try {
			xmlParser.useDatabase("database_select_4");
			Set<String> columns = new TreeSet<>();

			columns.add("column_1");
			columns.add("column_2");
			columns.add("column_3");
			RecordSet recordSet = new RecordSet();
			LinkedHashMap<String, DBDatatype> resExpected = new LinkedHashMap<>();

			resExpected.put("column_1", DatatypeFactory.convertToDataType(550));
			resExpected.put("column_2", DatatypeFactory.convertToDataType("KHalED"));
			resExpected.put("column_3", DatatypeFactory.convertToDataType("ANAS"));
			recordSet.add(new Record(resExpected));
			resExpected = new LinkedHashMap<>();
			resExpected.put("column_1", DatatypeFactory.convertToDataType(14));
			resExpected.put("column_2", DatatypeFactory.convertToDataType("TOLBa"));
			resExpected.put("column_3", DatatypeFactory.convertToDataType("YAKoUT"));
			recordSet.add(new Record(resExpected));
			resExpected = new LinkedHashMap<>();
			resExpected.put("column_1", DatatypeFactory.convertToDataType(1512));
			resExpected.put("column_2", DatatypeFactory.convertToDataType("Merci"));
			resExpected.put("column_3", DatatypeFactory.convertToDataType("School"));
			recordSet.add(new Record(resExpected));
			resExpected = new LinkedHashMap<>();
			resExpected.put("column_1", DatatypeFactory.convertToDataType(56277));
			resExpected.put("column_2", DatatypeFactory.convertToDataType("Merci"));
			resExpected.put("column_3", DatatypeFactory.convertToDataType("Wal3aa"));
			recordSet.add(new Record(resExpected));
			resExpected = new LinkedHashMap<>();
			resExpected.put("column_1", DatatypeFactory.convertToDataType(889884));
			resExpected.put("column_2", DatatypeFactory.convertToDataType("yes"));
			resExpected.put("column_3", DatatypeFactory.convertToDataType("no"));
			recordSet.add(new Record(resExpected));
			resExpected = new LinkedHashMap<>();
			resExpected.put("column_1", DatatypeFactory.convertToDataType(7777));
			resExpected.put("column_2", DatatypeFactory.convertToDataType("HelloFromTheOtherSide"));
			resExpected.put("column_3", DatatypeFactory.convertToDataType("noAgain"));
			recordSet.add(new Record(resExpected));
			resExpected = new LinkedHashMap<>();
			resExpected.put("column_1", DatatypeFactory.convertToDataType(7897));
			resExpected.put("column_2", DatatypeFactory.convertToDataType("HelloFromTheOtherSideFam"));
			resExpected.put("column_3", DatatypeFactory.convertToDataType("noAgainNo"));
			recordSet.add(new Record(resExpected));
			resExpected = new LinkedHashMap<>();
			resExpected.put("column_1", null);
			resExpected.put("column_2",
					DatatypeFactory.convertToDataType("HelloFromTheOtherSideFamHHHHhhhHHHHhhhhjkvh sdkjdhlvhczxhvsdhnufhs , oidnoiwhadshkfnh [fsnanhdx"));
			resExpected.put("column_3", DatatypeFactory.convertToDataType("noAgainNohnifyueirefucnyweifyuewuciuuighrmoxarignriegxfiwhiufhzr"));
			recordSet.add(new Record(resExpected));

			RecordSet actualRes = xmlParser.select("table_name", columns, null);

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
			fail("Error occurred!");

		}

	}

	@Test
	public void testSix() {

		BackendParserFactory.getFactory().setCurrentParser("xml");
		try {
			xmlParser.createDatabase("database_select_5");

			Map<String, Class<? extends DBDatatype>> passMap = new LinkedHashMap<>();
			passMap.put("column_1", DBInteger.class);
			passMap.put("column_2", DBInteger.class);

			xmlParser.createTable("table_name", passMap);

			Map<String, DBDatatype> entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(550));
			xmlParser.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(80));
			xmlParser.insertIntoTable("table_name", entriesMap);
			Set<String> columns = new TreeSet<>();
			columns.add("column_1");
			Condition conditionQ = new Where("column_1 > 100");
			LinkedHashMap<String, DBDatatype> resExpected = new LinkedHashMap<>();
			resExpected.put("column_1", DatatypeFactory.convertToDataType(550));
			resExpected.put("column_2", null);
			Record expRecord = new Record(resExpected);
			RecordSet result = new RecordSet();
			result.add(expRecord);
			RecordSet actualRes = xmlParser.select("table_name", null, conditionQ);
			assertTrue(result.getRecords().get(0).getRecord().equals(actualRes.getRecords().get(0).getRecord()));
		} catch (Exception e) {
			e.printStackTrace();
			fail("Error occurred!");
		}
	}

	// TODO Testing
	// Select with Union clause.
	// Select with Distinct clause.
	// Select with Order by clause.
	// Select with new data types Date, floating-point numbers.
	// Select with complex and and or expressions.
}
