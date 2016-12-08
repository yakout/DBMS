package dbms.sqlparser.sqlInterpreter.rules;

import dbms.backend.BackendController;
import dbms.exception.DatabaseNotFoundException;
import dbms.exception.IncorrectDataEntryException;
import dbms.exception.SyntaxErrorException;
import dbms.exception.TableNotFoundException;
import dbms.sqlparser.sqlInterpreter.Where;

import java.util.Map;

public class Update implements DMLStatement {
    private String tableName;
    private Map<String, Object> values;
    private Map<String, String> columns;
    private Where where;
    private int updateCount;

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
    public int getUpdateCount() {
        return updateCount;
    }

    @Override
    public void execute() throws DatabaseNotFoundException, TableNotFoundException, SyntaxErrorException, IncorrectDataEntryException {
        updateCount = BackendController.getInstance().update(tableName, values, columns, where);
    }
}