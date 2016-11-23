package dbms.connection;

import java.util.Collection;
import java.util.HashMap;

import dbms.util.Result;
import dbms.util.sql.Condition;

public interface Connection {
	public void createDatabase(String dbName);

	public void dropDatabase(String dbName);

	public void createTable(String dbName, String tableName,
			HashMap<String, Class> columns);

	public void dropTable(String tableName);

	public void insertIntoTable(String dbName, String tableName,
			HashMap<String, Object> entryMap);

	public Result selectAll(String dbName, String tableName);

	public Result selectAll(String dbName, String tableName,
			Condition condition);

	public Result select(String dbName, String tableName,
			Collection<String> columns);

	public Result select(String dbName, String tableName,
			Collection<String> columns, Condition condition);

	public void delete(String dbName, String tableName,
			Collection<String> columns);

	public void delete(String dbName, String tableName,
			Collection<String> columns, Condition condition);

	public void update(String dbName, String tableName,
			Collection<String> columns);

	public void update(String dbName, String tableName,
			Collection<String> columnns, Condition condition);
}
