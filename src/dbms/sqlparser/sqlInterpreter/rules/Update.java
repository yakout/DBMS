package dbms.sqlparser.sqlInterpreter.rules;

import java.util.Map;

import dbms.connection.XMLConnection;
import dbms.exception.DataTypeNotSupportedException;
import dbms.exception.DatabaseNotFoundException;
import dbms.exception.SyntaxErrorException;
import dbms.exception.TableNotFoundException;

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
    public void execute() throws DatabaseNotFoundException, TableNotFoundException, SyntaxErrorException, DataTypeNotSupportedException {
    	XMLConnection.getInstance().update(tableName, values, columns, where);
    }
}