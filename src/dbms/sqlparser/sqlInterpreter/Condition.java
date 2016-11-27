package dbms.sqlparser.sqlInterpreter;

import java.util.Queue;

import dbms.exception.SyntaxErrorException;

public interface Condition {
    public Queue<Object> getPostfix() throws SyntaxErrorException;
}