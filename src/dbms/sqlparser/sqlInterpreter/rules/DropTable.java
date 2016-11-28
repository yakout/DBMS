package dbms.sqlparser.sqlInterpreter.rules;

import dbms.connection.XMLConnection;
import dbms.exception.DatabaseNotFoundException;

public class DropTable implements Expression {
    private String tableName;

    public DropTable(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }

    @Override
    public void execute() throws DatabaseNotFoundException {
        XMLConnection.getInstance().dropTable(tableName);
    }
}