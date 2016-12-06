package dbms.sqlparser.sqlInterpreter.rules;

import dbms.backend.BackendController;
import dbms.exception.DataTypeNotSupportedException;
import dbms.exception.DatabaseAlreadyCreatedException;
import dbms.exception.DatabaseNotFoundException;
import dbms.exception.IncorrectDataEntryException;
import dbms.exception.SyntaxErrorException;
import dbms.exception.TableAlreadyCreatedException;
import dbms.exception.TableNotFoundException;
import dbms.ui.Formatter;

public class AlterAdd implements Expression {

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
