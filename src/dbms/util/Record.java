package dbms.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Data holder for a single row of data, normally
 * is used with {@link RecordSet}.
 */
public class Record implements Cloneable {
	private Map<String, Object> record;

	/**
	 * Constructor for a Record.
	 */
	public Record() {
		record = new LinkedHashMap<String, Object>();
	}

	/**
	 * Constructor for a Record that takes in a
	 * {@link Map} as a data holder.
	 * @param record {@link Map} map of data.
	 */
	public Record(Map<String, Object> record) {
		this.record = record;
	}

	/**
	 * Adds a single piece of data to Record.
	 * @param key key to be used to access an object.
	 * @param {@link Object} to be accessed.
	 */
	public void add(String key, Object object) {
		record.put(key, object);
	}

	public Object get(String columnName) {
		return record.get(columnName);
	}

	/**
	 * Returns a {@link Map} representation of result.
	 * @return {@link Map} to be accessed.
	 */
	public Map<String, Object> getRecord() {
		return record;
	}

	public Object get(int columnIndex) {
		Object ret = null;
		try {
			ret = (new ArrayList<Object>(record.values()).get(columnIndex));
		} catch (IndexOutOfBoundsException e) {
		}
		return ret;
	}

	@Override
	public Record clone() {
		LinkedHashMap<String, Object> newMap =
				new LinkedHashMap<String, Object>();
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