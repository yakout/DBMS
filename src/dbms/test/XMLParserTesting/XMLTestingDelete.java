package dbms.test.XMLParserTesting;

import dbms.backend.BackendController;
import dbms.backend.BackendParserFactory;
import dbms.datatypes.*;
import dbms.sqlparser.sqlInterpreter.Condition;
import dbms.sqlparser.sqlInterpreter.Where;
import dbms.util.Record;
import dbms.util.RecordSet;
import org.junit.Test;

import java.sql.Date;
import java.util.*;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class XMLTestingDelete {
	private final BackendController xmlParser = BackendController.getInstance();

	@Test
	public void testOne() {

		BackendParserFactory.getFactory().setCurrentParser("xml");
		try {
			xmlParser.createDatabase("database_delete_1");

			LinkedHashMap<String, Class<? extends DBDatatype>> passMap = new LinkedHashMap<>();
			passMap.put("column_1", DBInteger.class);
			passMap.put("column_2", DBString.class);

			xmlParser.createTable("table_name", passMap);

			LinkedHashMap<String, DBDatatype> entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(550));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("KHalED"));
			xmlParser.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(200));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("waLiD"));
			xmlParser.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(7500));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("AnAs"));
			xmlParser.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(852));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("YaKoUt"));
			xmlParser.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(189));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("BaRRy"));
			xmlParser.insertIntoTable("table_name", entriesMap);

			Condition whereCondition = new Where("    column_1   >=    300 ");
			RecordSet result = new RecordSet();
			LinkedHashMap<String, DBDatatype> resExpected = new LinkedHashMap<>();
			resExpected.put("column_1", DatatypeFactory.convertToDataType(550));
			resExpected.put("column_2", DatatypeFactory.convertToDataType("KHalED"));
			Record expRecord = new Record(resExpected);
			result.add(expRecord);

			resExpected = new LinkedHashMap<>();
			resExpected.put("column_1", DatatypeFactory.convertToDataType(7500));
			resExpected.put("column_2", DatatypeFactory.convertToDataType("AnAs"));
			expRecord = new Record(resExpected);
			result.add(expRecord);

			resExpected = new LinkedHashMap<>();
			resExpected.put("column_1", DatatypeFactory.convertToDataType(852));
			resExpected.put("column_2", DatatypeFactory.convertToDataType("YaKoUt"));
			expRecord = new Record(resExpected);
			result.add(expRecord);

			xmlParser.delete("table_name", whereCondition);

			RecordSet actualRes = xmlParser.select("table_name", null, whereCondition);

			Iterator<Record> resultSetItr = result.iterator();
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
	public void testTwo() {

		BackendParserFactory.getFactory().setCurrentParser("xml");
		try {
			xmlParser.createDatabase("database_delete_2");

			LinkedHashMap<String, Class<? extends DBDatatype>> passMap = new LinkedHashMap<>();
			passMap.put("column_1", DBInteger.class);
			passMap.put("column_2", DBString.class);

			xmlParser.createTable("table_name", passMap);

			LinkedHashMap<String, DBDatatype> entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(550));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("KHalED"));
			xmlParser.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(200));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("waLiD"));
			xmlParser.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(7500));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("AnAs"));
			xmlParser.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(852));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("YaKoUt"));
			xmlParser.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(189));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("BaRRy"));
			xmlParser.insertIntoTable("table_name", entriesMap);

			Condition whereCondition = new Where("   column_1  >=  300  ");
			RecordSet result = new RecordSet();

			LinkedHashMap<String, DBDatatype> resExpected = new LinkedHashMap<>();
			resExpected.put("column_1", DatatypeFactory.convertToDataType(550));
			Record expRecord = new Record(resExpected);
			result.add(expRecord);

			resExpected = new LinkedHashMap<>();
			resExpected.put("column_1", DatatypeFactory.convertToDataType(7500));
			expRecord = new Record(resExpected);
			result.add(expRecord);

			resExpected = new LinkedHashMap<>();
			resExpected.put("column_1", DatatypeFactory.convertToDataType(852));
			expRecord = new Record(resExpected);
			result.add(expRecord);

			Set<String> columns = new TreeSet<>();

			columns.add("column_1");

			xmlParser.delete("table_name", whereCondition);

			RecordSet actualRes = xmlParser.select("table_name", columns, whereCondition);

			Iterator<Record> resultSetItr = result.iterator();
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
	public void testThree() {

		BackendParserFactory.getFactory().setCurrentParser("xml");
		try {
			xmlParser.createDatabase("database_delete_3");

			LinkedHashMap<String, Class<? extends DBDatatype>> passMap = new LinkedHashMap<>();
			passMap.put("column_1", DBInteger.class);
			passMap.put("column_2", DBString.class);

			xmlParser.createTable("table_name", passMap);

			LinkedHashMap<String, DBDatatype> entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(550));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("KHalED"));
			xmlParser.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(200));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("waLiD"));
			xmlParser.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(7500));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("AnAs"));
			xmlParser.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(852));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("YaKoUt"));
			xmlParser.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(189));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("BaRRy"));
			xmlParser.insertIntoTable("table_name", entriesMap);

			Condition whereConditionDel = new Where("   column_1  >  300  ");
			Condition whereConditionSel = new Where("   column_1  >  600  ");
			RecordSet result = new RecordSet();

			LinkedHashMap<String, DBDatatype> resExpected = new LinkedHashMap<>();
			resExpected.put("column_1", DatatypeFactory.convertToDataType(7500));
			Record expRecord = new Record(resExpected);
			result.add(expRecord);

			resExpected = new LinkedHashMap<>();
			resExpected.put("column_1", DatatypeFactory.convertToDataType(852));
			expRecord = new Record(resExpected);
			result.add(expRecord);

			Set<String> columns = new TreeSet<>();

			columns.add("column_1");

			xmlParser.delete("table_name", whereConditionDel);

			RecordSet actualRes = xmlParser.select("table_name", columns, whereConditionSel);

			Iterator<Record> resultSetItr = result.iterator();
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
			xmlParser.createDatabase("database_delete_4");

			LinkedHashMap<String, Class<? extends DBDatatype>> passMap = new LinkedHashMap<>();
			passMap.put("column_1", DBInteger.class);
			passMap.put("column_2", DBString.class);

			xmlParser.createTable("table_name", passMap);

			LinkedHashMap<String, DBDatatype> entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(550));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("KHalED"));
			xmlParser.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(200));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("waLiD"));
			xmlParser.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(7500));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("AnAs"));
			xmlParser.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(852));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("YaKoUt"));
			xmlParser.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(189));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("BaRRy"));
			xmlParser.insertIntoTable("table_name", entriesMap);

			Condition whereConditionDel = new Where("   column_1  <  300  ");
			Condition whereConditionSel = new Where("   column_1  <  500  ");
			//RecordSet result = new RecordSet();

			Set<String> columns = new TreeSet<>();

			columns.add("column_1");

			xmlParser.delete("table_name", whereConditionDel);
			RecordSet actualRes = xmlParser.select("table_name", columns, whereConditionSel);
			Iterator<Record> actualResItr = actualRes.iterator();
			while (actualResItr.hasNext()) {

				assertTrue(actualResItr.next() == null);

			}
		} catch (Exception e) {
			e.printStackTrace();
			fail("Error occurred!");

		}
	}

	@Test
	public void testFive() {

		BackendParserFactory.getFactory().setCurrentParser("xml");
		try {
			xmlParser.createDatabase("database_delete_5");

			LinkedHashMap<String, Class<? extends DBDatatype>> passMap = new LinkedHashMap<>();
			passMap.put("column_1", DBInteger.class);
			passMap.put("column_2", DBString.class);

			xmlParser.createTable("table_name", passMap);

			LinkedHashMap<String, DBDatatype> entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(550));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("KHalED"));
			xmlParser.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(200));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("waLiD"));
			xmlParser.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(7500));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("AnAs"));
			xmlParser.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(852));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("YaKoUt"));
			xmlParser.insertIntoTable("table_name", entriesMap);
			entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(189));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("BaRRy"));
			xmlParser.insertIntoTable("table_name", entriesMap);

			Condition whereConditionDel = new Where("   column_1  !=  300  ");

			//RecordSet result = new RecordSet();

			Set<String> columns = new TreeSet<>();

			columns.add("column_1");

			xmlParser.delete("table_name", whereConditionDel);
			RecordSet actualRes = xmlParser.select("table_name", null , null);
			Iterator<Record> actualResItr = actualRes.iterator();
			while (actualResItr.hasNext()) {
				assertTrue(actualResItr.next() == null);
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

			xmlParser.delete("table_name", whereConditionDel);
			RecordSet actualRes = xmlParser.select("table_name", null , null);
			Object[] recordsAct = actualRes.getRecords().toArray();
			Object[] recordsExp = new Object[2];

			Record expectedRecord = new Record();

			expectedRecord.add("column_1",DatatypeFactory.convertToDataType(200));
			expectedRecord.add("column_2",DatatypeFactory.convertToDataType("waLiD"));
			expectedRecord.add("column_3",DatatypeFactory.convertToDataType(Date.valueOf("2000-10-01")));
			expectedRecord.add("column_4",DatatypeFactory.convertToDataType((float)-7.55));
			recordsExp[0] = expectedRecord;

			expectedRecord = new Record();

			expectedRecord.add("column_1",DatatypeFactory.convertToDataType(189));
			expectedRecord.add("column_2",DatatypeFactory.convertToDataType("BaRRy"));
			expectedRecord.add("column_3",DatatypeFactory.convertToDataType(Date.valueOf("2003-10-01")));
			expectedRecord.add("column_4",DatatypeFactory.convertToDataType((float)-5.55));
			recordsExp[1] = expectedRecord;

			assertArrayEquals(recordsExp,recordsAct);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Error occurred!");
		}
	}


	@Test
	public void testSeven() {

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

			Condition whereConditionDel = new Where("((column_1  >  300) or (column_2 = 'waLiD'))  ");

			xmlParser.delete("table_name", whereConditionDel);
			RecordSet actualRes = xmlParser.select("table_name", null , null);
			Object[] recordsAct = actualRes.getRecords().toArray();
			Object[] recordsExp = new Object[1];

			Record expectedRecord = new Record();

			expectedRecord.add("column_1",DatatypeFactory.convertToDataType(189));
			expectedRecord.add("column_2",DatatypeFactory.convertToDataType("BaRRy"));
			expectedRecord.add("column_3",DatatypeFactory.convertToDataType(Date.valueOf("2003-10-01")));
			expectedRecord.add("column_4",DatatypeFactory.convertToDataType((float)-5.55));
			recordsExp[0] = expectedRecord;

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
			xmlParser.createDatabase("database_delete_8");

			LinkedHashMap<String, Class<? extends DBDatatype>> passMap = new LinkedHashMap<>();
			passMap.put("column_1", DBInteger.class);
			passMap.put("column_2", DBString.class);
			passMap.put("column_3", DBDate.class);
			passMap.put("column_4", DBFloat.class);

			xmlParser.createTable("table_name", passMap);

			LinkedHashMap<String, DBDatatype> entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(550));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("kHalED"));
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

			Condition whereConditionDel = new Where("((column_1  >  300) and (column_2 = 'waLiD'))  ");

			xmlParser.delete("table_name", whereConditionDel);
			RecordSet actualRes = xmlParser.select("table_name", null , null);
			Object[] recordsAct = actualRes.getRecords().toArray();
			Object[] recordsExp = new Object[5];

			Record expectedRecord = new Record();

			expectedRecord.add("column_1",DatatypeFactory.convertToDataType(550));
			expectedRecord.add("column_2",DatatypeFactory.convertToDataType("kHalED"));
			expectedRecord.add("column_3",DatatypeFactory.convertToDataType(Date.valueOf("2050-10-01")));
			expectedRecord.add("column_4",DatatypeFactory.convertToDataType((float)-10.55));
			recordsExp[0] = expectedRecord;

			expectedRecord = new Record();

			expectedRecord.add("column_1",DatatypeFactory.convertToDataType(200));
			expectedRecord.add("column_2",DatatypeFactory.convertToDataType("waLiD"));
			expectedRecord.add("column_3",DatatypeFactory.convertToDataType(Date.valueOf("2000-10-01")));
			expectedRecord.add("column_4",DatatypeFactory.convertToDataType((float)-7.55));
			recordsExp[1] = expectedRecord;

			expectedRecord = new Record();

			expectedRecord.add("column_1",DatatypeFactory.convertToDataType(7500));
			expectedRecord.add("column_2",DatatypeFactory.convertToDataType("AnAs"));
			expectedRecord.add("column_3",DatatypeFactory.convertToDataType(Date.valueOf("2001-10-01")));
			expectedRecord.add("column_4",DatatypeFactory.convertToDataType((float)-2.55));
			recordsExp[2] = expectedRecord;

			expectedRecord = new Record();

			expectedRecord.add("column_1",DatatypeFactory.convertToDataType(852));
			expectedRecord.add("column_2",DatatypeFactory.convertToDataType("YaKoUt"));
			expectedRecord.add("column_3",DatatypeFactory.convertToDataType(Date.valueOf("2002-10-01")));
			expectedRecord.add("column_4",DatatypeFactory.convertToDataType((float)-0.55));
			recordsExp[3] = expectedRecord;

			expectedRecord = new Record();

			expectedRecord.add("column_1",DatatypeFactory.convertToDataType(189));
			expectedRecord.add("column_2",DatatypeFactory.convertToDataType("BaRRy"));
			expectedRecord.add("column_3",DatatypeFactory.convertToDataType(Date.valueOf("2003-10-01")));
			expectedRecord.add("column_4",DatatypeFactory.convertToDataType((float)-5.55));
			recordsExp[4] = expectedRecord;

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
			xmlParser.createDatabase("database_delete_9");

			LinkedHashMap<String, Class<? extends DBDatatype>> passMap = new LinkedHashMap<>();
			passMap.put("column_1", DBInteger.class);
			passMap.put("column_2", DBString.class);
			passMap.put("column_3", DBDate.class);
			passMap.put("column_4", DBFloat.class);

			xmlParser.createTable("table_name", passMap);

			LinkedHashMap<String, DBDatatype> entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(550));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("kHalED"));
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

			Condition whereConditionDel = new Where("((column_1  >  300) and (column_3 > '2003-10-2'))  ");

			xmlParser.delete("table_name", whereConditionDel);
			RecordSet actualRes = xmlParser.select("table_name", null , null);
			Object[] recordsAct = actualRes.getRecords().toArray();
			Object[] recordsExp = new Object[4];

			Record expectedRecord = new Record();

			expectedRecord.add("column_1",DatatypeFactory.convertToDataType(200));
			expectedRecord.add("column_2",DatatypeFactory.convertToDataType("waLiD"));
			expectedRecord.add("column_3",DatatypeFactory.convertToDataType(Date.valueOf("2000-10-01")));
			expectedRecord.add("column_4",DatatypeFactory.convertToDataType((float)-7.55));
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

			expectedRecord = new Record();

			expectedRecord.add("column_1",DatatypeFactory.convertToDataType(189));
			expectedRecord.add("column_2",DatatypeFactory.convertToDataType("BaRRy"));
			expectedRecord.add("column_3",DatatypeFactory.convertToDataType(Date.valueOf("2003-10-01")));
			expectedRecord.add("column_4",DatatypeFactory.convertToDataType((float)-5.55));
			recordsExp[3] = expectedRecord;


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
			xmlParser.createDatabase("database_delete_10");

			LinkedHashMap<String, Class<? extends DBDatatype>> passMap = new LinkedHashMap<>();
			passMap.put("column_1", DBInteger.class);
			passMap.put("column_2", DBString.class);
			passMap.put("column_3", DBDate.class);
			passMap.put("column_4", DBFloat.class);

			xmlParser.createTable("table_name", passMap);

			LinkedHashMap<String, DBDatatype> entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(550));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("kHalED"));
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

			Condition whereConditionDel = new Where("((column_1  >  600) and (column_3 > '2003-10-2'))  ");

			xmlParser.delete("table_name", whereConditionDel);
			RecordSet actualRes = xmlParser.select("table_name", null , null);
			Object[] recordsAct = actualRes.getRecords().toArray();
			Object[] recordsExp = new Object[5];

			Record expectedRecord = new Record();

			expectedRecord.add("column_1",DatatypeFactory.convertToDataType(550));
			expectedRecord.add("column_2",DatatypeFactory.convertToDataType("kHalED"));
			expectedRecord.add("column_3",DatatypeFactory.convertToDataType(Date.valueOf("2050-10-1")));
			expectedRecord.add("column_4",DatatypeFactory.convertToDataType((float)-10.55));
			recordsExp[0] = expectedRecord;

			expectedRecord = new Record();

			expectedRecord.add("column_1",DatatypeFactory.convertToDataType(200));
			expectedRecord.add("column_2",DatatypeFactory.convertToDataType("waLiD"));
			expectedRecord.add("column_3",DatatypeFactory.convertToDataType(Date.valueOf("2000-10-01")));
			expectedRecord.add("column_4",DatatypeFactory.convertToDataType((float)-7.55));
			recordsExp[1] = expectedRecord;

			expectedRecord = new Record();

			expectedRecord.add("column_1",DatatypeFactory.convertToDataType(7500));
			expectedRecord.add("column_2",DatatypeFactory.convertToDataType("AnAs"));
			expectedRecord.add("column_3",DatatypeFactory.convertToDataType(Date.valueOf("2001-10-01")));
			expectedRecord.add("column_4",DatatypeFactory.convertToDataType((float)-2.55));
			recordsExp[2] = expectedRecord;

			expectedRecord = new Record();

			expectedRecord.add("column_1",DatatypeFactory.convertToDataType(852));
			expectedRecord.add("column_2",DatatypeFactory.convertToDataType("YaKoUt"));
			expectedRecord.add("column_3",DatatypeFactory.convertToDataType(Date.valueOf("2002-10-01")));
			expectedRecord.add("column_4",DatatypeFactory.convertToDataType((float)-0.55));
			recordsExp[3] = expectedRecord;

			expectedRecord = new Record();

			expectedRecord.add("column_1",DatatypeFactory.convertToDataType(189));
			expectedRecord.add("column_2",DatatypeFactory.convertToDataType("BaRRy"));
			expectedRecord.add("column_3",DatatypeFactory.convertToDataType(Date.valueOf("2003-10-01")));
			expectedRecord.add("column_4",DatatypeFactory.convertToDataType((float)-5.55));
			recordsExp[4] = expectedRecord;


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
			xmlParser.createDatabase("database_delete_11");

			LinkedHashMap<String, Class<? extends DBDatatype>> passMap = new LinkedHashMap<>();
			passMap.put("column_1", DBInteger.class);
			passMap.put("column_2", DBString.class);
			passMap.put("column_3", DBDate.class);
			passMap.put("column_4", DBFloat.class);

			xmlParser.createTable("table_name", passMap);

			LinkedHashMap<String, DBDatatype> entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(550));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("kHalED"));
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
			entriesMap.put("column_3", DatatypeFactory.convertToDataType(Date.valueOf("2005-10-1")));
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

			Condition whereConditionDel = new Where("(((column_1  >  300) and (column_3 > '2003-10-2')) or (column_2 = 'AnAs'))  ");

			xmlParser.delete("table_name", whereConditionDel);
			RecordSet actualRes = xmlParser.select("table_name", null , null);
			Object[] recordsAct = actualRes.getRecords().toArray();
			Object[] recordsExp = new Object[3];

			Record expectedRecord = new Record();

			expectedRecord.add("column_1",DatatypeFactory.convertToDataType(200));
			expectedRecord.add("column_2",DatatypeFactory.convertToDataType("waLiD"));
			expectedRecord.add("column_3",DatatypeFactory.convertToDataType(Date.valueOf("2000-10-01")));
			expectedRecord.add("column_4",DatatypeFactory.convertToDataType((float)-7.55));
			recordsExp[0] = expectedRecord;

			expectedRecord = new Record();

			expectedRecord.add("column_1",DatatypeFactory.convertToDataType(852));
			expectedRecord.add("column_2",DatatypeFactory.convertToDataType("YaKoUt"));
			expectedRecord.add("column_3",DatatypeFactory.convertToDataType(Date.valueOf("2002-10-01")));
			expectedRecord.add("column_4",DatatypeFactory.convertToDataType((float)-0.55));
			recordsExp[1] = expectedRecord;

			expectedRecord = new Record();

			expectedRecord.add("column_1",DatatypeFactory.convertToDataType(189));
			expectedRecord.add("column_2",DatatypeFactory.convertToDataType("BaRRy"));
			expectedRecord.add("column_3",DatatypeFactory.convertToDataType(Date.valueOf("2003-10-01")));
			expectedRecord.add("column_4",DatatypeFactory.convertToDataType((float)-5.55));
			recordsExp[2] = expectedRecord;


			assertArrayEquals(recordsExp,recordsAct);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Error occurred!");
		}
	}



}
