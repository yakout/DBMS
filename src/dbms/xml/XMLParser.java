package dbms.xml;

import java.io.File;
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
import dbms.xml.schema.dtd.DTDSchemaParser;
import dbms.xml.schema.xsd.XSDParser;

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

	/**
	 * Creates a new XML table inside a given database.
	 * @param dbName Name of the given database.
	 * @param tableName Name of the given table.
	 * @param columns {@link Map} between names of given columns and
	 * their data type.
	 * @throws DatabaseNotFoundException
	 * @throws TableAlreadyCreatedException
	 * @throws IncorrectDataEntryException
	 */
	public void createDatabase(String dbName)
			throws DatabaseAlreadyCreatedException {
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
			Map<String, Class> columns)
					throws DatabaseNotFoundException,
					TableAlreadyCreatedException, IncorrectDataEntryException {
		TableParser.getInstance().createTable(dbName, tableName, columns);
		XSDParser.getInstance().createSchema(dbName,
				tableName);
		DTDSchemaParser.getInstance().createDTDSchema(dbName, tableName);
	}

	/**
	 * Drops table from database.
	 * @param tableName Name of table.
	 * @param dbName Name of database.
	 * @throws DatabaseNotFoundException
	 */
	public void dropTable(String tableName, String dbName)
			throws DatabaseNotFoundException {
		TableParser.getInstance().dropTable(tableName, dbName);
	}

	/**
	 * Selects data from database given a certain condition,
	 * the result is stored after in a {@link ResultSet}.
	 * @param dbName Name of database.
	 * @param tableName Name of table inside database.
	 * @param condition {@link Condition} condition for data selection,
	 * can be null.
	 * @param columns {@link Collection<String>} columns to select from.
	 * @return {@link ResultSet} Set of returned data.
	 * @throws TableNotFoundException
	 * @throws DatabaseNotFoundException
	 * @throws IncorrectDataEntryException
	 * @throws SyntaxErrorException
	 */
	public ResultSet select(String dbName, String tableName,
			Condition condition, Collection<String> columns)
			throws DatabaseNotFoundException, TableNotFoundException,
			SyntaxErrorException, IncorrectDataEntryException {
		return TableParser.getInstance().select(dbName, tableName, condition ,columns);
	}

	/**
	 * Inserts new data into table.
	 * @param dbName Name of database.
	 * @param tableName Name of table inside database.
	 * @param entryMap {@link Map} between column names
	 * and objects to be inserted.
	 * @throws DatabaseNotFoundException
	 * @throws TableNotFoundException
	 * @throws IncorrectDataEntryException
	 */
	public void insertIntoTable(String dbName, String tableName,
			Map<String, Object> entryMap) throws DatabaseNotFoundException,
			TableNotFoundException, IncorrectDataEntryException {
		TableParser.getInstance().insertIntoTable(dbName, tableName, entryMap);
	}

	/**
	 * Drops a given database from its directory.
	 * @param dbName Name of a given database.
	 * @param tableName Name of a given table inside database.
	 * @throws DatabaseNotFoundException
	 */
	public void dropDatabase(String dbName) throws DatabaseNotFoundException {
		TableParser.getInstance().dropDatabase(dbName);
	}

	/**
	 * Updates data inside database given a certain condition.
	 * @param dbName Name of database.
	 * @param tableName Name of table.
	 * @param values {@link Map} between column names and
	 * objects to be updated inside database.
	 * @param columns {@link Map} between columns to be updated
	 * with values of other columns.
	 * @param condition {@link Condition} condition for data updating,
	 * can be null.
	 * @throws DatabaseNotFoundException
	 * @throws TableNotFoundException
	 * @throws SyntaxErrorException
	 * @throws IncorrectDataEntryException
	 */
	public void update(String dbName, String tableName, Map<String, Object> values,
			   Map<String, String> columns, Condition condition)
					   throws DatabaseNotFoundException,TableNotFoundException,
					   SyntaxErrorException, IncorrectDataEntryException {
		TableParser.getInstance().update(dbName, tableName, values, columns, condition);
	}

	/**
	 * Deletes data from table given a certain condition.
	 * @param dbName Name of database.
	 * @param tableName Name of table.
	 * @param condition {@link Condition} condition for data deletion,
	 * can be null.
	 * @throws DatabaseNotFoundException
	 * @throws TableNotFoundException
	 * @throws SyntaxErrorException
	 * @throws IncorrectDataEntryException
	 */
	public void delete(String dbName, String tableName, Condition condition)
			throws DatabaseNotFoundException, TableNotFoundException,
			SyntaxErrorException,IncorrectDataEntryException {
		TableParser.getInstance().delete(dbName, tableName, condition);
	}

	/**
	 * Adds a new row to table.
	 * @param dbName Name of database.
	 * @param tableName Name of table.
	 * @param columnName Name of column to be added.
	 * @param dataType dataType of the given column.
	 * @throws DatabaseNotFoundException
	 * @throws TableNotFoundException
	 */
	public void alterAdd(String dbName, String tableName, String columnName , Class dataType)
			throws DatabaseNotFoundException, TableNotFoundException {
		TableParser.getInstance().alterAdd(dbName, tableName, columnName, dataType);
	}

	public void alterDrop(String dbName, String tableName, String columnName)
			throws DatabaseNotFoundException, TableNotFoundException {
		TableParser.getInstance().alterDrop(dbName, tableName, columnName);
		
	}
}