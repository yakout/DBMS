package dbms.sqlparser.syntax;

import java.util.regex.Pattern;

public class AlterSyntax implements SQLSyntax {
    private final String ALTER_REGEX = "(?i)^\\s*alter\\s+table\\s+("
            + SyntaxUtil.TABLE_NAME + "){1}\\s+(((drop\\s+column){1}\\s+("
            + SyntaxUtil.COLUMN_NAME +"){1}\\s*)|((add){1}\\s+("
            + SyntaxUtil.COLUMN_NAME +"){1}\\s+"
            + SyntaxUtil.SUPPORTED_DATA_TYPES + "))"
            + SyntaxUtil.SEMI_COLON + "$";

    private Pattern alterPattern = null;

    private static AlterSyntax instance = null;

    private AlterSyntax() {
    }

    public static AlterSyntax getInstance() {
        if (instance == null) {
            instance = new AlterSyntax();
        }
        return instance;
    }

    @Override
    public Pattern getPattern() {
        if (alterPattern == null) {
            alterPattern = Pattern.compile(ALTER_REGEX);
        }
        return alterPattern;
    }

    @Override
    public String getRegex() {
        return ALTER_REGEX ;
    }

    public static void main(String[] args) {
        System.out.println(getInstance().getRegex());
    }

}
