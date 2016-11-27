package dbms.sqlparser.sqlInterpreter.rules;

import dbms.exception.DataTypeNotSupportedException;
import dbms.exception.DatabaseAlreadyCreatedException;
import dbms.exception.DatabaseNotFoundException;
import dbms.exception.SyntaxErrorException;
import dbms.exception.TableAlreadyCreatedException;
import dbms.exception.TableNotFoundException;

public interface Expression {
    void execute() throws DatabaseNotFoundException,
    TableNotFoundException,
    SyntaxErrorException,
    DataTypeNotSupportedException,
    TableAlreadyCreatedException,
    DatabaseAlreadyCreatedException;
}
