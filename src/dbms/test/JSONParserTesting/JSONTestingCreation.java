package dbms.test.JSONParserTesting;

import static org.junit.Assert.*;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

import dbms.backend.BackendController;

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
		} catch (Exception e) {
			e.printStackTrace();
			fail("Not yet implemented");
		}
	}

}
