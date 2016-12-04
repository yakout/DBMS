package dbms.xml;

import dbms.exception.DatabaseNotFoundException;
import dbms.exception.TableAlreadyCreatedException;
import dbms.exception.TableNotFoundException;
import dbms.util.Table;

public interface Parser {
	public void load(Table table) throws TableNotFoundException, DatabaseNotFoundException;

	public void writeTo(Table table) throws TableNotFoundException, DatabaseNotFoundException;

	void create(Table table) throws DatabaseNotFoundException, TableAlreadyCreatedException;
}
