package dbms.sqlparser;

import dbms.datatypes.*;
import dbms.exception.SyntaxErrorException;
import dbms.sqlparser.sqlInterpreter.Where;
import dbms.sqlparser.sqlInterpreter.rules.*;
import dbms.sqlparser.syntax.*;
import javafx.util.Pair;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * validate and parse a sql query.
 */
public class SQLParser {
    private static final String[] reservedKeywords = new String[]{
            "select", "create", "insert", "into", "delete", "update",
            "use", "from", "drop", "alter", "where", "and", "or", "not",
            "distinct", "ASC", "DESC", "database", "table", "column", "row",
            "int", "varchar", "float", "date", "order", "by", "add", "is",
            "union"
    };
    /**
     * singleton instance of {@link SQLParser}.
     */
    private static SQLParser instance;

    private SQLParser() {
    }

    /**
     * will returns the SQLParser singleton instance or create a new instance if
     * not found.
     * @return the singleton instance.
     */
    public static SQLParser getInstance() {
        if (instance == null) {
            instance = new SQLParser();
        }
        return instance;
    }

    /**
     * validate the query.
     * @param regex the pattern to validate against.
     * @param query the sql statement.
     * @return return the index of the error
     */
    public Matcher validate(final Pattern regex, final String query)
    		throws SyntaxErrorException {
        if (regex.matcher(query).matches()) {
            return regex.matcher(query);
        } else {
            throw new SyntaxErrorException("Error:"
            		+ " statement must end with \";\": syntax error");
        }
    }

    /**
     * parse the query.
     * @param query sql statement
     * @return {@link Expression}.
     * @throws SyntaxErrorException
     */
    public Expression parse(final String query) throws SyntaxErrorException {
        Pattern rulePattern = SyntaxUtil.RULE_PATTERN;
        Matcher ruleMatcher = validate(rulePattern, query);
        ruleMatcher.matches();
        if (ruleMatcher.group(2) != null) {
            return parseUnion(query, ruleMatcher.group(2));
        }
        switch (ruleMatcher.group(1).toLowerCase()) {
            case "select":
                Pattern selectPattern = SelectSyntax.getInstance().getPattern();
                return parseSelect(validate(selectPattern, query));
            case "drop":
                Pattern dropPattern = DropSyntax.getInstance().getPattern();
                return parseDrop(validate(dropPattern, query));
            case "insert":
                Pattern insertPattern = InsertSyntax.getInstance().getPattern();
                return parseInsert(validate(insertPattern, query));
            case "delete":
                Pattern deletePattern = DeleteSyntax.getInstance().getPattern();
                return parseDelete(validate(deletePattern, query));
            case "update":
                Pattern updatePattern = UpdateSyntax.getInstance().getPattern();
                return parseUpdate(validate(updatePattern, query));
            case "create":
                Pattern createPattern = CreateSyntax.getInstance().getPattern();
                return parseCreate(validate(createPattern, query));
            case "alter":
                Pattern alterPattern = AlterSyntax.getInstance().getPattern();
                return parseAlter(validate(alterPattern, query));
            case "use":
                Pattern usePattern = UseSyntax.getInstance().getPattern();
                return parseUse(validate(usePattern, query));
            default:
                return null;
        }
    }

    private Expression parseAlter(final Matcher matcher) {
        matcher.matches();
        String tableName = matcher.group(1);
        String columnName;
        Class< ? extends DBDatatype> dataType = null;

        if (matcher.group(7) != null) {
            columnName = matcher.group(8).toLowerCase();
            switch (matcher.group(9).toLowerCase()) {
                case "int":
                    dataType = DBInteger.class;
                    break;
                case "varchar":
                    dataType = DBString.class;
                    break;
                case "float":
                    dataType = DBFloat.class;
                    break;
                case "date":
                    dataType = DBDate.class;
            }
            return new AlterAdd(tableName, columnName, dataType);
        } else {
            columnName = matcher.group(5);
            return new AlterDrop(tableName, columnName);
        }
    }

    /**
     * parse insert statement.
     * @param matcher matched pattern from query.
     * @return {@link Expression}.
     * @throws SyntaxErrorException where the input syntax is invalid.
     */
    private Expression parseInsert(final Matcher matcher) throws
    	SyntaxErrorException {
        matcher.matches();
        String tableName = matcher.group(1);
        String[] columns = matcher.group(2) == null ? null :
        	matcher.group(2).split(",");
        String[] values = matcher.group(4).split(",");
        if (columns != null && columns.length != values.length) {
            throw new SyntaxErrorException("Error:"
            		+ " Columns number does not match values number");
        }
        HashMap<String, DBDatatype> entryMap = new LinkedHashMap<>();
        for (int i = 0; i < values.length; i++) {
            String column = columns == null ? String.valueOf(i) :
            	columns[i].trim().toLowerCase();
            String value = values[i].trim();
            entryMap.put(column, DatatypeFactory.convertToDataType(
            		DatatypeFactory.convertToObject(value)));
        }
        InsertIntoTable insertIntoTable = new InsertIntoTable(tableName, entryMap);
        if (columns == null) insertIntoTable.insertWithNoColumns(true);
        return insertIntoTable;
    }

    /**
     * parse select statement
     * @param matcher matched pattern from query.
     * @return {@link Expression}.
     */
    private Expression parseSelect(final Matcher matcher) {
        matcher.matches();
        String tableName = matcher.group(6);
        Select select = new Select(tableName);
        if (matcher.group(1) != null) {
            select.distinct();
        }
        if (matcher.group(5) != null) {
            select.setColumns(null);
        } else {
            String[] columnsTemp = matcher.group(3).split(",");
            Collection<String> columns = new ArrayList<>();
            for (int i = 0; i < columnsTemp.length; i++) {
                columns.add(columnsTemp[i].trim().toLowerCase());
            }
            select.setColumns(columns);
        }

        if (matcher.group(8) != null) { // if there is order by statement.
            select.setOrderBy(parseOrderby(matcher.group(8)));
        }
        if (matcher.group(12) != null) { // if there is where condition
            select.setWhere(new Where(matcher.group(12)));
        }
        return select;
    }

    /**
     * Helper Method for {@link SQLParser#parseSelect(Matcher)}
     */
    private List<Pair<String, Boolean>> parseOrderby(final String query) {
        List<Pair<String, Boolean>> columns = new ArrayList<>();
        String[] orderbyArray = query.trim().split(",");
        for (int i = 0; i < orderbyArray.length; i++) {
            String[] orderby = orderbyArray[i].trim().split("\\s+");
            if (orderby.length == 1) {
                columns.add(new Pair<>(orderby[0].toLowerCase(), false));
            } else {
                if (orderby[1].equals("ASC")) {
                    columns.add(new Pair<>(orderby[0].toLowerCase(), false));
                } else {
                    columns.add(new Pair<>(orderby[0].toLowerCase(), true));
                }
            }
        }
        return columns;
    }


    private Expression parseUnion(final String query, final String unionForm)
    		throws SyntaxErrorException {
        List<Select> selectList = new ArrayList<>();
        String[] selects = query.split("(?i)(union\\s+all|union)");
        for (int i = 0; i < selects.length; i++) {
            Select select = (Select) parseSelect(validate(
            		SelectSyntax.getInstance().getPattern(), selects[i]));
            selectList.add(select);
        }
        if (unionForm.toLowerCase().equals("union")) {
            return new Union(selectList, true);
        }
        return new Union(selectList, false);
    }

    /**
     * parse drop statement.
     * @param matcher matched pattern from query.
     * @return {@link Expression}.
     */
    private Expression parseDrop(final Matcher matcher) {
        matcher.matches();
        switch (matcher.group(1).toLowerCase()) {
            case "database":
                return new DropDatabase(matcher.group(2));
            case "table":
                return new DropTable(matcher.group(2));
            default:
                return null;
        }
    }

    /**
     * parse delete statement.
     * @param matcher matched pattern from query.
     * @return {@link Expression}.
     */
    private Expression parseDelete(final Matcher matcher) {
        matcher.matches();
        Delete delete = new Delete(matcher.group(2));

        if (matcher.group(4) != null) {
            delete.setWhere(new Where(matcher.group(4)));
        }
        return delete;
    }

    private Expression parseUpdate(final Matcher matcher) {
        matcher.matches();
        Map<String, DBDatatype> values = new LinkedHashMap<>();
        Map<String, String> columns = new LinkedHashMap<>();

        String[] setValues = matcher.group(2).split(",");
        for (int i = 0; i < setValues.length; i++) {
            String key = setValues[i].split("=")[0].trim().toLowerCase();
            String value = setValues[i].split("=")[1].trim();
            if (value.startsWith("'") || value.startsWith("\"")
            		|| value.matches(SyntaxUtil.NUMBER_FORMAT)) {
                values.put(key, DatatypeFactory.convertToDataType(
                		DatatypeFactory.convertToObject(value)));
            } else {
                columns.put(key, value.toLowerCase());
            }
        }
        Update update = new Update(matcher.group(1).toLowerCase(),
        		values, columns);

        if (matcher.group(7) != null) {
            update.setWhere(new Where(matcher.group(7)));
        }
        return update;
    }

    /**
     * parse create statement.
     * @param matcher matched pattern from query.
     * @return {@link Expression}.
     */
    private Expression parseCreate(final Matcher matcher) {
        matcher.matches();

        if (matcher.group(1).toLowerCase().startsWith("database")) {
            return new CreateDatabase(matcher.group(3));
        }

        String[] columnsDesc = matcher.group(6).split(",");
        Map<String, Class< ? extends DBDatatype>> columns =
        		new LinkedHashMap<>();
        for (int i = 0; i < columnsDesc.length; i++) {
            String key = columnsDesc[i].trim().split("\\s+")[0].toLowerCase();
            switch (columnsDesc[i].trim().split("\\s+")[1].toLowerCase()) {
                case "int":
                    columns.put(key, DBInteger.class);
                    break;
                case "varchar":
                    columns.put(key, DBString.class);
                    break;
                case "date":
                    columns.put(key, DBDate.class);
                    break;
                case "float":
                    columns.put(key, DBFloat.class);
            }
        }

        return new CreateTable(matcher.group(5).toLowerCase(), columns);
    }

    /**
     * parse the use statement.
     * @param matcher matched pattern from query.
     * @return {@link Expression}.
     */
    private Expression parseUse(Matcher matcher) {
        matcher.matches();
        return new UseDatabase(matcher.group(1));
    }
}