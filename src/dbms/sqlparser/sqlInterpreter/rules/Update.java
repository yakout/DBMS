package dbms.sqlparser.sqlInterpreter.rules;

import dbms.connection.XMLConnection;

import java.util.Collection;

/**
 * Created by khlailmohammedyakout on 11/23/16.
 */
public class Update implements Expression {
    private String dbName;
    private String tableName;
    private Collection<String> columns;
    private Where where;

    @Override
    public void execute() {
        if (where == null) {
            XMLConnection.getInstance().update(dbName, tableName, columns);
        } else {
            XMLConnection.getInstance().update(dbName, tableName, columns, where);
        }
    }
}
