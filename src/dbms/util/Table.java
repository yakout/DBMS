package dbms.util;

import java.util.ArrayList;
import java.util.List;

import dbms.exception.DatabaseNotFoundException;
import dbms.exception.TableNotFoundException;
import dbms.xml.XMLTableParser;

public class Table {
	private String dbName = null;
	private String name = null;
	private List<Column> columns = null;

	public Table(String dbName, String name) {
		this.dbName = dbName;
		this.name = name;
		columns = new ArrayList<Column>();
	}

	public void addColumn(Column col) {
		columns.add(col);
	}

	public void loadTable() throws TableNotFoundException, DatabaseNotFoundException {
		XMLTableParser.getInstance().loadTable(this);
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
}
