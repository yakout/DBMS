package dbms.sqlparser.sqlInterpreter.rules;

import java.util.Map;

import dbms.backend.BackendController;
import dbms.exception.DatabaseNotFoundException;
import dbms.exception.IncorrectDataEntryException;
import dbms.exception.TableNotFoundException;

public class InsertIntoTable implements Expression {
    private String tableName;
    private Map<String, Object> entryMap;

    public InsertIntoTable(String tableName, Map<String, Object> entryMap) {
        this.tableName = tableName;
        this.entryMap = entryMap;
    }

    public String getTableName() {
        return tableName;
    }

    public Map<String, Object> getEntryMap() {
        return entryMap;
    }

    @Override
    public void execute() throws DatabaseNotFoundException, TableNotFoundException, IncorrectDataEntryException {
        BackendController.getInstance().insertIntoTable(tableName, entryMap);
    }
}