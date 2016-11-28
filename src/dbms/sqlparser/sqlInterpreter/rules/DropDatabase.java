package dbms.sqlparser.sqlInterpreter.rules;

import dbms.connection.XMLConnection;
import dbms.exception.DatabaseNotFoundException;

public class DropDatabase implements Expression {
    private String dbName;

    public DropDatabase(String dbName) {
        this.dbName = dbName;
    }

    public String getDbName() {
        return dbName;
    }

    @Override
    public void execute() throws DatabaseNotFoundException {
        XMLConnection.getInstance().dropDatabase(dbName);
    }
}