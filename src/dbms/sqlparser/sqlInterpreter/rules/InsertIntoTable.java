package dbms.sqlparser.sqlInterpreter.rules;

import dbms.connection.XMLConnection;

import java.util.HashMap;

/**
 * Created by khlailmohammedyakout on 11/23/16.
 */
public class InsertIntoTable implements Expression {
    private String dbName;
    private String tableName;

    private HashMap<String, Object> entryMap;

    @Override
    public void execute() {
        XMLConnection.getInstance().insertIntoTable(dbName, tableName, entryMap);
    }
}
