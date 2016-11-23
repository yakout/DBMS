package dbms.sqlparser;

import dbms.sqlparser.sqlInterpreter.Expression;

/**
 * Created by khlailmohammedyakout on 11/23/16.
 */
public class SQLParser {
    private static SQLParser instance;

    private SQLParser() {
    }

    public static SQLParser getInstace() {
        if (instance == null) {
            instance = new SQLParser();
        }
        return instance;
    }

    boolean validate(String statement) {
        // TODO
        return true;
    }

    public Expression parse(String statement) {
        // TODO
        return null;
    }
}
