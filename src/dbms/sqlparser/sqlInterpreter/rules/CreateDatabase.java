package dbms.sqlparser.sqlInterpreter.rules;

import dbms.connection.XMLConnection;
import dbms.exception.DatabaseAlreadyCreatedException;

public class CreateDatabase implements Expression {
    private String dbName;

    public CreateDatabase(String dbName) {
        this.dbName = dbName;
    }

    public String getDbName() {
        return dbName;
    }

    @Override
    public void execute() throws DatabaseAlreadyCreatedException {
        XMLConnection.getInstance().createDatabase(dbName);
    }
}