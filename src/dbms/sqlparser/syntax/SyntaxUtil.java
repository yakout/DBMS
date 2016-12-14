package dbms.sqlparser.syntax;

import java.util.regex.Pattern;

public class SyntaxUtil {
    public static final String COLUMN_NAME = "\\w+";
    public static final String DATABASE_NAME = "\\w+";
    public static final String TABLE_NAME = "\\w+";
    public static final String MULTIPLE_WORDS_DOUBLE_QUOTES = "\"(?:\\s*\\w+\\s*)*\"";
    public static final String MULTIPLE_WORDS_SINGLE_QUOTES = "'(?:\\s*\\w+\\s*)*'";
    public static final String DATE_FORMAT = "'\\d+-\\d+-\\d+'";
    public static final String NUMBER_FORMAT = "-?[0-9]\\d*(?:\\.\\d+)?";
    public static final String SEMI_COLON = "\\s*"; // \\;\\s*";
    public static final String SUPPORTED_DATA_TYPES = "(int|varchar|date|float){1}";
    public static final String RULE_REGEX = "(?i)^\\s*(.*(union\\s+all|union)|select|drop|insert|update|delete|create|where|use|alter).*";

    public static final Pattern RULE_PATTERN = Pattern.compile(RULE_REGEX);
}
