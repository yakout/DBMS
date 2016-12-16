//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package jdbc.imp.resultSetMetaData;


import dbms.datatypes.*;
import jdbc.imp.resultSet.DBResultSetImpl;

import java.sql.SQLException;
import java.sql.Types;

public class DBResultSetMetaDataImpl extends DBResultSetMetaData {
    DBResultSetImpl resultSet = null;

    public DBResultSetMetaDataImpl(DBResultSetImpl resultSet) {
        this.resultSet = resultSet;
    }

    public int getColumnCount() throws SQLException {
        return this.resultSet.getRecordSet().getColumnList().size();
    }

    public String getColumnLabel(int column) throws SQLException {
        return getColumnName(column);
    }

    public String getColumnName(int column) throws SQLException {
        return resultSet.getRecordSet().getColumnList().get(column - 1)
                .getKey();
    }

    public int getColumnType(int column) throws SQLException {
        Class<? extends DBDatatype> datatype = resultSet
                .getRecordSet().getColumnList().get(column - 1)
                .getValue();
        if (datatype == DBInteger.class) {
            return Types.INTEGER;
        } else if (datatype == DBString.class) {
            return Types.VARCHAR;
        } else if (datatype == DBFloat.class) {
            return Types.FLOAT;
        } else if (datatype == DBDate.class) {
            return Types.DATE;
        }
        throw new SQLException();
    }
}
