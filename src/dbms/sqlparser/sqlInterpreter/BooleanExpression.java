package dbms.sqlparser.sqlInterpreter;

import dbms.util.Operator;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BooleanExpression {
    private final String predicateRegex = "((\\w+)\\s*(<|>|=)\\s*(\\w+|'\\w+'|\\d+))";

    public Stack<Object> toPostfix(String infix) {
        //
        return null;
    }

    private List<SQLPredicate> getPredicates(String infix) {
        Matcher matcher = Pattern.compile(predicateRegex).matcher(infix);
        List<SQLPredicate> sqlPredicates = new ArrayList<>();
        while (matcher.find()) {
            SQLPredicate sqlPredicate = new SQLPredicate(matcher.group(2), toOperator(matcher.group(3)), matcher.group(4));
            sqlPredicates.add(sqlPredicate);
        }
        return sqlPredicates;
    }

    private Operator toOperator(String operator) {
        switch (operator) {
            case "<":
                return Operator.SmallerThan;
            case ">":
                return Operator.GreaterThan;
            case "=":
                return Operator.Equal;
            default:
                return null;
        }
    }

    public static void main(String[] args) {
        new BooleanExpression().getPredicates("(a<d) a<d d  =   d dfd=fd (d=d) and or fd=d");

    }
}
