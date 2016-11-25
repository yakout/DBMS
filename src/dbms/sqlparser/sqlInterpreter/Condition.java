package dbms.sqlparser.sqlInterpreter;

import java.util.Collection;
import java.util.Stack;

public interface Condition {
    public Collection<SQLPredicate> getPredicates();
    public Stack<Object> getPostfix();
}