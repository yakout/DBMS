package dbms.sqlparser.sqlInterpreter.rules;

import dbms.connection.XMLConnection;
import dbms.sqlparser.sqlInterpreter.Condition;
import dbms.sqlparser.sqlInterpreter.Expression;

import java.util.Collection;

public class Select implements Expression {
    private String dbName;
    private String tableName;
    private Collection<String> columns;

    private boolean selectAll = false;
    private Where where;

    public Select(String tableName) {
        this.tableName = tableName;
    }

    public void setColumns(Collection<String> columns) {
        this.columns = columns;
    }

    public void setSelectAll(boolean selectAll) {
        this.selectAll = selectAll;
    }

    public void setWhere(Where where) {
        this.where = where;
    }

    @Override
    public void execute() {
        if (where == null) {
            if (selectAll) {
                XMLConnection.getInstance().selectAll(dbName, tableName);
            } else  {
                XMLConnection.getInstance().select(dbName, tableName, columns);
            }
        } else {
            if (selectAll) {
                XMLConnection.getInstance().selectAll(dbName, tableName, where);
            } else  {
                XMLConnection.getInstance().select(dbName, tableName, columns, where);
            }
        }
    }
}
