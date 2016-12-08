package jdbc.sql;

import dbms.util.Record;
import dbms.util.RecordSet;

import java.sql.Date;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DBResultSetImpl extends DBResultSet {

    private List<Record> recordList = null;
    private List<String> columnList = null;
    private Record current = null;
    private int positionBound;

    public DBResultSetImpl(RecordSet recordSet) {
        recordList = recordSet.getRecords();
        columnList = recordSet.getColumnList();
        positionBound = -1;
    }

    @Override
    public boolean absolute(int row) throws SQLException {
        if (row == 0) {
            current = null;
            positionBound = -1;
            return false;
        } else if (row > 0) {
            try {
                current = recordList.get(row - 1);
            } catch (IndexOutOfBoundsException e) {
                current = null;
                positionBound = 1;
                return false;
            }
        } else if (row < 0) {
            try {
                current = recordList.get(recordList.size() - row);
            } catch (IndexOutOfBoundsException e) {
                current = null;
                positionBound = -1;
                return false;
            }
        }
        positionBound = 0;
        return true;
    }

    @Override
    public void afterLast() throws SQLException {
        current = null;
        positionBound = 1;
    }

    @Override
    public void beforeFirst() throws SQLException {
        current = null;
        positionBound = -1;
    }

    @Override
    public void close() throws SQLException {
        recordList = null;
        current = null;
        positionBound = -1;
    }

    @Override
    public int findColumn(String columnLabel) throws SQLException {
        for (int i = 1; i <= columnList.size(); i++) {
            if (columnList.get(i).equals(columnLabel)) {
                return i;
            }
        }
        throw new SQLException();
    }

    @Override
    public boolean first() throws SQLException {
        return super.first();
    }

    @Override
    public int getInt(int columnIndex) throws SQLException {
        return super.getInt(columnIndex);
    }

    @Override
    public int getInt(String columnLabel) throws SQLException {
        return super.getInt(columnLabel);
    }

    @Override
    public Date getDate(int columnIndex) throws SQLException {
        return super.getDate(columnIndex);
    }

    @Override
    public Date getDate(String columnLabel) throws SQLException {
        return super.getDate(columnLabel);
    }

    @Override
    public String getString(int columnIndex) throws SQLException {
        return super.getString(columnIndex);
    }

    @Override
    public String getString(String columnLabel) throws SQLException {
        return super.getString(columnLabel);
    }

    @Override
    public float getFloat(int columnIndex) throws SQLException {
        return super.getFloat(columnIndex);
    }

    @Override
    public float getFloat(String columnLabel) throws SQLException {
        return super.getFloat(columnLabel);
    }

    @Override
    public Object getObject(int columnIndex) throws SQLException {
        return super.getObject(columnIndex);
    }

    @Override
    public Object getObject(String columnLabel) throws SQLException {
        return super.getObject(columnLabel);
    }

    @Override
    public ResultSetMetaData getMetaData() throws SQLException {
        return super.getMetaData();
    }

    @Override
    public Statement getStatement() throws SQLException {
        return super.getStatement();
    }

    @Override
    public boolean isAfterLast() throws SQLException {
        return super.isAfterLast();
    }

    @Override
    public boolean isBeforeFirst() throws SQLException {
        return super.isBeforeFirst();
    }

    @Override
    public boolean isClosed() throws SQLException {
        return super.isClosed();
    }

    @Override
    public boolean isFirst() throws SQLException {
        return super.isFirst();
    }

    @Override
    public boolean isLast() throws SQLException {
        return super.isLast();
    }

    @Override
    public boolean last() throws SQLException {
        return super.last();
    }

    @Override
    public boolean next() throws SQLException {
        return false;
    }

    @Override
    public boolean previous() throws SQLException {
        return super.previous();
    }
}
