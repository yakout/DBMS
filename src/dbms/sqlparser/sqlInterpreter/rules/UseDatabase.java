package dbms.sqlparser.sqlInterpreter.rules;

import dbms.backend.BackendController;
import dbms.exception.DatabaseNotFoundException;

public class UseDatabase implements DDLStatement {
    private String dbName;

    public UseDatabase(String dbName) {
        this.dbName = dbName;
    }

    public String getDbName() {
        return dbName;
    }

    @Override
    public void execute() throws DatabaseNotFoundException {
        BackendController.getInstance().useDatabase(dbName);
    }
}