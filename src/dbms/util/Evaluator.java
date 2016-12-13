package dbms.util;

import dbms.datatypes.DBDatatype;
import dbms.exception.IncorrectDataEntryException;
import dbms.sqlparser.sqlInterpreter.BooleanExpressionEvaluator;
import dbms.sqlparser.sqlInterpreter.BooleanOperator;
import dbms.sqlparser.sqlInterpreter.SQLPredicate;

import java.util.*;

public class Evaluator extends BooleanExpressionEvaluator {

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
    public boolean evaluate(Map<String, DBDatatype> row, Queue<Object> postfix,
                            Map<String, String> columns) throws IncorrectDataEntryException {
	    Map<String, DBDatatype> rowLower = new LinkedHashMap<String, DBDatatype>();
	    for (Map.Entry<String, DBDatatype> entry : row.entrySet()) {
	        rowLower.put(entry.getKey().toLowerCase(), entry.getValue());
        }
	    Stack<Object> helperStack = new Stack<>();
		Queue<Object> postfixClone = new LinkedList<Object>(postfix);
        // in case if only on predicate
        if(postfix.size() == 1) {
            SQLPredicate sqlPredicate =((SQLPredicate) postfix.poll());
            if (sqlPredicate.isAlwaysTrue() || sqlPredicate.isAlwaysFalse()) {
                return sqlPredicate.isAlwaysTrue();

            } else if (sqlPredicate.getColumnName2() == null) {
                DBDatatype o = get(sqlPredicate.getValue(), columns, rowLower, sqlPredicate.getColumnName()); //column1
                return sqlPredicate.test(o);
            } else {
                DBDatatype o1 = get(sqlPredicate.getValue(), columns, rowLower, sqlPredicate.getColumnName()); //column1
                DBDatatype o2 = get(sqlPredicate.getValue(), columns, rowLower, sqlPredicate.getColumnName2()); //column2

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
                    DBDatatype o1 = get(sqlPredicate2.getValue(), columns, rowLower, sqlPredicate2.getColumnName());

                    if (((BooleanOperator) object).getOperator() == BooleanOperator.Operator.And) {
                        helperStack.push(new SQLPredicate(sqlPredicate2.and(sqlPredicate1, o1, null, null, null)));
                    } else {
                        helperStack.push(new SQLPredicate(sqlPredicate2.or(sqlPredicate1, o1, null, null, null)));
                    }
                } else if (predicateHasResult(sqlPredicate1) && sqlPredicate2.getColumnName2() != null) {
                    DBDatatype o1 = get(sqlPredicate2.getValue(), columns, rowLower, sqlPredicate2.getColumnName());
                    DBDatatype o2 = get(sqlPredicate2.getValue(), columns, rowLower, sqlPredicate2.getColumnName2());

                    if (((BooleanOperator) object).getOperator() == BooleanOperator.Operator.And) {
                        helperStack.push(new SQLPredicate(sqlPredicate2.and(sqlPredicate1, o1, o2, null, null)));
                    } else {
                        helperStack.push(new SQLPredicate(sqlPredicate2.or(sqlPredicate1, o1, o2, null, null)));
                    }
                } else if (sqlPredicate1.getColumnName2() == null && predicateHasResult(sqlPredicate2)) {
                    DBDatatype o3 = get(sqlPredicate1.getValue(), columns, rowLower, sqlPredicate1.getColumnName());

                    if (((BooleanOperator) object).getOperator() == BooleanOperator.Operator.And) {
                        helperStack.push(new SQLPredicate(sqlPredicate2.and(sqlPredicate1, null, null, o3, null)));
                    } else {
                        helperStack.push(new SQLPredicate(sqlPredicate2.or(sqlPredicate1, null, null, o3, null)));
                    }
                } else if (sqlPredicate1.getColumnName2() != null && predicateHasResult(sqlPredicate2)) {
                    DBDatatype o3 = get(sqlPredicate1.getValue(), columns, rowLower, sqlPredicate1.getColumnName());
                    DBDatatype o4 = get(sqlPredicate1.getValue(), columns, rowLower, sqlPredicate1.getColumnName2());

                    if (((BooleanOperator) object).getOperator() == BooleanOperator.Operator.And) {
                        helperStack.push(new SQLPredicate(sqlPredicate2.and(sqlPredicate1, null, null, o3, o4)));
                    } else {
                        helperStack.push(new SQLPredicate(sqlPredicate2.or(sqlPredicate1, null, null, o3, o4)));
                    }
                } else if (sqlPredicate1.getColumnName2() != null && sqlPredicate2.getColumnName2() != null) {
                    DBDatatype o1 = get(sqlPredicate2.getValue(), columns, rowLower, sqlPredicate2.getColumnName());
                    DBDatatype o2 = get(sqlPredicate2.getValue(), columns, rowLower, sqlPredicate2.getColumnName2());

                    DBDatatype o3 = get(sqlPredicate1.getValue(), columns, rowLower, sqlPredicate1.getColumnName()); //column1
                    DBDatatype o4 = get(sqlPredicate1.getValue(), columns, rowLower, sqlPredicate1.getColumnName2()); //column2

                    if (((BooleanOperator) object).getOperator() == BooleanOperator.Operator.And) {
                        helperStack.push(new SQLPredicate(sqlPredicate2.and(sqlPredicate1, o1, o2, o3, o4)));
                    } else {
                        helperStack.push(new SQLPredicate(sqlPredicate2.or(sqlPredicate1, o1, o2, o3, o4)));
                    }
                } else if (sqlPredicate1.getColumnName2() != null && sqlPredicate2.getColumnName2() == null) {
                    DBDatatype o1 = get(sqlPredicate2.getValue(), columns, rowLower, sqlPredicate2.getColumnName());

                    DBDatatype o3 = get(sqlPredicate1.getValue(), columns, rowLower, sqlPredicate1.getColumnName());
                    DBDatatype o4 = get(sqlPredicate1.getValue(), columns, rowLower, sqlPredicate1.getColumnName2());

                    if (((BooleanOperator) object).getOperator() == BooleanOperator.Operator.And) {
                        helperStack.push(new SQLPredicate(sqlPredicate2.and(sqlPredicate1, o1, null, o3, o4)));
                    } else {
                        helperStack.push(new SQLPredicate(sqlPredicate2.or(sqlPredicate1, o1, null, o3, o4)));
                    }
                } else if (sqlPredicate1.getColumnName2() == null && sqlPredicate2.getColumnName2() != null) {
                    DBDatatype o1 = get(sqlPredicate2.getValue(), columns, rowLower, sqlPredicate2.getColumnName());
                    DBDatatype o2 = get(sqlPredicate2.getValue(), columns, rowLower, sqlPredicate2.getColumnName2());

                    DBDatatype o3 = get(sqlPredicate1.getValue(), columns, rowLower, sqlPredicate1.getColumnName());

                    if (((BooleanOperator) object).getOperator() == BooleanOperator.Operator.And) {
                        helperStack.push(new SQLPredicate(sqlPredicate2.and(sqlPredicate1, o1, o2, o3, null)));
                    } else {
                        helperStack.push(new SQLPredicate(sqlPredicate2.or(sqlPredicate1, o1, o2, o3, null)));
                    }
                } else if (sqlPredicate1.getColumnName2() == null && sqlPredicate2.getColumnName2() == null) {
                    DBDatatype o1 = get(sqlPredicate2.getValue(), columns, rowLower, sqlPredicate2.getColumnName());

                    DBDatatype o3 = get(sqlPredicate1.getValue(), columns, rowLower, sqlPredicate1.getColumnName());

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

    private DBDatatype get(DBDatatype val, Map<String, String> columns,
    		Map<String, DBDatatype> row, String colName)
    				throws IncorrectDataEntryException {
    	if (!columns.containsKey(colName)) {
    		throw new IncorrectDataEntryException("Column doesn't exist!");
    	}
    	if (val == null) {
    		return row.get(colName);
    	}
    	if (!val.getKey().equals(columns.get(colName))) {
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
