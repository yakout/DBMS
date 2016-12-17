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

    /**
     * Gets value inside record given a key (column name in case
     * of using records with columns).
     * @param key Key to get value inside record.
     * @return {@link DBDatatype} value that has given key,
     * returns null if no value is found.
     */
    public DBDatatype get(String key) {
        for (Map.Entry<String, DBDatatype> entry
                : record.entrySet()) {
            if (entry.getKey().equalsIgnoreCase(
                    key)) {
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

    /**
     * Gets value inside record given its index.
     * @param index Index of needed value.
     * @return {@link DBDatatype} value to be found.
     * @throws IndexOutOfBoundsException In case index is out of bounds.
     */
    public DBDatatype get(int index) {
        DBDatatype ret = null;
        try {
            ret = (new ArrayList<DBDatatype>(record.values()).get(index));
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
        return record != null ? record.equals(record1.record) :
                record1.record == null;
    }

    @Override
    public int hashCode() {
        return record != null ? record.hashCode() : 0;
    }
}