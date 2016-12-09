package dbms.sqlparser.syntax;

import java.util.regex.Pattern;

public class UpdateSyntax implements SQLSyntax {
    private static Pattern updatePattern;
    private static final String valueFormat = "('\\w+'|\\w+|\"\\w+\"){1}";
    private static final String updateRegex = "(?i)^\\s*update\\s+(\\w+){1}\\s+set\\s+(\\w+\\s*=\\s*"
            + valueFormat + "\\s*(\\s*,\\s*\\w+\\s*=\\s*" + valueFormat + ")*)";

    @Override
    public String getRegex() {
        return updateRegex;
    }

    @Override
    public Pattern getPattern() {
        if (updatePattern == null) {
            updatePattern = Pattern.compile(updateRegex);
        }
        return updatePattern;
    }
}
