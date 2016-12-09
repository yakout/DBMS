package dbms.sqlparser.syntax;

import java.util.regex.Pattern;

public class SelectSyntax implements SQLSyntax {
    private static Pattern selectPattern;
    public static final String selectRegex = "(?i)^\\s*select\\s+(distinct)?\\s+((\\w+\\s*(\\s*,\\s*\\w+)*)|(\\*))\\s+from\\s+(\\w+)";


    @Override
    public Pattern getPattern() {
        if (selectPattern == null) {
            selectPattern = Pattern.compile(selectRegex);
        }
        return selectPattern;
    }

    @Override
    public String getRegex() {
        return selectRegex;
    }
}
