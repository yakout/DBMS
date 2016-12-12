package dbms.test.onlineTester;

import dbms.exception.*;
import dbms.sqlparser.SQLParser;
import org.junit.Test;

public class DbmsSmokeTesting {

    @Test
    public void test1() throws SyntaxErrorException,
            DatabaseNotFoundException,
            DataTypeNotSupportedException, TableAlreadyCreatedException,
            TableNotFoundException, DatabaseAlreadyCreatedException,
            IncorrectDataEntryException {
        SQLParser.getInstance().parse("CREATE DATABASE sample").execute();
    }
}
