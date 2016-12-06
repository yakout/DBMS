package dbms.sqlparser.sqlInterpreter.rules;

import dbms.backend.BackendController;
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
        BackendController.getInstance().dropDatabase(dbName);
    }
}