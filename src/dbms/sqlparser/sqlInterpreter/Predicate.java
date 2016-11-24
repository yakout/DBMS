package dbms.sqlparser.sqlInterpreter;

/**
 * Created by khlailmohammedyakout on 11/24/16.
 */
public interface Predicate {
    boolean test(Object o);
}
