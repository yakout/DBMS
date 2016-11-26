package dbms.sqlparser.sqlInterpreter;

import dbms.util.Operator;

public class SQLPredicate implements Comparable<SQLPredicate> {
    @Override
    public int compareTo(SQLPredicate o) {
        return 0;
    }

    private String columnName;
    private String columnName2;
    private Operator operator;
    private Object value;
    private boolean isAlwaysTrue = false;
    private boolean isAlwaysFalse = false;

    public SQLPredicate(String columnName, String operator,
                        Object value) {
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
     *
     * @param o object for left-side column of this predicate
     * @return
     */
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
     * @param o2 object for right-side column of this predicate
     * @param o3 object for left-side column of predicate argument
     * @param o4 object for right-side column of predicate argument
     * @return boolean value true/false
     */
    public boolean or(SQLPredicate sqlPredicate, Object o1, Object o2, Object o3, Object o4) {
        if (o2 == null) {
            return test(o1) || sqlPredicate.test(o3, o4);
        } else if (o4 == null) {
            return test(o1, o2) || sqlPredicate.test(o3);
        } else if (o4 == null && o2 == null) {
            return test(o1) || sqlPredicate.test(o3);
        } else if (o1 == null && o2 == null) {
            return !isAlwaysFalse || sqlPredicate.test(o3, o4);
        } else if (o3 == null && o4 == null) {
            return test(o1, o2) || !sqlPredicate.isAlwaysFalse();
        } else {
            return test(o1, o2) || sqlPredicate.test(o3, o4);
        }
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
        if (o1 == null && o2 == null) {
        	return !isAlwaysFalse && sqlPredicate.test(o3, o4);
        } else if (o2 == null && o4 == null) {
        	return test(o1) && sqlPredicate.test(o3);
        } else if (o3 == null && o4 == null) {
        	return test(o1, o2) && !sqlPredicate.isAlwaysFalse();
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

    public Object getValue() {
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
            case "==":
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
                '}';
    }
}
