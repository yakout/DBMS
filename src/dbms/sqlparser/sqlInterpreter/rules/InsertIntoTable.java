package dbms.sqlparser.sqlInterpreter.rules;

import dbms.connection.XMLConnection;
import dbms.sqlparser.sqlInterpreter.Expression;
import java.util.Map;

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
    public void execute() {
        XMLConnection.getInstance().insertIntoTable(tableName, entryMap);
    }
}