package dbms.sqlparser.syntax;


import java.util.regex.Pattern;

public class InsertSyntax implements SQLSyntax {
    private static Pattern insertPattern;

    private static String columnsFormat = "[(]\\s*(\\w+\\s*(\\s*,\\s*\\w+)*)\\s*[)]";

    private static String valuesFormat = "[(]\\s*(('\\w+'|\\d+|\"\\w+\"){1}\\s*(\\s*,\\s*('\\w+'|\\d+|\"\\w+\"){1})*)\\s*[)]";

    private static final String insertRegex = "(?i)^\\s*insert\\s+into\\s+(\\w+){1}\\s+"
            + columnsFormat + "\\s*values\\s*" + valuesFormat + "\\s*;\\s*$";

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
}
