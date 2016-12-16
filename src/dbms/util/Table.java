package dbms.util;

import dbms.datatypes.DBDatatype;
import dbms.exception.IncorrectDataEntryException;
import dbms.exception.SyntaxErrorException;
import dbms.sqlparser.sqlInterpreter.Condition;
import javafx.util.Pair;

import java.util.*;

/**
 * Representation of a table inside a database.
 */
public class Table {
    /**
     * Reference to database containing table.
     */
    private Database database = null;
    /**
     * Table name.
     */
    private String name = null;
    /**
     * List of columns inside table.
     */
    private List<Column> columns = null;
    /**
     * Number of rows inside table.
     */
    private int size;

    /**
     * Creates a new table with a given table name.
     * @param name Table name.
     */
    public Table(String name) {
        this.name = name;
        columns = new ArrayList<Column>();
        size = 0;
    }

    /**
     * Creates a new table with a given table name
     * and a reference to the containing database.
     * @param name Table Name.
     * @param database {@link Database} database reference.
     */
    public Table(String name, Database database) {
        this.name = name;
        this.database = database;
        columns = new ArrayList<Column>();
        size = 0;
    }

    /**
     * Adds a new {@link Column} to table.
     * @param col
     */
    public void addColumn(Column col) {
        columns.add(col);
    }

    /**
     * Gets table name.
     * @return Table name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets reference to containing database.
     * @return database.
     */
    public Database getDatabase() {
        return database;
    }

    /**
     * Sets database containing this table.
     * @param database Database containing this table.
     */
    public void setDatabase(Database database) {
        this.database = database;
        this.database.addTable(this);
    }

    /**
     * Gets a {@link List} of {@link Column} inside table.
     * @return list of columns.
     */
    public List<Column> getColumns() {
        return columns;
    }

    /**
     * Sets a {@link List} of {@link Column} inside table.
     * @param columns Columns to be set to table.
     */
    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    /**
     * Gets column with a given index from table.
     * @param index Index of the column inside table.
     * @return {@link Column} Column that has the given index.
     * @throws IndexOutOfBoundsException If index is invalid.
     */
    public Column getColumn(int index) {
        return columns.get(index);
    }

    /**
     * Gets column with the given name (case-insensitive) from table.
     * @param name Column name.
     * @return {@link Column} Column that has the given name, returns null
     * if not found.
     */
    public Column getColumn(String name) {
        for (Column col : columns) {
            if (col.getName().equalsIgnoreCase(
                    name)) {
                return col;
            }
        }
        return null;
    }

    /**
     * Gets number of entries inside table.
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     * Sets number of entries inside table
     * (used to support serializing buffers mainly).
     * @param size Number of entries inside table.
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * Inserts a new row inside this table.
     * @param entryMap {@link Map} mapping between column names (case-insensitive)
     * and their {@link DBDatatype} values.
     * @return Update count (number of rows that are affected).
     * @throws IncorrectDataEntryException In case a given column name or a value are invalid.
     */
    public int insertRow(Map<String, DBDatatype> entryMap)
            throws IncorrectDataEntryException {
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

    /**
     * Inserts a complete row inside table, has to be in the order
     * of columns inside table.
     * @param entries {@link Collection} collection of {@link DBDatatype}
     * of values to be inserted.
     * @return Update count (number of rows that are affected).
     * @throws IncorrectDataEntryException In case a given value is incompatible
     * with a column type.
     */
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

    /**
     * Deletes rows from table given a condition.
     * @param condition {@link Condition} condition specified to validate row
     * deletion (can be null which means it deletes all rows inside table).
     * @return Update count (number of rows that are affected).
     * @throws IncorrectDataEntryException In case condition is invalid.
     * @throws SyntaxErrorException In case syntax of condition is invalid.
     */
    public int delete(Condition condition)
            throws IncorrectDataEntryException, SyntaxErrorException {
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

    /**
     * Selects and returns a number of rows that are contained
     * inside a {@link RecordSet}.
     * @param columns {@link Collection} collection of column names (case-insensitive).
     * @param condition {@link Condition} condition to be specified in order of a row
     * to be selected.
     * @return {@link RecordSet} that contains the selected rows.
     * @throws IncorrectDataEntryException
     * @throws SyntaxErrorException
     */
    public RecordSet select(Collection<String> columns, Condition condition)
            throws IncorrectDataEntryException, SyntaxErrorException {
        Collection<String> columnsLower = new ArrayList<String>();
        if (columns != null) {
            for (String col : columns) {
                if (!hasColumn(col)) {
                    throw new IncorrectDataEntryException("Column not found!");
                }
                columnsLower.add(col);
            }
        }
        List<Pair<String, Class<? extends DBDatatype>>> columnsResultSet
                = new ArrayList<Pair<String, Class<? extends DBDatatype>>>();
        RecordSet res = new RecordSet();
        if (columns == null) {
            for (Column col : this.columns) {
                columnsResultSet.add(new Pair<String, Class<? extends DBDatatype>>(col.getName(),
                        col.getType()));
            }
        } else {
            for (String colName : columns) {
                for (Column col : this.columns) {
                    if (col.getName().equalsIgnoreCase(colName)) {
                        columnsResultSet.add(new Pair<String, Class<? extends DBDatatype>>(colName,
                                col.getType()));
                    }
                }
            }
        }
        res.setColumnList(columnsResultSet);
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

    /**
     * Update entries inside table given a condition and a mapping
     * of values to be set.
     * @param values {@link Map} mapping between column names and values to be
     * set in case condition is met.
     * @param columns {@link Map} mapping between two column names, the first column name
     * value copies the second column name value if condition is met.
     * @param condition {@link Condition} condition to be met in order of
     * update to get applied.
     * @return Update count (number of rows that are affected).
     * @throws IncorrectDataEntryException In case given values are incompatible with column
     * types or a given column is not found inside table.
     * @throws SyntaxErrorException In case syntax of condition is invalid.
     */
    public int update(Map<String, DBDatatype> values,
                      Map<String, String> columns, Condition condition)
            throws IncorrectDataEntryException, SyntaxErrorException {
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

    /**
     * Alters table to add a new column that its values are set to have
     * null values with the same number of rows inside table.
     * @param colName Column name to be added.
     * @param datatype {@link Class<extends DBDatatype>} class that is registered
     * as one of the supported data types for table.
     * @throws IncorrectDataEntryException In case a given column name or data type are invalid.
     */
    public void alterAdd(String colName, Class<? extends DBDatatype> datatype)
            throws IncorrectDataEntryException {
        Column col = new Column(colName, datatype);
        for (int i = 0; i < size; i++) {
            col.addEntry(null);
        }
        addColumn(col);
    }

    /**
     * Alters table to remove an existing column from table.
     * @param colName Name of the column to be removed from table.
     * @throws IncorrectDataEntryException In case the given column doesn't exist.
     */
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

    /**
     * Clears table completely from entries and columns.
     */
    public void clear() {
        for (Column col : columns) {
            col.clear();
        }
        columns.clear();
        size = 0;
    }

    /**
     * Copies given table data to this table,
     * meaning all data inside this table are altered.
     * @param table {@link Table} Table to be copied from.
     */
    public void setTable(Table table) {
        this.name = table.getName();
        this.database = table.getDatabase();
        this.size = table.getSize();
        for (Column col : table.getColumns()) {
            this.addColumn(col);
        }
    }
}