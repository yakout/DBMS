package dbms.backend.parsers.json;

import java.util.LinkedHashMap;
import java.util.Map;

import dbms.backend.BackendController;
import dbms.backend.parsers.xml.XMLParser;
import dbms.datatypes.DBDatatype;
import dbms.datatypes.DBInteger;
import dbms.datatypes.DBString;
import dbms.datatypes.DatatypeFactory;
import dbms.exception.DatabaseAlreadyCreatedException;
import dbms.exception.DatabaseNotFoundException;
import dbms.exception.IncorrectDataEntryException;
import dbms.exception.SyntaxErrorException;
import dbms.exception.TableAlreadyCreatedException;
import dbms.exception.TableNotFoundException;
import dbms.util.RecordSet;


public class test {

    private final static BackendController JSONParserConc
    = BackendController.getInstance();

    public static void main(String[] args) {
        try {
            JSONParserConc.createDatabase("mine");
        } catch (DatabaseAlreadyCreatedException e) {
            e.printStackTrace();
        }
        LinkedHashMap<String, Class<? extends DBDatatype>> passMap = new LinkedHashMap<>();
        passMap.put("column_1", DBInteger.class);
        passMap.put("column_2", DBString.class);
        try {
            JSONParserConc.createTable("table11", passMap);
        } catch (DatabaseNotFoundException | TableAlreadyCreatedException 
        		| IncorrectDataEntryException e) {
            e.printStackTrace();
        }
        Map<String, DBDatatype> entriesMap = new LinkedHashMap<String, DBDatatype>();
        entriesMap.put("column_1", DatatypeFactory.convertToDataType(550));
        entriesMap.put("column_2", DatatypeFactory.convertToDataType("KHalED"));
        try {
            JSONParserConc.insertIntoTable("table11", entriesMap);
            RecordSet rs = JSONParserConc.select("table11", null, null);
        } catch (DatabaseNotFoundException | TableNotFoundException
        		| IncorrectDataEntryException e) {
            e.printStackTrace();
        } catch (SyntaxErrorException e) {
            e.printStackTrace();
        }
    }
}
