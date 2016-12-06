package dbms.sqlparser.sqlInterpreter.rules;

import java.util.Collection;

import dbms.backend.BackendController;
import dbms.exception.DatabaseNotFoundException;
import dbms.exception.IncorrectDataEntryException;
import dbms.exception.SyntaxErrorException;
import dbms.exception.TableNotFoundException;
import dbms.ui.Formatter;

public class Select implements Expression {
    private String tableName;
    private Collection<String> columns;
    private String orderBy;
    private boolean isAscending = false;

    private Where where;

    public Select(String tableName) {
        this.tableName = tableName;
    }

    public void setColumns(Collection<String> columns) {
        this.columns = columns;
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


    public String getTableName() {
    	return tableName;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public boolean isAscending() {
        return isAscending;
    }

    public void setAscending(boolean ascending) {
        isAscending = ascending;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    @Override
    public void execute() throws DatabaseNotFoundException, TableNotFoundException, SyntaxErrorException, IncorrectDataEntryException {
    	new Formatter().printTable(BackendController.getInstance().select(tableName, columns, where), orderBy, isAscending);
    }
}