package dbms.xml;

import java.io.File;
import java.util.HashMap;

import dbms.exception.DatabaseAlreadyFoundException;

public class XMLParser {
	private static final String WORKSPACE_DIR =
			System.getProperty("user.home") + "\\databases";
	private static XMLParser instance = null;

	private XMLParser() {

	}

	public static XMLParser getInstance() {
		if (instance == null) {
			instance = new XMLParser();
		}
		return instance;
	}

	public void createDatabase(String dbName) throws DatabaseAlreadyFoundException {
		File workspace = new File(WORKSPACE_DIR);
		if (!workspace.exists()) {
			workspace.mkdir();
		}
		File database = new File(workspace, dbName);
		if (!database.exists()) {
			database.mkdir();
		} else {
			throw new DatabaseAlreadyFoundException();
		}
	}

	public void createTable(String dbName, String tableName, HashMap<String, Class> columns) {
		DOMParser.getInstance().createTable(dbName, tableName);
	}
}
