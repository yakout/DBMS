package dbms.sqlparser.sqlInterpreter.rules;

import dbms.backend.BackendController;
import dbms.exception.*;

public class AlterDrop implements DDLStatement {
    private String tableName;
    private String columnName;

    public AlterDrop(String tableName, String columnName) {
        this.tableName = tableName;
        this.columnName = columnName;
    }

    public String getTableName() {
        return tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    @Override
    public void execute() throws DatabaseNotFoundException,
    TableNotFoundException, SyntaxErrorException, DataTypeNotSupportedException,
    TableAlreadyCreatedException, DatabaseAlreadyCreatedException,
    IncorrectDataEntryException {
        BackendController.getInstance().alterDrop(tableName, columnName);

    }
}
