package dbms.connection;

import java.util.Collection;
import java.util.Map;

import dbms.datatypes.DatatypeDBMS;
import dbms.datatypes.DatatypeFactory;
import dbms.exception.DatabaseAlreadyCreatedException;
import dbms.exception.DatabaseNotFoundException;
import dbms.exception.IncorrectDataEntryException;
import dbms.exception.SyntaxErrorException;
import dbms.exception.TableAlreadyCreatedException;
import dbms.exception.TableNotFoundException;
import dbms.sqlparser.sqlInterpreter.Condition;
import dbms.util.Column;
import dbms.util.Parser;
import dbms.util.ResultSet;
import dbms.util.Table;
import dbms.xml.XMLTableParser;

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
		this.dbName = dbName;
		Parser.createDatabase(dbName);
	}

	@Override
	public void dropDatabase(String dbName)
			throws DatabaseNotFoundException {
		Parser.dropDatabase(dbName);
	}

	@Override
	public void createTable(String tableName, Map<String, Class> columns)
			throws DatabaseNotFoundException,
			TableAlreadyCreatedException, IncorrectDataEntryException {
		Table table = new Table(dbName, tableName);
		for (Map.Entry<String, Class> col : columns.entrySet()) {
			Class<? extends DatatypeDBMS> type =
					DatatypeFactory.getFactory().getRegisteredDatatype(
							col.getValue().getSimpleName());
			if (type == null) {
				throw new IncorrectDataEntryException("Datatype not supported!");
			}
			table.addColumn(new Column(col.getKey(), type));
		}
		table.create();
		table.clear();
	}

	@Override
	public void dropTable(String tableName) throws DatabaseNotFoundException {
		XMLTableParser.getInstance().dropTable(dbName, tableName);
	}

	@Override
	public void insertIntoTable(String tableName, Map<String, Object> entryMap)
			throws DatabaseNotFoundException,
			TableNotFoundException, IncorrectDataEntryException {
		Table table = new Table(dbName, tableName);
		table.loadTable();
		table.insertRow(entryMap);
		table.writeToFile();
		table.clear();
	}

	@Override
	public ResultSet select(String tableName,
			Collection<String> columns, Condition condition)
					throws DatabaseNotFoundException,TableNotFoundException,
					SyntaxErrorException, IncorrectDataEntryException {
		Table table = new Table(dbName, tableName);
		table.loadTable();
		ResultSet ret = table.select(columns, condition);
		table.clear();
		return ret;
	}

	@Override
	public void delete(String tableName, Condition condition)
			throws DatabaseNotFoundException, TableNotFoundException,
			SyntaxErrorException, IncorrectDataEntryException {
		Table table = new Table(dbName, tableName);
		table.loadTable();
		table.delete(condition);
		table.writeToFile();
		table.clear();
	}

	@Override
	public void update(String tableName, Map<String, Object> values,
			Map<String, String> columns, Condition condition)
					throws DatabaseNotFoundException, TableNotFoundException,
					SyntaxErrorException, IncorrectDataEntryException {
		Table table = new Table(dbName, tableName);
		table.loadTable();
		table.update(values, columns, condition);
		table.writeToFile();
		table.clear();
	}

	@Override
	public void useDatabase(String dbName) throws DatabaseNotFoundException {
		try {
			this.createDatabase(dbName);
			this.dropDatabase(dbName);
			throw new DatabaseNotFoundException();
		} catch (DatabaseAlreadyCreatedException e) {
			this.dbName = dbName;
		}
	}

	@Override
	public String getDatabaseName() throws DatabaseNotFoundException {
		if (dbName == null) {
			throw new DatabaseNotFoundException();
		}
		return dbName;
	}

	@Override
	public void alterAdd(String tableName, String columnName, Class datatype)
			throws DatabaseNotFoundException, TableNotFoundException, IncorrectDataEntryException {
		Class<? extends DatatypeDBMS> type =
				DatatypeFactory.getFactory().getRegisteredDatatype(
						datatype.getSimpleName());
		if (type == null) {
			throw new IncorrectDataEntryException("Datatype not supported!");
		}
		Table table = new Table(dbName, tableName);
		table.loadTable();
		table.alterAdd(columnName, type);
		table.writeToFile();
		table.clear();
	}

	@Override
	public void alterDrop(String tableName, String columnName)
			throws DatabaseNotFoundException, TableNotFoundException, IncorrectDataEntryException {
		Table table = new Table(dbName, tableName);
		table.loadTable();
		table.alterDrop(columnName);
		table.writeToFile();
		table.clear();
	}
}