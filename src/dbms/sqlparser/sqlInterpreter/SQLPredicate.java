package dbms.sqlparser.sqlInterpreter;

import dbms.util.Operator;

public class SQLPredicate {
    private String columnName;
    private Operator operator;
    private String value;

    SQLPredicate(String columnName, Operator operator, String value) {
        this.columnName = columnName;
        this.operator = operator;
        this.value = value;
    }

    public String getColumnName() {
        return columnName;
    }

    public String getValue() {
        return value;
    }

    public Operator getOperator() {
        return operator;
    }
}
