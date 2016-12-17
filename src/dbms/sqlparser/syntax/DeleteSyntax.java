package dbms.sqlparser.syntax;

import java.util.regex.Pattern;

public class DeleteSyntax implements SQLSyntax {
    private static DeleteSyntax instance = null;
    private final String DELETE_REGEX = "(?i)^\\s*delete\\s+([*]{1}\\s+)"
            + "?from\\s+("
            + SyntaxUtil.TABLE_NAME + ")"
            + WhereSyntax.getInstance().getRegex();
    private Pattern deletePattern = Pattern.compile(DELETE_REGEX);

    private DeleteSyntax() {

    }

    public static DeleteSyntax getInstance() {
        if (instance == null) {
            instance = new DeleteSyntax();
        }
        return instance;
    }

    public static void main(String[] args) {
        System.out.print(getInstance().getRegex());
    }

    @Override
    public Pattern getPattern() {
        if (deletePattern == null) {
            deletePattern = Pattern.compile(DELETE_REGEX);
        }
        return deletePattern;
    }

    @Override
    public String getRegex() {
        return DELETE_REGEX;
    }
}
