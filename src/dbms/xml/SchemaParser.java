package dbms.xml;

import java.util.HashMap;

public class SchemaParser {
	private static SchemaParser instance  = null;
	private static final String EXTENSION = ".xsd";

	private SchemaParser() {
	}

	public static SchemaParser getInstance() {
		if (instance == null) {
			instance = new SchemaParser();
		}
		return instance;
	}

	public void createSchema(String dbName, String tableName,
			HashMap<String, Class> columns) {

	}
}
