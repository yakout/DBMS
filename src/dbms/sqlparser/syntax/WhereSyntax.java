package dbms.sqlparser.syntax;

import java.util.regex.Pattern;

public class WhereSyntax implements SQLSyntax {
    private static Pattern wherePattern;
    private static final String supportedOperators = "(>|<|>=|<=|=|!=){1}";
    private static final String booleanOperators = "(and|or)";
    private static final String valueFormat = "('\\w+'|\\w+|\"\\w+\")";

    private static final String whereRegex = "(\\s+where\\s+(TRUE|([(]\\s*)*\\s*(\\w+)\\s*"
            + supportedOperators + "\\s*" + valueFormat + "\\s*(\\s*[)])*\\s*(\\s+"
            + booleanOperators + "(\\s*([(]\\s*)*\\s*(\\w+)\\s*" + supportedOperators
            + "\\s*" + valueFormat + "\\s*(\\s*[)])*\\s*))*))?\\s*;\\s*$";

    @Override
    public String getRegex() {
        return whereRegex;
    }

    @Override
    public Pattern getPattern() {
        if (wherePattern == null) {
            wherePattern = Pattern.compile(whereRegex);
        }
        return wherePattern;
    }
}
