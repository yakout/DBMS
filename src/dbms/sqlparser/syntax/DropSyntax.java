package dbms.sqlparser.syntax;

import java.util.regex.Pattern;

public class DropSyntax implements SQLSyntax {

    private static DropSyntax instance = null;
    private final String DROP_REGEX = "(?i)^\\s*drop\\s+(table|database)"
            + "{1}\\s+("
            + SyntaxUtil.TABLE_NAME // OR SyntaxUtil.DATABASE_NAME
            + "){1}"
            + SyntaxUtil.SEMI_COLON
            + "$";
    private Pattern dropPattern = Pattern.compile(DROP_REGEX);

    private DropSyntax() {

    }

    public static DropSyntax getInstance() {
        if (instance == null) {
            instance = new DropSyntax();
        }
        return instance;
    }

    public static void main(String[] args) {
        System.out.print(getInstance().getRegex());
    }

    @Override
    public Pattern getPattern() {
        if (dropPattern == null) {
            dropPattern = Pattern.compile(DROP_REGEX);
        }
        return dropPattern;
    }

    @Override
    public String getRegex() {
        return DROP_REGEX;
    }
}
