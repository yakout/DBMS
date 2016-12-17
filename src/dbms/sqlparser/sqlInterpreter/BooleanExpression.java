package dbms.sqlparser.sqlInterpreter;

import dbms.datatypes.DatatypeFactory;
import dbms.exception.SyntaxErrorException;
import dbms.sqlparser.syntax.SyntaxUtil;
import dbms.sqlparser.syntax.WhereSyntax;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Converting infix representation of boolean expression to postfix.
 */
public class BooleanExpression {
    /**
     * regex for predicate syntax used in {@link #getPredicates(String)}.
     */
    // TODO: move this to syntax package
    private final String predicateRegex = "(TRUE|("
            + SyntaxUtil.COLUMN_NAME + ")\\s*"
            + WhereSyntax.SUPPORTED_OPERATORS + "\\s*"
            + WhereSyntax.VALUE_FORMAT + ")";
    /**
     * error message that will be sent with {@link SyntaxErrorException}.
     */
    private final String errorMessage = "invalid condition syntax";

    /**
     * main for testing toPostfix converter
     * @param args cmd arguments
     */
    public static void main(String[] args) {
        //System.out.println(new BooleanExpression().predicateRegex);
        Queue<Object> postfix = new LinkedList<>();
        try {
            postfix = new BooleanExpression().toPostfix(""
                    + "(    (    col      !=      '1996-08-17'  )"
                    + "   and   (     col2    =    6.255   )     )");
        } catch (SyntaxErrorException e) {
            e.printStackTrace();
        }
        int size = postfix.size();
        for (int i = 0; i < size; i++) {
            System.out.println(postfix.poll());
        }
    }

    /**
     * infix to postfix converter.
     * @param infix
     * @return a queue of (SQLPredicates and/or BooleanOperator) contains
     * the postfix representation of boolean expression.
     * @throws SyntaxErrorException if the boolean expression
     * is badly constructed.
     */
    public Queue<Object> toPostfix(final String infix) throws
            SyntaxErrorException {
        List<SQLPredicate> sqlPredicates = getPredicates(infix);
        Queue<Object> postfix = new LinkedList<Object>();
        Stack<Object> helperStack = new Stack<>();

        // in case if one predicate just return it in the postfix queue.
        if (sqlPredicates.size() == 1) {
            postfix.add(sqlPredicates.get(0));
            return postfix;
        }

        int predicateNumber = 0;
        for (int i = 0; i < infix.length(); i++) {
            char currentChar = infix.charAt(i);
            if (currentChar == '(') {
                helperStack.push(currentChar);
            } else if (currentChar == ')') {
                try {
                    while (!(helperStack.peek() instanceof Character)) {
                        postfix.add(helperStack.pop());
                    }
                } catch (EmptyStackException e) {
                    throw new SyntaxErrorException(errorMessage);
                }
                helperStack.pop();
            } else if (i + 3 < infix.length() && infix.substring(i, i + 3)
                    .toLowerCase().equals("and")) {
                pushAndToPostfix(postfix, helperStack);
                i += 2;
            } else if (i + 2 < infix.length() && infix.substring(i, i + 2)
                    .toLowerCase().equals("or")) {
                pushOrToPostfix(postfix, helperStack);
                i++;
            } else if (currentChar == ' ') {
                continue;
            } else {
                try {
                    postfix.add(sqlPredicates.get(predicateNumber++));
                    while (infix.charAt(++i) != ')') {
                        if (i + 3 < infix.length() && infix.substring(i, i + 3)
                                .equals(" or") || i + 4 < infix.length()
                                && infix.substring(i, i + 4).equals(" and")) {
                            throw new SyntaxErrorException(errorMessage);
                        }
                    }
                    i--;
                } catch (IndexOutOfBoundsException e) {
                    throw new SyntaxErrorException(errorMessage);
                }
            }
        }
        if (!helperStack.isEmpty()) {
            if (helperStack.peek() instanceof Character) {
                throw new SyntaxErrorException(errorMessage);
            } else { // in case the user didn't put parenthesis
                // around the whole expression some operator
                //will remain in helper stack
                while (!helperStack.isEmpty()) {
                    postfix.add(helperStack.pop());
                }
            }
        }
        return postfix;
    }

    /**
     * helper method for toPostfix() that adds BooleanOperator
     * (AND) to postfix queue.
     * @param postfix
     * @param helperStack
     */
    private void pushAndToPostfix(final Queue<Object> postfix,
                                  final Stack<Object> helperStack) {
        if (helperStack.isEmpty() || helperStack.peek() instanceof Character) {
            helperStack.push(new BooleanOperator(BooleanOperator.Operator.And));
        } else if (((BooleanOperator) helperStack.peek()).getOperator()
                == BooleanOperator.Operator.And) {
            postfix.add(new BooleanOperator(BooleanOperator.Operator.And));
        } else {
            helperStack.push(new BooleanOperator(BooleanOperator.Operator.And));
        }
    }

    /**
     * helper method for toPostfix() that adds BooleanOperator
     * (OR) to postfix queue.
     * @param postfix
     * @param helperStack
     */
    private void pushOrToPostfix(final Queue<Object> postfix,
                                 final Stack<Object> helperStack) {
        if (helperStack.isEmpty() || helperStack.peek() instanceof Character) {
            helperStack.push(new BooleanOperator(BooleanOperator.Operator.Or));
        } else {
            if (((BooleanOperator) helperStack.peek()).getOperator()
                    == BooleanOperator.Operator.And) {
                postfix.add(helperStack.pop());
                helperStack.push(new BooleanOperator(BooleanOperator
                        .Operator.Or));
            } else { // or in helper stack
                postfix.add(new BooleanOperator(BooleanOperator.Operator.Or));
            }
        }
    }

    /**
     * this helper function used to to extract predicates from
     * infix representation
     * of boolean expression.
     * @param infix infix representation for boolean expression.
     * @return list of SQLPredicates extracted from infix.
     */
    private List<SQLPredicate> getPredicates(final String infix) {
        /**
         *  TODO: move pattern to {@link dbms.sqlparser.syntax}
         */
        Matcher matcher = Pattern.compile(predicateRegex).matcher(infix);
        List<SQLPredicate> sqlPredicates = new ArrayList<>();
        while (matcher.find()) {
            if (matcher.group(1).equals("TRUE")) {
                sqlPredicates.add(new SQLPredicate(true));
                return sqlPredicates;
            }

            SQLPredicate sqlPredicate;
            if (matcher.group(4).startsWith("'") || matcher.group(4)
                    .startsWith("\"")
                    || matcher.group(4).matches(SyntaxUtil.NUMBER_FORMAT)) {
                // TODO: remove lower case and let the backend handles the logic
                sqlPredicate = new SQLPredicate(matcher.group(2).toLowerCase(),
                        matcher.group(3), DatatypeFactory.convertToDataType(
                        DatatypeFactory.convertToObject(matcher.group(4))));
            } else {
                sqlPredicate = new SQLPredicate(matcher.group(2).toLowerCase(),
                        matcher.group(3), matcher.group(4));
            }

            sqlPredicates.add(sqlPredicate);
        }
        return sqlPredicates;
    }
}
