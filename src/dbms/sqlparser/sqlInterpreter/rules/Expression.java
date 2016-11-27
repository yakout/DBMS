package dbms.sqlparser.sqlInterpreter.rules;

import dbms.exception.DatabaseNotFoundException;
import dbms.exception.SyntaxErrorException;
import dbms.exception.TableNotFoundException;

public interface Expression {
    void execute() throws DatabaseNotFoundException, TableNotFoundException, SyntaxErrorException;
}
