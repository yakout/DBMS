package dbms.connection;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import dbms.exception.DatabaseAlreadyCreatedException;
import dbms.exception.DatabaseNotFoundException;
import dbms.exception.TableAlreadyCreatedException;
import dbms.exception.TableNotFoundException;
import dbms.exception.TypeNotSupportedException;
import dbms.sqlparser.sqlInterpreter.Condition;
import dbms.util.ResultSet;


public interface Connection {
	public void createDatabase(String dbName)
			throws DatabaseAlreadyCreatedException;

	public void dropDatabase(String dbName)
			throws DatabaseNotFoundException;

	public void createTable(String tableName,
			Map<String, Class> columns)
				throws TableAlreadyCreatedException,
				DatabaseNotFoundException,
				TypeNotSupportedException;

	public void dropTable(String tableName)
			throws TableNotFoundException,
			DatabaseNotFoundException;

	public void insertIntoTable(String tableName,
			Map<String, Object> entryMap)
				throws TableNotFoundException,
				DatabaseNotFoundException,
				TypeNotSupportedException;

	public ResultSet selectAll(String tableName)
			throws TableNotFoundException,
			DatabaseNotFoundException;

	public ResultSet selectAll(String tableName,
			Condition condition)
				throws TableNotFoundException,
				DatabaseNotFoundException;

	public ResultSet select(String tableName,
			Collection<String> columns)
				throws TableNotFoundException,
				DatabaseNotFoundException;

	public ResultSet select(String tableName,
			Collection<String> columns, Condition condition)
				throws TableNotFoundException,
				DatabaseNotFoundException;

	public void delete(String tableName)
				throws TableNotFoundException,
				DatabaseNotFoundException;

	public void delete(String tableName, Condition condition)
				throws TableNotFoundException,
				DatabaseNotFoundException;

	public void update(String tableName, Map<String, Object> entryMap)
				throws TableNotFoundException,
				DatabaseNotFoundException;

	public void update(String tableName,
					   Map<String, Object> entryMap, Condition condition)
				throws TableNotFoundException,
				DatabaseNotFoundException;

	public void useDatabase(String dbName);
	public String getDatabaseName() throws DatabaseNotFoundException;
}
