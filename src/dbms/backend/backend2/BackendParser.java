package dbms.backend.backend2;

import dbms.exception.DatabaseNotFoundException;
import dbms.exception.TableAlreadyCreatedException;
import dbms.exception.TableNotFoundException;
import dbms.util.Table;

public interface BackendParser {
    dbms.backend.BackendParser getParser();

    void load(Table table)
            throws TableNotFoundException, DatabaseNotFoundException;

    void writeTo(Table table)
            throws TableNotFoundException, DatabaseNotFoundException;

    void create(Table table)
            throws DatabaseNotFoundException, TableAlreadyCreatedException;

    void dropTable(String dbName, String tableName)
            throws DatabaseNotFoundException;
}
