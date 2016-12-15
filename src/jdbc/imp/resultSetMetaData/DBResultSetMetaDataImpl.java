//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package jdbc.imp.resultSetMetaData;


import jdbc.imp.resultSet.DBResultSetImpl;

import java.sql.SQLException;

public class DBResultSetMetaDataImpl extends DBResultSetMetaData {
    DBResultSetImpl resultSet = null;

    public DBResultSetMetaDataImpl(DBResultSetImpl resultSet) {
        this.resultSet = resultSet;
    }

    public int getColumnCount() throws SQLException {
        return resultSet.getRecordSet().getColumnList().size();
    }

    public String getColumnLabel(int column) throws SQLException {
        return null;
    }

    public String getColumnName(int column) throws SQLException {
        return resultSet.getRecordSet().getColumnList().get(column);
    }

    public int getColumnType(int column) throws SQLException {
        return 0;
    }
}
