package dbms.sqlparser.sqlInterpreter.rules;

import java.util.HashMap;
import java.util.Map;

import dbms.connection.XMLConnection;
import dbms.sqlparser.sqlInterpreter.Expression;

public class CreateTable implements Expression {
    private String tableName;
    private Map<String, Class> columns;

    public CreateTable(String tableName, Map<String, Class> columns) {
        this.tableName = tableName;
        this.columns = columns;
    }

    public String getTableName() {
        return tableName;
    }

    public Map<String, Class> getColumns() {
        return columns;
    }

    @Override
    public void execute() {
        XMLConnection.getInstance().createTable(tableName, columns);
    }
}