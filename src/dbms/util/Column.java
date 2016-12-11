package dbms.util;

import dbms.datatypes.DBDatatype;

import java.util.ArrayList;
import java.util.List;

public class Column {
	private ArrayList<DBDatatype> entries = null;
	private Class<? extends DBDatatype> type = null;
	private String name = null;

	public Column() {
		entries = new ArrayList<DBDatatype>();
	}

	public Column(String name, Class<? extends DBDatatype> type) {
		entries = new ArrayList<DBDatatype>();
		this.name = name;
		this.type = type;
	}

	public void addEntry(DBDatatype o) {
		entries.add(o);
	}

	public void removeEntry(int index) {
		entries.remove(index);
	}

	public void setType(Class<? extends DBDatatype> type) {
		this.type = type;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Class<? extends DBDatatype> getType() {
		return type;
	}

	public List<DBDatatype> getEntries() {
		return entries;
	}

	public DBDatatype get(int index) {
		return entries.get(index);
	}

	public void set(int index, DBDatatype o) {
		entries.set(index, o);
	}

	public void clear() {
		entries = null;
		type = null;
		name = null;
	}
}
