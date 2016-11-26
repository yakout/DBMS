package dbms.sqlparser.sqlInterpreter;

import java.util.Collection;
import java.util.Queue;

public interface Condition {
    public Collection<SQLPredicate> getPredicates();
    public Queue<Object> getPostfix();
}