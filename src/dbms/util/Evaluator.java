package dbms.util;

import dbms.exception.IncorrectDataEntryException;
import dbms.sqlparser.sqlInterpreter.BooleanExpressionEvaluator;
import dbms.sqlparser.sqlInterpreter.BooleanOperator;
import dbms.sqlparser.sqlInterpreter.SQLPredicate;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class Evaluator extends BooleanExpressionEvaluator{

	private static Evaluator instance = null;

	private Evaluator() {
		super();
	}

	public static Evaluator getInstance() {
		if (instance == null) {
			instance = new Evaluator();
		}
		return instance;
	}

	@Override
    public boolean evaluate(Map<String, Object> row, Queue<Object> postfix,
    		Map<String, String> columns) throws IncorrectDataEntryException {
        Stack<Object> helperStack = new Stack<>();
		Queue<Object> postfixClone = new LinkedList<Object>(postfix);
        // in case if only on predicate
        if(postfix.size() == 1) {
            SQLPredicate sqlPredicate =((SQLPredicate) postfix.poll());
            if (sqlPredicate.isAlwaysTrue() || sqlPredicate.isAlwaysFalse()) {
                return sqlPredicate.isAlwaysTrue();

            } else if (sqlPredicate.getColumnName2() == null) {
                Object o = get(sqlPredicate.getValue(), columns, row, sqlPredicate.getColumnName()); //column1
                return sqlPredicate.test(o);
            } else {
                Object o1 = get(sqlPredicate.getValue(), columns, row, sqlPredicate.getColumnName()); //column1
                Object o2 = get(sqlPredicate.getValue(), columns, row, sqlPredicate.getColumnName2()); //column2

                return sqlPredicate.test(o1, o2);
            }
        }

        while(!postfixClone.isEmpty()) {
            Object object = postfixClone.poll();
            if (object instanceof SQLPredicate) {
                helperStack.push(object);
            } else {
                SQLPredicate sqlPredicate1 = (SQLPredicate) helperStack.pop();
                SQLPredicate sqlPredicate2 = (SQLPredicate) helperStack.pop();

                if (predicateHasResult(sqlPredicate1) && sqlPredicate2.getColumnName2() == null) {
                    Object o1 = get(sqlPredicate2.getValue(), columns, row, sqlPredicate2.getColumnName());

                    if (((BooleanOperator) object).getOperator() == BooleanOperator.Operator.And) {
                        helperStack.push(new SQLPredicate(sqlPredicate2.and(sqlPredicate1, o1, null, null, null)));
                    } else {
                        helperStack.push(new SQLPredicate(sqlPredicate2.or(sqlPredicate1, o1, null, null, null)));
                    }
                } else if (predicateHasResult(sqlPredicate1) && sqlPredicate2.getColumnName2() != null) {
                    Object o1 = get(sqlPredicate2.getValue(), columns, row, sqlPredicate2.getColumnName());
                    Object o2 = get(sqlPredicate2.getValue(), columns, row, sqlPredicate2.getColumnName2());

                    if (((BooleanOperator) object).getOperator() == BooleanOperator.Operator.And) {
                        helperStack.push(new SQLPredicate(sqlPredicate2.and(sqlPredicate1, o1, o2, null, null)));
                    } else {
                        helperStack.push(new SQLPredicate(sqlPredicate2.or(sqlPredicate1, o1, o2, null, null)));
                    }
                } else if (sqlPredicate1.getColumnName2() == null && predicateHasResult(sqlPredicate2)) {
                    Object o3 = get(sqlPredicate1.getValue(), columns, row, sqlPredicate1.getColumnName());

                    if (((BooleanOperator) object).getOperator() == BooleanOperator.Operator.And) {
                        helperStack.push(new SQLPredicate(sqlPredicate2.and(sqlPredicate1, null, null, o3, null)));
                    } else {
                        helperStack.push(new SQLPredicate(sqlPredicate2.or(sqlPredicate1, null, null, o3, null)));
                    }
                } else if (sqlPredicate1.getColumnName2() != null && predicateHasResult(sqlPredicate2)) {
                    Object o3 = get(sqlPredicate1.getValue(), columns, row, sqlPredicate1.getColumnName());
                    Object o4 = get(sqlPredicate1.getValue(), columns, row, sqlPredicate1.getColumnName2());

                    if (((BooleanOperator) object).getOperator() == BooleanOperator.Operator.And) {
                        helperStack.push(new SQLPredicate(sqlPredicate2.and(sqlPredicate1, null, null, o3, o4)));
                    } else {
                        helperStack.push(new SQLPredicate(sqlPredicate2.or(sqlPredicate1, null, null, o3, o4)));
                    }
                } else if (sqlPredicate1.getColumnName2() != null && sqlPredicate2.getColumnName2() != null) {
                	Object o1 = get(sqlPredicate2.getValue(), columns, row, sqlPredicate2.getColumnName());
                    Object o2 = get(sqlPredicate2.getValue(), columns, row, sqlPredicate2.getColumnName2());

                    Object o3 = get(sqlPredicate1.getValue(), columns, row, sqlPredicate1.getColumnName()); //column1
                    Object o4 = get(sqlPredicate1.getValue(), columns, row, sqlPredicate1.getColumnName2()); //column2

                    if (((BooleanOperator) object).getOperator() == BooleanOperator.Operator.And) {
                        helperStack.push(new SQLPredicate(sqlPredicate2.and(sqlPredicate1, o1, o2, o3, o4)));
                    } else {
                        helperStack.push(new SQLPredicate(sqlPredicate2.or(sqlPredicate1, o1, o2, o3, o4)));
                    }
                } else if (sqlPredicate1.getColumnName2() != null && sqlPredicate2.getColumnName2() == null) {
                    Object o1 = get(sqlPredicate2.getValue(), columns, row, sqlPredicate2.getColumnName());

                    Object o3 = get(sqlPredicate1.getValue(), columns, row, sqlPredicate1.getColumnName());
                    Object o4 = get(sqlPredicate1.getValue(), columns, row, sqlPredicate1.getColumnName2());

                    if (((BooleanOperator) object).getOperator() == BooleanOperator.Operator.And) {
                        helperStack.push(new SQLPredicate(sqlPredicate2.and(sqlPredicate1, o1, null, o3, o4)));
                    } else {
                        helperStack.push(new SQLPredicate(sqlPredicate2.or(sqlPredicate1, o1, null, o3, o4)));
                    }
                } else if (sqlPredicate1.getColumnName2() == null && sqlPredicate2.getColumnName2() != null) {
                    Object o1 = get(sqlPredicate2.getValue(), columns, row, sqlPredicate2.getColumnName());
                    Object o2 = get(sqlPredicate2.getValue(), columns, row, sqlPredicate2.getColumnName2());

                    Object o3 = get(sqlPredicate1.getValue(), columns, row, sqlPredicate1.getColumnName());

                    if (((BooleanOperator) object).getOperator() == BooleanOperator.Operator.And) {
                        helperStack.push(new SQLPredicate(sqlPredicate2.and(sqlPredicate1, o1, o2, o3, null)));
                    } else {
                        helperStack.push(new SQLPredicate(sqlPredicate2.or(sqlPredicate1, o1, o2, o3, null)));
                    }
                } else if (sqlPredicate1.getColumnName2() == null && sqlPredicate2.getColumnName2() == null) {
                    Object o1 = get(sqlPredicate2.getValue(), columns, row, sqlPredicate2.getColumnName());

                    Object o3 = get(sqlPredicate1.getValue(), columns, row, sqlPredicate1.getColumnName());

                    if (((BooleanOperator) object).getOperator() == BooleanOperator.Operator.And) {
                        helperStack.push(new SQLPredicate(sqlPredicate2.and(sqlPredicate1, o1, null, o3, null)));
                    } else {
                        helperStack.push(new SQLPredicate(sqlPredicate2.or(sqlPredicate1, o1, null, o3, null)));
                    }
                }
            }
        }
        return ((SQLPredicate) helperStack.pop()).isAlwaysTrue();
    }

    private Object get(Object val, Map<String, String> columns,
    		Map<String, Object> row, String colName)
    				throws IncorrectDataEntryException {
    	if (!columns.containsKey(colName)) {
    		throw new IncorrectDataEntryException("Column doesn't exist!");
    	}
    	if (val == null) {
    		return row.get(colName);
    	}
    	if (!val.getClass().getSimpleName()
    			.equals(columns.get(colName))) {
    		throw new IncorrectDataEntryException("Data type is incorrect!");
    	}
    	return row.get(colName);
    }

    private boolean predicateHasResult(SQLPredicate sqlPredicate) {
        return sqlPredicate.isAlwaysFalse() || sqlPredicate.isAlwaysTrue();
    }


    /**
     *
     * @param args
     */
    public static void main(String[] args) {

    }
}
