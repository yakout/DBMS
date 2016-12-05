package dbms.util;

import java.util.ArrayList;
import java.util.List;

import dbms.datatypes.DatatypeDBMS;

public class Column {
	private ArrayList<Object> entries = null;
	private Class<? extends DatatypeDBMS> type = null;
	private String name = null;

	public Column() {
		entries = new ArrayList<Object>();
	}

	public Column(String name, Class<? extends DatatypeDBMS> type) {
		entries = new ArrayList<Object>();
		this.name = name;
		this.type = type;
	}

	public void addEntry(Object o) {
		entries.add(o);
	}

	public void removeEntry(int index) {
		entries.remove(index);
	}

	public void setType(Class<? extends DatatypeDBMS> type) {
		this.type = type;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Class<? extends DatatypeDBMS> getType() {
		return type;
	}

	public List<Object> getEntries() {
		return entries;
	}

	public Object get(int index) {
		return entries.get(index);
	}

	public void set(int index, Object o) {
		entries.set(index, o);
	}

	public void clear() {
		entries = null;
		type = null;
		name = null;
	}
}
