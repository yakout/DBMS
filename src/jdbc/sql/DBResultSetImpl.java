package jdbc.sql;

import dbms.datatypes.DBDatatype;
import dbms.datatypes.DBFloat;
import dbms.datatypes.DBInteger;
import dbms.datatypes.DBString;
import dbms.util.Record;
import dbms.util.RecordSet;

import java.sql.Date;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class DBResultSetImpl extends DBResultSet {

    private  enum Position {
        BEFORE_FIRST, IN, AFTER_LAST
    };
    private enum State {
        OPEN, CLOSED
    };
    private RecordSet recordSet = null;
    private Record current = null;
    private State state = null;
    private Position position = null;
    private DBResultSetMetaDataImpl metaData = null;

    public DBResultSetImpl(RecordSet recordSet) {
        this.recordSet = recordSet;
        position = Position.BEFORE_FIRST;
        metaData = new DBResultSetMetaDataImpl(this);
        //TODO: Should have another parameter for statement.
    }

    @Override
    public boolean absolute(int row) throws SQLException {
        if (row == 0) {
            current = null;
            position = Position.BEFORE_FIRST;
            return false;
        } else if (row > 0) {
            try {
                current = recordSet.getRecords().get(row - 1);
            } catch (IndexOutOfBoundsException e) {
                current = null;
                position = Position.AFTER_LAST;
                return false;
            }
        } else if (row < 0) {
            try {
                current = recordSet.getRecords().get(recordSet.size() - row);
            } catch (IndexOutOfBoundsException e) {
                current = null;
                position = Position.BEFORE_FIRST;
                return false;
            }
        }
        position = Position.IN;
        return true;
    }

    @Override
    public void afterLast() throws SQLException {
        current = null;
        position = Position.AFTER_LAST;
    }

    @Override
    public void beforeFirst() throws SQLException {
        current = null;
        position = Position.BEFORE_FIRST;
    }

    @Override
    public void close() throws SQLException {
        recordSet = null;
        current = null;
        position = Position.BEFORE_FIRST;
    }

    @Override
    public int findColumn(String columnLabel) throws SQLException {
        for (int i = 1; i <= recordSet.size(); i++) {
            if (recordSet.getRecords().get(i - 1).equals(columnLabel)) {
                return i;
            }
        }
        throw new SQLException();
    }

    @Override
    public boolean first() throws SQLException {
        if (recordSet.isEmpty()) {
            return false;
        }
        current = recordSet.getRecords().get(0);
        return true;
    }

    @Override
    public boolean last() throws SQLException {
        if (recordSet.isEmpty()) {
            return false;
        }
        current = recordSet.getRecords().get(recordSet.size() - 1);
        return true;
    }

    @Override
    public int getInt(int columnIndex) throws SQLException {
        if (state == State.CLOSED || !columnIsValid(columnIndex)) {
            throw new SQLException();
        }
        DBDatatype o =
                recordSet.getRecords().get(columnIndex - 1).get(columnIndex - 1);
        if (o.getKey().equals(DBInteger.KEY)) {
            return (int) o.getValue();
        }
        return 0;
    }

    @Override
    public int getInt(String columnLabel) throws SQLException {
        return getInt(findColumn(columnLabel));
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
        if (state == State.CLOSED || !columnIsValid(columnIndex)) {
            throw new SQLException();
        }
        DBDatatype o =
                recordSet.getRecords().get(columnIndex - 1).get(columnIndex - 1);
        if (o.getKey().equals(DBString.KEY)) {
            return (String) o.getValue();
        }
        return null;
    }

    @Override
    public String getString(String columnLabel) throws SQLException {
        return getString(findColumn(columnLabel));
    }

    @Override
    public float getFloat(int columnIndex) throws SQLException {
        if (state == State.CLOSED || !columnIsValid(columnIndex)) {
            throw new SQLException();
        }
        DBDatatype o =
                recordSet.getRecords().get(columnIndex - 1).get(columnIndex - 1);
        if (o.getKey().equals(DBFloat.KEY)) {
            return (Float) o.getValue();
        }
        return 0;
    }

    @Override
    public float getFloat(String columnLabel) throws SQLException {
        return getFloat(findColumn(columnLabel));
    }

    @Override
    public Object getObject(int columnIndex) throws SQLException {
        if (state == State.CLOSED || !columnIsValid(columnIndex)) {
            throw new SQLException();
        }
        DBDatatype o =
                recordSet.getRecords().get(columnIndex - 1).get(columnIndex - 1);
        return o.getValue();
    }

    @Override
    public Object getObject(String columnLabel) throws SQLException {
        return getObject(findColumn(columnLabel));
    }

    @Override
    public ResultSetMetaData getMetaData() throws SQLException {
        return metaData;
    }

    @Override
    public Statement getStatement() throws SQLException {
        //TODO
        return null;
    }

    @Override
    public boolean isAfterLast() throws SQLException {
        return position == Position.AFTER_LAST;
    }

    @Override
    public boolean isBeforeFirst() throws SQLException {
        return position == Position.BEFORE_FIRST;
    }

    @Override
    public boolean isClosed() throws SQLException {
        return state == State.CLOSED;
    }

    @Override
    public boolean isFirst() throws SQLException {
        if (!recordSet.hasPrev()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isLast() throws SQLException {
        if (!recordSet.hasNext()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean next() throws SQLException {
        if (!recordSet.hasNext()) {
            position = Position.AFTER_LAST;
            current = null;
            return false;
        }
        if (position == Position.BEFORE_FIRST) {
            position = Position.IN;
        }
        current = recordSet.next();
        return true;
    }

    @Override
    public boolean previous() throws SQLException {
        if (!recordSet.hasPrev()) {
            position = Position.BEFORE_FIRST;
            current = null;
            return false;
        }
        if (position == Position.AFTER_LAST) {
            position = Position.IN;
        }
        current = recordSet.prev();
        return true;
    }

    private boolean columnIsValid(int columnIndex) {
        try {
            recordSet.getColumnList().get(columnIndex - 1);
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
        return true;
    }

    private boolean columnIsValid(String columnLabel) {
        int index;
        try {
            index = findColumn(columnLabel);
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    protected RecordSet getRecordSet() {
        return recordSet;
    }

}
