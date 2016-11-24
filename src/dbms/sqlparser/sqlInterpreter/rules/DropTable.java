package dbms.sqlparser.sqlInterpreter.rules;

import dbms.connection.XMLConnection;
import dbms.sqlparser.sqlInterpreter.Expression;

public class DropTable implements Expression {
    private String tableName;

    public DropTable(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public void execute() {
        XMLConnection.getInstance().dropTable(tableName);
    }
}
