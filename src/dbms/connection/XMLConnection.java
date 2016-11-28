package dbms.connection;

import java.util.Collection;
import java.util.Map;

import dbms.exception.DatabaseAlreadyCreatedException;
import dbms.exception.DatabaseNotFoundException;
import dbms.exception.IncorrectDataEntryException;
import dbms.exception.SyntaxErrorException;
import dbms.exception.TableAlreadyCreatedException;
import dbms.exception.TableNotFoundException;
import dbms.sqlparser.sqlInterpreter.Condition;
import dbms.util.ResultSet;
import dbms.xml.XMLParser;

/**
 * Connector to database that uses {@link XMLParser}
 * to store data in XML.
 *
 */
public class XMLConnection implements Connection {
	private static XMLConnection instance = null;
	private String dbName;

	private XMLConnection() {

	}

	/**
	 * Gets the static instance of XMLConnection.
	 * @return
	 */
	public static XMLConnection getInstance() {
		if (instance == null)
			instance = new XMLConnection();
		return instance;
	}

	@Override
	public void createDatabase(String dbName)
			throws DatabaseAlreadyCreatedException {
		this.useDatabase(dbName);
		XMLParser.getInstance().createDatabase(dbName);
	}

	@Override
	public void dropDatabase(String dbName)
			throws DatabaseNotFoundException {
		XMLParser.getInstance().dropDataBase(dbName);
	}

	@Override
	public void createTable(String tableName, Map<String, Class> columns)
			throws DatabaseNotFoundException,
			TableAlreadyCreatedException, IncorrectDataEntryException {
		XMLParser.getInstance().createTable(dbName, tableName, columns);
	}

	@Override
	public void dropTable(String tableName) throws DatabaseNotFoundException {
		XMLParser.getInstance().dropTable(tableName, dbName);

	}

	@Override
	public void insertIntoTable(String tableName, Map<String, Object> entryMap)
			throws DatabaseNotFoundException,
			TableNotFoundException, IncorrectDataEntryException {
		XMLParser.getInstance().insertIntoTable(dbName, tableName, entryMap);
	}

	@Override
	public ResultSet select(String tableName,
			Collection<String> columns, Condition condition)
					throws DatabaseNotFoundException,TableNotFoundException,
					SyntaxErrorException, IncorrectDataEntryException {
		return XMLParser.getInstance().select(dbName, tableName, condition, columns);
	}

	@Override
	public void delete(String tableName, Condition condition)
			throws DatabaseNotFoundException, TableNotFoundException,
			SyntaxErrorException, IncorrectDataEntryException {
		XMLParser.getInstance().delete(dbName, tableName, condition);
	}

	@Override
	public void update(String tableName, Map<String, Object> values,
					   Map<String, String> columns, Condition condition)
							   throws DatabaseNotFoundException, TableNotFoundException,
							   SyntaxErrorException, IncorrectDataEntryException {
		XMLParser.getInstance().update(dbName, tableName, values, columns, condition);
	}

	@Override
	public void useDatabase(String dbName) {
		this.dbName = dbName;
	}

	@Override
	public String getDatabaseName() throws DatabaseNotFoundException {
		if (dbName == null) {
			throw new DatabaseNotFoundException();
		}
		return dbName;
	}

	@Override
	public void alterAdd(String tableName, String columnName) {
		XMLParser.getInstance().alterAdd(dbName, tableName, columnName);
	}

	@Override
	public void alterDrop(String tableName, String columnName) {
		
	}
}