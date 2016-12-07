package dbms.sqlparser.sqlInterpreter.rules;

import dbms.backend.BackendController;
import dbms.exception.*;
import dbms.ui.Formatter;
import dbms.util.ResultSet;

import java.util.Arrays;
import java.util.List;

public class Union implements Expression {
    private List<Select> selects;

    public Union(Select... selects) {
        this.selects = Arrays.asList(selects);
    }

    @Override
    public void execute() throws DatabaseNotFoundException, TableNotFoundException,
            SyntaxErrorException, DataTypeNotSupportedException,
            TableAlreadyCreatedException, DatabaseAlreadyCreatedException,
            IncorrectDataEntryException {

        ResultSet result = new ResultSet();
        for (Select select : selects) {
            // TODO handel order by and distinct
            result.union(BackendController.getInstance().select(select.getTableName(),
                    select.getColumns(), select.getWhere()));
        }

        Formatter.getInstance().printTable(result);
    }
}
