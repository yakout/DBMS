package dbms.sqlparser.sqlInterpreter.rules;

import dbms.connection.XMLConnection;

/**
 * Created by ahmedyakout on 11/23/16.
 */
public class DropDatabase implements Expression {
    private String dbName;

    @Override
    public void execute() {
        XMLConnection.getInstance().dropDatabase(dbName);
    }
}
