package dbms.sqlparser.syntax;

import java.util.regex.Pattern;

public class CreateSyntax implements SQLSyntax {
    private static Pattern createPattern;

    private static final String supportedDataTypes = "(int|varchar|date|float){1}";

    private static final String createRegex = "(?i)^\\s*create\\s+((database\\s+(\\w+)){1}|(table\\s+(\\w+)\\s*[(]\\s*(\\s*\\w+\\s+"
            + supportedDataTypes + "\\s*(\\s*,\\s*\\w+\\s+" + supportedDataTypes + "\\s*)*)\\s*[)]){1})\\s*;\\s*$";


    @Override
    public Pattern getPattern() {
        if (createPattern == null) {
            createPattern = Pattern.compile(createRegex);
        }
        return createPattern;
    }

    @Override
    public String getRegex() {
        return createRegex;
    }
}
