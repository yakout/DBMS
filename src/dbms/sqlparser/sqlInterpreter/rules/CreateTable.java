package dbms.sqlparser.sqlInterpreter.rules;

import java.util.HashMap;

import dbms.connection.XMLConnection;
import dbms.sqlparser.sqlInterpreter.Expression;

/**
 * Created by ahmedyakout on 11/23/16.
 */
public class CreateTable implements Expression {
    private String tableName;
    private String dbName; // sql session database
    private HashMap<String, Class> columns;

    @Override
    public void execute() {
        XMLConnection.getInstance().createTable(tableName, dbName, columns);
    }
}
