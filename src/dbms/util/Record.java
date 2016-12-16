package dbms.util;

import dbms.datatypes.DBDatatype;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Data holder for a single row of data, normally
 * is used with {@link RecordSet}.
 */
public class Record implements Cloneable {
	private LinkedHashMap<String, DBDatatype> record;

	/**
	 * Constructor for a Record.
	 */
	public Record() {
		record = new LinkedHashMap<String, DBDatatype>();
	}

	/**
	 * Constructor for a Record that takes in a
	 * {@link Map} as a data holder.
	 * @param record {@link Map} map of data.
	 */
	public Record(LinkedHashMap<String, DBDatatype> record) {
		this.record = record;
	}

	/**
	 * Adds a single piece of data to Record.
	 * @param key key to be used to access an DBDatatype.
	 * @param {@link DBDatatype} to be accessed.
	 */
	public void add(String key, DBDatatype DBDatatype) {
		record.put(key, DBDatatype);
	}

	public DBDatatype get(String columnName) {
		for (Map.Entry<String, DBDatatype> entry
				: record.entrySet()) {
			if (entry.getKey().equalsIgnoreCase(
					columnName)) {
				return entry.getValue();
			}
		}
		return null;
	}

	/**
	 * Returns a {@link Map} representation of result.
	 * @return {@link Map} to be accessed.
	 */
	public LinkedHashMap<String, DBDatatype> getRecord() {
		return record;
	}

	public DBDatatype get(int columnIndex) {
		DBDatatype ret = null;
		try {
			ret = (new ArrayList<DBDatatype>(record.values()).get(columnIndex));
		} catch (IndexOutOfBoundsException e) {
		}
		return ret;
	}

	@Override
	public Record clone() {
		LinkedHashMap<String, DBDatatype> newMap =
				new LinkedHashMap<String, DBDatatype>();
		newMap.putAll(record);
		Record newRecord = new Record(newMap);
		return newRecord;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Record record1 = (Record) o;
		return record != null ? record.equals(record1.record) : record1.record == null;
	}

	@Override
	public int hashCode() {
		return record != null ? record.hashCode() : 0;
	}
}