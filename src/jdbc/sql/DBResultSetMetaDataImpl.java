package jdbc.sql;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DBResultSetMetaDataImpl extends DBResultSetMetaData {
    DBResultSetImpl resultSet = null;

    public DBResultSetMetaDataImpl(DBResultSetImpl resultSet) {
        this.resultSet = resultSet;
    }

    @Override
    public int getColumnCount() throws SQLException {
        return resultSet.getRecordSet().getColumnList().size();
    }

    @Override
    public String getColumnLabel(int column) throws SQLException {
        //TODO: Have no idea what this is.
        return null;
    }

    @Override
    public String getColumnName(int column) throws SQLException {
        return resultSet.getRecordSet().getColumnList().get(column);
    }

    @Override
    public int getColumnType(int column) throws SQLException {
        //TODO: Make column list keep types for easier access.
        return 0;
    }
}
