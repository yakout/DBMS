package dbms.sqlparser.syntax;

import java.util.regex.Pattern;

public class CreateSyntax implements SQLSyntax {
    private final String CREATE_REGEX = "(?i)^\\s*create\\s+((database\\s+("
            + SyntaxUtil.DATABASE_NAME + ")){1}|(table\\s+("
            + SyntaxUtil.TABLE_NAME + ")\\s*[(]\\s*(\\s*"
            + SyntaxUtil.COLUMN_NAME + "\\s+"
            + SyntaxUtil.SUPPORTED_DATA_TYPES + "\\s*(\\s*,\\s*"
            + SyntaxUtil.COLUMN_NAME + "\\s+"
            + SyntaxUtil.SUPPORTED_DATA_TYPES + "\\s*)*)\\s*[)]){1})"
            + SyntaxUtil.SEMI_COLON +"$";

    private Pattern createPattern = Pattern.compile(CREATE_REGEX);

    private static CreateSyntax instance = null;

    private CreateSyntax() {

    }

    public static CreateSyntax getInstance() {
        if (instance == null) {
            instance = new CreateSyntax();
        }
        return instance;
    }

    @Override
    public Pattern getPattern() {
        if (createPattern == null) {
            createPattern = Pattern.compile(CREATE_REGEX);
        }
        return createPattern;
    }

    @Override
    public String getRegex() {
        return CREATE_REGEX;
    }

    public static void main(String[] args) {
        System.out.println(getInstance().getRegex());
    }
}
