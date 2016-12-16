package dbms.sqlparser.sqlInterpreter;

import dbms.datatypes.DBDatatype;
import dbms.exception.IncorrectDataEntryException;

import java.util.Map;
import java.util.Queue;

public abstract class BooleanExpressionEvaluator {
    private Queue<Object> postfix;

    public BooleanExpressionEvaluator(Queue<Object> postfix) {
        this.postfix = postfix;
    }

    public BooleanExpressionEvaluator() {
    }

    public void setPostfix(Queue<Object> postfix) {
        this.postfix = postfix;
    }

    private Object fetchFromColumn(String columnName) {
        return null;
    }

    protected abstract boolean evaluate(Map<String, DBDatatype> row,
    		Queue<Object> postfix, Map<String, String> columns)
    				throws IncorrectDataEntryException;

}
