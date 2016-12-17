package jdbc.imp.resultSet;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Map;


public class DBResultSet implements ResultSet {

    @Override
    public boolean next() throws SQLException {
        return false;
    }

    @Override
    public void close() throws SQLException {

    }

    @Override
    public final boolean wasNull() throws SQLException {
        return false;
    }

    @Override
    public String getString(final int columnIndex) throws SQLException {
        return null;
    }

    @Override
    public final boolean getBoolean(final int columnIndex) throws SQLException {
        return false;
    }

    @Override
    public final byte getByte(final int columnIndex) throws SQLException {
        return 0;
    }

    @Override
    public final short getShort(final int columnIndex) throws SQLException {
        return 0;
    }

    @Override
    public int getInt(final int columnIndex) throws SQLException {
        return 0;
    }

    @Override
    public final long getLong(final int columnIndex) throws SQLException {
        return 0;
    }

    @Override
    public float getFloat(final int columnIndex) throws SQLException {
        return 0;
    }

    @Override
    public final double getDouble(final int columnIndex) throws SQLException {
        return 0;
    }

    @Override
    public final BigDecimal getBigDecimal(final int columnIndex, final int
            scale)
            throws SQLException {
        return null;
    }

    @Override
    public final byte[] getBytes(final int columnIndex) throws SQLException {
        return new byte[0];
    }

    @Override
    public Date getDate(final int columnIndex) throws SQLException {
        return null;
    }

    @Override
    public final Time getTime(final int columnIndex) throws SQLException {
        return null;
    }

    @Override
    public final Timestamp getTimestamp(final int columnIndex) throws
            SQLException {
        return null;
    }

    @Override
    public final InputStream getAsciiStream(final int columnIndex) throws
            SQLException {
        return null;
    }

    @Override
    public final InputStream getUnicodeStream(final int columnIndex) throws
            SQLException {
        return null;
    }

    @Override
    public final InputStream getBinaryStream(final int columnIndex) throws
            SQLException {
        return null;
    }

    @Override
    public String getString(final String columnLabel) throws SQLException {
        return null;
    }

    @Override
    public final boolean getBoolean(final String columnLabel) throws
            SQLException {
        return false;
    }

    @Override
    public final byte getByte(final String columnLabel) throws SQLException {
        return 0;
    }

    @Override
    public final short getShort(final String columnLabel) throws SQLException {
        return 0;
    }

    @Override
    public int getInt(final String columnLabel) throws SQLException {
        return 0;
    }

    @Override
    public final long getLong(final String columnLabel) throws SQLException {
        return 0;
    }

    @Override
    public float getFloat(final String columnLabel) throws SQLException {
        return 0;
    }

    @Override
    public final double getDouble(final String columnLabel) throws
            SQLException {
        return 0;
    }

    @Override
    public final BigDecimal getBigDecimal(final String columnLabel, final int
            scale) throws SQLException {
        return null;
    }

    @Override
    public final byte[] getBytes(final String columnLabel) throws SQLException {
        return new byte[0];
    }

    @Override
    public Date getDate(final String columnLabel) throws SQLException {
        return null;
    }

    @Override
    public final Time getTime(final String columnLabel) throws SQLException {
        return null;
    }

    @Override
    public final Timestamp getTimestamp(final String columnLabel) throws
            SQLException {
        return null;
    }

    @Override
    public final InputStream getAsciiStream(final String columnLabel) throws
            SQLException {
        return null;
    }

    @Override
    public final InputStream getUnicodeStream(final String columnLabel) throws
            SQLException {
        return null;
    }

    @Override
    public final InputStream getBinaryStream(final String columnLabel) throws
            SQLException {
        return null;
    }

    @Override
    public final SQLWarning getWarnings() throws SQLException {
        return null;
    }

    @Override
    public final void clearWarnings() throws SQLException {

    }

    @Override
    public final String getCursorName() throws SQLException {
        return null;
    }

    @Override
    public ResultSetMetaData getMetaData() throws SQLException {
        return null;
    }

    @Override
    public Object getObject(final int columnIndex) throws SQLException {
        return null;
    }

    @Override
    public Object getObject(final String columnLabel) throws SQLException {
        return null;
    }

    @Override
    public int findColumn(final String columnLabel) throws SQLException {
        return 0;
    }

    @Override
    public final Reader getCharacterStream(final int columnIndex) throws
            SQLException {
        return null;
    }

    @Override
    public final Reader getCharacterStream(final String columnLabel) throws
            SQLException {
        return null;
    }

    @Override
    public final BigDecimal getBigDecimal(final int columnIndex) throws
            SQLException {
        return null;
    }

    @Override
    public final BigDecimal getBigDecimal(final String columnLabel) throws
            SQLException {
        return null;
    }

    @Override
    public boolean isBeforeFirst() throws SQLException {
        return false;
    }

    @Override
    public boolean isAfterLast() throws SQLException {
        return false;
    }

    @Override
    public boolean isFirst() throws SQLException {
        return false;
    }

    @Override
    public boolean isLast() throws SQLException {
        return false;
    }

    @Override
    public void beforeFirst() throws SQLException {

    }

    @Override
    public void afterLast() throws SQLException {

    }

    @Override
    public boolean first() throws SQLException {
        return false;
    }

    @Override
    public boolean last() throws SQLException {
        return false;
    }

    @Override
    public final int getRow() throws SQLException {
        return 0;
    }

    @Override
    public boolean absolute(final int row) throws SQLException {
        return false;
    }

    @Override
    public final boolean relative(final int rows) throws SQLException {
        return false;
    }

    @Override
    public boolean previous() throws SQLException {
        return false;
    }

    @Override
    public final void setFetchDirection(final int direction) throws
            SQLException {

    }

    @Override
    public final int getFetchDirection() throws SQLException {
        return 0;
    }

    @Override
    public final void setFetchSize(final int rows) throws SQLException {

    }

    @Override
    public final int getFetchSize() throws SQLException {
        return 0;
    }

    @Override
    public final int getType() throws SQLException {
        return 0;
    }

    @Override
    public final int getConcurrency() throws SQLException {
        return 0;
    }

    @Override
    public final boolean rowUpdated() throws SQLException {
        return false;
    }

    @Override
    public final boolean rowInserted() throws SQLException {
        return false;
    }

    @Override
    public final boolean rowDeleted() throws SQLException {
        return false;
    }

    @Override
    public final void updateNull(final int columnIndex) throws SQLException {

    }

    @Override
    public final void updateBoolean(final int columnIndex, final boolean x)
            throws
            SQLException {

    }

    @Override
    public final void updateByte(final int columnIndex, final byte x) throws
            SQLException {

    }

    @Override
    public final void updateShort(final int columnIndex, final short x) throws
            SQLException {

    }

    @Override
    public final void updateInt(final int columnIndex, final int x) throws
            SQLException {

    }

    @Override
    public final void updateLong(final int columnIndex, final long x) throws
            SQLException {

    }

    @Override
    public final void updateFloat(final int columnIndex, final float x) throws
            SQLException {

    }

    @Override
    public final void updateDouble(final int columnIndex, final double x) throws
            SQLException {

    }

    @Override
    public final void updateBigDecimal(final int columnIndex, final
    BigDecimal x)
            throws SQLException {

    }

    @Override
    public final void updateString(final int columnIndex, final String x) throws
            SQLException {

    }

    @Override
    public final void updateBytes(final int columnIndex, final byte[] x) throws
            SQLException {

    }

    @Override
    public final void updateDate(final int columnIndex, final Date x) throws
            SQLException {

    }

    @Override
    public final void updateTime(final int columnIndex, final Time x) throws
            SQLException {

    }

    @Override
    public final void updateTimestamp(final int columnIndex, final Timestamp x)
            throws SQLException {

    }

    @Override
    public final void updateAsciiStream(final int columnIndex, final
    InputStream x,
                                        final int length) throws SQLException {

    }

    @Override
    public final void updateBinaryStream(final int columnIndex, final
    InputStream
            x, final int length) throws SQLException {

    }

    @Override
    public final void updateCharacterStream(final int columnIndex, final
    Reader x,
                                            final int length) throws
            SQLException {

    }

    @Override
    public final void updateObject(final int columnIndex, final Object x,
                                   final int
            scaleOrLength) throws SQLException {

    }

    @Override
    public final void updateObject(final int columnIndex, final Object x) throws
            SQLException {

    }

    @Override
    public final void updateNull(final String columnLabel) throws SQLException {

    }

    @Override
    public final void updateBoolean(final String columnLabel, final boolean x)
            throws SQLException {

    }

    @Override
    public final void updateByte(final String columnLabel, final byte x) throws
            SQLException {

    }

    @Override
    public final void updateShort(final String columnLabel, final short x)
            throws
            SQLException {

    }

    @Override
    public final void updateInt(final String columnLabel, final int x) throws
            SQLException {

    }

    @Override
    public final void updateLong(final String columnLabel, final long x) throws
            SQLException {

    }

    @Override
    public final void updateFloat(final String columnLabel, final float x)
            throws
            SQLException {

    }

    @Override
    public final void updateDouble(final String columnLabel, final double x)
            throws
            SQLException {

    }

    @Override
    public final void updateBigDecimal(final String columnLabel, final
    BigDecimal
            x) throws SQLException {

    }

    @Override
    public final void updateString(final String columnLabel, final String x)
            throws
            SQLException {

    }

    @Override
    public final void updateBytes(final String columnLabel, final byte[] x)
            throws
            SQLException {

    }

    @Override
    public final void updateDate(final String columnLabel, final Date x) throws
            SQLException {

    }

    @Override
    public final void updateTime(final String columnLabel, final Time x) throws
            SQLException {

    }

    @Override
    public final void updateTimestamp(final String columnLabel, final
    Timestamp x)
            throws SQLException {

    }

    @Override
    public final void updateAsciiStream(final String columnLabel, final
    InputStream
            x, final int length) throws SQLException {

    }

    @Override
    public final void updateBinaryStream(final String columnLabel, final
    InputStream x, final int length) throws SQLException {

    }

    @Override
    public final void updateCharacterStream(final String columnLabel, final
    Reader
            reader, final int length) throws SQLException {

    }

    @Override
    public final void updateObject(final String columnLabel, final Object x,
                                   final
    int scaleOrLength) throws SQLException {

    }

    @Override
    public final void updateObject(final String columnLabel, final Object x)
            throws
            SQLException {

    }

    @Override
    public final void insertRow() throws SQLException {

    }

    @Override
    public final void updateRow() throws SQLException {

    }

    @Override
    public final void deleteRow() throws SQLException {

    }

    @Override
    public final void refreshRow() throws SQLException {

    }

    @Override
    public final void cancelRowUpdates() throws SQLException {

    }

    @Override
    public final void moveToInsertRow() throws SQLException {

    }

    @Override
    public final void moveToCurrentRow() throws SQLException {

    }

    @Override
    public Statement getStatement() throws SQLException {
        return null;
    }

    @Override
    public final Object getObject(final int columnIndex, final Map<String, Class
            <?>> map) throws SQLException {
        return null;
    }

    @Override
    public final Ref getRef(final int columnIndex) throws SQLException {
        return null;
    }

    @Override
    public final Blob getBlob(final int columnIndex) throws SQLException {
        return null;
    }

    @Override
    public final Clob getClob(final int columnIndex) throws SQLException {
        return null;
    }

    @Override
    public final Array getArray(final int columnIndex) throws SQLException {
        return null;
    }

    @Override
    public final Object getObject(final String columnLabel, final Map<String,
            Class
            <?>> map) throws SQLException {
        return null;
    }

    @Override
    public final Ref getRef(final String columnLabel) throws SQLException {
        return null;
    }

    @Override
    public final Blob getBlob(final String columnLabel) throws SQLException {
        return null;
    }

    @Override
    public final Clob getClob(final String columnLabel) throws SQLException {
        return null;
    }

    @Override
    public final Array getArray(final String columnLabel) throws SQLException {
        return null;
    }

    @Override
    public final Date getDate(final int columnIndex, final Calendar cal) throws
            SQLException {
        return null;
    }

    @Override
    public final Date getDate(final String columnLabel, final Calendar cal)
            throws
            SQLException {
        return null;
    }

    @Override
    public final Time getTime(final int columnIndex, final Calendar cal) throws
            SQLException {
        return null;
    }

    @Override
    public final Time getTime(final String columnLabel, final Calendar cal)
            throws
            SQLException {
        return null;
    }

    @Override
    public final Timestamp getTimestamp(final int columnIndex, final Calendar
            cal)
            throws SQLException {
        return null;
    }

    @Override
    public final Timestamp getTimestamp(final String columnLabel, final Calendar
            cal) throws SQLException {
        return null;
    }

    @Override
    public final URL getURL(final int columnIndex) throws SQLException {
        return null;
    }

    @Override
    public final URL getURL(final String columnLabel) throws SQLException {
        return null;
    }

    @Override
    public final void updateRef(final int columnIndex, final Ref x) throws
            SQLException {

    }

    @Override
    public final void updateRef(final String columnLabel, final Ref x) throws
            SQLException {

    }

    @Override
    public final void updateBlob(final int columnIndex, final Blob x) throws
            SQLException {

    }

    @Override
    public final void updateBlob(final String columnLabel, final Blob x) throws
            SQLException {

    }

    @Override
    public final void updateClob(final int columnIndex, final Clob x) throws
            SQLException {

    }

    @Override
    public final void updateClob(final String columnLabel, final Clob x) throws
            SQLException {

    }

    @Override
    public final void updateArray(final int columnIndex, final Array x) throws
            SQLException {

    }

    @Override
    public final void updateArray(final String columnLabel, final Array x)
            throws
            SQLException {

    }

    @Override
    public final RowId getRowId(final int columnIndex) throws SQLException {
        return null;
    }

    @Override
    public final RowId getRowId(final String columnLabel) throws SQLException {
        return null;
    }

    @Override
    public final void updateRowId(final int columnIndex, final RowId x) throws
            SQLException {

    }

    @Override
    public final void updateRowId(final String columnLabel, final RowId x)
            throws
            SQLException {

    }

    @Override
    public final int getHoldability() throws SQLException {
        return 0;
    }

    @Override
    public boolean isClosed() throws SQLException {
        return false;
    }

    @Override
    public final void updateNString(final int columnIndex, final String nString)
            throws SQLException {

    }

    @Override
    public final void updateNString(final String columnLabel, final String
            nString)
            throws SQLException {

    }

    @Override
    public final void updateNClob(final int columnIndex, final NClob nClob)
            throws
            SQLException {

    }

    @Override
    public final void updateNClob(final String columnLabel, final NClob nClob)
            throws SQLException {

    }

    @Override
    public final NClob getNClob(final int columnIndex) throws SQLException {
        return null;
    }

    @Override
    public final NClob getNClob(final String columnLabel) throws SQLException {
        return null;
    }

    @Override
    public final SQLXML getSQLXML(final int columnIndex) throws SQLException {
        return null;
    }

    @Override
    public final SQLXML getSQLXML(final String columnLabel) throws
            SQLException {
        return null;
    }

    @Override
    public final void updateSQLXML(final int columnIndex, final SQLXML
            xmlObject)
            throws SQLException {

    }

    @Override
    public final void updateSQLXML(final String columnLabel, final SQLXML
            xmlObject) throws SQLException {

    }

    @Override
    public final String getNString(final int columnIndex) throws SQLException {
        return null;
    }

    @Override
    public final String getNString(final String columnLabel) throws
            SQLException {
        return null;
    }

    @Override
    public final Reader getNCharacterStream(final int columnIndex) throws
            SQLException {
        return null;
    }

    @Override
    public final Reader getNCharacterStream(final String columnLabel) throws
            SQLException {
        return null;
    }

    @Override
    public final void updateNCharacterStream(final int columnIndex, final
    Reader x,
                                             final long length) throws
            SQLException {

    }

    @Override
    public final void updateNCharacterStream(final String columnLabel, final
    Reader
            reader, final long length) throws SQLException {

    }

    @Override
    public final void updateAsciiStream(final int columnIndex, final
    InputStream x,
                                        final long length) throws SQLException {

    }

    @Override
    public final void updateBinaryStream(final int columnIndex, final
    InputStream
            x, final long length) throws SQLException {

    }

    @Override
    public final void updateCharacterStream(final int columnIndex, final
    Reader x,
                                            final long length) throws
            SQLException {

    }

    @Override
    public final void updateAsciiStream(final String columnLabel, final
    InputStream
            x, final long length) throws SQLException {

    }

    @Override
    public final void updateBinaryStream(final String columnLabel, final
    InputStream x, final long length) throws SQLException {

    }

    @Override
    public final void updateCharacterStream(final String columnLabel, final
    Reader
            reader, final long length) throws SQLException {

    }

    @Override
    public final void updateBlob(final int columnIndex, final InputStream
            inputStream, final long length) throws SQLException {

    }

    @Override
    public final void updateBlob(final String columnLabel, final InputStream
            inputStream, final long length) throws SQLException {

    }

    @Override
    public final void updateClob(final int columnIndex, final Reader reader,
                                 final
    long length) throws SQLException {

    }

    @Override
    public final void updateClob(final String columnLabel, final Reader reader,
                                 final long length) throws SQLException {

    }

    @Override
    public final void updateNClob(final int columnIndex, final Reader reader,
                                  final
    long length) throws SQLException {

    }

    @Override
    public final void updateNClob(final String columnLabel, final Reader reader,
                                  final long length) throws SQLException {

    }

    @Override
    public final void updateNCharacterStream(final int columnIndex, final
    Reader x)
            throws SQLException {

    }

    @Override
    public final void updateNCharacterStream(final String columnLabel, final
    Reader
            reader) throws SQLException {

    }

    @Override
    public final void updateAsciiStream(final int columnIndex, final
    InputStream x)
            throws SQLException {

    }

    @Override
    public final void updateBinaryStream(final int columnIndex, final
    InputStream
            x) throws SQLException {

    }

    @Override
    public final void updateCharacterStream(final int columnIndex, final
    Reader x)
            throws SQLException {

    }

    @Override
    public final void updateAsciiStream(final String columnLabel, final
    InputStream
            x) throws SQLException {

    }

    @Override
    public final void updateBinaryStream(final String columnLabel, final
    InputStream x) throws SQLException {

    }

    @Override
    public final void updateCharacterStream(final String columnLabel, final
    Reader
            reader) throws SQLException {

    }

    @Override
    public final void updateBlob(final int columnIndex, final InputStream
            inputStream) throws SQLException {

    }

    @Override
    public final void updateBlob(final String columnLabel, final InputStream
            inputStream) throws SQLException {

    }

    @Override
    public final void updateClob(final int columnIndex, final Reader reader)
            throws
            SQLException {

    }

    @Override
    public final void updateClob(final String columnLabel, final Reader reader)
            throws SQLException {

    }

    @Override
    public final void updateNClob(final int columnIndex, final Reader reader)
            throws SQLException {

    }

    @Override
    public final void updateNClob(final String columnLabel, final Reader reader)
            throws SQLException {

    }

    @Override
    public final <T> T getObject(final int columnIndex, final Class<T> type)
            throws
            SQLException {
        return null;
    }

    @Override
    public final <T> T getObject(final String columnLabel, final Class<T> type)
            throws SQLException {
        return null;
    }

    @Override
    public final <T> T unwrap(final Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public final boolean isWrapperFor(final Class<?> iface) throws SQLException {
        return false;
    }
}
