package dbms.sqlparser.sqlInterpreter.rules;

import java.util.Collection;
import java.util.Queue;

import dbms.exception.SyntaxErrorException;
import dbms.sqlparser.sqlInterpreter.BooleanExpression;
import dbms.sqlparser.sqlInterpreter.Condition;

public class Where implements Condition {
    private String infix;

    public Where(String infix) {
        this.infix = infix;
    }

    @Override
    public Queue<Object> getPostfix() throws SyntaxErrorException {
    	return new BooleanExpression().toPostfix(infix);
    }
}