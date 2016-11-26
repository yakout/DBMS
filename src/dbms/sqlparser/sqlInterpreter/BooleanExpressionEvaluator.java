package dbms.sqlparser.sqlInterpreter;

import dbms.sqlparser.sqlInterpreter.rules.BooleanOperator;

import java.util.Stack;

public class BooleanExpressionEvaluator {
    private Stack<Object> postfix;

    public BooleanExpressionEvaluator(Stack<Object> postfix) {
        this.postfix = postfix;
    }


    private Object fetchFromColumn(String columnName) {
        return null;
    }


    public boolean evaluate() {
        Stack<Object> helperStack = new Stack<>();

        while(!postfix.isEmpty()) {
            Object object = postfix.pop();

            if(object instanceof SQLPredicate) {
                helperStack.push(object);
            } else {
                SQLPredicate sqlPredicate1 = (SQLPredicate) helperStack.pop();
                SQLPredicate sqlPredicate2 = (SQLPredicate) helperStack.pop();

                if (sqlPredicate1.getColumnName2() != null && sqlPredicate2.getColumnName2() != null) {
                    Object o1 = fetchFromColumn(sqlPredicate2.getColumnName());
                    Object o2 = fetchFromColumn(sqlPredicate2.getColumnName2());

                    Object o3 = fetchFromColumn(sqlPredicate1.getColumnName());
                    Object o4 = fetchFromColumn(sqlPredicate1.getColumnName2());

                    if (((BooleanOperator) object).getOperator() == BooleanOperator.Operator.And) {
                        helperStack.push(new SQLPredicate(sqlPredicate2.and(sqlPredicate1, o1, o2, o3, o4)));
                    } else {
                        helperStack.push(new SQLPredicate(sqlPredicate2.or(sqlPredicate1, o1, o2, o3, o4)));
                    }
                } else if (sqlPredicate1.getColumnName2() != null && sqlPredicate2.getColumnName2() == null) {
                    Object o1 = fetchFromColumn(sqlPredicate2.getColumnName());

                    Object o3 = fetchFromColumn(sqlPredicate1.getColumnName());
                    Object o4 = fetchFromColumn(sqlPredicate1.getColumnName2());

                    if (((BooleanOperator) object).getOperator() == BooleanOperator.Operator.And) {
                        helperStack.push(new SQLPredicate(sqlPredicate2.and(sqlPredicate1, o1, null, o3, o4)));
                    } else {
                        helperStack.push(new SQLPredicate(sqlPredicate2.or(sqlPredicate1, o1, null, o3, o4)));
                    }
                } else if (sqlPredicate1.getColumnName2() == null && sqlPredicate2.getColumnName2() != null) {
                    Object o1 = fetchFromColumn(sqlPredicate2.getColumnName());
                    Object o2 = fetchFromColumn(sqlPredicate2.getColumnName2());

                    Object o3 = fetchFromColumn(sqlPredicate1.getColumnName());

                    if (((BooleanOperator) object).getOperator() == BooleanOperator.Operator.And) {
                        helperStack.push(new SQLPredicate(sqlPredicate2.and(sqlPredicate1, o1, o2, o3, null)));
                    } else {
                        helperStack.push(new SQLPredicate(sqlPredicate2.or(sqlPredicate1, o1, o2, o3, null)));
                    }
                } else if (sqlPredicate1.getColumnName2() == null && sqlPredicate2.getColumnName2() == null) {
                    Object o1 = fetchFromColumn(sqlPredicate2.getColumnName());

                    Object o3 = fetchFromColumn(sqlPredicate1.getColumnName());

                    if (((BooleanOperator) object).getOperator() == BooleanOperator.Operator.And) {
                        helperStack.push(new SQLPredicate(sqlPredicate2.and(sqlPredicate1, o1, null, o3, null)));
                    } else {
                        helperStack.push(new SQLPredicate(sqlPredicate2.or(sqlPredicate1, o1, null, o3, null)));
                    }
                } else if (predicateHasResult(sqlPredicate1) && sqlPredicate2.getColumnName2() == null) {
                    Object o1 = fetchFromColumn(sqlPredicate2.getColumnName());

                    if (((BooleanOperator) object).getOperator() == BooleanOperator.Operator.And) {
                        helperStack.push(new SQLPredicate(sqlPredicate2.and(sqlPredicate1, o1, null, null, null)));
                    } else {
                        helperStack.push(new SQLPredicate(sqlPredicate2.or(sqlPredicate1, o1, null, null, null)));
                    }
                } else if (predicateHasResult(sqlPredicate1) && sqlPredicate2.getColumnName2() != null) {
                    Object o1 = fetchFromColumn(sqlPredicate2.getColumnName());
                    Object o2 = fetchFromColumn(sqlPredicate2.getColumnName2());

                    if (((BooleanOperator) object).getOperator() == BooleanOperator.Operator.And) {
                        helperStack.push(new SQLPredicate(sqlPredicate2.and(sqlPredicate1, o1, o2, null, null)));
                    } else {
                        helperStack.push(new SQLPredicate(sqlPredicate2.or(sqlPredicate1, o1, o2, null, null)));
                    }
                } else if (sqlPredicate1.getColumnName2() == null && predicateHasResult(sqlPredicate2)) {
                    Object o3 = fetchFromColumn(sqlPredicate1.getColumnName());

                    if (((BooleanOperator) object).getOperator() == BooleanOperator.Operator.And) {
                        helperStack.push(new SQLPredicate(sqlPredicate2.and(sqlPredicate1, null, null, o3, null)));
                    } else {
                        helperStack.push(new SQLPredicate(sqlPredicate2.or(sqlPredicate1, null, null, o3, null)));
                    }
                } else {
                    Object o3 = fetchFromColumn(sqlPredicate1.getColumnName());
                    Object o4 = fetchFromColumn(sqlPredicate1.getColumnName2());

                    if (((BooleanOperator) object).getOperator() == BooleanOperator.Operator.And) {
                        helperStack.push(new SQLPredicate(sqlPredicate2.and(sqlPredicate1, null, null, o3, o4)));
                    } else {
                        helperStack.push(new SQLPredicate(sqlPredicate2.or(sqlPredicate1, null, null, o3, o4)));
                    }
                }
            }
        }
        return ((SQLPredicate) helperStack.pop()).isAlwaysTrue();
    }

    private boolean predicateHasResult(SQLPredicate sqlPredicate) {
        return sqlPredicate.isAlwaysFalse() || sqlPredicate.isAlwaysTrue();
    }

    /**
     * for testing.
     * @param args
     */
    public static void main(String[] args) {

    }

}
