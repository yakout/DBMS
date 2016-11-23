package dbms.sqlparser.sqlInterpreter.rules;

import dbms.connection.XMLConnection;

/**
 * Created by khlailmohammedyakout on 11/23/16.
 */
public class DropTable implements Expression {
    private String tableName;

    @Override
    public void execute() {
        XMLConnection.getInstance().dropTable(tableName);
    }
}
