package dbms.xml;

import java.io.File;
import java.util.HashMap;

import javax.xml.transform.TransformerException;

import dbms.exception.DatabaseAlreadyCreatedException;
import dbms.exception.DatabaseNotFoundException;
import dbms.exception.TableAlreadyCreatedException;

public class XMLParser {

	private static XMLParser instance = null;

	private static final String WORKSPACE_DIR =
			System.getProperty("user.home") + "\\databases";

	private XMLParser() {
	}

	public static XMLParser getInstance() {
		if (instance == null) {
			instance = new XMLParser();
		}
		return instance;
	}

	public void createDatabase(String dbName) throws DatabaseAlreadyCreatedException {
		File workspace = new File(WORKSPACE_DIR);
		if (!workspace.exists()) {
			workspace.mkdir();
		}
		File database = new File(workspace, dbName);
		if (!database.exists()) {
			database.mkdir();
		} else {
			throw new DatabaseAlreadyCreatedException();
		}
	}

	public void createTable(String dbName, String tableName,
			HashMap<String, Class> columns) throws
			DatabaseNotFoundException, TableAlreadyCreatedException,
			TransformerException {
		
		SchemaParser.getInstance().createSchema(dbName,
				tableName);
		TableParser.getInstance().createTable(dbName, tableName);
	}
}
