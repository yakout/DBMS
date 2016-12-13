package dbms.sqlparser.sqlInterpreter.rules;

import dbms.exception.*;
import dbms.ui.Formatter;
import dbms.util.RecordSet;

import java.util.Iterator;
import java.util.List;

public class Union implements DMLStatement {
    private List<Select> selects;
    private int updateCount = 0;
    private boolean removeDuplicates = true; // default

    public Union(List<Select> selects, boolean removeDuplicates) {
        this.selects = selects;
        this.removeDuplicates = removeDuplicates;
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

        Iterator<Select> it = selects.iterator();
        Select firstSelect = it.next();
        firstSelect.execute();
        RecordSet recordSet = firstSelect.getRecordSet();
        RecordSet result = null;
        while (it.hasNext()) {
            Select select = it.next();
            select.execute();
            result = recordSet.union(select.getRecordSet(), removeDuplicates);
        }

        Formatter.getInstance().printTable(result);
        result.reset();
    }
}
