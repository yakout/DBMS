package dbms.sqlparser.sqlInterpreter.rules;

import java.util.Collection;
import java.util.Queue;

import dbms.sqlparser.sqlInterpreter.Condition;
import dbms.sqlparser.sqlInterpreter.SQLPredicate;

public class Where implements Condition {
    private Collection<SQLPredicate> predicates;

    public Where(Collection<SQLPredicate> predicates) {
        this.predicates = predicates;
    }

    @Override
    public Collection<SQLPredicate> getPredicates() {
        return predicates;
    }

    @Override
    public Queue<Object> getPostfix() {
        return null;
    }
}