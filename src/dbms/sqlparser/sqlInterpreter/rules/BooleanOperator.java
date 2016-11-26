package dbms.sqlparser.sqlInterpreter.rules;


public class BooleanOperator {
    public enum Operator {
        And,
        Or
    }

    private Operator operator;

    public BooleanOperator(Operator operator) {
        this.operator = operator;
    }

    public Operator getOperator() {
        return operator;
    }

    @Override
    public String toString() {
        return "BooleanOperator{" +
                "operator=" + operator +
                '}';
    }
}
