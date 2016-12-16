package dbms.sqlparser.syntax;

import java.util.regex.Pattern;

public class SelectSyntax implements SQLSyntax {
    private static SelectSyntax instance = null;
    private final String COLUMNS_FORMAT = "(("
            + SyntaxUtil.COLUMN_NAME + "\\s*(\\s*,\\s*"
            + SyntaxUtil.COLUMN_NAME + ")*)|(\\*))";
    private final String ORDERBY_COLUMNS_FORMAT = "("
            + SyntaxUtil.COLUMN_NAME + "\\s*(\\s+ASC|\\s+DESC)?\\s*(\\s*,\\s*"
            + SyntaxUtil.COLUMN_NAME + "\\s*(?:\\s+ASC|\\s+DESC)?\\s*)*)";
    private final String ORDERBY_REGEX = "(\\s+(?i)(?:order\\s+by)(?-i)\\s+"
            + ORDERBY_COLUMNS_FORMAT + ")?";
    private final String SELECT_REGEX = "(?i)^\\s*select\\s+(distinct\\s+)?"
            + COLUMNS_FORMAT + "\\s+from\\s+("
            + SyntaxUtil.TABLE_NAME + ")"
            + ORDERBY_REGEX
            + WhereSyntax.getInstance().getRegex();
    private final String UNION_REGEX = SELECT_REGEX.replaceAll("(\\^|\\$)", "")
            + "\\s+((union|union\\s+all)\\s+"
            + SELECT_REGEX.replaceAll("(\\^|\\$)", "") + ")*$";
    private Pattern selectPattern = null;

    private SelectSyntax() {
    }

    public static SelectSyntax getInstance() {
        if (instance == null) {
            instance = new SelectSyntax();
        }
        return instance;
    }

    public static void main(String[] args) {
        System.out.println(getInstance().UNION_REGEX);
    }

    @Override
    public Pattern getPattern() {
        if (selectPattern == null) {
            selectPattern = Pattern.compile(SELECT_REGEX);
        }
        return selectPattern;
    }

    @Override
    public String getRegex() {
        return SELECT_REGEX;
    }
}
