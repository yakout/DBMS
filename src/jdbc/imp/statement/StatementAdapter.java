package jdbc.imp.statement;

import dbms.exception.*;
import dbms.sqlparser.SQLParser;
import dbms.sqlparser.sqlInterpreter.rules.*;
import dbms.util.RecordSet;
import jdbc.imp.resultSet.DBResultSetImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StatementAdapter extends DBStatement {
    private Connection connection;
    private List<String> batch;
    private ResultSet resultSet;
    private Logger log = LogManager.getLogger(StatementAdapter.class.getName());

    public StatementAdapter(Connection connection) {
        this.connection = connection;
        this.batch = new ArrayList<>();
    }

    @Override
    public Connection getConnection() throws SQLException {
        return connection;
    }


    @Override
    public void addBatch(String sql) throws SQLException {
        batch.add(sql);
    }

    @Override
    public void clearBatch() throws SQLException {
        batch.clear();
    }

    @Override
    public int[] executeBatch() throws SQLException {
        int[] values = new int[batch.size()];
        for (int i = 0; i < batch.size(); i++) {
            String query = batch.get(i);
            if (execute(query)) values[i] = SUCCESS_NO_INFO;
            values[i] = executeUpdate(query);
        }
        return values;
    }

    @Override
    public boolean execute(String sql) throws SQLException {
        try {
            Expression expression = SQLParser.getInstance().parse(sql);
            expression.execute();
            log.debug(expression.getClass().toString().replace("dbms"
                    + ".sqlparser.sqlInterpreter.rules.", "")
                    + " Command executed successfully");
            if (expression.getClass() == Select.class) {
                RecordSet recordSet = ((Select) expression).getRecordSet();
                resultSet = new DBResultSetImpl(this, recordSet);
                if (recordSet.size() == 0) {
                    return false;
                }
                return true;
            } else if (expression.getClass() == Union.class) {
                RecordSet recordSet = ((Union) expression).getRecordSet();
                resultSet = new DBResultSetImpl(this, recordSet);
                if (recordSet.size() == 0) {
                    return false;
                }
                return true;
            }
        } catch (dbms.exception.SyntaxErrorException
                | IncorrectDataEntryException | DataTypeNotSupportedException
                | DatabaseNotFoundException | TableNotFoundException
                | DatabaseAlreadyCreatedException |
                TableAlreadyCreatedException e) {
            log.error("Invalid sql command " + e.toString());
            throw new SQLException(e.toString());
        }
        return false; // insert, update, delete, drop, create, use, alter ...
    }

    @Override
    public ResultSet executeQuery(String sql) throws SQLException {
        try {
            Expression expression = SQLParser.getInstance().parse(sql);
            expression.execute();
            log.debug(expression.getClass().toString().replace("dbms"
                    + ".sqlparser.sqlInterpreter.rules.", "")
                    + " Command executed successfully");
            RecordSet recordSet = ((Select) expression).getRecordSet();
            resultSet = new DBResultSetImpl(this, recordSet);
            return resultSet;
        } catch (Exception e) {
            log.error("Invalid sql command " + e.toString());
            throw new SQLException(e.toString());
        }
    }

    @Override
    public int executeUpdate(String sql) throws SQLException {
        try {
            Expression expression = SQLParser.getInstance().parse(sql);
            expression.execute();
            log.debug(expression.getClass().toString().replace("dbms"
                    + ".sqlparser.sqlInterpreter.rules.", "")
                    + " Command executed successfully");
            if (expression instanceof DDLStatement) {
                return 0;
            }
            return ((DMLStatement) expression).getUpdateCount();
        } catch (SyntaxErrorException
                | IncorrectDataEntryException | DataTypeNotSupportedException
                | DatabaseNotFoundException | TableNotFoundException
                | DatabaseAlreadyCreatedException |
                TableAlreadyCreatedException e) {
            log.error("Invalid sql command " + e.toString());
            throw new SQLException(e.toString());
        }
    }

    @Override
    public ResultSet getResultSet() throws SQLException {
        return resultSet;
    }


    @Override
    public int getUpdateCount() throws SQLException {
        return super.getUpdateCount();
    }

    @Override
    public void close() throws SQLException {
        connection = null;
        batch = null;
        resultSet = null;
    }
}
