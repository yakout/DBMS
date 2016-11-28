package dbms.sqlparser.sqlInterpreter.rules;

import dbms.connection.XMLConnection;
import dbms.exception.DataTypeNotSupportedException;
import dbms.exception.DatabaseAlreadyCreatedException;
import dbms.exception.DatabaseNotFoundException;
import dbms.exception.IncorrectDataEntryException;
import dbms.exception.SyntaxErrorException;
import dbms.exception.TableAlreadyCreatedException;
import dbms.exception.TableNotFoundException;

public class AlterDrop implements Expression {

	private String tableName;
	private String columnName;

    public AlterDrop(String tableName , String columnName) {
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
	public void execute() throws DatabaseNotFoundException, TableNotFoundException, SyntaxErrorException,
			DataTypeNotSupportedException, TableAlreadyCreatedException, DatabaseAlreadyCreatedException,
			IncorrectDataEntryException {
		XMLConnection.getInstance().alterDrop(tableName, columnName);

	}

}
