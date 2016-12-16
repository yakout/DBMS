package dbms.sqlparser.sqlInterpreter.rules;

import dbms.backend.BackendController;
import dbms.datatypes.DBDatatype;
import dbms.exception.DatabaseNotFoundException;
import dbms.exception.IncorrectDataEntryException;
import dbms.exception.TableNotFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

public class InsertIntoTable implements DMLStatement {
    private String tableName;
    private Map<String, DBDatatype> entryMap;
    private int updateCount;
    private boolean insertWithNoColumns = false;

    public InsertIntoTable(String tableName, Map<String, DBDatatype> entryMap) {
        this.tableName = tableName;
        this.entryMap = entryMap;
    }

    public void insertWithNoColumns(boolean insertWithNoColumns) {
        this.insertWithNoColumns = insertWithNoColumns;
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
        if (insertWithNoColumns) {
            Collection<DBDatatype> entries = new ArrayList<>();
            Iterator<Map.Entry<String, DBDatatype>> it = entryMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                entries.add((DBDatatype) pair.getValue());
            }
            updateCount = BackendController.getInstance().insertIntoTable(tableName, entries);
            return;
        }
        updateCount = BackendController.getInstance().insertIntoTable(tableName, entryMap);
    }
}