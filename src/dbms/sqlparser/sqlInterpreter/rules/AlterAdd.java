package dbms.sqlparser.sqlInterpreter.rules;

import dbms.backend.BackendController;
import dbms.exception.*;

public class AlterAdd implements DDLStatement {

	private String tableName;
	private String columnName;
	private Class dataType;

	public AlterAdd(String tableName, String columnName, Class dataType) {
		this.tableName = tableName;
		this.columnName = columnName;
		this.dataType = dataType;
	}

	public String getTableName() {
		return tableName;
	}

	public String getColumnName() {
		return columnName;
	}

	public Class getDataType() {
		return dataType;
	}

	@Override
	public void execute() throws DatabaseNotFoundException, TableNotFoundException, SyntaxErrorException,
			DataTypeNotSupportedException, TableAlreadyCreatedException, DatabaseAlreadyCreatedException,
			IncorrectDataEntryException {
		BackendController.getInstance().alterAdd(tableName, columnName, dataType);

	}

}
