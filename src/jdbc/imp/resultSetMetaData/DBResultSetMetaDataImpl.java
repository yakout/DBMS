//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package jdbc.imp.resultSetMetaData;


import dbms.datatypes.*;
import jdbc.imp.resultSet.DBResultSetImpl;

import java.sql.SQLException;
import java.sql.Types;

/**
 * Result Set Metadata implamentation.
 */
public class DBResultSetMetaDataImpl extends DBResultSetMetaData {
    /**
     * Reference to re{@link DBResultSetImpl} that has this metadata.
     */
    DBResultSetImpl resultSet = null;

    /**
     * Constructs a new metadata.
     * @param resultSet
     */
    public DBResultSetMetaDataImpl(DBResultSetImpl resultSet) {
        this.resultSet = resultSet;
    }

    @Override
    public int getColumnCount() throws SQLException {
        return this.resultSet.getRecordSet().getColumnList().size();
    }

    @Override
    public String getColumnLabel(int column) throws SQLException {
        return getColumnName(column);
    }

    @Override
    public String getColumnName(int column) throws SQLException {
        return resultSet.getRecordSet().getColumnList().get(column - 1)
                .getKey();
    }

    @Override
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
