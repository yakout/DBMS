package dbms.sqlparser.sqlInterpreter.rules;

import dbms.backend.BackendController;
import dbms.exception.DatabaseNotFoundException;
import dbms.exception.IncorrectDataEntryException;
import dbms.exception.SyntaxErrorException;
import dbms.exception.TableNotFoundException;
import dbms.sqlparser.sqlInterpreter.Where;
import dbms.ui.Formatter;
import dbms.util.ResultSet;

import java.util.Collection;

public class Select implements DMLStatement {
    private String tableName;
    private Collection<String> columns;
    private String orderBy;
    private boolean isAscending = false;
    private boolean isDistinct = false;
    private int updateCount = 0;

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
    public int getUpdateCount() {
        return updateCount;
    }

    @Override
    public void execute() throws DatabaseNotFoundException, TableNotFoundException, SyntaxErrorException, IncorrectDataEntryException {
        ResultSet resultSet = BackendController.getInstance().select(tableName, columns, where);
        if (isDistinct) resultSet.distinct();
        if (orderBy != null) resultSet.orderBy(isAscending, orderBy);
    	Formatter.getInstance().printTable(resultSet);
    }
}