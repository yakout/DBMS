package dbms.test.JSONParserTesting;

import dbms.backend.BackendController;
import dbms.datatypes.DBDatatype;
import dbms.datatypes.DBInteger;
import dbms.datatypes.DBString;
import dbms.datatypes.DatatypeFactory;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.fail;

public class JSONTestingCreation {
	private final BackendController JSONParserConc = BackendController.getInstance();
	@Test
	public void test() {
		try {
			JSONParserConc.createDatabase("mine");
			Map<String, Class<? extends DBDatatype>> passMap = new LinkedHashMap<>();
			passMap.put("column_1", DBInteger.class);
			passMap.put("column_2", DBString.class);
			JSONParserConc.createTable("table11", passMap);
			Map<String, DBDatatype> entriesMap = new LinkedHashMap<>();
			entriesMap.put("column_1", DatatypeFactory.convertToDataType(550));
			entriesMap.put("column_2", DatatypeFactory.convertToDataType("KHalED"));
			JSONParserConc.insertIntoTable("mine", entriesMap);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Not yet implemented");
		}
	}

}
