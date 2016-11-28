package dbms.connection;

import java.util.Collection;
import java.util.Map;

import dbms.exception.DatabaseAlreadyCreatedException;
import dbms.exception.DatabaseNotFoundException;
import dbms.exception.IncorrectDataEntryException;
import dbms.exception.SyntaxErrorException;
import dbms.exception.TableAlreadyCreatedException;
import dbms.exception.TableNotFoundException;
import dbms.exception.TypeNotSupportedException;
import dbms.sqlparser.sqlInterpreter.Condition;
import dbms.util.ResultSet;

/**
 * Interface for connectors to database,
 * holds methods to interact with a database.
 */
public interface Connection {

	/**
	 * Creates database inside system-dependent user's
	 * home path.
	 * @param dbName Name of database.
	 * @throws DatabaseAlreadyCreatedException
	 */
	public void createDatabase(String dbName)
			throws DatabaseAlreadyCreatedException;

	/**
	 * Drops database from path.
	 * @param dbName Name of database.
	 * @throws DatabaseNotFoundException
	 */
	public void dropDatabase(String dbName)
			throws DatabaseNotFoundException;

	/**
	 * Creates table inside used database.
	 * @param tableName Name of database.
	 * @param columns {@link Map} between names of given columns and
	 * their data type.
	 * @throws TableAlreadyCreatedException
	 * @throws DatabaseNotFoundException
	 * @throws TypeNotSupportedException
	 * @throws IncorrectDataEntryException
	 */
	public void createTable(String tableName,
			Map<String, Class> columns)
				throws TableAlreadyCreatedException,
				DatabaseNotFoundException,
				TypeNotSupportedException, IncorrectDataEntryException;

	/**
	 * Deletes table inside used database.
	 * @param tableName Name of table.
	 * @throws TableNotFoundException
	 * @throws DatabaseNotFoundException
	 */
	public void dropTable(String tableName)
			throws TableNotFoundException,
			DatabaseNotFoundException;

	/**
	 * Inserts new data into table
	 * @param tableName Name of table.
	 * @param entryMap {@link Map} between column names
	 * and objects to be inserted.
	 * @throws TableNotFoundException
	 * @throws DatabaseNotFoundException
	 * @throws TypeNotSupportedException
	 * @throws IncorrectDataEntryException
	 */
	public void insertIntoTable(String tableName,
			Map<String, Object> entryMap)
				throws TableNotFoundException,
				DatabaseNotFoundException,
				TypeNotSupportedException, IncorrectDataEntryException;

	/**
	 * Selects data from database given a certain condition,
	 * the result is stored after in a {@link ResultSet}.
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
	public ResultSet select(String tableName,
			Collection<String> columns, Condition condition)
				throws TableNotFoundException,
				DatabaseNotFoundException, SyntaxErrorException,
				IncorrectDataEntryException;

	/**
	 * Deletes data from database given a certain condition.
	 * @param tableName Name of table.
	 * @param condition {@link Condition} condition for data deletion,
	 * can be null.
	 * @throws DatabaseNotFoundException
	 * @throws TableNotFoundException
	 * @throws SyntaxErrorException
	 * @throws IncorrectDataEntryException
	 */
	public void delete(String tableName, Condition condition)
				throws TableNotFoundException,
				DatabaseNotFoundException, SyntaxErrorException,
				IncorrectDataEntryException;

	/**
	 * Updates data inside database given a certain condition.
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
	public void update(String tableName, Map<String, Object> values,
					   Map<String, String> columns, Condition condition)
			throws TableNotFoundException,
			DatabaseNotFoundException, SyntaxErrorException,
			IncorrectDataEntryException;

	/**
	 * Uses a given database to operate on tables inside of it.
	 * @param dbName Name of database.
	 */
	public void useDatabase(String dbName);

	/**
	 * Gets name of the currently used database.
	 * @return Name of the currently used database.
	 * @throws DatabaseNotFoundException
	 */
	public String getDatabaseName() throws DatabaseNotFoundException;
}