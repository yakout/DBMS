package dbms.sqlparser.sqlInterpreter.rules;

import java.util.Map;

import dbms.backend.BackendController;
import dbms.exception.DatabaseNotFoundException;
import dbms.exception.IncorrectDataEntryException;
import dbms.exception.TableAlreadyCreatedException;

public class CreateTable implements Expression {
    private String tableName;
    private Map<String, Class> columns;

    public CreateTable(String tableName, Map<String, Class> columns) {
        this.tableName = tableName;
        this.columns = columns;
    }

    public String getTableName() {
        return tableName;
    }

    public Map<String, Class> getColumns() {
        return columns;
    }

    @Override
    public void execute() throws DatabaseNotFoundException, TableAlreadyCreatedException, IncorrectDataEntryException {
        BackendController.getInstance().createTable(tableName, columns);
    }
}