package dbms.sqlparser.sqlInterpreter.rules;

import dbms.connection.XMLConnection;
import dbms.sqlparser.sqlInterpreter.Expression;

import java.util.HashMap;

public class InsertIntoTable implements Expression {
    private String dbName;
    private String tableName;

    private HashMap<String, Object> entryMap;

    public InsertIntoTable(String tableName, HashMap<String, Object> entryMap) {
        this.tableName = tableName;
        this.entryMap = entryMap;
    }

    @Override
    public void execute() {
        XMLConnection.getInstance().insertIntoTable(dbName, tableName, entryMap);
    }
}
