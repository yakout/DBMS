package dbms.sqlparser.sqlInterpreter.rules;

public interface DMLStatement extends Expression {
    int getUpdateCount();
}
