package dbms.sqlparser.syntax;

import java.util.regex.Pattern;

public class UseSyntax implements SQLSyntax {
    private static UseSyntax instance = null;
    private final String USE_REGEX = "(?i)^\\s*use\\s+("
            + SyntaxUtil.DATABASE_NAME + ")"
            + SyntaxUtil.SEMI_COLON + "$";
    private Pattern usePattern = null;

    private UseSyntax() {
    }

    public static UseSyntax getInstance() {
        if (instance == null) {
            instance = new UseSyntax();
        }
        return instance;
    }

    @Override
    public Pattern getPattern() {
        if (usePattern == null) {
            usePattern = Pattern.compile(USE_REGEX);
        }
        return usePattern;
    }

    @Override
    public String getRegex() {
        return USE_REGEX;
    }
}
