package dbms.connection;

import java.util.Collection;
import java.util.Map;

import javax.xml.transform.TransformerException;

import dbms.exception.DataTypeNotSupportedException;
import dbms.exception.DatabaseAlreadyCreatedException;
import dbms.exception.DatabaseNotFoundException;
import dbms.exception.SyntaxErrorException;
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
				TypeNotSupportedException, TransformerException, SyntaxErrorException;

	public void dropTable(String tableName)
			throws TableNotFoundException,
			DatabaseNotFoundException;

	public void insertIntoTable(String tableName,
			Map<String, Object> entryMap)
				throws TableNotFoundException,
				DatabaseNotFoundException,
				TypeNotSupportedException, SyntaxErrorException;

	public ResultSet select(String tableName,
			Collection<String> columns, Condition condition)
				throws TableNotFoundException,
				DatabaseNotFoundException, SyntaxErrorException;

	public void delete(String tableName)
				throws TableNotFoundException,
				DatabaseNotFoundException;

	public void delete(String tableName, Condition condition)
				throws TableNotFoundException,
				DatabaseNotFoundException;

	public void update(String tableName, Map<String, Object> values,
					   Map<String, String> columns, Condition condition)
			throws TableNotFoundException,
			DatabaseNotFoundException, SyntaxErrorException, DataTypeNotSupportedException;

	public void useDatabase(String dbName);

	public String getDatabaseName() throws DatabaseNotFoundException;
}