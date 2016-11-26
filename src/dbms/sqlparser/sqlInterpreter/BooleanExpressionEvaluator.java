package dbms.sqlparser.sqlInterpreter;

import java.util.Stack;

public class BooleanExpressionEvaluator {
    private Stack<Object> postfix;

    public BooleanExpressionEvaluator(Stack<Object> postfix) {
        this.postfix = postfix;
    }

    public boolean evaluate() {

        // TODO: 11/26/16
        return false;
    }

}
