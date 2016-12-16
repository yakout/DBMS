package dbms.util;

import dbms.datatypes.DBDatatype;
import dbms.exception.DatabaseNotFoundException;
import dbms.exception.IncorrectDataEntryException;
import dbms.exception.SyntaxErrorException;
import dbms.exception.TableNotFoundException;
import dbms.sqlparser.sqlInterpreter.Condition;

import java.util.*;

public class Table {
	private Database database = null;
	private String name = null;
	private List<Column> columns = null;
	private int size;

	public Table(String name) {
		this.name = name;
		columns = new ArrayList<Column>();
		size = 0;
	}

	public Table(String name, Database database) {
		this.name = name;
		this.database = database;
		columns = new ArrayList<Column>();
		size = 0;
	}

	public void setDatabase(Database database) {
		this.database = database;
		this.database.addTable(this);
	}

	public void addColumn(Column col) {
		columns.add(col);
	}

	public String getName() {
		return name;
	}

	public Database getDatabase() {
		return database;
	}

	public List<Column> getColumns() {
		return columns;
	}

	public Column getColumn(int index) {
		return columns.get(index);
	}

	public Column getColumn(String name) {
		for (Column col : columns) {
			if (col.getName().toLowerCase().equals(
					name.toLowerCase())) {
				return col;
			}
		}
		return null;
	}

	public void setColumns (List<Column> newColumns) {
		columns = newColumns;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int insertRow(Map<String, DBDatatype> entryMap)
			throws IncorrectDataEntryException,
			TableNotFoundException, DatabaseNotFoundException {
		if (entryMap == null) {
			return 0;
		}
		validateValues(entryMap);
		for (Column col : columns) {
			col.addEntry(entryMap.get(col.getName()));
		}
		size++;
		return 1; //updateCount
	}

	public int insertRow(Collection<DBDatatype> entries)
			throws IncorrectDataEntryException {
		if (entries.size() != columns.size()) {
			throw new IncorrectDataEntryException("Column count invalid!");
		}
		int i = 0;
		for (DBDatatype entry : entries) {
			Column col = columns.get(i);
			if (!entry.getClass().equals(col.getType())) {
				throw new IncorrectDataEntryException("Datatype conflict!");
			}
			i++;
		}
		i = 0;
		for (DBDatatype entry : entries) {
			Column col = columns.get(i);
			col.addEntry(entry);
			i++;
		}
		size++;
		return 1;
	}

	public int delete(Condition condition)
			throws IncorrectDataEntryException, SyntaxErrorException,
			TableNotFoundException, DatabaseNotFoundException {
		int updateCount = 0;
		Map<String, String> cols =
				mapColumns();
		for (int i = 0; i < size; i++) {
			Map<String, DBDatatype> row =
					getRow(i, null);
			if (condition == null || Evaluator.getInstance().evaluate(row,
					condition.getPostfix(), cols)) {
				deleteRow(i);
				updateCount++;
				i--;
			}
		}
		return updateCount;
	}

	public RecordSet select(Collection<String> columns, Condition condition)
			throws IncorrectDataEntryException, SyntaxErrorException,
			TableNotFoundException, DatabaseNotFoundException {
		Collection<String> columnsLower = new ArrayList<String>();
		if (columns != null) {
			for (String col : columns) {
				if (!hasColumn(col)) {
					throw new IncorrectDataEntryException("Column not found!");
				}
				columnsLower.add(col.toLowerCase());
			}
		}
		List<String> columnNames =
				new ArrayList<String>();
		RecordSet res = new RecordSet();
		for (Column col : this.columns) {
			if (columns == null || columnsLower.contains(
					col.getName().toLowerCase())) {
				columnNames.add(col.getName());
			}
		}
		res.setColumnList(columnNames);
		for (int i = 0; i < size; i++) {
			LinkedHashMap<String, DBDatatype> row =
					getRow(i, columns);
			if (!row.isEmpty() && (condition == null || Evaluator.getInstance()
					.evaluate(getRow(i, null), condition.getPostfix(), mapColumns()))) {
				res.add(new Record(row));
			}
		}
		return res;
	}

	public int update(Map<String, DBDatatype> values, Map<String, String> columns, Condition condition)
			throws IncorrectDataEntryException, SyntaxErrorException,
			TableNotFoundException, DatabaseNotFoundException {
		int updateCount = 0;
		validateColumns(columns);
		validateValues(values);
		for (int i = 0; i < size; i++) {
			Map<String, DBDatatype> row =
					getRow(i, null);
			if (!row.isEmpty() && (condition == null || Evaluator.getInstance()
					.evaluate(row, condition.getPostfix(), mapColumns()))) {
				updateRow(values, columns, i);
				updateCount++;
			}
		}
		return updateCount;
	}

	public void alterAdd(String colName, Class<? extends DBDatatype> datatype)
			throws IncorrectDataEntryException {
		Column col = new Column(colName, datatype);
		for (int i = 0; i < size; i++) {
			col.addEntry(null);
		}
		addColumn(col);
	}

	public void alterDrop(String colName)
			throws IncorrectDataEntryException {
		Column col = getColumn(colName);
		if (col == null) {
			throw new IncorrectDataEntryException("Column not found!");
		}
		columns.remove(col);
	}

	private void updateRow(Map<String, DBDatatype> values, Map<String, String> columns, int index) {
		if (values != null) {
			for (Map.Entry<String, DBDatatype> entry : values.entrySet()) {
				Column col = getColumn(entry.getKey());
				col.set(index, entry.getValue());
			}
		}
		if (columns != null) {
			for (Map.Entry<String, String> entry : columns.entrySet()) {
				Column col1 = getColumn(entry.getKey());
				Column col2 = getColumn(entry.getValue());
				col1.set(index, col2.get(index));
			}
		}
	}

	private LinkedHashMap<String, DBDatatype> getRow(int index, Collection<String> columns) {
		LinkedHashMap<String, DBDatatype> ret =
				new LinkedHashMap<String, DBDatatype>();
		Collection<String> columnsLower = new ArrayList<>();
		if (columns == null) {
			for (Column col : this.columns) {
				ret.put(col.getName(), col.get(index));
			}
		} else {
			for (String colName : columns) {
				for (Column col : this.columns) {
					if (colName.toLowerCase().equals(
							col.getName().toLowerCase())) {
						ret.put(col.getName(), col.get(index));
					}
				}
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
			if (col.getName().toLowerCase().equals(column.toLowerCase())) {
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
				type = (String) col.getType().getField("KEY").get(col.getType().newInstance());
			} catch (NoSuchFieldException | SecurityException
					| IllegalArgumentException
					| IllegalAccessException | InstantiationException e) {
				e.printStackTrace();
			}
			cols.put(col.getName().toLowerCase(), type);
		}
		return cols;
	}

	private void validateColumns(Map<String, String> columns)
			throws IncorrectDataEntryException {
		if (columns == null) {
			return;
		}
		for (Map.Entry<String, String> entry : columns.entrySet()) {
			Column col1 = getColumn(entry.getKey());
			Column col2 = getColumn(entry.getValue());
			if (col1 == null || col2 == null) {
				throw new IncorrectDataEntryException("Column not found!");
			} else if (!col1.getType().equals(col2.getType())) {
				throw new IncorrectDataEntryException("Datatype conflict!");
			}
		}
	}

	private void validateValues(Map<String, DBDatatype> values) throws IncorrectDataEntryException {
		if (values == null) {
			return;
		}
		Map<String, String> cols = mapColumns();
		for (Map.Entry<String, DBDatatype> entry : values.entrySet()) {
			String type = cols.get(entry.getKey().toLowerCase());
			if (type == null) {
				throw new IncorrectDataEntryException("Column not found!");
			}
			if (!type.equals(entry.getValue().getKey())) {
				throw new IncorrectDataEntryException("Datatype conflict!");
			}
		}
	}

	public void clear() {
		for (Column col : columns) {
			col.clear();
		}
		columns.clear();
		size = 0;
	}

	public void setTable(Table table) {
		this.name = table.getName();
		this.database = table.getDatabase();
		this.size = table.getSize();
		for (Column col : table.getColumns()) {
			this.addColumn(col);
		}
	}
}
