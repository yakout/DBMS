package dbms.sqlparser.sqlInterpreter.rules;

import dbms.connection.XMLConnection;
import dbms.exception.DatabaseNotFoundException;

public class UseDatabase implements Expression {
    private String dbName;

    public UseDatabase(String dbName) {
        this.dbName = dbName;
    }

    public String getDbName() {
        return dbName;
    }

    @Override
    public void execute() throws DatabaseNotFoundException {
        XMLConnection.getInstance().useDatabase(dbName);
    }
}