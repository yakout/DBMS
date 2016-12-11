package dbms.sqlparser.sqlInterpreter;

import dbms.exception.SyntaxErrorException;

import java.util.Queue;

public interface Condition {
    public Queue<Object> getPostfix() throws SyntaxErrorException;
}