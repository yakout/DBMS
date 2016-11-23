package dbms.sqlparser.sqlInterpreter.rules;

import java.util.Collection;

/**
 * Created by khlailmohammedyakout on 11/23/16.
 */
public class Where implements Condition {
    private Collection<SQLPredicate> predicates;

    public void addPredicate(SQLPredicate sqlPredicate) {
        predicates.add(sqlPredicate);
    }
}
