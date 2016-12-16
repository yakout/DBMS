package dbms.test.JSONParserTesting;

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

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class JSONTestingUpdate {

    private final BackendController jsonParser = BackendController.getInstance();

    @Test
    public void testOne() {

        BackendParserFactory.getFactory().setCurrentParser("alt");
        try {
            jsonParser.createDatabase("test1");
            LinkedHashMap<String, Class<? extends DBDatatype>> passMap = new LinkedHashMap<>();
            passMap.put("col_1", DBString.class);
            passMap.put("col_2", DBString.class);
            passMap.put("col_3", DBString.class);
            passMap.put("col_4", DBInteger.class);
            jsonParser.createTable("table_1", passMap);
            LinkedHashMap<String, DBDatatype> entriesMap = new LinkedHashMap<>();
            entriesMap.put("col_1", DatatypeFactory.convertToDataType("Sea"));
            entriesMap.put("col_2", DatatypeFactory.convertToDataType("SQL"));
            entriesMap.put("col_3", DatatypeFactory.convertToDataType("SkY"));
            entriesMap.put("col_4", DatatypeFactory.convertToDataType(1));
            jsonParser.insertIntoTable("table_1", entriesMap);
            entriesMap.clear();
            entriesMap.put("col_1", DatatypeFactory.convertToDataType("high"));
            entriesMap.put("col_2", DatatypeFactory.convertToDataType("CFFF"));
            entriesMap.put("col_3", DatatypeFactory.convertToDataType("wyyyyy"));
            entriesMap.put("col_4", DatatypeFactory.convertToDataType(10));
            jsonParser.insertIntoTable("table_1", entriesMap);
            entriesMap.clear();
            entriesMap.put("col_1", DatatypeFactory.convertToDataType("keyyyy"));
            entriesMap.put("col_2", DatatypeFactory.convertToDataType("steadyyy"));
            entriesMap.put("col_3", DatatypeFactory.convertToDataType("andddd"));
            entriesMap.put("col_4", DatatypeFactory.convertToDataType(100));
            jsonParser.insertIntoTable("table_1", entriesMap);
            entriesMap.clear();

            LinkedHashMap<String, DBDatatype> values = new LinkedHashMap<>();
            values.put("col_1", DatatypeFactory.convertToDataType("changed"));
            values.put("col_2", DatatypeFactory.convertToDataType("changed"));
            values.put("col_3", DatatypeFactory.convertToDataType("changed"));
            values.put("col_4", DatatypeFactory.convertToDataType(10000));
            int updateCount = jsonParser.update("table_1", values, null, null);
            assertTrue(updateCount == 3);
            Set<String> columns = new TreeSet<>();
            columns.add("col_1");
            columns.add("col_2");
            columns.add("col_4");
            LinkedHashMap<String, DBDatatype> resExpected = new LinkedHashMap<>();
            values.clear();
            RecordSet recordSet = new RecordSet();
            resExpected.put("col_1", DatatypeFactory.convertToDataType("changed"));
            resExpected.put("col_2", DatatypeFactory.convertToDataType("changed"));
            resExpected.put("col_4", DatatypeFactory.convertToDataType(10000));
            recordSet.add(new Record(resExpected));
            resExpected = new LinkedHashMap<>();
            resExpected.put("col_1", DatatypeFactory.convertToDataType("changed"));
            resExpected.put("col_2", DatatypeFactory.convertToDataType("changed"));
            resExpected.put("col_4", DatatypeFactory.convertToDataType(10000));
            recordSet.add(new Record(resExpected));
            resExpected = new LinkedHashMap<>();
            resExpected.put("col_1", DatatypeFactory.convertToDataType("changed"));
            resExpected.put("col_2", DatatypeFactory.convertToDataType("changed"));
            resExpected.put("col_4", DatatypeFactory.convertToDataType(10000));
            recordSet.add(new Record(resExpected));
            RecordSet actualRes = jsonParser.select("table_1", columns, null);
            Iterator<Record> resultSetIt = recordSet.iterator();
            Iterator<Record> actualResultIt = actualRes.iterator();
            while (resultSetIt.hasNext() && actualResultIt.hasNext()) {
                assertTrue(resultSetIt.next().getRecord().equals(
                        actualResultIt.next().getRecord()));
            }
        } catch (Exception e) {
            e.printStackTrace();
            fail("Not yet implemented");
        }
    }

    @Test
    public void testTwo() {

        BackendParserFactory.getFactory().setCurrentParser("alt");
        try {
            jsonParser.createDatabase("test2");
            LinkedHashMap<String, Class<? extends DBDatatype>> passMap = new LinkedHashMap<>();
            passMap.put("col_1", DBString.class);
            passMap.put("col_2", DBString.class);
            passMap.put("col_3", DBString.class);
            passMap.put("col_4", DBInteger.class);
            jsonParser.createTable("table_1", passMap);
            LinkedHashMap<String, DBDatatype> entriesMap = new LinkedHashMap<>();
            entriesMap.put("col_1", DatatypeFactory.convertToDataType("Sea"));
            entriesMap.put("col_2", DatatypeFactory.convertToDataType("SQL"));
            entriesMap.put("col_3", DatatypeFactory.convertToDataType("SkY"));
            entriesMap.put("col_4", DatatypeFactory.convertToDataType(1));
            jsonParser.insertIntoTable("table_1", entriesMap);
            entriesMap.clear();
            entriesMap.put("col_1", DatatypeFactory.convertToDataType("high"));
            entriesMap.put("col_2", DatatypeFactory.convertToDataType("CFFF"));
            entriesMap.put("col_3", DatatypeFactory.convertToDataType("wyyyyy"));
            entriesMap.put("col_4", DatatypeFactory.convertToDataType(10));
            jsonParser.insertIntoTable("table_1", entriesMap);
            entriesMap.clear();
            entriesMap.put("col_1", DatatypeFactory.convertToDataType("keyyyy"));
            entriesMap.put("col_2", DatatypeFactory.convertToDataType("steadyyy"));
            entriesMap.put("col_3", DatatypeFactory.convertToDataType("andddd"));
            entriesMap.put("col_4", DatatypeFactory.convertToDataType(100));
            jsonParser.insertIntoTable("table_1", entriesMap);
            entriesMap.clear();

            LinkedHashMap<String, DBDatatype> values = new LinkedHashMap<>();
            values.put("col_1", DatatypeFactory.convertToDataType("changed"));
            values.put("col_2", DatatypeFactory.convertToDataType("changed"));
            values.put("col_3", DatatypeFactory.convertToDataType("changed"));
            values.put("col_4", DatatypeFactory.convertToDataType(10000));
            Condition where = new Where("col_4 < 50");
            jsonParser.update("table_1", values, null, where);

            Set<String> columns = new TreeSet<>();
            columns.add("col_1");
            columns.add("col_2");
            columns.add("col_4");
            LinkedHashMap<String, DBDatatype> resExpected = new LinkedHashMap<>();
            values.clear();
            RecordSet recordSet = new RecordSet();
            resExpected.put("col_1", DatatypeFactory.convertToDataType("keyyyy"));
            resExpected.put("col_2", DatatypeFactory.convertToDataType("steadyyy"));
            resExpected.put("col_4", DatatypeFactory.convertToDataType(100));
            recordSet.add(new Record(resExpected));
            Condition where2 = new Where("col_4 < 10000");
            RecordSet actualRes = jsonParser.select("table_1", columns, where2);
            Iterator<Record> resultSetIt = recordSet.iterator();
            Iterator<Record> actualResultIt = actualRes.iterator();
            while (resultSetIt.hasNext() && actualResultIt.hasNext()) {
                assertTrue(resultSetIt.next().getRecord().equals(
                        actualResultIt.next().getRecord()));
            }
        } catch (Exception e) {
            e.printStackTrace();
            fail("Not yet implemented");
        }
    }

    @Test
    public void testThree() {

        BackendParserFactory.getFactory().setCurrentParser("alt");
        try {
            jsonParser.createDatabase("test3");
            LinkedHashMap<String, Class<? extends DBDatatype>> passMap = new LinkedHashMap<>();
            passMap.put("col_1", DBString.class);
            passMap.put("col_2", DBString.class);
            passMap.put("col_3", DBString.class);
            passMap.put("col_4", DBInteger.class);
            jsonParser.createTable("table_1", passMap);
            LinkedHashMap<String, DBDatatype> entriesMap = new LinkedHashMap<>();
            entriesMap.put("col_1", DatatypeFactory.convertToDataType("Sea"));
            entriesMap.put("col_2", DatatypeFactory.convertToDataType("SQL"));
            entriesMap.put("col_3", DatatypeFactory.convertToDataType("SkY"));
            entriesMap.put("col_4", DatatypeFactory.convertToDataType(1));
            jsonParser.insertIntoTable("table_1", entriesMap);
            entriesMap.clear();
            entriesMap.put("col_1", DatatypeFactory.convertToDataType("high"));
            entriesMap.put("col_2", DatatypeFactory.convertToDataType("CFFF"));
            entriesMap.put("col_3", DatatypeFactory.convertToDataType("wyyyyy"));
            entriesMap.put("col_4", DatatypeFactory.convertToDataType(10));
            jsonParser.insertIntoTable("table_1", entriesMap);
            entriesMap.clear();
            entriesMap.put("col_1", DatatypeFactory.convertToDataType("keyyyy"));
            entriesMap.put("col_2", DatatypeFactory.convertToDataType("steadyyy"));
            entriesMap.put("col_3", DatatypeFactory.convertToDataType("andddd"));
            entriesMap.put("col_4", DatatypeFactory.convertToDataType(100));
            jsonParser.insertIntoTable("table_1", entriesMap);
            entriesMap.clear();

            LinkedHashMap<String, DBDatatype> values = new LinkedHashMap<>();
            values.put("col_1", DatatypeFactory.convertToDataType("changed"));
            values.put("col_2", DatatypeFactory.convertToDataType("changed"));
            values.put("col_3", DatatypeFactory.convertToDataType("changed"));
            values.put("col_4", DatatypeFactory.convertToDataType(10000));
            Condition where = new Where("col_2 = 'SQL'");
            jsonParser.update("table_1", values, null, where);


            Set<String> columns = new TreeSet<>();
            columns.add("col_1");
            columns.add("col_2");
            columns.add("col_3");
            columns.add("col_4");
            LinkedHashMap<String, DBDatatype> resExpected = new LinkedHashMap<>();
            values.clear();
            RecordSet recordSet = new RecordSet();
            resExpected.put("col_1", DatatypeFactory.convertToDataType("changed"));
            resExpected.put("col_2", DatatypeFactory.convertToDataType("changed"));
            resExpected.put("col_3", DatatypeFactory.convertToDataType("changed"));
            resExpected.put("col_4", DatatypeFactory.convertToDataType(10000));
            recordSet.add(new Record(resExpected));
            resExpected = new LinkedHashMap<>();
            resExpected.put("col_1", DatatypeFactory.convertToDataType("high"));
            resExpected.put("col_2", DatatypeFactory.convertToDataType("CFFF"));
            resExpected.put("col_3", DatatypeFactory.convertToDataType("wyyyyy"));
            resExpected.put("col_4", DatatypeFactory.convertToDataType(10));
            recordSet.add(new Record(resExpected));
            resExpected = new LinkedHashMap<>();
            resExpected.put("col_1", DatatypeFactory.convertToDataType("keyyyy"));
            resExpected.put("col_2", DatatypeFactory.convertToDataType("steadyyy"));
            resExpected.put("col_3", DatatypeFactory.convertToDataType("andddd"));
            resExpected.put("col_4", DatatypeFactory.convertToDataType(100));
            recordSet.add(new Record(resExpected));
            RecordSet actualRes = jsonParser.select("table_1", columns, null);
            Iterator<Record> resultSetIt = recordSet.iterator();
            Iterator<Record> actualResultIt = actualRes.iterator();
            while (resultSetIt.hasNext() && actualResultIt.hasNext()) {
                assertTrue(resultSetIt.next().getRecord().equals(
                        actualResultIt.next().getRecord()));
            }
        } catch (Exception e) {
            e.printStackTrace();
            fail("Not yet implemented");
        }
    }

    @Test
    public void testFour() {

        BackendParserFactory.getFactory().setCurrentParser("alt");
        try {
            jsonParser.createDatabase("test4");
            LinkedHashMap<String, Class<? extends DBDatatype>> passMap = new LinkedHashMap<>();
            passMap.put("col_1", DBString.class);
            passMap.put("col_2", DBString.class);
            passMap.put("col_3", DBString.class);
            passMap.put("col_4", DBInteger.class);
            jsonParser.createTable("table_1", passMap);
            LinkedHashMap<String, DBDatatype> entriesMap = new LinkedHashMap<>();
            entriesMap.put("col_1", DatatypeFactory.convertToDataType("Sea"));
            entriesMap.put("col_2", DatatypeFactory.convertToDataType("SQL"));
            entriesMap.put("col_3", DatatypeFactory.convertToDataType("SkY"));
            entriesMap.put("col_4", DatatypeFactory.convertToDataType(1));
            jsonParser.insertIntoTable("table_1", entriesMap);
            entriesMap.clear();
            entriesMap.put("col_1", DatatypeFactory.convertToDataType("high"));
            entriesMap.put("col_2", DatatypeFactory.convertToDataType("CFFF"));
            entriesMap.put("col_3", DatatypeFactory.convertToDataType("wyyyyy"));
            entriesMap.put("col_4", DatatypeFactory.convertToDataType(10));
            jsonParser.insertIntoTable("table_1", entriesMap);
            entriesMap.clear();
            entriesMap.put("col_1", DatatypeFactory.convertToDataType("keyyyy"));
            entriesMap.put("col_2", DatatypeFactory.convertToDataType("steadyyy"));
            entriesMap.put("col_3", DatatypeFactory.convertToDataType("andddd"));
            entriesMap.put("col_4", DatatypeFactory.convertToDataType(100));
            jsonParser.insertIntoTable("table_1", entriesMap);
            entriesMap.clear();

            LinkedHashMap<String, DBDatatype> values = new LinkedHashMap<>();
            values.put("col_1", DatatypeFactory.convertToDataType("changed"));
            values.put("col_2", DatatypeFactory.convertToDataType("changed"));
            values.put("col_3", DatatypeFactory.convertToDataType("changed"));
            values.put("col_4", DatatypeFactory.convertToDataType(10000));
            Condition where = new Where("        TRUE             ");
            jsonParser.update("table_1", values, null, where);


            Set<String> columns = new TreeSet<>();
            columns.add("col_1");
            columns.add("col_2");
            columns.add("col_3");
            columns.add("col_4");
            LinkedHashMap<String, DBDatatype> resExpected = new LinkedHashMap<>();
            values.clear();
            RecordSet recordSet = new RecordSet();
            resExpected.put("col_1", DatatypeFactory.convertToDataType("changed"));
            resExpected.put("col_2", DatatypeFactory.convertToDataType("changed"));
            resExpected.put("col_3", DatatypeFactory.convertToDataType("changed"));
            resExpected.put("col_4", DatatypeFactory.convertToDataType(10000));
            recordSet.add(new Record(resExpected));
            resExpected = new LinkedHashMap<>();
            resExpected.put("col_1", DatatypeFactory.convertToDataType("changed"));
            resExpected.put("col_2", DatatypeFactory.convertToDataType("changed"));
            resExpected.put("col_3", DatatypeFactory.convertToDataType("changed"));
            resExpected.put("col_4", DatatypeFactory.convertToDataType(10000));
            recordSet.add(new Record(resExpected));
            resExpected = new LinkedHashMap<>();
            resExpected.put("col_1", DatatypeFactory.convertToDataType("changed"));
            resExpected.put("col_2", DatatypeFactory.convertToDataType("changed"));
            resExpected.put("col_3", DatatypeFactory.convertToDataType("changed"));
            resExpected.put("col_4", DatatypeFactory.convertToDataType(10000));
            recordSet.add(new Record(resExpected));
            RecordSet actualRes = jsonParser.select("table_1", columns, null);
            Iterator<Record> resultSetIt = recordSet.iterator();
            Iterator<Record> actualResultIt = actualRes.iterator();
            while (resultSetIt.hasNext() && actualResultIt.hasNext()) {
                assertTrue(resultSetIt.next().getRecord().equals(
                        actualResultIt.next().getRecord()));
            }
        } catch (Exception e) {
            e.printStackTrace();
            fail("Not yet implemented");
        }
    }

    @Test
    public void testFive() {

        BackendParserFactory.getFactory().setCurrentParser("alt");
        try {
            jsonParser.createDatabase("test5");
            LinkedHashMap<String, Class<? extends DBDatatype>> passMap = new LinkedHashMap<>();
            passMap.put("col_1", DBString.class);
            passMap.put("col_2", DBString.class);
            passMap.put("col_3", DBString.class);
            passMap.put("col_4", DBInteger.class);
            jsonParser.createTable("table_1", passMap);
            LinkedHashMap<String, DBDatatype> entriesMap = new LinkedHashMap<>();
            entriesMap.put("col_1", DatatypeFactory.convertToDataType("Sea"));
            entriesMap.put("col_2", DatatypeFactory.convertToDataType("SQL"));
            entriesMap.put("col_3", DatatypeFactory.convertToDataType("SkY"));
            entriesMap.put("col_4", DatatypeFactory.convertToDataType(1));
            jsonParser.insertIntoTable("table_1", entriesMap);
            entriesMap.clear();
            entriesMap.put("col_1", DatatypeFactory.convertToDataType("high"));
            entriesMap.put("col_2", DatatypeFactory.convertToDataType("CFFF"));
            entriesMap.put("col_3", DatatypeFactory.convertToDataType("wyyyyy"));
            entriesMap.put("col_4", DatatypeFactory.convertToDataType(10));
            jsonParser.insertIntoTable("table_1", entriesMap);
            entriesMap.clear();
            entriesMap.put("col_1", DatatypeFactory.convertToDataType("keyyyy"));
            entriesMap.put("col_2", DatatypeFactory.convertToDataType("steadyyy"));
            entriesMap.put("col_3", DatatypeFactory.convertToDataType("andddd"));
            entriesMap.put("col_4", DatatypeFactory.convertToDataType(100));
            jsonParser.insertIntoTable("table_1", entriesMap);
            entriesMap.clear();

            LinkedHashMap<String, DBDatatype> values = new LinkedHashMap<>();
            values.put("col_1", DatatypeFactory.convertToDataType("changed"));
            values.put("col_2", DatatypeFactory.convertToDataType("changed"));
            values.put("col_3", DatatypeFactory.convertToDataType("changed"));
            values.put("col_4", DatatypeFactory.convertToDataType(10000));
            Condition where = new Where("((col_1  !=                'high')      AND (col_1!=     'keyyyy'         ))");
            jsonParser.update("table_1", values, null, where);


            Set<String> columns = new TreeSet<>();
            columns.add("col_1");
            columns.add("col_2");
            columns.add("col_3");
            columns.add("col_4");
            LinkedHashMap<String, DBDatatype> resExpected = new LinkedHashMap<>();
            values.clear();
            RecordSet recordSet = new RecordSet();
            resExpected.put("col_1", DatatypeFactory.convertToDataType("changed"));
            resExpected.put("col_2", DatatypeFactory.convertToDataType("changed"));
            resExpected.put("col_3", DatatypeFactory.convertToDataType("changed"));
            resExpected.put("col_4", DatatypeFactory.convertToDataType(10000));
            recordSet.add(new Record(resExpected));
            resExpected = new LinkedHashMap<>();
            resExpected.put("col_1", DatatypeFactory.convertToDataType("high"));
            resExpected.put("col_2", DatatypeFactory.convertToDataType("CFFF"));
            resExpected.put("col_3", DatatypeFactory.convertToDataType("wyyyyy"));
            resExpected.put("col_4", DatatypeFactory.convertToDataType(10));
            recordSet.add(new Record(resExpected));
            resExpected = new LinkedHashMap<>();
            resExpected.put("col_1", DatatypeFactory.convertToDataType("keyyyy"));
            resExpected.put("col_2", DatatypeFactory.convertToDataType("steadyyy"));
            resExpected.put("col_3", DatatypeFactory.convertToDataType("andddd"));
            resExpected.put("col_4", DatatypeFactory.convertToDataType(100));
            recordSet.add(new Record(resExpected));
            RecordSet actualRes = jsonParser.select("table_1", columns, null);
            Iterator<Record> resultSetIt = recordSet.iterator();
            Iterator<Record> actualResultIt = actualRes.iterator();
            while (resultSetIt.hasNext() && actualResultIt.hasNext()) {
                assertTrue(resultSetIt.next().getRecord().equals(
                        actualResultIt.next().getRecord()));
            }
        } catch (Exception e) {
            e.printStackTrace();
            fail("Not yet implemented");
        }
    }


    @Test
    public void testSix() {

        BackendParserFactory.getFactory().setCurrentParser("alt");
        try {
            jsonParser.createDatabase("test6");
            LinkedHashMap<String, Class<? extends DBDatatype>> passMap = new LinkedHashMap<>();
            passMap.put("col_1", DBString.class);
            passMap.put("col_2", DBString.class);
            passMap.put("col_3", DBString.class);
            passMap.put("col_4", DBInteger.class);
            jsonParser.createTable("table_1", passMap);
            LinkedHashMap<String, DBDatatype> entriesMap = new LinkedHashMap<>();
            entriesMap.put("col_1", DatatypeFactory.convertToDataType("Sea"));
            entriesMap.put("col_2", DatatypeFactory.convertToDataType("SQL"));
            entriesMap.put("col_3", DatatypeFactory.convertToDataType("SkY"));
            entriesMap.put("col_4", DatatypeFactory.convertToDataType(1));
            jsonParser.insertIntoTable("table_1", entriesMap);
            entriesMap.clear();
            entriesMap.put("col_1", DatatypeFactory.convertToDataType("high"));
            entriesMap.put("col_2", DatatypeFactory.convertToDataType("CFFF"));
            entriesMap.put("col_3", DatatypeFactory.convertToDataType("wyyyyy"));
            entriesMap.put("col_4", DatatypeFactory.convertToDataType(10));
            jsonParser.insertIntoTable("table_1", entriesMap);
            entriesMap.clear();
            entriesMap.put("col_1", DatatypeFactory.convertToDataType("keyyyy"));
            entriesMap.put("col_2", DatatypeFactory.convertToDataType("steadyyy"));
            entriesMap.put("col_3", DatatypeFactory.convertToDataType("andddd"));
            entriesMap.put("col_4", DatatypeFactory.convertToDataType(100));
            jsonParser.insertIntoTable("table_1", entriesMap);
            entriesMap.clear();

            LinkedHashMap<String, String> columnss = new LinkedHashMap<>();
            columnss.put("col_1", "col_2");
            columnss.put("col_2", "col_3");

            Condition where = new Where("col_1 = 'high'");
            jsonParser.update("table_1", null, columnss, where);

            Set<String> columns = new TreeSet<>();
            columns.add("col_1");
            columns.add("col_2");
            columns.add("col_3");
            columns.add("col_4");
            LinkedHashMap<String, DBDatatype> resExpected = new LinkedHashMap<>();
            RecordSet recordSet = new RecordSet();
            resExpected.put("col_1", DatatypeFactory.convertToDataType("Sea"));
            resExpected.put("col_2", DatatypeFactory.convertToDataType("SQL"));
            resExpected.put("col_3", DatatypeFactory.convertToDataType("SkY"));
            resExpected.put("col_4", DatatypeFactory.convertToDataType(1));
            recordSet.add(new Record(resExpected));
            resExpected = new LinkedHashMap<>();
            resExpected.put("col_1", DatatypeFactory.convertToDataType("CFFF"));
            resExpected.put("col_2", DatatypeFactory.convertToDataType("wyyyyy"));
            resExpected.put("col_3", DatatypeFactory.convertToDataType("wyyyyy"));
            resExpected.put("col_4", DatatypeFactory.convertToDataType(10));
            recordSet.add(new Record(resExpected));
            resExpected = new LinkedHashMap<>();
            resExpected.put("col_1", DatatypeFactory.convertToDataType("keyyyy"));
            resExpected.put("col_2", DatatypeFactory.convertToDataType("steadyyy"));
            resExpected.put("col_3", DatatypeFactory.convertToDataType("andddd"));
            resExpected.put("col_4", DatatypeFactory.convertToDataType(100));
            recordSet.add(new Record(resExpected));
            RecordSet actualRes = jsonParser.select("table_1", columns, null);
            Iterator<Record> resultSetIt = recordSet.iterator();
            Iterator<Record> actualResultIt = actualRes.iterator();
            while (resultSetIt.hasNext() && actualResultIt.hasNext()) {
                assertTrue(resultSetIt.next().getRecord().equals(
                        actualResultIt.next().getRecord()));
            }
        } catch (Exception e) {
            e.printStackTrace();
            fail("Not yet implemented");
        }
    }

    // TODO Testing
    // Update with new data types Date, floating-point numbers.
    // Update with complex and and or expressions.

}
