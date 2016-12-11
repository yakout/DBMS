package dbms.test.JSONParserTesting;

import dbms.backend.BackendController;
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
			Map<String, Class> passMap = new LinkedHashMap<String, Class>();
			passMap.put("column_1", Integer.class);
			passMap.put("column_2", String.class);
			JSONParserConc.createTable("table11", passMap);
			Map<String, Object> entriesMap = new LinkedHashMap<String, Object>();
			entriesMap.put("column_1", 550);
			entriesMap.put("column_2", "KHalED");
			JSONParserConc.insertIntoTable("mine", entriesMap);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Not yet implemented");
		}
	}

}
