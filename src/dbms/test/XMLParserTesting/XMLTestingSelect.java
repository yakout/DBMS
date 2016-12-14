package dbms.test.XMLParserTesting;

import dbms.backend.BackendController;
import dbms.backend.BackendParserFactory;
import dbms.datatypes.*;
import dbms.sqlparser.sqlInterpreter.Condition;
import dbms.sqlparser.sqlInterpreter.Where;
import dbms.util.Record;
import dbms.util.RecordSet;
import javafx.util.Pair;
import org.junit.Test;
import java.sql.Date;

import java.util.*;

import static org.junit.Assert.*;

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

			columns.add("COLUMN_1");
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
			assertTrue(actualRes.getRecords().get(0).getRecord().equals(result.getRecords().get(0).getRecord()));
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
					DatatypeFactory.convertToDataType("HelloFromTheOtherSideFamHHHHhhhH" +
							"HHHhhhhjkvh sdkjdhlvhczxhvsdhnufhs , oidnoiwhadshkfnh [fsnanhdx"));
			resExpected.put("column_3", DatatypeFactory.convertToDataType("noAgainNohnifyueirefucn" +
					"yweifyuewuciuuighrmoxarignriegxfiwhiufhzr"));
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


	@Test
	public void testSeven() {

		BackendParserFactory.getFactory().setCurrentParser("xml");
		try {
			xmlParser.createDatabase("database_delete_6");

			LinkedHashMap<String, Class<? extends DBDatatype>> passMap = new LinkedHashMap<>();
			passMap.put("column_1", DBInteger.class);
			passMap.put("column_2", DBString.class);
			passMap.put("column_3", DBDate.class);
			passMap.put("column_4", DBFloat.class);

			xmlParser.createTable("table_name", passMap);

			LinkedHashMap<String, DBDatatype> entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(550));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("KHalED"));
			entriesMap.put("column_3", DatatypeFactory.convertToDataType(Date.valueOf("2050-10-1")));
			entriesMap.put("column_4", DatatypeFactory.convertToDataType((float)-10.55));
			xmlParser.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(200));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("waLiD"));
			entriesMap.put("column_3", DatatypeFactory.convertToDataType(Date.valueOf("2000-10-1")));
			entriesMap.put("column_4", DatatypeFactory.convertToDataType((float)-7.55));
			xmlParser.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(7500));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("AnAs"));
			entriesMap.put("column_3", DatatypeFactory.convertToDataType(Date.valueOf("2001-10-1")));
			entriesMap.put("column_4", DatatypeFactory.convertToDataType((float)-2.55));
			xmlParser.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(852));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("YaKoUt"));
			entriesMap.put("column_3", DatatypeFactory.convertToDataType(Date.valueOf("2002-10-1")));
			entriesMap.put("column_4", DatatypeFactory.convertToDataType((float)-0.55));
			xmlParser.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(189));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("BaRRy"));
			entriesMap.put("column_3", DatatypeFactory.convertToDataType(Date.valueOf("2003-10-1")));
			entriesMap.put("column_4", DatatypeFactory.convertToDataType((float)-5.55));
			xmlParser.insertIntoTable("table_name", entriesMap);

			Condition whereConditionDel = new Where("   column_1  >  300  ");

			RecordSet actualRes = xmlParser.select("table_name", null , whereConditionDel);
			Object[] recordsAct = actualRes.getRecords().toArray();
			Object[] recordsExp = new Object[3];

			Record expectedRecord = new Record();

			expectedRecord.add("column_1",DatatypeFactory.convertToDataType(550));
			expectedRecord.add("column_2",DatatypeFactory.convertToDataType("KHalED"));
			expectedRecord.add("column_3",DatatypeFactory.convertToDataType(Date.valueOf("2050-10-01")));
			expectedRecord.add("column_4",DatatypeFactory.convertToDataType((float)-10.55));
			recordsExp[0] = expectedRecord;

			expectedRecord = new Record();

			expectedRecord.add("column_1",DatatypeFactory.convertToDataType(7500));
			expectedRecord.add("column_2",DatatypeFactory.convertToDataType("AnAs"));
			expectedRecord.add("column_3",DatatypeFactory.convertToDataType(Date.valueOf("2001-10-01")));
			expectedRecord.add("column_4",DatatypeFactory.convertToDataType((float)-2.55));
			recordsExp[1] = expectedRecord;

			expectedRecord = new Record();

			expectedRecord.add("column_1",DatatypeFactory.convertToDataType(852));
			expectedRecord.add("column_2",DatatypeFactory.convertToDataType("YaKoUt"));
			expectedRecord.add("column_3",DatatypeFactory.convertToDataType(Date.valueOf("2002-10-01")));
			expectedRecord.add("column_4",DatatypeFactory.convertToDataType((float)-0.55));
			recordsExp[2] = expectedRecord;

			assertArrayEquals(recordsExp,recordsAct);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Error occurred!");
		}
	}

	@Test
	public void testEight() {

		BackendParserFactory.getFactory().setCurrentParser("xml");
		try {
			xmlParser.createDatabase("database_delete_7");

			LinkedHashMap<String, Class<? extends DBDatatype>> passMap = new LinkedHashMap<>();
			passMap.put("column_1", DBInteger.class);
			passMap.put("column_2", DBString.class);
			passMap.put("column_3", DBDate.class);
			passMap.put("column_4", DBFloat.class);

			xmlParser.createTable("table_name", passMap);

			LinkedHashMap<String, DBDatatype> entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(550));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("KHalED"));
			entriesMap.put("column_3", DatatypeFactory.convertToDataType(Date.valueOf("2050-10-1")));
			entriesMap.put("column_4", DatatypeFactory.convertToDataType((float)-10.55));
			xmlParser.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(200));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("waLiD"));
			entriesMap.put("column_3", DatatypeFactory.convertToDataType(Date.valueOf("2000-10-1")));
			entriesMap.put("column_4", DatatypeFactory.convertToDataType((float)-7.55));
			xmlParser.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(7500));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("AnAs"));
			entriesMap.put("column_3", DatatypeFactory.convertToDataType(Date.valueOf("2001-10-1")));
			entriesMap.put("column_4", DatatypeFactory.convertToDataType((float)-2.55));
			xmlParser.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(852));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("YaKoUt"));
			entriesMap.put("column_3", DatatypeFactory.convertToDataType(Date.valueOf("2002-10-1")));
			entriesMap.put("column_4", DatatypeFactory.convertToDataType((float)-0.55));
			xmlParser.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(189));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("BaRRy"));
			entriesMap.put("column_3", DatatypeFactory.convertToDataType(Date.valueOf("2003-10-1")));
			entriesMap.put("column_4", DatatypeFactory.convertToDataType((float)-5.55));
			xmlParser.insertIntoTable("table_name", entriesMap);

			Condition whereConditionDel = new Where("  ( (column_1  >  300) and (column_3 > '2001-10-1'))  ");

			RecordSet actualRes = xmlParser.select("table_name", null , whereConditionDel);
			Object[] recordsAct = actualRes.getRecords().toArray();
			Object[] recordsExp = new Object[2];

			Record expectedRecord = new Record();

			expectedRecord.add("column_1",DatatypeFactory.convertToDataType(550));
			expectedRecord.add("column_2",DatatypeFactory.convertToDataType("KHalED"));
			expectedRecord.add("column_3",DatatypeFactory.convertToDataType(Date.valueOf("2050-10-01")));
			expectedRecord.add("column_4",DatatypeFactory.convertToDataType((float)-10.55));
			recordsExp[0] = expectedRecord;

			expectedRecord = new Record();

			expectedRecord.add("column_1",DatatypeFactory.convertToDataType(852));
			expectedRecord.add("column_2",DatatypeFactory.convertToDataType("YaKoUt"));
			expectedRecord.add("column_3",DatatypeFactory.convertToDataType(Date.valueOf("2002-10-01")));
			expectedRecord.add("column_4",DatatypeFactory.convertToDataType((float)-0.55));
			recordsExp[1] = expectedRecord;

			assertArrayEquals(recordsExp,recordsAct);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Error occurred!");
		}
	}

	@Test
	public void testNine() {

		BackendParserFactory.getFactory().setCurrentParser("xml");
		try {
			xmlParser.createDatabase("database_delete_8");

			LinkedHashMap<String, Class<? extends DBDatatype>> passMap = new LinkedHashMap<>();
			passMap.put("column_1", DBInteger.class);
			passMap.put("column_2", DBString.class);
			passMap.put("column_3", DBDate.class);
			passMap.put("column_4", DBFloat.class);

			xmlParser.createTable("table_name", passMap);

			LinkedHashMap<String, DBDatatype> entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(550));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("KHalED"));
			entriesMap.put("column_3", DatatypeFactory.convertToDataType(Date.valueOf("2050-10-1")));
			entriesMap.put("column_4", DatatypeFactory.convertToDataType((float)-10.55));
			xmlParser.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(200));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("waLiD"));
			entriesMap.put("column_3", DatatypeFactory.convertToDataType(Date.valueOf("2000-10-1")));
			entriesMap.put("column_4", DatatypeFactory.convertToDataType((float)-7.55));
			xmlParser.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(7500));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("AnAs"));
			entriesMap.put("column_3", DatatypeFactory.convertToDataType(Date.valueOf("2001-10-1")));
			entriesMap.put("column_4", DatatypeFactory.convertToDataType((float)-2.55));
			xmlParser.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(852));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("YaKoUt"));
			entriesMap.put("column_3", DatatypeFactory.convertToDataType(Date.valueOf("2002-10-1")));
			entriesMap.put("column_4", DatatypeFactory.convertToDataType((float)-0.55));
			xmlParser.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(189));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("BaRRy"));
			entriesMap.put("column_3", DatatypeFactory.convertToDataType(Date.valueOf("2003-10-1")));
			entriesMap.put("column_4", DatatypeFactory.convertToDataType((float)-5.55));
			xmlParser.insertIntoTable("table_name", entriesMap);

			Condition whereConditionDel = new Where("  ( (column_1  >  300) and (column_3 > '2001-10-1'))  ");

			Collection<String> selectedColumns = new ArrayList<>();


			selectedColumns.add("column_3");
			selectedColumns.add("column_4");

			RecordSet actualRes = xmlParser.select("table_name", selectedColumns , whereConditionDel);
			Object[] recordsAct = actualRes.getRecords().toArray();
			Object[] recordsExp = new Object[2];

			Record expectedRecord = new Record();

			expectedRecord.add("column_3",DatatypeFactory.convertToDataType(Date.valueOf("2050-10-01")));
			expectedRecord.add("column_4",DatatypeFactory.convertToDataType((float)-10.55));
			recordsExp[0] = expectedRecord;

			expectedRecord = new Record();

			expectedRecord.add("column_3",DatatypeFactory.convertToDataType(Date.valueOf("2002-10-01")));
			expectedRecord.add("column_4",DatatypeFactory.convertToDataType((float)-0.55));
			recordsExp[1] = expectedRecord;

			assertArrayEquals(recordsExp,recordsAct);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Error occurred!");
		}
	}

	@Test
	public void testTen() {

		BackendParserFactory.getFactory().setCurrentParser("xml");
		try {
			xmlParser.createDatabase("database_delete_9");

			LinkedHashMap<String, Class<? extends DBDatatype>> passMap = new LinkedHashMap<>();
			passMap.put("column_1", DBInteger.class);
			passMap.put("column_2", DBString.class);
			passMap.put("column_3", DBDate.class);
			passMap.put("column_4", DBFloat.class);

			xmlParser.createTable("table_name", passMap);

			LinkedHashMap<String, DBDatatype> entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(550));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("KHalED"));
			entriesMap.put("column_3", DatatypeFactory.convertToDataType(Date.valueOf("2050-10-1")));
			entriesMap.put("column_4", DatatypeFactory.convertToDataType((float)-10.55));
			xmlParser.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(200));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("waLiD"));
			entriesMap.put("column_3", DatatypeFactory.convertToDataType(Date.valueOf("2000-10-1")));
			entriesMap.put("column_4", DatatypeFactory.convertToDataType((float)-7.55));
			xmlParser.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(7500));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("AnAs"));
			entriesMap.put("column_3", DatatypeFactory.convertToDataType(Date.valueOf("2001-10-1")));
			entriesMap.put("column_4", DatatypeFactory.convertToDataType((float)-2.55));
			xmlParser.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(852));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("YaKoUt"));
			entriesMap.put("column_3", DatatypeFactory.convertToDataType(Date.valueOf("2002-10-1")));
			entriesMap.put("column_4", DatatypeFactory.convertToDataType((float)-0.55));
			xmlParser.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(189));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("BaRRy"));
			entriesMap.put("column_3", DatatypeFactory.convertToDataType(Date.valueOf("2003-10-1")));
			entriesMap.put("column_4", DatatypeFactory.convertToDataType((float)-5.55));
			xmlParser.insertIntoTable("table_name", entriesMap);

			Condition whereConditionDel = new Where("  ( (column_1  >  300) and (column_3 > '2001-10-1'))  ");

			Collection<String> selectedColumns = new ArrayList<>();


			selectedColumns.add("column_4");
			selectedColumns.add("column_3");


			RecordSet actualRes = xmlParser.select("table_name", selectedColumns , whereConditionDel);
			Object[] recordsAct = actualRes.getRecords().toArray();
			Object[] recordsExp = new Object[2];

			Record expectedRecord = new Record();

			expectedRecord.add("column_4",DatatypeFactory.convertToDataType((float)-10.55));
			expectedRecord.add("column_3",DatatypeFactory.convertToDataType(Date.valueOf("2050-10-01")));

			recordsExp[0] = expectedRecord;

			expectedRecord = new Record();

			expectedRecord.add("column_4",DatatypeFactory.convertToDataType((float)-0.55));
			expectedRecord.add("column_3",DatatypeFactory.convertToDataType(Date.valueOf("2002-10-01")));

			recordsExp[1] = expectedRecord;

			assertArrayEquals(recordsExp,recordsAct);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Error occurred!");
		}
	}

	@Test
	public void testEleven() {

		BackendParserFactory.getFactory().setCurrentParser("xml");
		try {
			xmlParser.createDatabase("database_delete_10");

			LinkedHashMap<String, Class<? extends DBDatatype>> passMap = new LinkedHashMap<>();
			passMap.put("column_1", DBInteger.class);
			passMap.put("column_2", DBString.class);
			passMap.put("column_3", DBDate.class);
			passMap.put("column_4", DBFloat.class);

			xmlParser.createTable("table_name", passMap);

			LinkedHashMap<String, DBDatatype> entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(550));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("KHalED"));
			entriesMap.put("column_3", DatatypeFactory.convertToDataType(Date.valueOf("2050-10-1")));
			entriesMap.put("column_4", DatatypeFactory.convertToDataType((float)-10.55));
			xmlParser.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(200));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("waLiD"));
			entriesMap.put("column_3", DatatypeFactory.convertToDataType(Date.valueOf("2000-10-1")));
			entriesMap.put("column_4", DatatypeFactory.convertToDataType((float)-7.55));
			xmlParser.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(7500));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("AnAs"));
			entriesMap.put("column_3", DatatypeFactory.convertToDataType(Date.valueOf("2001-10-1")));
			entriesMap.put("column_4", DatatypeFactory.convertToDataType((float)-2.55));
			xmlParser.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(852));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("YaKoUt"));
			entriesMap.put("column_3", DatatypeFactory.convertToDataType(Date.valueOf("2002-10-1")));
			entriesMap.put("column_4", DatatypeFactory.convertToDataType((float)-0.55));
			xmlParser.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(189));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("BaRRy"));
			entriesMap.put("column_3", DatatypeFactory.convertToDataType(Date.valueOf("2003-10-1")));
			entriesMap.put("column_4", DatatypeFactory.convertToDataType((float)-5.55));
			xmlParser.insertIntoTable("table_name", entriesMap);

			Condition whereConditionDel = new Where("  ( (column_1  >  300) and (column_3 > '2001-10-1'))  ");

			Collection<String> selectedColumns = new ArrayList<>();


			selectedColumns.add("COlUmn_4");
			selectedColumns.add("colUMN_3");


			RecordSet actualRes = xmlParser.select("table_name", selectedColumns , whereConditionDel);
			Object[] recordsAct = actualRes.getRecords().toArray();
			Object[] recordsExp = new Object[2];

			Record expectedRecord = new Record();

			expectedRecord.add("column_4",DatatypeFactory.convertToDataType((float)-10.55));
			expectedRecord.add("column_3",DatatypeFactory.convertToDataType(Date.valueOf("2050-10-01")));

			recordsExp[0] = expectedRecord;

			expectedRecord = new Record();

			expectedRecord.add("column_4",DatatypeFactory.convertToDataType((float)-0.55));
			expectedRecord.add("column_3",DatatypeFactory.convertToDataType(Date.valueOf("2002-10-01")));

			recordsExp[1] = expectedRecord;

			assertArrayEquals(recordsExp,recordsAct);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Error occurred!");
		}
	}

	@Test
	public void testTwelve() {

		BackendParserFactory.getFactory().setCurrentParser("xml");
		try {
			xmlParser.createDatabase("database_delete_13");

			LinkedHashMap<String, Class<? extends DBDatatype>> passMap = new LinkedHashMap<>();
			passMap.put("column_1", DBInteger.class);
			passMap.put("column_2", DBString.class);
			passMap.put("column_3", DBDate.class);
			passMap.put("column_4", DBFloat.class);

			xmlParser.createTable("table_name", passMap);

			LinkedHashMap<String, DBDatatype> entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(550));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("KHalED"));
			entriesMap.put("column_3", DatatypeFactory.convertToDataType(Date.valueOf("2050-10-1")));
			xmlParser.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(200));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("waLiD"));
			entriesMap.put("column_4", DatatypeFactory.convertToDataType((float)-7.55));
			xmlParser.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(7500));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("AnAs"));
			entriesMap.put("column_3", DatatypeFactory.convertToDataType(Date.valueOf("2001-10-1")));
			entriesMap.put("column_4", DatatypeFactory.convertToDataType((float)-2.55));
			xmlParser.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(852));
			entriesMap.put("column_3", DatatypeFactory.convertToDataType(Date.valueOf("2002-10-1")));
			entriesMap.put("column_4", DatatypeFactory.convertToDataType((float)-0.55));
			xmlParser.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(189));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("BaRRy"));
			entriesMap.put("column_3", DatatypeFactory.convertToDataType(Date.valueOf("2003-10-1")));
			xmlParser.insertIntoTable("table_name", entriesMap);

			Condition whereConditionDel = new Where("  ( (column_1  >  300) and (column_4 > -1.0))  ");

			Collection<String> selectedColumns = new ArrayList<>();


			selectedColumns.add("COlUmn_4");
			selectedColumns.add("colUMN_3");


			RecordSet actualRes = xmlParser.select("table_name", selectedColumns , whereConditionDel);
			Object[] recordsAct = actualRes.getRecords().toArray();
			Object[] recordsExp = new Object[1];

			Record expectedRecord = new Record();

			expectedRecord.add("column_4",DatatypeFactory.convertToDataType((float)-0.55));
			expectedRecord.add("column_3",DatatypeFactory.convertToDataType(Date.valueOf("2002-10-01")));

			recordsExp[0] = expectedRecord;

			assertArrayEquals(recordsExp,recordsAct);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Error occurred!");
		}
	}

	@Test
	public void testThirteen() {

		BackendParserFactory.getFactory().setCurrentParser("xml");
		try {
			xmlParser.createDatabase("database_delete_12");

			LinkedHashMap<String, Class<? extends DBDatatype>> passMap = new LinkedHashMap<>();
			passMap.put("column_1", DBInteger.class);
			passMap.put("column_2", DBString.class);
			passMap.put("column_3", DBDate.class);
			passMap.put("column_4", DBFloat.class);

			xmlParser.createTable("table_name", passMap);

			LinkedHashMap<String, DBDatatype> entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(550));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("KHalED"));
			entriesMap.put("column_3", DatatypeFactory.convertToDataType(Date.valueOf("2050-10-1")));
			xmlParser.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(200));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("waLiD"));
			entriesMap.put("column_4", DatatypeFactory.convertToDataType((float)-7.55));
			xmlParser.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(7500));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("AnAs"));
			entriesMap.put("column_3", DatatypeFactory.convertToDataType(Date.valueOf("2001-10-1")));
			entriesMap.put("column_4", DatatypeFactory.convertToDataType((float)-2.55));
			xmlParser.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(852));
			entriesMap.put("column_3", DatatypeFactory.convertToDataType(Date.valueOf("2002-10-1")));
			xmlParser.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(189));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("BaRRy"));
			entriesMap.put("column_3", DatatypeFactory.convertToDataType(Date.valueOf("2003-10-1")));
			xmlParser.insertIntoTable("table_name", entriesMap);

			Condition whereConditionDel = new Where("  ( (column_1  >  300) and (column_4 > -1.0))  ");

			RecordSet actualRes = xmlParser.select("table_name", null , whereConditionDel);
			Object[] recordsAct = actualRes.getRecords().toArray();
			assertEquals(recordsAct.length , 0);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Error occurred!");
		}
	}

	@Test
	public void testFourteen() {

		BackendParserFactory.getFactory().setCurrentParser("xml");
		try {
			xmlParser.createDatabase("database_delete_11");

			LinkedHashMap<String, Class<? extends DBDatatype>> passMap = new LinkedHashMap<>();
			passMap.put("column_1", DBInteger.class);
			passMap.put("column_2", DBString.class);
			passMap.put("column_3", DBDate.class);
			passMap.put("column_4", DBFloat.class);

			xmlParser.createTable("table_name", passMap);

			LinkedHashMap<String, DBDatatype> entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(550));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("KHalED"));
			entriesMap.put("column_3", DatatypeFactory.convertToDataType(Date.valueOf("2050-10-1")));
			entriesMap.put("column_4", DatatypeFactory.convertToDataType((float)-0.55));
			xmlParser.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(200));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("waLiD"));
			entriesMap.put("column_3", DatatypeFactory.convertToDataType(Date.valueOf("2000-10-1")));
			entriesMap.put("column_4", DatatypeFactory.convertToDataType((float)-7.55));
			xmlParser.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(7500));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("AnAs"));
			entriesMap.put("column_3", DatatypeFactory.convertToDataType(Date.valueOf("2001-10-1")));
			entriesMap.put("column_4", DatatypeFactory.convertToDataType((float)-2.55));
			xmlParser.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(852));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("YaKoUt"));
			entriesMap.put("column_3", DatatypeFactory.convertToDataType(Date.valueOf("2050-10-1")));
			entriesMap.put("column_4", DatatypeFactory.convertToDataType((float)-0.55));
			xmlParser.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(189));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("BaRRy"));
			entriesMap.put("column_3", DatatypeFactory.convertToDataType(Date.valueOf("2003-10-1")));
			entriesMap.put("column_4", DatatypeFactory.convertToDataType((float)-5.55));
			xmlParser.insertIntoTable("table_name", entriesMap);

			Condition whereConditionDel = new Where("  ( (column_1  >  300) and (column_3 > '2001-10-1'))  ");

			Collection<String> selectedColumns = new ArrayList<>();


			selectedColumns.add("COlUmn_4");
			selectedColumns.add("colUMN_3");


			RecordSet actualRes = xmlParser.select("table_name", selectedColumns , whereConditionDel);
			RecordSet actualResUpdated = new RecordSet(actualRes.getRecords());
			actualResUpdated.distinct();
			Object[] recordsAct = actualResUpdated.getRecords().toArray();
			Object[] recordsExp = new Object[1];

			Record expectedRecord = new Record();

			expectedRecord.add("column_4",DatatypeFactory.convertToDataType((float)-0.55));
			expectedRecord.add("column_3",DatatypeFactory.convertToDataType(Date.valueOf("2050-10-01")));

			recordsExp[0] = expectedRecord;

			assertArrayEquals(recordsExp,recordsAct);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Error occurred!");
		}
	}

	@Test
	public void testFifteen() {

		BackendParserFactory.getFactory().setCurrentParser("xml");
		try {
			xmlParser.createDatabase("database_delete_15");

			LinkedHashMap<String, Class<? extends DBDatatype>> passMap = new LinkedHashMap<>();
			passMap.put("column_1", DBInteger.class);
			passMap.put("column_2", DBString.class);
			passMap.put("column_3", DBDate.class);
			passMap.put("column_4", DBFloat.class);

			xmlParser.createTable("table_name", passMap);

			LinkedHashMap<String, DBDatatype> entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(550));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("KHalED"));
			entriesMap.put("column_3", DatatypeFactory.convertToDataType(Date.valueOf("2010-10-1")));
			entriesMap.put("column_4", DatatypeFactory.convertToDataType((float)-2.55));
			xmlParser.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(200));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("waLiD"));
			entriesMap.put("column_3", DatatypeFactory.convertToDataType(Date.valueOf("2000-10-1")));
			entriesMap.put("column_4", DatatypeFactory.convertToDataType((float)-7.55));
			xmlParser.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(7500));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("AnAs"));
			entriesMap.put("column_3", DatatypeFactory.convertToDataType(Date.valueOf("2001-10-1")));
			entriesMap.put("column_4", DatatypeFactory.convertToDataType((float)-2.55));
			xmlParser.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(852));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("YaKoUt"));
			entriesMap.put("column_3", DatatypeFactory.convertToDataType(Date.valueOf("2050-10-1")));
			entriesMap.put("column_4", DatatypeFactory.convertToDataType((float)-55.55));
			xmlParser.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(189));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("BaRRy"));
			entriesMap.put("column_3", DatatypeFactory.convertToDataType(Date.valueOf("2003-10-1")));
			entriesMap.put("column_4", DatatypeFactory.convertToDataType((float)-5.55));
			xmlParser.insertIntoTable("table_name", entriesMap);

			Condition whereConditionDel = new Where("  ( (column_1  >  300) and (column_3 > '2001-10-1'))  ");

			Collection<String> selectedColumns = new ArrayList<>();


			selectedColumns.add("COlUmn_4");
			selectedColumns.add("colUMN_3");

            List<Pair<String,Boolean> > orderByCols = new ArrayList<>();

            orderByCols.add(new Pair<>("coluMN_3",false));

			RecordSet actualRes = xmlParser.select("table_name", selectedColumns , whereConditionDel);
			RecordSet actualResUpdated = new RecordSet(actualRes.getRecords());
			actualResUpdated.orderBy(orderByCols);
			Object[] recordsAct = actualResUpdated.getRecords().toArray();
			Object[] recordsExp = new Object[2];

			Record expectedRecord = new Record();


			expectedRecord.add("column_4",DatatypeFactory.convertToDataType((float)-55.55));
			expectedRecord.add("column_3",DatatypeFactory.convertToDataType(Date.valueOf("2050-10-01")));

			recordsExp[0] = expectedRecord;

			expectedRecord = new Record();

			expectedRecord.add("column_4",DatatypeFactory.convertToDataType((float)-2.55));
			expectedRecord.add("column_3",DatatypeFactory.convertToDataType(Date.valueOf("2010-10-01")));

			recordsExp[1] = expectedRecord;

			assertArrayEquals(recordsExp,recordsAct);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Error occurred!");
		}
	}


	@Test
	public void testSixteen() {

		BackendParserFactory.getFactory().setCurrentParser("xml");
		try {
			xmlParser.createDatabase("database_delete_16");

			LinkedHashMap<String, Class<? extends DBDatatype>> passMap = new LinkedHashMap<>();
			passMap.put("column_1", DBInteger.class);
			passMap.put("column_2", DBString.class);
			passMap.put("column_3", DBDate.class);
			passMap.put("column_4", DBFloat.class);

			xmlParser.createTable("table_name", passMap);

			LinkedHashMap<String, DBDatatype> entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(550));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("KHalED"));
			entriesMap.put("column_3", DatatypeFactory.convertToDataType(Date.valueOf("2010-10-1")));
			entriesMap.put("column_4", DatatypeFactory.convertToDataType((float)-2.55));
			xmlParser.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(200));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("waLiD"));
			entriesMap.put("column_3", DatatypeFactory.convertToDataType(Date.valueOf("2000-10-1")));
			entriesMap.put("column_4", DatatypeFactory.convertToDataType((float)-7.55));
			xmlParser.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(7500));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("AnAs"));
			entriesMap.put("column_3", DatatypeFactory.convertToDataType(Date.valueOf("2001-10-1")));
			entriesMap.put("column_4", DatatypeFactory.convertToDataType((float)-2.55));
			xmlParser.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(852));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("YaKoUt"));
			entriesMap.put("column_3", DatatypeFactory.convertToDataType(Date.valueOf("2050-10-1")));
			entriesMap.put("column_4", DatatypeFactory.convertToDataType((float)-55.55));
			xmlParser.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(189));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("BaRRy"));
			entriesMap.put("column_3", DatatypeFactory.convertToDataType(Date.valueOf("2003-10-1")));
			entriesMap.put("column_4", DatatypeFactory.convertToDataType((float)-5.55));
			xmlParser.insertIntoTable("table_name", entriesMap);

			Condition whereConditionDel = new Where("  ( (column_1  >  300) and (column_3 > '2001-10-1'))  ");

			Collection<String> selectedColumns = new ArrayList<>();


			selectedColumns.add("COlUmn_4");
			selectedColumns.add("colUMN_3");

			List<Pair<String,Boolean> > orderByCols = new ArrayList<>();

			orderByCols.add(new Pair<>("coluMN_3",true));

			RecordSet actualRes = xmlParser.select("table_name", selectedColumns , whereConditionDel);
			RecordSet actualResUpdated = new RecordSet(actualRes.getRecords());
			actualResUpdated.orderBy(orderByCols);
			Object[] recordsAct = actualResUpdated.getRecords().toArray();
			Object[] recordsExp = new Object[2];

			Record expectedRecord = new Record();

			expectedRecord.add("column_4",DatatypeFactory.convertToDataType((float)-2.55));
			expectedRecord.add("column_3",DatatypeFactory.convertToDataType(Date.valueOf("2010-10-01")));

			recordsExp[0] = expectedRecord;

			expectedRecord = new Record();

			expectedRecord.add("column_4",DatatypeFactory.convertToDataType((float)-55.55));
			expectedRecord.add("column_3",DatatypeFactory.convertToDataType(Date.valueOf("2050-10-01")));

			recordsExp[1] = expectedRecord;

			assertArrayEquals(recordsExp,recordsAct);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Error occurred!");
		}
	}

	@Test
	public void testSeventeen() {

		BackendParserFactory.getFactory().setCurrentParser("xml");
		try {
			xmlParser.createDatabase("database_delete_17");

			LinkedHashMap<String, Class<? extends DBDatatype>> passMap = new LinkedHashMap<>();
			passMap.put("column_1", DBInteger.class);
			passMap.put("column_2", DBString.class);
			passMap.put("column_3", DBDate.class);
			passMap.put("column_4", DBFloat.class);

			xmlParser.createTable("table_name", passMap);

			LinkedHashMap<String, DBDatatype> entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(550));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("KHalED"));
			entriesMap.put("column_3", DatatypeFactory.convertToDataType(Date.valueOf("2010-10-1")));
			entriesMap.put("column_4", DatatypeFactory.convertToDataType((float)-2.55));
			xmlParser.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(200));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("waLiD"));
			entriesMap.put("column_3", DatatypeFactory.convertToDataType(Date.valueOf("2000-10-1")));
			entriesMap.put("column_4", DatatypeFactory.convertToDataType((float)-7.55));
			xmlParser.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(7500));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("AnAs"));
			entriesMap.put("column_3", DatatypeFactory.convertToDataType(Date.valueOf("2001-10-1")));
			entriesMap.put("column_4", DatatypeFactory.convertToDataType((float)-2.55));
			xmlParser.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(852));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("YaKoUt"));
			entriesMap.put("column_3", DatatypeFactory.convertToDataType(Date.valueOf("2050-10-1")));
			entriesMap.put("column_4", DatatypeFactory.convertToDataType((float)-55.55));
			xmlParser.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(189));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("BaRRy"));
			entriesMap.put("column_3", DatatypeFactory.convertToDataType(Date.valueOf("2003-10-1")));
			entriesMap.put("column_4", DatatypeFactory.convertToDataType((float)-5.55));
			xmlParser.insertIntoTable("table_name", entriesMap);

			Condition whereConditionDel = new Where("  ( (column_1  >  300) and (column_3 > '2001-10-1'))  ");

			Collection<String> selectedColumns = new ArrayList<>();

			selectedColumns.add("COlUmn_4");

			List<Pair<String,Boolean> > orderByCols = new ArrayList<>();

			orderByCols.add(new Pair<>("coluMN_3",true));

			RecordSet actualRes = xmlParser.select("table_name", selectedColumns , whereConditionDel);
			RecordSet actualResUpdated = new RecordSet(actualRes.getRecords());
			actualResUpdated.orderBy(orderByCols);
			Object[] recordsAct = actualResUpdated.getRecords().toArray();
			Object[] recordsExp = new Object[2];

			Record expectedRecord = new Record();

			expectedRecord.add("column_4",DatatypeFactory.convertToDataType((float)-2.55));

			recordsExp[0] = expectedRecord;

			expectedRecord = new Record();

			expectedRecord.add("column_4",DatatypeFactory.convertToDataType((float)-55.55));

			recordsExp[1] = expectedRecord;

			assertArrayEquals(recordsExp,recordsAct);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Error occurred!");
		}
	}


	// TODO Testing
	// Select with Union clause.
}
