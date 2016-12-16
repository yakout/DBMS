package dbms.sqlparser.sqlInterpreter.rules;

import dbms.backend.BackendController;
import dbms.datatypes.DBDatatype;
import dbms.exception.DatabaseNotFoundException;
import dbms.exception.IncorrectDataEntryException;
import dbms.exception.TableAlreadyCreatedException;

import java.util.Map;

public class CreateTable implements DDLStatement {
    private String tableName;
    private Map<String, Class<? extends DBDatatype>> columns;

    public CreateTable(final String tableName, final Map<String,
    		Class< ? extends DBDatatype>> columns) {
        this.tableName = tableName;
        this.columns = columns;
    }

    public String getTableName() {
        return tableName;
    }

    public Map<String, Class<? extends DBDatatype>> getColumns() {
        return columns;
    }

    @Override
    public void execute() throws DatabaseNotFoundException, TableAlreadyCreatedException, IncorrectDataEntryException {
        BackendController.getInstance().createTable(tableName, columns);
    }
}