package dbms.sqlparser.sqlInterpreter.rules;

import dbms.connection.XMLConnection;
import dbms.sqlparser.sqlInterpreter.Expression;

public class UseDatabase implements Expression {
    private String dbName;

    public UseDatabase(String dbName) {
        this.dbName = dbName;
    }

    @Override
    public void execute() {
        XMLConnection.getInstance().useDatabase(dbName);
    }
}
