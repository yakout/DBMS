//package dbms.test.XMLParserTesting;
//
//import dbms.backend.BackendController;
//import dbms.sqlparser.sqlInterpreter.Condition;
//import dbms.sqlparser.sqlInterpreter.Where;
//import dbms.util.Record;
//import dbms.util.RecordSet;
//import org.junit.Test;
//
//import java.util.*;
//
//import static org.junit.Assert.assertTrue;
//import static org.junit.Assert.fail;
//
//public class XMLTestingUpdate {
//	private final BackendController xmlParserConc = BackendController.getInstance();
//	/*
//	 * These tests check the validity of XML Parser(UPDATE command) module
//	 */
//	@Test
//	public void testOne() {
//		try {
//			xmlParserConc.createDatabase("test1");
//			Map<String, Class> passMap = new LinkedHashMap<String, Class>();
//			passMap.put("col_1", String.class);
//			passMap.put("col_2", String.class);
//			passMap.put("col_3", String.class);
//			passMap.put("col_4", Integer.class);
//			xmlParserConc.createTable("table_1", passMap);
//			Map<String, Object> entriesMap = new LinkedHashMap<String, Object>();
//			entriesMap.put("col_1", "Sea");
//			entriesMap.put("col_2", "SQL");
//			entriesMap.put("col_3", "SkY");
//			entriesMap.put("col_4", 1);
//			xmlParserConc.insertIntoTable("table_1", entriesMap);
//			entriesMap.clear();
//			entriesMap.put("col_1", "high");
//			entriesMap.put("col_2", "CFFF");
//			entriesMap.put("col_3", "wyyyyy");
//			entriesMap.put("col_4", 10);
//			xmlParserConc.insertIntoTable("table_1", entriesMap);
//			entriesMap.clear();
//			entriesMap.put("col_1", "keyyyy");
//			entriesMap.put("col_2", "steadyyy");
//			entriesMap.put("col_3", "andddd");
//			entriesMap.put("col_4", 100);
//			xmlParserConc.insertIntoTable("table_1", entriesMap);
//			entriesMap.clear();
//
//			Map<String, Object> values = new LinkedHashMap<String, Object>();
//			values.put("col_1", "changed");
//			values.put("col_2", "changed");
//			values.put("col_3", "changed");
//			values.put("col_4", 10000);
//			int updateCount = xmlParserConc.update("table_1", values, null, null);
//			assertTrue(updateCount == 3);
//			Set<String> columns = new TreeSet<String>();
//			columns.add("col_1");
//			columns.add("col_2");
//			columns.add("col_4");
//			Map<String, Object> resExpected = new LinkedHashMap<String, Object>();
//			values.clear();
//			RecordSet recordSet = new RecordSet();
//			resExpected.put("col_1", "changed");
//			resExpected.put("col_2", "changed");
//			resExpected.put("col_4", 10000);
//			recordSet.add(new Record(resExpected));
//			resExpected = new LinkedHashMap<String, Object>();
//			resExpected.put("col_1", "changed");
//			resExpected.put("col_2", "changed");
//			resExpected.put("col_4", 10000);
//			recordSet.add(new Record(resExpected));
//			resExpected = new LinkedHashMap<String, Object>();
//			resExpected.put("col_1", "changed");
//			resExpected.put("col_2", "changed");
//			resExpected.put("col_4", 10000);
//			recordSet.add(new Record(resExpected));
//			RecordSet actualRes = xmlParserConc.select("table_1", columns, null);
//			Iterator<Record> resultSetIt = recordSet.iterator();
//			Iterator<Record> actualResultIt = actualRes.iterator();
//			while (resultSetIt.hasNext() && actualResultIt.hasNext()) {
//				assertTrue(resultSetIt.next().getRecord().equals(
//						actualResultIt.next().getRecord()));
//			}
//		} catch(Exception e) {
//			e.printStackTrace();
//			fail("Not yet implemented");
//		}
//	}
//
//	@Test
//	public void testTwo() {
//		try {
//			xmlParserConc.createDatabase("test2");
//			Map<String, Class> passMap = new LinkedHashMap<String, Class>();
//			passMap.put("col_1", String.class);
//			passMap.put("col_2", String.class);
//			passMap.put("col_3", String.class);
//			passMap.put("col_4", Integer.class);
//			xmlParserConc.createTable("table_1", passMap);
//			Map<String, Object> entriesMap = new LinkedHashMap<String, Object>();
//			entriesMap.put("col_1", "Sea");
//			entriesMap.put("col_2", "SQL");
//			entriesMap.put("col_3", "SkY");
//			entriesMap.put("col_4", 1);
//			xmlParserConc.insertIntoTable("table_1", entriesMap);
//			entriesMap.clear();
//			entriesMap.put("col_1", "high");
//			entriesMap.put("col_2", "CFFF");
//			entriesMap.put("col_3", "wyyyyy");
//			entriesMap.put("col_4", 10);
//			xmlParserConc.insertIntoTable("table_1", entriesMap);
//			entriesMap.clear();
//			entriesMap.put("col_1", "keyyyy");
//			entriesMap.put("col_2", "steadyyy");
//			entriesMap.put("col_3", "andddd");
//			entriesMap.put("col_4", 100);
//			xmlParserConc.insertIntoTable("table_1", entriesMap);
//			entriesMap.clear();
//
//			Map<String, Object> values = new LinkedHashMap<String, Object>();
//			values.put("col_1", "changed");
//			values.put("col_2", "changed");
//			values.put("col_3", "changed");
//			values.put("col_4", 10000);
//			Condition where = new Where("col_4 < 50");
//			xmlParserConc.update("table_1", values, null, where);
//
//
//			Set<String> columns = new TreeSet<String>();
//			columns.add("col_1");
//			columns.add("col_2");
//			columns.add("col_4");
//			Map<String, Object> resExpected = new LinkedHashMap<String, Object>();
//			values.clear();
//			RecordSet recordSet = new RecordSet();
//			resExpected.put("col_1", "keyyyy");
//			resExpected.put("col_2", "steadyyy");
//			resExpected.put("col_4", 100);
//			recordSet.add(new Record(resExpected));
//			Condition where2 =  new Where("col_4 < 10000");
//			RecordSet actualRes = xmlParserConc.select("table_1", columns, where2);
//			Iterator<Record> resultSetIt = recordSet.iterator();
//			Iterator<Record> actualResultIt = actualRes.iterator();
//			while (resultSetIt.hasNext() && actualResultIt.hasNext()) {
////				System.out.println(resultSetIt.next().getRecord() + "    " + actualResultIt.next().getRecord());
//				assertTrue(resultSetIt.next().getRecord().equals(
//						actualResultIt.next().getRecord()));
//			}
//		} catch(Exception e) {
//			e.printStackTrace();
//			fail("Not yet implemented");
//		}
//	}
//
//	@Test
//	public void testThree() {
//		try {
//			xmlParserConc.createDatabase("test3");
//			Map<String, Class> passMap = new LinkedHashMap<String, Class>();
//			passMap.put("col_1", String.class);
//			passMap.put("col_2", String.class);
//			passMap.put("col_3", String.class);
//			passMap.put("col_4", Integer.class);
//			xmlParserConc.createTable("table_1", passMap);
//			Map<String, Object> entriesMap = new LinkedHashMap<String, Object>();
//			entriesMap.put("col_1", "Sea");
//			entriesMap.put("col_2", "SQL");
//			entriesMap.put("col_3", "SkY");
//			entriesMap.put("col_4", 1);
//			xmlParserConc.insertIntoTable("table_1", entriesMap);
//			entriesMap.clear();
//			entriesMap.put("col_1", "high");
//			entriesMap.put("col_2", "CFFF");
//			entriesMap.put("col_3", "wyyyyy");
//			entriesMap.put("col_4", 10);
//			xmlParserConc.insertIntoTable("table_1", entriesMap);
//			entriesMap.clear();
//			entriesMap.put("col_1", "keyyyy");
//			entriesMap.put("col_2", "steadyyy");
//			entriesMap.put("col_3", "andddd");
//			entriesMap.put("col_4", 100);
//			xmlParserConc.insertIntoTable("table_1", entriesMap);
//			entriesMap.clear();
//
//			Map<String, Object> values = new LinkedHashMap<String, Object>();
//			values.put("col_1", "changed");
//			values.put("col_2", "changed");
//			values.put("col_3", "changed");
//			values.put("col_4", 10000);
//			Condition where = new Where("col_2 = 'SQL'");
//			xmlParserConc.update("table_1", values, null, where);
//
//
//			Set<String> columns = new TreeSet<String>();
//			columns.add("col_1");
//			columns.add("col_2");
//			columns.add("col_3");
//			columns.add("col_4");
//			Map<String, Object> resExpected = new LinkedHashMap<String, Object>();
//			values.clear();
//			RecordSet recordSet = new RecordSet();
//			resExpected.put("col_1", "changed");
//			resExpected.put("col_2", "changed");
//			resExpected.put("col_3", "changed");
//			resExpected.put("col_4", 10000);
//			recordSet.add(new Record(resExpected));
//			resExpected = new LinkedHashMap<String, Object>();
//			resExpected.put("col_1", "high");
//			resExpected.put("col_2", "CFFF");
//			resExpected.put("col_3", "wyyyyy");
//			resExpected.put("col_4", 10);
//			recordSet.add(new Record(resExpected));
//			resExpected = new LinkedHashMap<String, Object>();
//			resExpected.put("col_1", "keyyyy");
//			resExpected.put("col_2", "steadyyy");
//			resExpected.put("col_3", "andddd");
//			resExpected.put("col_4", 100);
//			recordSet.add(new Record(resExpected));
//			RecordSet actualRes = xmlParserConc.select("table_1", columns, null);
//			Iterator<Record> resultSetIt = recordSet.iterator();
//			Iterator<Record> actualResultIt = actualRes.iterator();
//			while (resultSetIt.hasNext() && actualResultIt.hasNext()) {
////				System.out.println(resultSetIt.next().getRecord() + "    " + actualResultIt.next().getRecord());
//				assertTrue(resultSetIt.next().getRecord().equals(
//						actualResultIt.next().getRecord()));
//			}
//		} catch(Exception e) {
//			e.printStackTrace();
//			fail("Not yet implemented");
//		}
//	}
//
//	@Test
//	public void testFour() {
//		try {
//			xmlParserConc.createDatabase("test4");
//			Map<String, Class> passMap = new LinkedHashMap<String, Class>();
//			passMap.put("col_1", String.class);
//			passMap.put("col_2", String.class);
//			passMap.put("col_3", String.class);
//			passMap.put("col_4", Integer.class);
//			xmlParserConc.createTable("table_1", passMap);
//			Map<String, Object> entriesMap = new LinkedHashMap<String, Object>();
//			entriesMap.put("col_1", "Sea");
//			entriesMap.put("col_2", "SQL");
//			entriesMap.put("col_3", "SkY");
//			entriesMap.put("col_4", 1);
//			xmlParserConc.insertIntoTable("table_1", entriesMap);
//			entriesMap.clear();
//			entriesMap.put("col_1", "high");
//			entriesMap.put("col_2", "CFFF");
//			entriesMap.put("col_3", "wyyyyy");
//			entriesMap.put("col_4", 10);
//			xmlParserConc.insertIntoTable("table_1", entriesMap);
//			entriesMap.clear();
//			entriesMap.put("col_1", "keyyyy");
//			entriesMap.put("col_2", "steadyyy");
//			entriesMap.put("col_3", "andddd");
//			entriesMap.put("col_4", 100);
//			xmlParserConc.insertIntoTable("table_1", entriesMap);
//			entriesMap.clear();
//
//			Map<String, Object> values = new LinkedHashMap<String, Object>();
//			values.put("col_1", "changed");
//			values.put("col_2", "changed");
//			values.put("col_3", "changed");
//			values.put("col_4", 10000);
//			Condition where = new Where("        TRUE             ");
//			xmlParserConc.update("table_1", values, null, where);
//
//
//			Set<String> columns = new TreeSet<String>();
//			columns.add("col_1");
//			columns.add("col_2");
//			columns.add("col_3");
//			columns.add("col_4");
//			Map<String, Object> resExpected = new LinkedHashMap<String, Object>();
//			values.clear();
//			RecordSet recordSet = new RecordSet();
//			resExpected.put("col_1", "changed");
//			resExpected.put("col_2", "changed");
//			resExpected.put("col_3", "changed");
//			resExpected.put("col_4", 10000);
//			recordSet.add(new Record(resExpected));
//			resExpected = new LinkedHashMap<String, Object>();
//			resExpected.put("col_1", "changed");
//			resExpected.put("col_2", "changed");
//			resExpected.put("col_3", "changed");
//			resExpected.put("col_4", 10000);
//			recordSet.add(new Record(resExpected));
//			resExpected = new LinkedHashMap<String, Object>();
//			resExpected.put("col_1", "changed");
//			resExpected.put("col_2", "changed");
//			resExpected.put("col_3", "changed");
//			resExpected.put("col_4", 10000);
//			recordSet.add(new Record(resExpected));
//			RecordSet actualRes = xmlParserConc.select("table_1", columns, null);
//			Iterator<Record> resultSetIt = recordSet.iterator();
//			Iterator<Record> actualResultIt = actualRes.iterator();
//			while (resultSetIt.hasNext() && actualResultIt.hasNext()) {
////				System.out.println(resultSetIt.next().getRecord() + "    " + actualResultIt.next().getRecord());
//				assertTrue(resultSetIt.next().getRecord().equals(
//						actualResultIt.next().getRecord()));
//			}
//		} catch(Exception e) {
//			e.printStackTrace();
//			fail("Not yet implemented");
//		}
//	}
//
//	@Test
//	public void testFive() {
//		try {
//			xmlParserConc.createDatabase("test5");
//			Map<String, Class> passMap = new LinkedHashMap<String, Class>();
//			passMap.put("col_1", String.class);
//			passMap.put("col_2", String.class);
//			passMap.put("col_3", String.class);
//			passMap.put("col_4", Integer.class);
//			xmlParserConc.createTable("table_1", passMap);
//			Map<String, Object> entriesMap = new LinkedHashMap<String, Object>();
//			entriesMap.put("col_1", "Sea");
//			entriesMap.put("col_2", "SQL");
//			entriesMap.put("col_3", "SkY");
//			entriesMap.put("col_4", 1);
//			xmlParserConc.insertIntoTable("table_1", entriesMap);
//			entriesMap.clear();
//			entriesMap.put("col_1", "high");
//			entriesMap.put("col_2", "CFFF");
//			entriesMap.put("col_3", "wyyyyy");
//			entriesMap.put("col_4", 10);
//			xmlParserConc.insertIntoTable("table_1", entriesMap);
//			entriesMap.clear();
//			entriesMap.put("col_1", "keyyyy");
//			entriesMap.put("col_2", "steadyyy");
//			entriesMap.put("col_3", "andddd");
//			entriesMap.put("col_4", 100);
//			xmlParserConc.insertIntoTable("table_1", entriesMap);
//			entriesMap.clear();
//
//			Map<String, Object> values = new LinkedHashMap<String, Object>();
//			values.put("col_1", "changed");
//			values.put("col_2", "changed");
//			values.put("col_3", "changed");
//			values.put("col_4", 10000);
//			Condition where = new Where("((col_1  !=                'high')      AND (col_1!=     'keyyyy'         ))");
//			xmlParserConc.update("table_1", values, null, where);
//
//
//			Set<String> columns = new TreeSet<String>();
//			columns.add("col_1");
//			columns.add("col_2");
//			columns.add("col_3");
//			columns.add("col_4");
//			Map<String, Object> resExpected = new LinkedHashMap<String, Object>();
//			values.clear();
//			RecordSet recordSet = new RecordSet();
//			resExpected.put("col_1", "changed");
//			resExpected.put("col_2", "changed");
//			resExpected.put("col_3", "changed");
//			resExpected.put("col_4", 10000);
//			recordSet.add(new Record(resExpected));
//			resExpected = new LinkedHashMap<String, Object>();
//			resExpected.put("col_1", "high");
//			resExpected.put("col_2", "CFFF");
//			resExpected.put("col_3", "wyyyyy");
//			resExpected.put("col_4", 10);
//			recordSet.add(new Record(resExpected));
//			resExpected = new LinkedHashMap<String, Object>();
//			resExpected.put("col_1", "keyyyy");
//			resExpected.put("col_2", "steadyyy");
//			resExpected.put("col_3", "andddd");
//			resExpected.put("col_4", 100);
//			recordSet.add(new Record(resExpected));
//			RecordSet actualRes = xmlParserConc.select("table_1", columns, null);
//			Iterator<Record> resultSetIt = recordSet.iterator();
//			Iterator<Record> actualResultIt = actualRes.iterator();
//			while (resultSetIt.hasNext() && actualResultIt.hasNext()) {
////				System.out.println(resultSetIt.next().getRecord() + "    " + actualResultIt.next().getRecord());
//				assertTrue(resultSetIt.next().getRecord().equals(
//						actualResultIt.next().getRecord()));
//			}
//		} catch(Exception e) {
//			e.printStackTrace();
//			fail("Not yet implemented");
//		}
//	}
//
//	@Test
//	public void testSix() {
//		try {
//			xmlParserConc.createDatabase("test6");
//			Map<String, Class> passMap = new LinkedHashMap<String, Class>();
//			passMap.put("col_1", String.class);
//			passMap.put("col_2", String.class);
//			passMap.put("col_3", String.class);
//			passMap.put("col_4", Integer.class);
//			xmlParserConc.createTable("table_1", passMap);
//			Map<String, Object> entriesMap = new LinkedHashMap<String, Object>();
//			entriesMap.put("col_1", "Sea");
//			entriesMap.put("col_2", "SQL");
//			entriesMap.put("col_3", "SkY");
//			entriesMap.put("col_4", 1);
//			xmlParserConc.insertIntoTable("table_1", entriesMap);
//			entriesMap.clear();
//			entriesMap.put("col_1", "high");
//			entriesMap.put("col_2", "CFFF");
//			entriesMap.put("col_3", "wyyyyy");
//			entriesMap.put("col_4", 10);
//			xmlParserConc.insertIntoTable("table_1", entriesMap);
//			entriesMap.clear();
//			entriesMap.put("col_1", "keyyyy");
//			entriesMap.put("col_2", "steadyyy");
//			entriesMap.put("col_3", "andddd");
//			entriesMap.put("col_4", 100);
//			xmlParserConc.insertIntoTable("table_1", entriesMap);
//			entriesMap.clear();
//
//			Map<String, String> columnss = new LinkedHashMap<String, String>();
//			columnss.put("col_1", "col_2");
//			columnss.put("col_2", "col_3");
////			columns.put("col_3", "changed");
////			columns.put("col_4", 10000);
//			Condition where = new Where("col_1 = 'high'");
//			xmlParserConc.update("table_1", null, columnss, where);
//
//
//			Set<String> columns = new TreeSet<String>();
//			columns.add("col_1");
//			columns.add("col_2");
//			columns.add("col_3");
//			columns.add("col_4");
//			Map<String, Object> resExpected = new LinkedHashMap<String, Object>();
////			values.clear();
//			RecordSet recordSet = new RecordSet();
//			resExpected.put("col_1", "Sea");
//			resExpected.put("col_2", "SQL");
//			resExpected.put("col_3", "SkY");
//			resExpected.put("col_4", 1);
//			recordSet.add(new Record(resExpected));
//			resExpected = new LinkedHashMap<String, Object>();
//			resExpected.put("col_1", "CFFF");
//			resExpected.put("col_2", "wyyyyy");
//			resExpected.put("col_3", "wyyyyy");
//			resExpected.put("col_4", 10);
//			recordSet.add(new Record(resExpected));
//			resExpected = new LinkedHashMap<String, Object>();
//			resExpected.put("col_1", "keyyyy");
//			resExpected.put("col_2", "steadyyy");
//			resExpected.put("col_3", "andddd");
//			resExpected.put("col_4", 100);
//			recordSet.add(new Record(resExpected));
//			RecordSet actualRes = xmlParserConc.select("table_1", columns, null);
//			Iterator<Record> resultSetIt = recordSet.iterator();
//			Iterator<Record> actualResultIt = actualRes.iterator();
//			while (resultSetIt.hasNext() && actualResultIt.hasNext()) {
////				System.out.println(resultSetIt.next().getRecord() + "    " + actualResultIt.next().getRecord());
//				assertTrue(resultSetIt.next().getRecord().equals(
//						actualResultIt.next().getRecord()));
//			}
//		} catch(Exception e) {
//			e.printStackTrace();
//			fail("Not yet implemented");
//		}
//	}
//}
