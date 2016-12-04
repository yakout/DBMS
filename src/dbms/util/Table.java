package dbms.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import dbms.exception.DatabaseNotFoundException;
import dbms.exception.IncorrectDataEntryException;
import dbms.exception.SyntaxErrorException;
import dbms.exception.TableNotFoundException;
import dbms.sqlparser.sqlInterpreter.Condition;
import dbms.xml.Evaluator;
import dbms.xml.XMLTableParser;

public class Table {
	private String dbName = null;
	private String name = null;
	private List<Column> columns = null;
	private int size;

	public Table(String dbName, String name) {
		this.dbName = dbName;
		this.name = name;
		columns = new ArrayList<Column>();
		size = 0;
	}

	public void addColumn(Column col) {
		columns.add(col);
	}

	public void loadTable()
			throws TableNotFoundException, DatabaseNotFoundException {
		XMLTableParser.getInstance().load(this);
	}

	public String getName() {
		return name;
	}

	public String getDBName() {
		return dbName;
	}

	public List<Column> getColumns() {
		return columns;
	}

	public Column getColumn(int index) {
		return columns.get(index);
	}

	public Column getColumn(String name) {
		for (Column col : columns) {
			if (col.getName().equals(name)) {
				return col;
			}
		}
		return null;
	}

	public void insertRow(Map<String, Object> entryMap) {
		for (Column col : columns) {
			col.addEntry(entryMap.get(
					col.getName()));
		}
		size++;
	}

	public void delete(Condition condition) throws IncorrectDataEntryException, SyntaxErrorException {
		Map<String, String> cols =
				mapColumns();
		for (int i = 0; i < size; i++) {
			Map<String, Object> row =
					getRow(i, null);
			if (condition == null || Evaluator.getInstance().evaluate(row,
					condition.getPostfix(), cols)) {
				deleteRow(i);
			}
		}
	}

	public ResultSet select(Condition condition, Collection<String> columns)
			throws IncorrectDataEntryException, SyntaxErrorException {
		Map<String, String> cols =
				new HashMap<String, String>();
		for (String col : columns) {
			if (!hasColumn(col)) {
				throw new IncorrectDataEntryException("Column not found!");
			}
		}
		ResultSet res = new ResultSet();
		for (int i = 0; i < size; i++) {
			Map<String, Object> row =
					getRow(i, columns);
			if (!row.isEmpty() && (condition == null || Evaluator.getInstance()
					.evaluate(row, condition.getPostfix(), cols))) {
				res.add(new Result(row));
			}
		}
		return res;
	}

	private Map<String, Object> getRow(int index, Collection<String> columns) {
		Map<String, Object> ret =
				new LinkedHashMap<String, Object>();
		for (Column col : this.columns) {
			if (columns == null
					|| columns.contains(col.getName())) {
				ret.put(col.getName(), col.get(index));
			}
		}
		return ret;
	}

	private void deleteRow(int index) {
		for (Column col : columns) {
			col.removeEntry(index);
		}
		size--;
	}

	public boolean hasColumn(String column) {
		for (Column col : columns) {
			if (col.getName().equals(column)) {
				return true;
			}
		}
		return false;
	}

	private Map<String, String> mapColumns() {
		Map<String, String> cols =
				new HashMap<String, String>();
		for (Column col : columns) {
			String type = null;
			try {
				col.getType().getField("KEY").get(type);
			} catch (NoSuchFieldException | SecurityException
					| IllegalArgumentException
					| IllegalAccessException e) {
				e.printStackTrace();
			}
			cols.put(col.getName(), type);
		}
		return cols;
	}
}
