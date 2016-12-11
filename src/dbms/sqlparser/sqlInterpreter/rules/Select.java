package dbms.sqlparser.sqlInterpreter.rules;

import dbms.backend.BackendController;
import dbms.exception.DatabaseNotFoundException;
import dbms.exception.IncorrectDataEntryException;
import dbms.exception.SyntaxErrorException;
import dbms.exception.TableNotFoundException;
import dbms.sqlparser.sqlInterpreter.Where;
import dbms.ui.Formatter;
import dbms.util.RecordSet;
import javafx.util.Pair;

import java.util.Collection;
import java.util.List;

public class Select implements DMLStatement {
    private String tableName;
    private Collection<String> columns;
    private List<Pair<String, Boolean>> orderBy;
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

    public List<Pair<String, Boolean>> getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(List<Pair<String, Boolean>> orderBy) {
        this.orderBy = orderBy;
    }

    @Override
    public int getUpdateCount() {
        return updateCount;
    }

    @Override
    public void execute() throws DatabaseNotFoundException, TableNotFoundException, SyntaxErrorException, IncorrectDataEntryException {
        RecordSet recordSet = BackendController.getInstance().select(tableName, columns, where);
        if (isDistinct) recordSet.distinct();
        if (orderBy != null) recordSet.orderBy(orderBy);
    	Formatter.getInstance().printTable(recordSet);
    }
}