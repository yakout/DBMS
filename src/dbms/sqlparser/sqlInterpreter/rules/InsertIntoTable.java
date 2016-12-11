package dbms.sqlparser.sqlInterpreter.rules;

import dbms.backend.BackendController;
import dbms.datatypes.DBDatatype;
import dbms.exception.DatabaseNotFoundException;
import dbms.exception.IncorrectDataEntryException;
import dbms.exception.TableNotFoundException;

import java.util.Map;

public class InsertIntoTable implements DMLStatement {
    private String tableName;
    private Map<String, DBDatatype> entryMap;
    private int updateCount;

    public InsertIntoTable(String tableName, Map<String, DBDatatype> entryMap) {
        this.tableName = tableName;
        this.entryMap = entryMap;
    }

    public String getTableName() {
        return tableName;
    }

    public Map<String, DBDatatype> getEntryMap() {
        return entryMap;
    }

    @Override
    public int getUpdateCount() {
        return updateCount;
    }

    @Override
    public void execute() throws DatabaseNotFoundException, TableNotFoundException, IncorrectDataEntryException {
        updateCount = BackendController.getInstance().insertIntoTable(tableName, entryMap);
    }
}