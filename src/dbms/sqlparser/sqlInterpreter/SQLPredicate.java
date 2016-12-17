package dbms.sqlparser.sqlInterpreter;

import dbms.datatypes.DBDatatype;
import dbms.util.Operator;

public class SQLPredicate {
    private String columnName;
    private String columnName2;
    private Operator operator;
    private DBDatatype value;
    private boolean isAlwaysTrue = false;
    private boolean isAlwaysFalse = false;

    public SQLPredicate(String columnName, String operator,
                        DBDatatype value) {
        this.columnName = columnName;
        this.operator = toOperator(operator);
        this.value = value;
    }

    public SQLPredicate(String columnName, String operator,
                        String columnName2) {
        this.columnName = columnName;
        this.operator = toOperator(operator);
        this.columnName2 = columnName2;
    }

    public SQLPredicate(boolean predicateValue) {
        if (predicateValue) {
            isAlwaysTrue = true;
        } else {
            isAlwaysFalse = true;
        }
    }

    public boolean isAlwaysTrue() {
        return isAlwaysTrue;
    }

    public boolean isAlwaysFalse() {
        return isAlwaysFalse;
    }

    /**
     * @param o DBDatatype for left-side column of this predicate
     * @return
     */
    public boolean test(DBDatatype o) {
        return operator.apply(o, value);
    }

    /**
     * @param o1 DBDatatype for left-side column of this predicate
     * @param o2 DBDatatype for right-side column of this predicate.
     * @return
     */
    public boolean test(DBDatatype o1, DBDatatype o2) {
        return operator.apply(o1, o2);
    }

    /**
     * @param sqlPredicate
     * @param o1 DBDatatype for left-side column of this predicate
     * @param o2 DBDatatype for right-side column of this predicate
     * @param o3 DBDatatype for left-side column of predicate argument
     * @param o4 DBDatatype for right-side column of predicate argument
     * @return boolean value true/false
     */
    public boolean or(SQLPredicate sqlPredicate, DBDatatype o1, DBDatatype
            o2, DBDatatype o3, DBDatatype o4) {
        if (o4 == null && o3 == null && o2 == null) {
            return test(o1) || sqlPredicate.isAlwaysTrue();
        } else if (o1 == null && o2 == null && o4 == null) {
            return isAlwaysTrue() || sqlPredicate.test(o3);
        } else if (o1 == null && o2 == null) {
            return isAlwaysTrue || sqlPredicate.test(o3, o4);
        } else if (o2 == null && o4 == null) {
            return test(o1) || sqlPredicate.test(o3);
        } else if (o3 == null && o4 == null) {
            return test(o1, o2) || sqlPredicate.isAlwaysTrue();
        } else if (o2 == null) {
            return test(o1) || sqlPredicate.test(o3, o4);
        } else if (o4 == null) {
            return test(o1, o2) || sqlPredicate.test(o3);
        } else {
            return test(o1, o2) || sqlPredicate.test(o3, o4);
        }
    }

    /**
     * @param sqlPredicate
     * @param o1 DBDatatype for left-side column of this predicate
     * @param o2 DBDatatype for right-side column of this predicate
     * @param o3 DBDatatype for left-side column of predicate argument
     * @param o4 DBDatatype for right-side column of predicate argument
     * @return boolean value true/false
     */
    public boolean and(SQLPredicate sqlPredicate, DBDatatype o1, DBDatatype
            o2, DBDatatype o3, DBDatatype o4) {
        if (o4 == null && o3 == null && o2 == null) {
            return test(o1) && sqlPredicate.isAlwaysTrue();
        } else if (o1 == null && o2 == null && o4 == null) {
            return isAlwaysTrue() && sqlPredicate.test(o3);
        } else if (o1 == null && o2 == null) {
            return isAlwaysTrue && sqlPredicate.test(o3, o4);
        } else if (o2 == null && o4 == null) {
            return test(o1) && sqlPredicate.test(o3);
        } else if (o3 == null && o4 == null) {
            return test(o1, o2) && sqlPredicate.isAlwaysTrue();
        } else if (o2 == null) {
            return test(o1) && sqlPredicate.test(o3, o4);
        } else if (o4 == null) {
            return test(o1, o2) && sqlPredicate.test(o3);
        } else {
            return test(o1, o2) && sqlPredicate.test(o3, o4);
        }
    }

    public String getColumnName() {
        return columnName;
    }

    public DBDatatype getValue() {
        return value;
    }

    public Operator getOperator() {
        return operator;
    }

    public String getColumnName2() {
        return columnName2;
    }

    public boolean compareWithValue() {
        return (getColumnName2() == null);
    }

    private Operator toOperator(String operator) {
        switch (operator) {
            case "<":
                return Operator.SmallerThan;
            case ">":
                return Operator.GreaterThan;
            case "=":
                return Operator.Equal;
            case "<=":
                return Operator.SmallerThanOrEqual;
            case ">=":
                return Operator.GreaterThanOrEqual;
            case "!=":
                return Operator.NotEqual;
            default:
                return null;
        }
    }

    @Override
    public String toString() {
        return "SQLPredicate{" +
                "columnName='" + columnName + '\'' +
                ", columnName2='" + columnName2 + '\'' +
                ", operator=" + operator +
                ", value=" + value +
                ", isAlwaysTrue=" + isAlwaysTrue +
                ", isAlwaysFalse=" + isAlwaysFalse +
                '}';
    }
}
