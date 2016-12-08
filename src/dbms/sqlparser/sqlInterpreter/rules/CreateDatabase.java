package dbms.sqlparser.sqlInterpreter.rules;

import dbms.backend.BackendController;
import dbms.exception.DatabaseAlreadyCreatedException;

public class CreateDatabase implements DDLStatement {
    private String dbName;

    public CreateDatabase(String dbName) {
        this.dbName = dbName;
    }

    public String getDbName() {
        return dbName;
    }

    @Override
    public void execute() throws DatabaseAlreadyCreatedException {
        BackendController.getInstance().createDatabase(dbName);
    }
}