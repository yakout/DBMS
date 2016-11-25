package dbms.sqlparser.sqlInterpreter;

import dbms.util.Operator;

public class SQLPredicate implements Predicate {
    private String columnName;
    private String columnName2;
    private Operator operator;
    private Object value;

    public SQLPredicate(String columnName, Operator operator,
                        Object value) {
        this.columnName = columnName;
        this.operator = operator;
        this.value = value;
    }

    public SQLPredicate(String columnName, Operator operator,
                        String columnName2) {
        this.columnName = columnName;
        this.operator = operator;
        this.columnName2 = columnName2;
    }

    /**
     *
     * @param o object for left-side column of this predicate
     * @return
     */
    @Override
    public boolean test(Object o) {
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
        return false;
    }

    /**
     *
     * @param o1 object for left-side column of this predicate
     * @param o2 object for left-side column of predicate argument.
     * @return
     */
    public boolean test(Object o1, Object o2) {
        if (o1 instanceof String && o2 instanceof String) {
            switch (operator) {
                case GreaterThan:
                    return (o1.toString().compareTo(o2.toString())) > 0;
                case Equal:
                    return o1.equals(o2);
                case SmallerThan:
                    return (o1.toString().compareTo(o2.toString())) < 0;
            }
        } else if (o1 instanceof Integer && o2 instanceof Integer) {
            switch (operator) {
                case GreaterThan:
                    return (Integer) o1 > (Integer) o2;
                case Equal:
                    return o1.equals(o2);
                case SmallerThan:
                    return (Integer) o1 < (Integer) o2;
            }
        }
        return false;
    }

    /**
     *
     * @param sqlPredicate
     * @param o1 object for left-side column of this predicate
     * @param o2 object for left-side column of predicate argument.
     * @return
     */
    public boolean or(SQLPredicate sqlPredicate, Object o1, Object o2) {
        return test(o1) || sqlPredicate.test(o2);
    }

    /**
     *
     * @param sqlPredicate
     * @param o1 object for left-side column of this predicate
     * @param o2 object for right-side column of this predicate
     * @param o3 object for left-side column of predicate argument
     * @param o4 object for right-side column of predicate argument
     * @return boolean value true/false
     */
    public boolean or(SQLPredicate sqlPredicate, Object o1, Object o2, Object o3, Object o4) {
        return test(o1, o2) || sqlPredicate.test(o3, o4);
    }

    /**
     *
     * @param sqlPredicate
     * @param o1 object for left-side column of this predicate
     * @param o2 object for left-side column of predicate argument.
     * @return
     */
    public boolean and(SQLPredicate sqlPredicate, Object o1, Object o2) {
        return test(o1) && sqlPredicate.test(o2);
    }

    /**
     *
     * @param sqlPredicate
     * @param o1 object for left-side column of this predicate
     * @param o2 object for right-side column of this predicate
     * @param o3 object for left-side column of predicate argument
     * @param o4 object for right-side column of predicate argument
     * @return boolean value true/false
     */
    public boolean and(SQLPredicate sqlPredicate, Object o1, Object o2, Object o3, Object o4) {
        return test(o1, o2) && sqlPredicate.test(o3, o4);
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

    public String getColumnName2() {
        return columnName2;
    }
}