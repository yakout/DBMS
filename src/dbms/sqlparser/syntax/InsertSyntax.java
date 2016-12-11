package dbms.sqlparser.syntax;

import java.util.regex.Pattern;


public class InsertSyntax implements SQLSyntax {
    private final String COLUMNS_FORMAT = "(?:[(]\\s*("
            + SyntaxUtil.COLUMN_NAME + "\\s*(\\s*,\\s*"
            + SyntaxUtil.COLUMN_NAME + ")*)\\s*[)])?";

    private final String VALUE_FORMAT = "("
            + SyntaxUtil.MULTIPLE_WORDS_SINGLE_QUOTES
            + "|" + SyntaxUtil.MULTIPLE_WORDS_DOUBLE_QUOTES
            + "|" + SyntaxUtil.NUMBER_FORMAT
            + "|" + SyntaxUtil.DATE_FORMAT + ")";

    private final String VALUES_FORMAT = "[(]\\s*("
            + VALUE_FORMAT + "\\s*(\\s*,\\s*"
            + VALUE_FORMAT + ")*)\\s*[)]";

    private final String insertRegex = "(?i)^\\s*insert\\s+into\\s+("
            + SyntaxUtil.TABLE_NAME + ")\\s*"
            + COLUMNS_FORMAT + "\\s*values\\s*"
            + VALUES_FORMAT
            + SyntaxUtil.SEMI_COLON + "$";

    private Pattern insertPattern = null;

    private static InsertSyntax instance = null;

    private InsertSyntax() {
    }

    public static InsertSyntax getInstance() {
        if (instance == null) {
            instance = new InsertSyntax();
        }
        return instance;
    }

    @Override
    public Pattern getPattern() {
        if (insertPattern == null) {
            insertPattern = Pattern.compile(insertRegex);
        }
        return insertPattern;
    }

    @Override
    public String getRegex() {
        return insertRegex;
    }

    public static void main(String[] args) {
        System.out.println(getInstance().getRegex());
    }
}
