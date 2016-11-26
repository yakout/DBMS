package dbms.sqlparser.sqlInterpreter.rules;

import dbms.connection.XMLConnection;

import java.util.Map;

public class Update implements Expression {
    private String tableName;
    private Map<String, Object> values;
    private Map<String, String> columns;
    private Where where;

    public Update(String tableName, Map<String, Object> values, Map<String, String> columns) {
        this.tableName = tableName;
        this.values = values;
        this.columns = columns;
    }

    public void setWhere(Where where) {
        this.where = where;
    }

    public Where getWhere() {
        return where;
    }

    public Map<String, Object> getValues() {
        return values;
    }

    public Map<String, String> getColumns() {
        return columns;
    }

    public String getTableName() {
        return tableName;
    }

    @Override
    public void execute() {
        if (where == null) {
            XMLConnection.getInstance().update(tableName, values, columns);
        } else {
            XMLConnection.getInstance().update(tableName, values, columns, where);
        }
    }
}