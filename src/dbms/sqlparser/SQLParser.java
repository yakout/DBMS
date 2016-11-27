package dbms.sqlparser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dbms.exception.SyntaxErrorException;
import dbms.sqlparser.sqlInterpreter.SQLPredicate;
import dbms.sqlparser.sqlInterpreter.rules.CreateDatabase;
import dbms.sqlparser.sqlInterpreter.rules.CreateTable;
import dbms.sqlparser.sqlInterpreter.rules.Delete;
import dbms.sqlparser.sqlInterpreter.rules.DropDatabase;
import dbms.sqlparser.sqlInterpreter.rules.DropTable;
import dbms.sqlparser.sqlInterpreter.rules.Expression;
import dbms.sqlparser.sqlInterpreter.rules.InsertIntoTable;
import dbms.sqlparser.sqlInterpreter.rules.Select;
import dbms.sqlparser.sqlInterpreter.rules.Update;
import dbms.sqlparser.sqlInterpreter.rules.UseDatabase;
import dbms.sqlparser.sqlInterpreter.rules.Where;

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
                throw new SyntaxErrorException("Error: near \"" + query.substring(i - 1, i) +
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
        Pattern rulePattern = Pattern.compile(SQLRegexProperties.getString("rule.regex"));
        Matcher ruleMatcher = validate(rulePattern, query);

        ruleMatcher.matches();
        switch (ruleMatcher.group(1).toLowerCase()) {
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
        String tableName = matcher.group(5);
        Select select = new Select(tableName);
        SQLPredicate sqlPredicate;
        if (matcher.group(4) != null) {
            select.setSelectAll(true);
        } else {
            String[] columnsTemp = matcher.group(2).split(",");
            Collection<String> columns = new ArrayList<>();
            for (int i = 0; i < columnsTemp.length; i++) {
                columns.add(columnsTemp[i].trim());
            }
            select.setColumns(columns);
        }
        if (matcher.group(7) != null) { // if there is where condition
            select.setWhere(new Where(matcher.group(7)));
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
            String column = columns[i].trim();
            String value = values[i].trim();
            if (value.startsWith("'") || value.startsWith("\"")) {
                entryMap.put(column, value.replaceAll("('|\")", ""));
            } else {
                entryMap.put(column, Integer.parseInt(value));
            }
        }
        InsertIntoTable insertIntoTable = new InsertIntoTable(tableName, entryMap);
        return insertIntoTable;
    }

    private Expression parseDelete(Matcher matcher) {
        matcher.matches();
        Delete delete = new Delete(matcher.group(2));

        if (matcher.group(4) != null) {
            delete.setWhere(new Where(matcher.group(4)));
        }
        return delete;
    }

    private Expression parseUpdate(Matcher matcher) {
        matcher.matches();
        Map<String, Object> values = new HashMap<>();
        Map<String, String> columns = new HashMap<>();

        String[] setValues = matcher.group(2).split(",");
        for (int i = 0; i < setValues.length; i++) {
            String key = setValues[i].split("=")[0].trim();
            String value = setValues[i].split("=")[1].trim();
            if (value.startsWith("'") || value.startsWith("\"")) {
                values.put(key, value.replaceAll("('|\")", ""));
            } else {
                try {
                    values.put(key, Integer.parseInt(value));
                } catch (NumberFormatException e) {
                    columns.put(key, value);
                }
            }
        }
        Update update = new Update(matcher.group(1), values, columns);

        if (matcher.group(7) != null) {
            update.setWhere(new Where(matcher.group(7)));
        }
        return update;
    }

    private Expression parseCreate(Matcher matcher) {
        matcher.matches();

        if (matcher.group(1).toLowerCase().startsWith("database")) {
            return new CreateDatabase(matcher.group(3));
        }

        String[] columnsDesc = matcher.group(6).split(",");
        Map<String, Class> columns = new HashMap<>();
        for (int i = 0; i < columnsDesc.length; i++) {
            String key = columnsDesc[i].trim().split("\\s+")[0];
            switch (columnsDesc[i].trim().split("\\s+")[1].toLowerCase()) {
                case "int":
                    columns.put(key, Integer.class);
                    break;
                case "varchar":
                    columns.put(key, String.class);
                    break;
            }
        }

        return new CreateTable(matcher.group(5), columns);
    }

    private Expression parseUse(Matcher matcher) {
        matcher.matches();
        UseDatabase useDatabase = new UseDatabase(matcher.group(1));
        return useDatabase;
    }

    public static void main(String[] args) {
        try {
            System.out.println(new SQLParser().parse("SELECT COL_NAME FROM TABLE_NAME WHERE COL1 = COL2;"));
        } catch (SyntaxErrorException e) {
            System.out.println(e.toString());
        }

    }
}