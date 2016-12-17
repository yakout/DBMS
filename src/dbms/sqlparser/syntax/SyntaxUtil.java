package dbms.sqlparser.syntax;

import java.util.regex.Pattern;

/**
 * Utility class that stores regex that is frequently used by
 * sql statements in {@link dbms.sqlparser.syntax} package.
 */
public class SyntaxUtil {
    /**
     * Regex for column name.
     */
    public static final String COLUMN_NAME = "\\w+";
    /**
     * Regex for tatabase name.
     */
    public static final String DATABASE_NAME = "\\w+";
    /**
     * Regex for table name.
     */
    public static final String TABLE_NAME = "\\w+";

    /**
     * Regex to support spaces in values between double quotes.
     * TODO: support 'afloatis 0.3'
     */
    public static final String MULTIPLE_WORDS_DOUBLE_QUOTES = "\""
            + "(?:\\s*\\w+\\s*)*\"";
    /**
     * Regex to support spaces in values between single quotes.
     */
    public static final String MULTIPLE_WORDS_SINGLE_QUOTES = "'"
            + "(?:\\s*\\w+\\s*)*'";

    /**
     * Regex for date format.
     */
    public static final String DATE_FORMAT = "'\\d+-\\d+-\\d+'";
    /**
     * Regex for number format.
     */
    public static final String NUMBER_FORMAT = "-?[0-9]\\d*(?:\\.\\d+)?";

    /**
     * Regex to support semi-colon in sql statement.
     * To remove support for semi-colon just replace "\s*\;\s*" with "\s*"
     */
//    public static final String SEMI_COLON = "\\s*\\;\\s*";
    public static final String SEMI_COLON = "\\s*";

    /**
     * The supported data types.
     */
    public static final String SUPPORTED_DATA_TYPES = ""
            + "(int|varchar|date|float)";

    /**
     * Regex for the supported sql statements (rules).
     */
    public static final String RULE_REGEX = "(?i)^\\s*(.*(union\\s+all|union)"
            + "|select|" +
            "drop|insert|update|delete|create|where|use|alter).*";

    /**
     * Regex for rule The supported data types.
     */
    public static final Pattern RULE_PATTERN = Pattern.compile(RULE_REGEX);
}
