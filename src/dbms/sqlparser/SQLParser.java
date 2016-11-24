package dbms.sqlparser;

import dbms.exception.SyntaxErrorException;
import dbms.sqlparser.sqlInterpreter.Expression;
import dbms.sqlparser.sqlInterpreter.rules.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SQLParser {
    private final String propFileName = "dbms.sqlparser.SQLRegex";
    private ResourceBundle SQLRegexProperties = ResourceBundle
            .getBundle(propFileName);

    private static SQLParser instance;

    private SQLParser() {
    }

    public static SQLParser getInstace() {
        if (instance == null) {
            instance = new SQLParser();
        }
        return instance;
    }

    /**
     * validate the query.
     * @param regex
     * @param query
     * @return return the index of the error
     */
    public Matcher validate(Pattern regex, String query) throws SyntaxErrorException {
        for (int i = 0; i <= query.length(); i++) {
            Matcher m = regex.matcher(query.substring(0, i));
            if (!m.matches() && !m.hitEnd()) {
                throw new SyntaxErrorException("Error: near \"" + query.substring(i - 2, i) +
                        "\" : syntax error");
            }
        }
        if (regex.matcher(query).matches()) {
            return regex.matcher(query);
        } else {
            throw new SyntaxErrorException("Error: statement must end with \";\": syntax error");
        }
    }

    public Expression parse(String query) throws SyntaxErrorException {
        query = query.toLowerCase();
        Pattern rulePattern = Pattern.compile(SQLRegexProperties.getString("rule.regex"));
        Matcher ruleMatcher = validate(rulePattern, query);

        ruleMatcher.matches(); // FUCKKKKKKKKK!!!!!
        switch (ruleMatcher.group(1)) {
            case "select":
                Pattern selectPattern = Pattern.compile(SQLRegexProperties.getString("select.regex"));
                return parseSelect(validate(selectPattern, query));
            case "drop":
                Pattern dropPattern = Pattern.compile(SQLRegexProperties.getString("drop.regex"));
                return parseDrop(validate(dropPattern, query));
            case "insert":
                Pattern insertPattern = Pattern.compile(SQLRegexProperties.getString("insert.regex"));
                return parseInsert(validate(insertPattern, query));
            case "delete":
                Pattern deletePattern = Pattern.compile(SQLRegexProperties.getString("delete.regex"));
                return parseDelete(validate(deletePattern, query));
            case "update":
                Pattern updatePattern = Pattern.compile(SQLRegexProperties.getString("update.regex"));
                return parseUpdate(validate(updatePattern, query));
            case "create":
                Pattern createPattern = Pattern.compile(SQLRegexProperties.getString("create.regex"));
                return parseCreate(validate(createPattern, query));
            case "use":
                Pattern usePattern = Pattern.compile(SQLRegexProperties.getString("use.database.regex"));
                return parseUse(validate(usePattern, query));
            default:
                return null;
        }
    }

    private Expression parseSelect(Matcher matcher) {
        matcher.matches();
//        String dbName = matcher.group(5).split("\\.")[0];
//        String tableName = matcher.group(5).split("\\.")[1];

        Select select = new Select(matcher.group(5));
        if (matcher.group(4) != null) {
            select.setSelectAll(true);
        } else {
            select.setColumns(Arrays.asList(matcher.group(2).split(",")));
        }
        if (matcher.group(7) != null) {
            // SQLPredicate sqlPredicate = new SQLPredicate(ma)
            // select.setWhere(matcher.group(7));
        }

        return select;
    }

    private Expression parseDrop(Matcher matcher) {
        matcher.matches();
        switch (matcher.group(1)) {
            case "database":
                return new DropDatabase(matcher.group(2));
            case "table":
                return new DropTable(matcher.group(2));
            default:
                return null;
        }
    }

    private Expression parseInsert(Matcher matcher) throws SyntaxErrorException {
        matcher.matches();
        String tableName = matcher.group(1);
        String[] columns = matcher.group(2).split(",");
        String[] values = matcher.group(4).split(",");
        if (columns.length != values.length) {
            throw new SyntaxErrorException("Error: Columns number does not match values number");
        }
        HashMap<String, Object> entryMap = new HashMap<>();
        for (int i = 0; i < columns.length; i++) {
            if (values[i].trim().startsWith("'")) {
                entryMap.put(columns[i], values[i]);
            } else {
                entryMap.put(columns[i], Integer.parseInt(values[i]));
            }
        }
        InsertIntoTable insertIntoTable = new InsertIntoTable(tableName, entryMap);
        return insertIntoTable;
    }

    private Expression parseDelete(Matcher matcher) {
        matcher.matches();
        // TODO
        return null;
    }

    private Expression parseUpdate(Matcher matcher) {
        matcher.matches();
        // TODO
        return null;
    }

    private Expression parseCreate(Matcher matcher) {
        matcher.matches();
        // TODO
        return null;
    }

    private Expression parseUse(Matcher matcher) {
        matcher.matches();
        UseDatabase useDatabase = new UseDatabase(matcher.group(1));
        return useDatabase;
    }

    public static void main(String[] args) {
        try {
            System.out.println(new SQLParser().parse("insert into table_name (col1, col2) values (1, 'val2')"));
//                    "select col1,col2,col3,col4, col " +
//                    "from db.fuck where fuck  > fuck" +
//                    " ;"));
        } catch (SyntaxErrorException e) {
            System.out.println(e.toString());
        }
    }
}


