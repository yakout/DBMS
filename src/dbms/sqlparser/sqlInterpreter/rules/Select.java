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
    private RecordSet recordSet;

    private Where where;

    /**
     * Sets the name of the local table name.
     * @param tableName the name of current table.
     */
    public Select(String tableName) {
        this.tableName = tableName;
    }

    /**
     * @return {@link Where} the local where statement.
     */
    public Where getWhere() {
        return where;
    }

    /**
     * @return {@link Where} the local where statement.
     */
    public void setWhere(final Where where) {
        this.where = where;
    }

    /**
     * @return {@link Collection} collection of columns.
     */
    public Collection<String> getColumns() {
        return columns;
    }

    /**
     * Sets the local Collection of columns.
     * @param columns {@link Collection} columns of current table.
     */
    public void setColumns(final Collection<String> columns) {
        this.columns = columns;
    }

    /**
     * @return the name of the table.
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * @return {@link List} list of columns orders.
     */
    public List<Pair<String, Boolean>> getOrderBy() {
        return orderBy;
    }

    /**
     * @param orderBy {@link List} list of columns orders.
     */
    public void setOrderBy(final List<Pair<String, Boolean>> orderBy) {
        this.orderBy = orderBy;
    }

    public boolean isDistinct() {
        return isDistinct;
    }

    public RecordSet getRecordSet() {
        return recordSet;
    }

    public void distinct() {
        isDistinct = true;
    }

    @Override
    public int getUpdateCount() {
        return updateCount;
    }

    @Override
    public void execute() throws DatabaseNotFoundException,
            TableNotFoundException, SyntaxErrorException,
            IncorrectDataEntryException {
        if (orderBy != null) {
            recordSet = BackendController.getInstance().select(
                    tableName, null, where);
            recordSet.orderBy(orderBy, columns);
        } else {
            recordSet = BackendController.getInstance().select(
                    tableName, columns, where);
        }
        if (isDistinct) recordSet.distinct();
        Formatter.getInstance().printTable(recordSet);
        recordSet.reset();
    }
}