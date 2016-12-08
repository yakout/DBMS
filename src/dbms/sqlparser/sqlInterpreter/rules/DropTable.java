package dbms.sqlparser.sqlInterpreter.rules;

import dbms.backend.BackendController;
import dbms.exception.DatabaseNotFoundException;

public class DropTable implements DDLStatement {
    private String tableName;

    public DropTable(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }

    @Override
    public void execute() throws DatabaseNotFoundException {
        BackendController.getInstance().dropTable(tableName);
    }
}