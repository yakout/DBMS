package dbms.util;

import dbms.datatypes.DBDatatype;

import java.util.ArrayList;
import java.util.List;

/**
 * Representation of a column that is contained by a table.
 */
public class Column {
    /**
     * List of entries inside this table.
     */
    private List<DBDatatype> entries = null;

    /**
     * Column type.
     */
    private Class<? extends DBDatatype> type = null;

    /**
     * Column name.
     */
    private String name = null;


    /**
     * Constructs a new column.
     */
    public Column() {
        entries = new ArrayList<DBDatatype>();
    }

    /**
     * Constructs a new column.
     * @param name Column name.
     * @param type Column type.
     */
    public Column(String name, Class<? extends DBDatatype> type) {
        entries = new ArrayList<DBDatatype>();
        this.name = name;
        this.type = type;
    }

    /**
     * Adds a new entry to column.
     * @param o Entry to be added.
     * @throws RuntimeException In case the type of entry is incompatible.
     */
    public void addEntry(DBDatatype o) {
        if (o != null && o.getClass() != type) {
            throw new RuntimeException();
        }
        entries.add(o);
    }

    /**
     * Removes entry from column given index.
     * @param index Index of the entry inside column.
     * @throws IndexOutOfBoundsException In case index is out of bounds.
     */
    public void removeEntry(int index) {
        entries.remove(index);
    }

    /**
     * Gets column name.
     * @return Column name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets column name.
     * @param name Column name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets type of column.
     * @return {@link Class<? extends DBDatatype>} Type of column.
     */
    public Class<? extends DBDatatype> getType() {
        return type;
    }

    /**
     * Sets type of column (used mainly by serializing buffers).
     * @param type
     */
    public void setType(Class<? extends DBDatatype> type) {
        this.type = type;
    }

    /**
     * Gets list of entries inside column.
     * @return List of entries.
     */
    public List<DBDatatype> getEntries() {
        return entries;
    }

    /**
     * Gets entry with a given index inside column.
     * @param index Index to the needed entry.
     * @return Entry with the given index.
     */
    public DBDatatype get(int index) {
        return entries.get(index);
    }

    /**
     * Sets entry inside column.
     * @param index Index of entry to be updated.
     * @param o Entry to be set to.
     */
    public void set(int index, DBDatatype o) {
        if (o != null && o.getClass() != type) {
            throw new RuntimeException();
        }
        entries.set(index, o);
    }

    /**
     * Clears column.
     */
    public void clear() {
        entries = null;
        type = null;
        name = null;
    }
}
