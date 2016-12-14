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
    private RecordSet recordSet = null;

    public Union(List<Select> selects, boolean removeDuplicates) {
        this.selects = selects;
        this.removeDuplicates = removeDuplicates;
    }

    @Override
    public int getUpdateCount() {
        return updateCount;
    }

    public RecordSet getRecordSet() {
        return recordSet;
    }

    @Override
    public void execute() throws DatabaseNotFoundException, TableNotFoundException,
            SyntaxErrorException, DataTypeNotSupportedException,
            TableAlreadyCreatedException, DatabaseAlreadyCreatedException,
            IncorrectDataEntryException {

        Iterator<Select> it = selects.iterator();
        Select firstSelect = it.next();
        firstSelect.execute();
        RecordSet firstSelectRecordSet = firstSelect.getRecordSet();
        while (it.hasNext()) {
            Select select = it.next();
            select.execute();
            recordSet = firstSelectRecordSet.union(select.getRecordSet(), removeDuplicates);
        }

        Formatter.getInstance().printTable(recordSet);
        recordSet.reset();
    }
}
