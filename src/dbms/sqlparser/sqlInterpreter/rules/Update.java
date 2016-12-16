package dbms.sqlparser.sqlInterpreter.rules;

import dbms.backend.BackendController;
import dbms.datatypes.DBDatatype;
import dbms.exception.DatabaseNotFoundException;
import dbms.exception.IncorrectDataEntryException;
import dbms.exception.SyntaxErrorException;
import dbms.exception.TableNotFoundException;
import dbms.sqlparser.sqlInterpreter.Where;

import java.util.Map;

public class Update implements DMLStatement {
    private String tableName;
    private Map<String, DBDatatype> values;
    private Map<String, String> columns;
    private Where where;
    private int updateCount;

    public Update(final String tableName, final Map<String,
    		DBDatatype> values, final Map<String, String> columns) {
        this.tableName = tableName;
        this.values = values;
        this.columns = columns;
    }
    /**
     * 
     * @return {@link Where} the where statement.
     */
    public Where getWhere() {
        return where;
    }
    /**
     * 
     * @param where {@link Where} sets the where statement. 
     */
    public void setWhere(Where where) {
        this.where = where;
    }
    /**
     * 
     * @return {@link Map} the new values of data.
     */
    public Map<String, DBDatatype> getValues() {
        return values;
    }
    /**
     * 
     * @return {@link Map} the old columns (before updation).
     */
    public Map<String, String> getColumns() {
        return columns;
    }

    public String getTableName() {
        return tableName;
    }

    @Override
    public int getUpdateCount() {
        return updateCount;
    }

    @Override
    public void execute() throws DatabaseNotFoundException,
    TableNotFoundException, SyntaxErrorException, IncorrectDataEntryException {
        updateCount = BackendController.getInstance().update(
        		tableName, values, columns, where);
    }
}