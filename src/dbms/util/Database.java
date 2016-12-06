package dbms.util;

import java.util.ArrayList;

public class Database {
	private String name = null;
	private ArrayList<Table> tables = null;

	public Database(String name) {
		this.name = name;
		tables = new ArrayList<Table>();
	}

	public Table createTable(String name) {
		Table table = new Table(name, this);
		tables.add(table);
		return table;
	}

	public Table getTable(String tableName) {
		for (Table table : tables) {
			if (table.getName().equals(tableName)) {
				return table;
			}
		}
		return null;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
