package dbms.sqlparser.sqlInterpreter;

public interface Predicate {
	boolean test(Object o);
}