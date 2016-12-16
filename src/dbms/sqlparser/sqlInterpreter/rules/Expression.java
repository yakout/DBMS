package dbms.sqlparser.sqlInterpreter.rules;

import dbms.exception.*;

public interface Expression {
    void execute() throws DatabaseNotFoundException,
            TableNotFoundException,
            SyntaxErrorException,
            DataTypeNotSupportedException,
            TableAlreadyCreatedException,
            DatabaseAlreadyCreatedException,
            IncorrectDataEntryException;
}