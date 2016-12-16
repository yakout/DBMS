package dbms.sqlparser.sqlInterpreter;

import dbms.exception.SyntaxErrorException;

import java.util.Queue;

public class Where implements Condition {
    private String infix;

    public Where(String infix) {
        this.infix = infix;
    }

    @Override
    public Queue<Object> getPostfix() throws SyntaxErrorException {
        return new BooleanExpression().toPostfix(infix);
    }

    @Override
    public String toString() {
        return "infix: " + infix;
    }
}