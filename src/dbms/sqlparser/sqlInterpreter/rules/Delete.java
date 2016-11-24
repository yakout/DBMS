package dbms.sqlparser.sqlInterpreter.rules;

import dbms.connection.XMLConnection;
import dbms.sqlparser.sqlInterpreter.Expression;
import java.util.Collection;

public class Delete implements Expression {
    private String tableName;
    private Where where;

    public Delete(String tableName) {
        this.tableName = tableName;
    }

    public void setWhere(Where where) {
        this.where = where;
    }

    public String getTableName() {
        return tableName;
    }

    public Where getWhere() {
        return where;
    }

    @Override
    public void execute() {
        if (where == null) {
            XMLConnection.getInstance().delete(tableName);
        } else {
            XMLConnection.getInstance().delete(tableName, where);
        }
    }
}
