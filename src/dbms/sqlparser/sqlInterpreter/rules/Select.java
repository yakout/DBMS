package dbms.sqlparser.sqlInterpreter.rules;

import java.util.Collection;

import dbms.connection.XMLConnection;
import dbms.exception.DatabaseNotFoundException;
import dbms.exception.SyntaxErrorException;
import dbms.exception.TableNotFoundException;
import dbms.ui.Formatter;

public class Select implements Expression {
    private String tableName;
    private Collection<String> columns;

    private boolean selectAll = false;
    private Where where;

    public Select(String tableName) {
        this.tableName = tableName;
    }

    public void setColumns(Collection<String> columns) {
        this.columns = columns;
    }

    public void setSelectAll(boolean selectAll) {
        this.selectAll = selectAll;
    }

    public void setWhere(Where where) {
        this.where = where;
    }

    public Where getWhere() {
    	return where;
    }

    public Collection<String> getColumns() {
    	return columns;
    }

    public boolean getSelectAll() {
    	return selectAll;
    }

    public String getTableName() {
    	return tableName;
    }

    @Override
    public void execute() throws DatabaseNotFoundException, TableNotFoundException, SyntaxErrorException {
        if (where == null) {
            if (selectAll) {
                new Formatter().printTable(XMLConnection.getInstance().select(tableName, null, null));
            } else  {
            	new Formatter().printTable(XMLConnection.getInstance().select(tableName, columns, null));
            }
        } else {
            if (selectAll) {
            	new Formatter().printTable(XMLConnection.getInstance().select(tableName, null, where));
            } else  {
            	new Formatter().printTable(XMLConnection.getInstance().select(tableName, columns, where));
            }
        }
    }
}