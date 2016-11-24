package dbms.sqlparser.sqlInterpreter.rules;

import dbms.connection.XMLConnection;
import dbms.sqlparser.sqlInterpreter.Expression;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Update implements Expression {
    private String tableName;
    private Map<String, Object> entryMap;
    private Where where;

    public Update(String tableName, Map<String, Object> entryMap) {
        this.tableName = tableName;
        this.entryMap = entryMap;
    }

    public void setWhere(Where where) {
        this.where = where;
    }

    public Where getWhere() {
        return where;
    }

    public Map<String, Object> getEntryMap() {
        return entryMap;
    }

    public String getTableName() {
        return tableName;
    }

    @Override
    public void execute() {
        if (where == null) {
            XMLConnection.getInstance().update(tableName, entryMap);
        } else {
            XMLConnection.getInstance().update(tableName, entryMap, where);
        }
    }
}
