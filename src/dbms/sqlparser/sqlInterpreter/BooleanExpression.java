package dbms.sqlparser.sqlInterpreter;

import dbms.exception.SyntaxErrorException;
import dbms.sqlparser.sqlInterpreter.rules.BooleanOperator;
import dbms.util.Operator;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BooleanExpression {
    private final String predicateRegex = "((\\w+)\\s*(<|>|=)\\s*(\\w+|'\\w+'|\\d+))";
    private final String errorMessage = "invalid condition syntax";

    public Stack<Object> toPostfix(String infix) throws SyntaxErrorException {
        List<SQLPredicate> sqlPredicates = getPredicates(infix);
        Stack<Object> postfix = new Stack<>();
        Stack<Object> helperStack = new Stack<>();

        int predicateNumber = 0;
        for (int i = 0; i < infix.length(); i++) {
            char currentChar = infix.charAt(i);
            if (currentChar == '(') {
                helperStack.push(currentChar);
            } else if (currentChar == ')') {
                try {
                    while (!(helperStack.peek() instanceof Character)) {
                        postfix.push(helperStack.pop());
                    }
                } catch (EmptyStackException e) {
                    throw new SyntaxErrorException(errorMessage);
                }
                helperStack.pop();
            } else if (infix.substring(i, i + 3).equals("and")) {
                if (helperStack.isEmpty() || helperStack.peek() instanceof Character) {
                    helperStack.push(new BooleanOperator(BooleanOperator.Operator.And));
                } else if (((BooleanOperator) helperStack.peek()).getOperator() == BooleanOperator.Operator.And) {
                    postfix.push(new BooleanOperator(BooleanOperator.Operator.And));
                } else {
                    helperStack.push(new BooleanOperator(BooleanOperator.Operator.And));
                }
                i += 2;
            } else if (infix.substring(i, i + 2).equals("or")) {
                if (helperStack.isEmpty() || helperStack.peek() instanceof Character) {
                    helperStack.push(new BooleanOperator(BooleanOperator.Operator.Or));
                } else {
                    if (((BooleanOperator) helperStack.peek()).getOperator() == BooleanOperator.Operator.And) {
                        postfix.push(helperStack.pop());
                        helperStack.push(new BooleanOperator(BooleanOperator.Operator.Or));
                    } else { // or in helper stack
                        postfix.push(new BooleanOperator(BooleanOperator.Operator.Or));
                    }
                }
                i++;
            } else if (currentChar == ' ') {
                continue;
            } else {
                if (predicateNumber + 1 <= sqlPredicates.size())
                    postfix.push(sqlPredicates.get(predicateNumber++));
                while(i + 1 != infix.length() && infix.charAt(++i) != ')');
                if (!(i + 1 == infix.length())) i--;
            }
        }
        if (!helperStack.isEmpty()) {
            if (helperStack.peek() instanceof Character) {
                throw new SyntaxErrorException(errorMessage);
            } else { // in case the user didn't put parenthesis
                // around the whole expression some operator will remain in helper stack
                while (!helperStack.isEmpty()) {
                    postfix.push(helperStack.pop());
                }
            }
        }
        return postfix;
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
        Stack<Object> postfix = new Stack<>();
        try {
            postfix = new BooleanExpression().toPostfix("((a < r) or (d < d))");
        } catch (SyntaxErrorException e) {
            e.printStackTrace();
        }
        int size = postfix.size();
        for (int i = 0; i < size; i++) {
            System.out.println(postfix.firstElement());
            postfix.remove(postfix.firstElement());
        }
    }
}
