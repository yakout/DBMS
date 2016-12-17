package jdbc.imp.resultSetMetaData;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class DBResultSetMetaData implements ResultSetMetaData {
    @Override
    public int getColumnCount() throws SQLException {
        return 0;
    }

    @Override
    public final boolean isAutoIncrement(int column) throws SQLException {
        return false;
    }

    @Override
    public final boolean isCaseSensitive(int column) throws SQLException {
        return false;
    }

    @Override
    public final boolean isSearchable(int column) throws SQLException {
        return false;
    }

    @Override
    public final boolean isCurrency(int column) throws SQLException {
        return false;
    }

    @Override
    public final int isNullable(int column) throws SQLException {
        return 0;
    }

    @Override
    public final boolean isSigned(int column) throws SQLException {
        return false;
    }

    @Override
    public final int getColumnDisplaySize(int column) throws SQLException {
        return 0;
    }

    @Override
    public String getColumnLabel(int column) throws SQLException {
        return null;
    }

    @Override
    public String getColumnName(int column) throws SQLException {
        return null;
    }

    @Override
    public final String getSchemaName(int column) throws SQLException {
        return null;
    }

    @Override
    public final int getPrecision(int column) throws SQLException {
        return 0;
    }

    @Override
    public final int getScale(int column) throws SQLException {
        return 0;
    }

    @Override
    public final String getTableName(int column) throws SQLException {
        return null;
    }

    @Override
    public final String getCatalogName(int column) throws SQLException {
        return null;
    }

    @Override
    public int getColumnType(int column) throws SQLException {
        return 0;
    }

    @Override
    public final String getColumnTypeName(int column) throws SQLException {
        return null;
    }

    @Override
    public final boolean isReadOnly(int column) throws SQLException {
        return false;
    }

    @Override
    public final boolean isWritable(int column) throws SQLException {
        return false;
    }

    @Override
    public final boolean isDefinitelyWritable(int column) throws SQLException {
        return false;
    }

    @Override
    public final String getColumnClassName(int column) throws SQLException {
        return null;
    }

    @Override
    public final <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public final boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }
}
