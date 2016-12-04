package dbms.sqlparser.sqlInterpreter;

import java.util.List;
import java.util.Map;
import java.util.Queue;

import dbms.exception.IncorrectDataEntryException;
import dbms.util.Column;

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

    protected abstract boolean evaluate(Map<String, Object> row, Queue<Object> postfix,
    		Map<String, String> columns) throws IncorrectDataEntryException;


    /**
     * for testing.
     * @param args
     */
    public static void main(String[] args) {

    }

	public boolean evaluate(Map<String, Object> row, Queue<Object> postfix, List<Column> columns)
			throws IncorrectDataEntryException {
		// TODO Auto-generated method stub
		return false;
	}

}
