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

public class JSONTestingDelete {

    private final BackendController jsonParser = BackendController.getInstance();

    @Test
    public void testOne() {

        BackendParserFactory.getFactory().setCurrentParser("json");
        try {
            jsonParser.createDatabase("database_delete_1");

            LinkedHashMap<String, Class<? extends DBDatatype>> passMap = new LinkedHashMap<>();
            passMap.put("column_1", DBInteger.class);
            passMap.put("column_2", DBString.class);

            jsonParser.createTable("table_name", passMap);

            LinkedHashMap<String, DBDatatype> entriesMap = new LinkedHashMap<>();
            entriesMap.put("column_1", DatatypeFactory.convertToDataType(550));
            entriesMap.put("column_2", DatatypeFactory.convertToDataType("KHalED"));
            jsonParser.insertIntoTable("table_name", entriesMap);
            entriesMap = new LinkedHashMap<>();
            entriesMap.put("column_1", DatatypeFactory.convertToDataType(200));
            entriesMap.put("column_2", DatatypeFactory.convertToDataType("waLiD"));
            jsonParser.insertIntoTable("table_name", entriesMap);
            entriesMap = new LinkedHashMap<>();
            entriesMap.put("column_1", DatatypeFactory.convertToDataType(7500));
            entriesMap.put("column_2", DatatypeFactory.convertToDataType("AnAs"));
            jsonParser.insertIntoTable("table_name", entriesMap);
            entriesMap = new LinkedHashMap<>();
            entriesMap.put("column_1", DatatypeFactory.convertToDataType(852));
            entriesMap.put("column_2", DatatypeFactory.convertToDataType("YaKoUt"));
            jsonParser.insertIntoTable("table_name", entriesMap);
            entriesMap = new LinkedHashMap<>();
            entriesMap.put("column_1", DatatypeFactory.convertToDataType(189));
            entriesMap.put("column_2", DatatypeFactory.convertToDataType("BaRRy"));
            jsonParser.insertIntoTable("table_name", entriesMap);

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

            jsonParser.delete("table_name", whereCondition);

            RecordSet actualRes = jsonParser.select("table_name", null, whereCondition);

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

        BackendParserFactory.getFactory().setCurrentParser("json");
        try {
            jsonParser.createDatabase("database_delete_2");

            LinkedHashMap<String, Class<? extends DBDatatype>> passMap = new LinkedHashMap<>();
            passMap.put("column_1", DBInteger.class);
            passMap.put("column_2", DBString.class);

            jsonParser.createTable("table_name", passMap);

            LinkedHashMap<String, DBDatatype> entriesMap = new LinkedHashMap<>();
            entriesMap.put("column_1", DatatypeFactory.convertToDataType(550));
            entriesMap.put("column_2", DatatypeFactory.convertToDataType("KHalED"));
            jsonParser.insertIntoTable("table_name", entriesMap);
            entriesMap = new LinkedHashMap<>();
            entriesMap.put("column_1", DatatypeFactory.convertToDataType(200));
            entriesMap.put("column_2", DatatypeFactory.convertToDataType("waLiD"));
            jsonParser.insertIntoTable("table_name", entriesMap);
            entriesMap = new LinkedHashMap<>();
            entriesMap.put("column_1", DatatypeFactory.convertToDataType(7500));
            entriesMap.put("column_2", DatatypeFactory.convertToDataType("AnAs"));
            jsonParser.insertIntoTable("table_name", entriesMap);
            entriesMap = new LinkedHashMap<>();
            entriesMap.put("column_1", DatatypeFactory.convertToDataType(852));
            entriesMap.put("column_2", DatatypeFactory.convertToDataType("YaKoUt"));
            jsonParser.insertIntoTable("table_name", entriesMap);
            entriesMap = new LinkedHashMap<>();
            entriesMap.put("column_1", DatatypeFactory.convertToDataType(189));
            entriesMap.put("column_2", DatatypeFactory.convertToDataType("BaRRy"));
            jsonParser.insertIntoTable("table_name", entriesMap);

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

            jsonParser.delete("table_name", whereCondition);

            RecordSet actualRes = jsonParser.select("table_name", columns, whereCondition);

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

        BackendParserFactory.getFactory().setCurrentParser("json");
        try {
            jsonParser.createDatabase("database_delete_3");

            LinkedHashMap<String, Class<? extends DBDatatype>> passMap = new LinkedHashMap<>();
            passMap.put("column_1", DBInteger.class);
            passMap.put("column_2", DBString.class);

            jsonParser.createTable("table_name", passMap);

            LinkedHashMap<String, DBDatatype> entriesMap = new LinkedHashMap<>();
            entriesMap.put("column_1", DatatypeFactory.convertToDataType(550));
            entriesMap.put("column_2", DatatypeFactory.convertToDataType("KHalED"));
            jsonParser.insertIntoTable("table_name", entriesMap);
            entriesMap = new LinkedHashMap<>();
            entriesMap.put("column_1", DatatypeFactory.convertToDataType(200));
            entriesMap.put("column_2", DatatypeFactory.convertToDataType("waLiD"));
            jsonParser.insertIntoTable("table_name", entriesMap);
            entriesMap = new LinkedHashMap<>();
            entriesMap.put("column_1", DatatypeFactory.convertToDataType(7500));
            entriesMap.put("column_2", DatatypeFactory.convertToDataType("AnAs"));
            jsonParser.insertIntoTable("table_name", entriesMap);
            entriesMap = new LinkedHashMap<>();
            entriesMap.put("column_1", DatatypeFactory.convertToDataType(852));
            entriesMap.put("column_2", DatatypeFactory.convertToDataType("YaKoUt"));
            jsonParser.insertIntoTable("table_name", entriesMap);
            entriesMap = new LinkedHashMap<>();
            entriesMap.put("column_1", DatatypeFactory.convertToDataType(189));
            entriesMap.put("column_2", DatatypeFactory.convertToDataType("BaRRy"));
            jsonParser.insertIntoTable("table_name", entriesMap);

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

            jsonParser.delete("table_name", whereConditionDel);

            RecordSet actualRes = jsonParser.select("table_name", columns, whereConditionSel);

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

        BackendParserFactory.getFactory().setCurrentParser("json");
        try {
            jsonParser.createDatabase("database_delete_4");

            LinkedHashMap<String, Class<? extends DBDatatype>> passMap = new LinkedHashMap<>();
            passMap.put("column_1", DBInteger.class);
            passMap.put("column_2", DBString.class);

            jsonParser.createTable("table_name", passMap);

            LinkedHashMap<String, DBDatatype> entriesMap = new LinkedHashMap<>();
            entriesMap.put("column_1", DatatypeFactory.convertToDataType(550));
            entriesMap.put("column_2", DatatypeFactory.convertToDataType("KHalED"));
            jsonParser.insertIntoTable("table_name", entriesMap);
            entriesMap = new LinkedHashMap<>();
            entriesMap.put("column_1", DatatypeFactory.convertToDataType(200));
            entriesMap.put("column_2", DatatypeFactory.convertToDataType("waLiD"));
            jsonParser.insertIntoTable("table_name", entriesMap);
            entriesMap = new LinkedHashMap<>();
            entriesMap.put("column_1", DatatypeFactory.convertToDataType(7500));
            entriesMap.put("column_2", DatatypeFactory.convertToDataType("AnAs"));
            jsonParser.insertIntoTable("table_name", entriesMap);
            entriesMap = new LinkedHashMap<>();
            entriesMap.put("column_1", DatatypeFactory.convertToDataType(852));
            entriesMap.put("column_2", DatatypeFactory.convertToDataType("YaKoUt"));
            jsonParser.insertIntoTable("table_name", entriesMap);
            entriesMap = new LinkedHashMap<>();
            entriesMap.put("column_1", DatatypeFactory.convertToDataType(189));
            entriesMap.put("column_2", DatatypeFactory.convertToDataType("BaRRy"));
            jsonParser.insertIntoTable("table_name", entriesMap);

            Condition whereConditionDel = new Where("   column_1  <  300  ");
            Condition whereConditionSel = new Where("   column_1  <  500  ");

            Set<String> columns = new TreeSet<>();

            columns.add("column_1");

            jsonParser.delete("table_name", whereConditionDel);
            RecordSet actualRes = jsonParser.select("table_name", columns, whereConditionSel);
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

        BackendParserFactory.getFactory().setCurrentParser("json");
        try {
            jsonParser.createDatabase("database_delete_5");

            LinkedHashMap<String, Class<? extends DBDatatype>> passMap = new LinkedHashMap<>();
            passMap.put("column_1", DBInteger.class);
            passMap.put("column_2", DBString.class);

            jsonParser.createTable("table_name", passMap);

            LinkedHashMap<String, DBDatatype> entriesMap = new LinkedHashMap<>();
            entriesMap.put("column_1", DatatypeFactory.convertToDataType(550));
            entriesMap.put("column_2", DatatypeFactory.convertToDataType("KHalED"));
            jsonParser.insertIntoTable("table_name", entriesMap);
            entriesMap = new LinkedHashMap<>();
            entriesMap.put("column_1", DatatypeFactory.convertToDataType(200));
            entriesMap.put("column_2", DatatypeFactory.convertToDataType("waLiD"));
            jsonParser.insertIntoTable("table_name", entriesMap);
            entriesMap = new LinkedHashMap<>();
            entriesMap.put("column_1", DatatypeFactory.convertToDataType(7500));
            entriesMap.put("column_2", DatatypeFactory.convertToDataType("AnAs"));
            jsonParser.insertIntoTable("table_name", entriesMap);
            entriesMap = new LinkedHashMap<>();
            entriesMap.put("column_1", DatatypeFactory.convertToDataType(852));
            entriesMap.put("column_2", DatatypeFactory.convertToDataType("YaKoUt"));
            jsonParser.insertIntoTable("table_name", entriesMap);
            entriesMap = new LinkedHashMap<>();
            entriesMap.put("column_1", DatatypeFactory.convertToDataType(189));
            entriesMap.put("column_2", DatatypeFactory.convertToDataType("BaRRy"));
            jsonParser.insertIntoTable("table_name", entriesMap);

            Condition whereConditionDel = new Where("   column_1  !=  300  ");

            Set<String> columns = new TreeSet<>();

            columns.add("column_1");

            jsonParser.delete("table_name", whereConditionDel);
            RecordSet actualRes = jsonParser.select("table_name", null , null);
            Iterator<Record> actualResItr = actualRes.iterator();
            while (actualResItr.hasNext()) {
                assertTrue(actualResItr.next() == null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            fail("Error occurred!");
        }
    }

    // TODO Testing
    // Delete with new data type date and floating-point numbers.
    // Delete with complex and and or expressions.
}
