package dbms.sqlparser.sqlInterpreter;

import dbms.exception.TypeNotSupportedException;
import dbms.util.Operator;

public class SQLPredicate {
    private String columnName;
    private Operator operator;
    private Object value;

    public SQLPredicate(String columnName, Operator operator, Object value) {
        this.columnName = columnName;
        this.operator = operator;
        this.value = value;
    }

    public boolean test(Object o) throws TypeNotSupportedException {
        if (o instanceof String && value instanceof String) {
            switch (operator) {
                case GreaterThan:
                    return (o.toString().compareTo(value.toString())) > 0;
                case Equal:
                    return o.equals(value);
                case SmallerThan:
                    return (o.toString().compareTo(value.toString())) < 0;
            }
        } else if (o instanceof Integer && value instanceof Integer) {
            switch (operator) {
                case GreaterThan:
                    return (Integer) o > (Integer) value;
                case Equal:
                    return o.equals(value);
                case SmallerThan:
                    return (Integer) o < (Integer) value;
            }
        }
        throw new TypeNotSupportedException();
    }

    public boolean or(SQLPredicate sqlPredicate, Object o1, Object o2) throws TypeNotSupportedException {
        return test(o1) || sqlPredicate.test(o2);
    }

    public boolean and(SQLPredicate sqlPredicate, Object o1, Object o2) throws TypeNotSupportedException {
        return test(o1) && sqlPredicate.test(o2);
    }

    public String getColumnName() {
        return columnName;
    }

    public Object getValue() {
        return value;
    }

    public Operator getOperator() {
        return operator;
    }
}
