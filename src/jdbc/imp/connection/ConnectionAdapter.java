package jdbc.imp.connection;

import dbms.backend.BackendController;
import dbms.backend.BackendParser;
import dbms.backend.BackendParserFactory;
import jdbc.imp.statement.StatementAdapter;

import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionAdapter extends DBConnection {
    private StatementAdapter statement;

    public ConnectionAdapter(String url, String databaseDir) {
        BackendController.getInstance().setCurrentDatabaseDir(databaseDir);
        BackendParserFactory.loadParsers();
        BackendParserFactory.getFactory().setCurrentParser(url);
    }

    @Override
    public Statement createStatement() throws SQLException {
        statement = new StatementAdapter(this);
        return statement;
    }

    @Override
    public void close() throws SQLException {
        statement = null;
    }
}
