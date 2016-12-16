package dbms.util;

import java.util.ArrayList;

/**
 * Representation of a database.
 */
public class Database {
    /**
     * Name of database.
     */
    private String name = null;

    /**
     * {@link java.util.List} list of tables inside database.
     */
    private ArrayList<Table> tables = null;

    /**
     * Constructor of database.
     * @param name Name of the database.
     */
    public Database(String name) {
        this.name = name;
        tables = new ArrayList<Table>();
    }

    /**
     * Creates a new table inside this database.
     * @param name Name of the table.
     * @return A new table that is contained inside this database.
     */
    public Table createTable(String name) {
        Table table = new Table(name, this);
        tables.add(table);
        return table;
    }

    /**
     * Adds a new {@link Table} to this database.
     * @param table Table to be added.
     */
    public void addTable(Table table) {
        tables.add(table);
    }

    /**
     * Gets table that has given name inside this database.
     * @param tableName Name of table to be found.
     * @return {@link Table} table that has given name, if no table
     * is found with the given name it returns null.
     */
    public Table getTable(String tableName) {
        for (Table table : tables) {
            if (table.getName().equals(tableName)) {
                return table;
            }
        }
        return null;
    }

    /**
     * Gets database name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets database name.
     * @param name Database name.
     */
    public void setName(String name) {
        this.name = name;
    }
}
