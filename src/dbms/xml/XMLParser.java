package dbms.xml;

import java.io.File;
import java.util.Collection;
import java.util.Map;

import javax.xml.transform.TransformerException;

import dbms.exception.DataTypeNotSupportedException;
import dbms.exception.DatabaseAlreadyCreatedException;
import dbms.exception.DatabaseNotFoundException;
import dbms.exception.SyntaxErrorException;
import dbms.exception.TableAlreadyCreatedException;
import dbms.exception.TableNotFoundException;
import dbms.sqlparser.sqlInterpreter.Condition;
import dbms.util.ResultSet;

public class XMLParser {

	private static XMLParser instance = null;

	private static final String WORKSPACE_DIR =
			System.getProperty("user.home") + File.separator + "databases";

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

	public ResultSet select(String dbName, String tableName, Condition condition, Collection<String> columns)
			throws DatabaseNotFoundException, TableNotFoundException, SyntaxErrorException {
		return TableParser.getInstance().select(dbName, tableName, condition ,columns);
	}

	public void insertIntoTable(String dbName, String tableName,
			Map<String, Object> entryMap) throws DatabaseNotFoundException,
			TableNotFoundException, SyntaxErrorException {
		TableParser.getInstance().insertIntoTable(dbName, tableName, entryMap);
	}

	public void update(String dbName, String tableName, Map<String, Object> values,
			   Map<String, String> columns, Condition condition)
					   throws DatabaseNotFoundException,TableNotFoundException,
					   SyntaxErrorException, DataTypeNotSupportedException {
		TableParser.getInstance().update(dbName, tableName, values, columns, condition);
	}
}