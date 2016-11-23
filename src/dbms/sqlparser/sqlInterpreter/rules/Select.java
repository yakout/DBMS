package dbms.sqlparser.sqlInterpreter.rules;

import dbms.connection.XMLConnection;

import java.util.Collection;

/**
 * Created by khlailmohammedyakout on 11/23/16.
 */
public class Select implements Expression {
    private String dbName;
    private String tableName;
    private Collection<String> columns;

    private boolean selectAll = false;
    private Where where;

    @Override
    public void execute() {
        if (where == null) {
            if (selectAll) {
                XMLConnection.getInstance().selectAll(dbName, tableName);
            } else  {
                XMLConnection.getInstance().select(dbName, tableName, columns);
            }
        } else {
            if (selectAll) {
                XMLConnection.getInstance().selectAll(dbName, tableName, where);
            } else  {
                XMLConnection.getInstance().select(dbName, tableName, columns, where);
            }
        }
    }
}
