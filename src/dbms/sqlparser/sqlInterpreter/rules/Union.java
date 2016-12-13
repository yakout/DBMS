package dbms.sqlparser.sqlInterpreter.rules;

import dbms.backend.BackendController;
import dbms.exception.*;
import dbms.ui.Formatter;
import dbms.util.RecordSet;

import java.util.Arrays;
import java.util.List;

public class Union implements DMLStatement {
    private List<Select> selects;
    private int updateCount = 0;
    private boolean removeDuplicates = true;

    public Union(Select... selects) {
        this.selects = Arrays.asList(selects);
    }

    @Override
    public int getUpdateCount() {
        return updateCount;
    }

    @Override
    public void execute() throws DatabaseNotFoundException, TableNotFoundException,
            SyntaxErrorException, DataTypeNotSupportedException,
            TableAlreadyCreatedException, DatabaseAlreadyCreatedException,
            IncorrectDataEntryException {

        RecordSet result = new RecordSet();
        for (Select select : selects) {
            // TODO handel order by and distinct
            result.union(BackendController.getInstance().select(select.getTableName(),
                    select.getColumns(), select.getWhere()), removeDuplicates);
        }

        Formatter.getInstance().printTable(result);
    }
}
