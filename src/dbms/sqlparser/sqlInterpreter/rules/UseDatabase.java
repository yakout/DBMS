package dbms.sqlparser.sqlInterpreter.rules;

import dbms.connection.XMLConnection;

public class UseDatabase implements Expression {
    private String dbName;

    public UseDatabase(String dbName) {
        this.dbName = dbName;
    }

    public String getDbName() {
        return dbName;
    }

    @Override
    public void execute() {
        XMLConnection.getInstance().useDatabase(dbName);
    }
}