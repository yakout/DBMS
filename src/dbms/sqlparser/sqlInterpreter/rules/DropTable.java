package dbms.sqlparser.sqlInterpreter.rules;

import dbms.connection.XMLConnection;

public class DropTable implements Expression {
    private String tableName;

    public DropTable(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }

    @Override
    public void execute() {
        XMLConnection.getInstance().dropTable(tableName);
    }
}