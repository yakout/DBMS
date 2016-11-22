package dbms.connection;

import java.util.Collection;
import java.util.HashMap;

import dbms.util.Result;
import dbms.util.sql.Condition;

public interface Connection {
	public void createDatabase(String dbName);

	public void dropDatabase(String dbName);

	public void createTable(String tableName);

	public void dropTable(String tableName);

	public void insertIntoTable(String tableName,
			HashMap<Object, Object> entryMap);

	public Result selectAll(String tableName);

	public Result selectAll(String tableName,
			Condition condition);

	public Result select(String tableName,
			Collection<String> columns);

	public Result select(String tableName,
			Collection<String> columns, Condition condition);

	public void delete(String tableName,
			Collection<String> columns);

	public void delete(String tableName,
			Collection<String> columns, Condition condition);

	public void update(String tableName,
			Collection<String> columns);

	public void update(String tableName,
			Collection<String> columnns, Condition condition);
}
