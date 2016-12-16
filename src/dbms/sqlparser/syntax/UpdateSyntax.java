package dbms.sqlparser.syntax;

import java.util.regex.Pattern;

public class UpdateSyntax implements SQLSyntax {
    private static UpdateSyntax instance = null;
    private final String VALUE_FORMAT = "("
            + SyntaxUtil.MULTIPLE_WORDS_SINGLE_QUOTES + "|"
            + SyntaxUtil.NUMBER_FORMAT + "|"
            + SyntaxUtil.DATE_FORMAT + "|"
            + SyntaxUtil.COLUMN_NAME + ")";
    private final String UPDATE_REGEX = "(?i)^\\s*update\\s+("
            + SyntaxUtil.TABLE_NAME + "){1}\\s+set\\s+("
            + SyntaxUtil.COLUMN_NAME + "\\s*=\\s*"
            + VALUE_FORMAT + "\\s*(\\s*,\\s*"
            + SyntaxUtil.COLUMN_NAME + "\\s*=\\s*"
            + VALUE_FORMAT + ")*)"
            + WhereSyntax.getInstance().getRegex();
    private Pattern updatePattern = null;

    private UpdateSyntax() {
    }

    public static UpdateSyntax getInstance() {
        if (instance == null) {
            instance = new UpdateSyntax();
        }
        return instance;
    }

    public static void main(String[] args) {
        System.out.println(getInstance().getRegex());
    }

    @Override
    public Pattern getPattern() {
        if (updatePattern == null) {
            updatePattern = Pattern.compile(UPDATE_REGEX);
        }
        return updatePattern;
    }

    @Override
    public String getRegex() {
        return UPDATE_REGEX;
    }
}
