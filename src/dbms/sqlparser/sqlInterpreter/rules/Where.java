package dbms.sqlparser.sqlInterpreter.rules;

import dbms.sqlparser.sqlInterpreter.Condition;
import dbms.sqlparser.sqlInterpreter.SQLPredicate;
import java.util.Collection;

public class Where implements Condition {
    private Collection<SQLPredicate> predicates;

    public Where(Collection<SQLPredicate> predicates) {
        this.predicates = predicates;
    }

    public Collection<SQLPredicate> getPredicates() {
        return predicates;
    }
}
