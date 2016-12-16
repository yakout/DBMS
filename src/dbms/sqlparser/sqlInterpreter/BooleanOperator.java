package dbms.sqlparser.sqlInterpreter;


public class BooleanOperator {
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

    public enum Operator {
        And,
        Or
    }
}
