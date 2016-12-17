package dbms.sqlparser;

import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class PatternsHolder {
    /**
     * name of the properties file that stores all regex pattern for sql
     * statements.
     */
    private static final String propFileName = "dbms.sqlparser.SQLRegex";
    /**
     * reference to SQLRegex.properties file.
     */
    private static ResourceBundle SQLRegexProperties = ResourceBundle.
            getBundle(propFileName);
    private static Pattern rulePattern;
    private static Pattern selectPattern;
    private static Pattern updatePattern;
    private static Pattern usePattern;
    private static Pattern deletePattern;
    private static Pattern insertIntoPattern;
    private static Pattern dropPattern;
    private static Pattern alterPattern;
    private static Pattern createPattern;


    static synchronized Pattern getSelectPattern() {
        if (selectPattern == null) {
            selectPattern = Pattern.compile(
                    SQLRegexProperties.getString("select.regex")
                            + SQLRegexProperties.getString("orderby.regex")
                            + SQLRegexProperties.getString("where.regex"));
        }
        return selectPattern;
    }

    static synchronized Pattern getAlterPattern() {
        if (alterPattern == null) {
            alterPattern = Pattern.compile(SQLRegexProperties.getString(
                    "alter.regex"));
        }
        return alterPattern;
    }

    static synchronized Pattern getCreatePattern() {
        if (createPattern == null) {
            createPattern = Pattern.compile(SQLRegexProperties.getString(
                    "create.regex"));
        }
        return createPattern;
    }


    static synchronized Pattern getRulePattern() {
        if (rulePattern == null) {
            rulePattern = Pattern.compile(SQLRegexProperties.getString(
                    "rule.regex"));
        }
        return rulePattern;
    }

    static synchronized Pattern getUpdatePattern() {
        if (updatePattern == null) {
            updatePattern = Pattern.compile(
                    SQLRegexProperties.getString("update.regex")
                            + SQLRegexProperties.getString("where.regex"));
        }
        return updatePattern;
    }

    static synchronized Pattern getUsePattern() {
        if (usePattern == null) {
            usePattern = Pattern.compile(SQLRegexProperties.getString(
                    "use.database.regex"));
        }
        return usePattern;
    }


    static synchronized Pattern getDeletePattern() {
        if (dropPattern == null) {
            deletePattern = Pattern.compile(
                    SQLRegexProperties.getString("delete.regex")
                            + SQLRegexProperties.getString("where.regex"));
        }
        return deletePattern;
    }

    static synchronized Pattern getInsertIntoPattern() {
        if (insertIntoPattern == null) {
            insertIntoPattern = Pattern.compile(SQLRegexProperties.getString(
                    "insert.regex"));
        }
        return insertIntoPattern;
    }

    static synchronized Pattern getDropPattern() {
        if (dropPattern == null) {
            dropPattern = Pattern.compile(SQLRegexProperties.getString(
                    "drop.regex"));
        }
        return dropPattern;
    }


}
