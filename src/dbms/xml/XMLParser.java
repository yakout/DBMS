package dbms.xml;

import java.io.File;
import java.util.Map;

import javax.xml.transform.TransformerException;

import dbms.exception.DatabaseAlreadyCreatedException;
import dbms.exception.DatabaseNotFoundException;
import dbms.exception.SyntaxErrorException;
import dbms.exception.TableAlreadyCreatedException;
import dbms.exception.TableNotFoundException;

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
			Map<String, Class> columns) throws
			DatabaseNotFoundException, TableAlreadyCreatedException,
			TransformerException, SyntaxErrorException {

		TableParser.getInstance().createTable(dbName, tableName, columns);
		SchemaParser.getInstance().createSchema(dbName,
				tableName);
	}

	public void insertIntoTable(String dbName, String tableName,
			Map<String, Object> entryMap) throws DatabaseNotFoundException, TableNotFoundException, SyntaxErrorException {
		TableParser.getInstance().insertIntoTable(dbName, tableName, entryMap);
	}
}
