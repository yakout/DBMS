package dbms.sqlparser.syntax;

import java.util.regex.Pattern;

public interface SQLSyntax {
    Pattern getPattern();

    String getRegex();
}
