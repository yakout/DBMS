package dbms.sqlparser.sqlInterpreter.rules;

import dbms.connection.XMLConnection;
import dbms.sqlparser.sqlInterpreter.Expression;
import java.util.Collection;

public class Delete implements Expression {
    private String dbName;
    private String tableName;
    private Collection<String> columns;
    private Where where;


    @Override
    public void execute() {
        if (where == null) {
            XMLConnection.getInstance().delete(dbName, tableName, columns);
        } else {
            XMLConnection.getInstance().delete(dbName, tableName, columns, where);
        }
    }
}
