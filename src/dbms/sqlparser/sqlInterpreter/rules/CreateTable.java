package dbms.sqlparser.sqlInterpreter.rules;

import java.util.HashMap;
import dbms.connection.XMLConnection;
import dbms.sqlparser.sqlInterpreter.Expression;

public class CreateTable implements Expression {
    private String tableName;
    private String dbName; // sql session database
    private HashMap<String, Class> columns;

    public CreateTable(String dbName, String tableName, HashMap<String, Class> columns) {
        this.dbName = dbName;
        this.tableName = tableName;
        this.columns = columns;
    }

    @Override
    public void execute() {
        XMLConnection.getInstance().createTable(tableName, dbName, columns);
    }
}
