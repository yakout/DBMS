package dbms.sqlparser.sqlInterpreter.rules;

import dbms.sqlparser.sqlInterpreter.Condition;
import dbms.sqlparser.sqlInterpreter.SQLPredicate;
import java.util.Collection;
import java.util.Stack;

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
    public Stack<Object> getPostfix() {
        return null;
    }
}