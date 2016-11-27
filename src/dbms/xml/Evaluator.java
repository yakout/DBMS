package dbms.xml;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

import dbms.sqlparser.sqlInterpreter.SQLPredicate;
import dbms.sqlparser.sqlInterpreter.rules.BooleanOperator;

class Evaluator {

	private static Evaluator instance = null;

	private Evaluator() {

	}

	protected static Evaluator getInstance() {
		if (instance == null) {
			instance = new Evaluator();
		}
		return instance;
	}

    protected boolean evaluate(Map<String, Object> row, Queue<Object> postfix) {
        Stack<Object> helperStack = new Stack<>();
		Queue<Object> postfixClone = new LinkedList<Object>(postfix);
        while(!postfixClone.isEmpty()) {
            Object object = postfixClone.poll();
            System.out.println(object);
            if (object instanceof SQLPredicate) {
                helperStack.push(object);
            } else {
                SQLPredicate sqlPredicate1 = (SQLPredicate) helperStack.pop();
                SQLPredicate sqlPredicate2 = (SQLPredicate) helperStack.pop();

                if (sqlPredicate1.getColumnName2() != null && sqlPredicate2.getColumnName2() != null) {
                    Object o1 = row.get(sqlPredicate2.getColumnName());
                    Object o2 = row.get(sqlPredicate2.getColumnName2());

                    Object o3 = row.get(sqlPredicate1.getColumnName());
                    Object o4 = row.get(sqlPredicate1.getColumnName2());

                    if (((BooleanOperator) object).getOperator() == BooleanOperator.Operator.And) {
                        helperStack.push(new SQLPredicate(sqlPredicate2.and(sqlPredicate1, o1, o2, o3, o4)));
                    } else {
                        helperStack.push(new SQLPredicate(sqlPredicate2.or(sqlPredicate1, o1, o2, o3, o4)));
                    }
                } else if (sqlPredicate1.getColumnName2() != null && sqlPredicate2.getColumnName2() == null) {
                    Object o1 = row.get(sqlPredicate2.getColumnName());

                    Object o3 = row.get(sqlPredicate1.getColumnName());
                    Object o4 = row.get(sqlPredicate1.getColumnName2());

                    if (((BooleanOperator) object).getOperator() == BooleanOperator.Operator.And) {
                        helperStack.push(new SQLPredicate(sqlPredicate2.and(sqlPredicate1, o1, null, o3, o4)));
                    } else {
                        helperStack.push(new SQLPredicate(sqlPredicate2.or(sqlPredicate1, o1, null, o3, o4)));
                    }
                } else if (sqlPredicate1.getColumnName2() == null && sqlPredicate2.getColumnName2() != null) {
                    Object o1 = row.get(sqlPredicate2.getColumnName());
                    Object o2 = row.get(sqlPredicate2.getColumnName2());

                    Object o3 = row.get(sqlPredicate1.getColumnName());

                    if (((BooleanOperator) object).getOperator() == BooleanOperator.Operator.And) {
                        helperStack.push(new SQLPredicate(sqlPredicate2.and(sqlPredicate1, o1, o2, o3, null)));
                    } else {
                        helperStack.push(new SQLPredicate(sqlPredicate2.or(sqlPredicate1, o1, o2, o3, null)));
                    }
                } else if (sqlPredicate1.getColumnName2() == null && sqlPredicate2.getColumnName2() == null) {
                    Object o1 = row.get(sqlPredicate2.getColumnName());

                    Object o3 = row.get(sqlPredicate1.getColumnName());

                    if (((BooleanOperator) object).getOperator() == BooleanOperator.Operator.And) {
                        helperStack.push(new SQLPredicate(sqlPredicate2.and(sqlPredicate1, o1, null, o3, null)));
                    } else {
                        helperStack.push(new SQLPredicate(sqlPredicate2.or(sqlPredicate1, o1, null, o3, null)));
                    }
                } else if (predicateHasResult(sqlPredicate1) && sqlPredicate2.getColumnName2() == null) {
                    Object o1 = row.get(sqlPredicate2.getColumnName());

                    if (((BooleanOperator) object).getOperator() == BooleanOperator.Operator.And) {
                        helperStack.push(new SQLPredicate(sqlPredicate2.and(sqlPredicate1, o1, null, null, null)));
                    } else {
                        helperStack.push(new SQLPredicate(sqlPredicate2.or(sqlPredicate1, o1, null, null, null)));
                    }
                } else if (predicateHasResult(sqlPredicate1) && sqlPredicate2.getColumnName2() != null) {
                    Object o1 = row.get(sqlPredicate2.getColumnName());
                    Object o2 = row.get(sqlPredicate2.getColumnName2());

                    if (((BooleanOperator) object).getOperator() == BooleanOperator.Operator.And) {
                        helperStack.push(new SQLPredicate(sqlPredicate2.and(sqlPredicate1, o1, o2, null, null)));
                    } else {
                        helperStack.push(new SQLPredicate(sqlPredicate2.or(sqlPredicate1, o1, o2, null, null)));
                    }
                } else if (sqlPredicate1.getColumnName2() == null && predicateHasResult(sqlPredicate2)) {
                    Object o3 = row.get(sqlPredicate1.getColumnName());

                    if (((BooleanOperator) object).getOperator() == BooleanOperator.Operator.And) {
                        helperStack.push(new SQLPredicate(sqlPredicate2.and(sqlPredicate1, null, null, o3, null)));
                    } else {
                        helperStack.push(new SQLPredicate(sqlPredicate2.or(sqlPredicate1, null, null, o3, null)));
                    }
                } else {
                    Object o3 = row.get(sqlPredicate1.getColumnName());
                    Object o4 = row.get(sqlPredicate1.getColumnName2());

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
}
