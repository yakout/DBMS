package dbms.sqlparser.sqlInterpreter.rules;

import dbms.connection.XMLConnection;
import dbms.sqlparser.sqlInterpreter.Expression;

/**
 * Created by khlailmohammedyakout on 11/23/16.
 */
public class CreateDatabase implements Expression {
    private String dbName;

    @Override
    public void execute() {
        XMLConnection.getInstance().createDatabase(dbName);
    }
}
