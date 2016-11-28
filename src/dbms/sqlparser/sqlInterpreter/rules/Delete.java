package dbms.sqlparser.sqlInterpreter.rules;

import dbms.connection.XMLConnection;
import dbms.exception.DatabaseNotFoundException;
import dbms.exception.IncorrectDataEntryException;
import dbms.exception.SyntaxErrorException;
import dbms.exception.TableNotFoundException;

public class Delete implements Expression {
	private String tableName;
	private Where where;

	public Delete(String tableName) {
		this.tableName = tableName;
	}

	public void setWhere(Where where) {
		this.where = where;
	}

	public String getTableName() {
		return tableName;
	}

	public Where getWhere() {
		return where;
	}

	@Override
	public void execute() throws DatabaseNotFoundException, TableNotFoundException, SyntaxErrorException, IncorrectDataEntryException {
		XMLConnection.getInstance().delete(tableName, where);
	}
}