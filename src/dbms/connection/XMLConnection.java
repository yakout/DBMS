package dbms.connection;

import java.util.Collection;
import java.util.HashMap;

import dbms.util.Result;
import dbms.util.sql.Condition;

public class XMLConnection implements Connection {

	private static XMLConnection instance = null;

	private XMLConnection() {

	}

	public static XMLConnection getInstance() {
		if (instance == null)
			instance = new XMLConnection();
		return instance;
	}

	@Override
	public void createDatabase(String dbName) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dropDatabase(String dbName) {
		// TODO Auto-generated method stub

	}

	@Override
	public void createTable(String dbName, String tableName, HashMap<String, Class> columns) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dropTable(String tableName) {
		// TODO Auto-generated method stub

	}

	@Override
	public void insertIntoTable(String dbName, String tableName, HashMap<String, Object> entryMap) {
		// TODO Auto-generated method stub

	}

	@Override
	public Result selectAll(String dbName, String tableName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result selectAll(String dbName, String tableName, Condition condition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result select(String dbName, String tableName, Collection<String> columns) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result select(String dbName, String tableName, Collection<String> columns, Condition condition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String dbName, String tableName, Collection<String> columns) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String dbName, String tableName, Collection<String> columns, Condition condition) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(String dbName, String tableName, Collection<String> columns) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(String dbName, String tableName, Collection<String> columnns, Condition condition) {
		// TODO Auto-generated method stub

	}
}
