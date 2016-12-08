package dbms.sqlparser.sqlInterpreter.rules;

import dbms.backend.BackendController;
import dbms.exception.DatabaseNotFoundException;
import dbms.exception.IncorrectDataEntryException;
import dbms.exception.SyntaxErrorException;
import dbms.exception.TableNotFoundException;
import dbms.sqlparser.sqlInterpreter.Where;

public class Delete implements DMLStatement {
	private String tableName;
	private Where where;
    private int updateCount;

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
	public int getUpdateCount() {
        return updateCount;
	}

	@Override
	public void execute() throws DatabaseNotFoundException, TableNotFoundException, SyntaxErrorException, IncorrectDataEntryException {
		BackendController.getInstance().delete(tableName, where); // TODO return int
	}
}