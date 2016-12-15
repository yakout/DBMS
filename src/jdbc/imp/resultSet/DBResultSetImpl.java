//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package jdbc.imp.resultSet;

import dbms.datatypes.*;
import dbms.util.Record;
import dbms.util.RecordSet;
import jdbc.imp.resultSetMetaData.DBResultSetMetaDataImpl;

import java.sql.Date;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class DBResultSetImpl extends DBResultSet {
    private RecordSet recordSet = null;
    private Record current = null;
    private State state = null;
    private Position position = null;
    private DBResultSetMetaDataImpl metaData = null;
    private Statement statement = null;

    public DBResultSetImpl(Statement statement, RecordSet recordSet) {
        this.recordSet = recordSet;
        position = Position.BEFORE_FIRST;
        metaData = new DBResultSetMetaDataImpl(this);
        this.statement = statement;
    }

    @Override
    public boolean absolute(int row) throws SQLException {
        if (row == 0) {
            current = null;
            position = Position.BEFORE_FIRST;
            return false;
        } else {
            if (row > 0) {
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
    }

    @Override
    public void afterLast() throws SQLException {
        while (recordSet.hasNext()) {
            recordSet.next();
        }
        current = null;
        position = Position.AFTER_LAST;
    }

    @Override
    public void beforeFirst() throws SQLException {
        recordSet.reset();
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
        for(int i = 0; i < recordSet.getColumnList().size(); i++) {
            if (recordSet.getColumnList().get(i).toLowerCase()
                    .equals(columnLabel.toLowerCase())) {
                return i + 1;
            }
        }
        throw new SQLException("Can't find column!");
    }

    @Override
    public boolean first() throws SQLException {
        if(recordSet.isEmpty()) {
            return false;
        } else {
            while (recordSet.hasPrev()) {
                current = recordSet.prev();
            }
            position = Position.IN;
            return true;
        }
    }

    @Override
    public boolean last() throws SQLException {
        if(recordSet.isEmpty()) {
            return false;
        } else {
            while (recordSet.hasNext()) {
                current = recordSet.next();
            }
            position = Position.IN;
            return true;
        }
    }

    @Override
    public int getInt(int columnIndex) throws SQLException {
        if (state != State.CLOSED
                && columnIsValid(columnIndex)) {
            DBDatatype o = current.get(columnIndex - 1);
            if (o == null) {
                return 0;
            } else if (o.getKey().equals(DBInteger.KEY)) {
                return (Integer) o.getValue();
            }
        }
        throw new SQLException();
    }

    @Override
    public int getInt(String columnLabel) throws SQLException {
        if (state != State.CLOSED
                && columnIsValid(columnLabel)) {
            DBDatatype o = current.get(columnLabel);
            if (o == null) {
                return 0;
            } else if (o.getKey().equals(DBInteger.KEY)) {
                return (Integer) o.getValue();
            }
        }
        throw new SQLException();
    }

    @Override
    public Date getDate(int columnIndex) throws SQLException {
        if (state != State.CLOSED
                && columnIsValid(columnIndex)) {
            DBDatatype o = current.get(columnIndex - 1);
            if (o == null) {
                return null;
            } else if (o.getKey().equals(DBDate.KEY)) {
                return (Date) o.getValue();
            }
        }
        throw new SQLException();
    }

    @Override
    public Date getDate(String columnLabel) throws SQLException {
        if (state != State.CLOSED
                && columnIsValid(columnLabel)) {
            DBDatatype o = current.get(columnLabel);
            if (o == null) {
                return null;
            } else if (o.getKey().equals(DBDate.KEY)) {
                return (Date) o.getValue();
            }
        }
        throw new SQLException();
    }

    @Override
    public String getString(int columnIndex) throws SQLException {
        if (state != State.CLOSED
                && columnIsValid(columnIndex)) {
            DBDatatype o = current.get(columnIndex - 1);
            if (o == null) {
                return null;
            } else if (o.getKey().equals(DBString.KEY)) {
                return (String) o.getValue();
            }
        }
        throw new SQLException();
    }


    @Override
    public String getString(String columnLabel) throws SQLException {
        if (state != State.CLOSED
                && columnIsValid(columnLabel)) {
            DBDatatype o = current.get(columnLabel);
            if (o == null) {
                return null;
            } else if (o.getKey().equals(DBString.KEY)) {
                return (String) o.getValue();
            }
        }
        throw new SQLException();
    }

    @Override
    public float getFloat(int columnIndex) throws SQLException {
        if (state != State.CLOSED
                && columnIsValid(columnIndex)) {
            DBDatatype o = current.get(columnIndex - 1);
            if (o == null) {
                return 0;
            } else if (o.getKey().equals(DBFloat.KEY)) {
                return (Float) o.getValue();
            }
        }
        throw new SQLException();
    }

    @Override
    public float getFloat(String columnLabel) throws SQLException {
        if (state != State.CLOSED
                && columnIsValid(columnLabel)) {
            DBDatatype o = current.get(columnLabel);
            if (o == null) {
                return 0;
            } else if (o.getKey().equals(DBFloat.KEY)) {
                return (Float) o.getValue();
            }
        }
        throw new SQLException();
    }

    @Override
    public Object getObject(int columnIndex) throws SQLException {
        if (state != State.CLOSED
                && columnIsValid(columnIndex)) {
            DBDatatype o = current.get(columnIndex - 1);
            if (o == null) {
                return null;
            }
            return o.getValue();
        }
        throw new SQLException();
    }

    @Override
    public Object getObject(String columnLabel) throws SQLException {
        if (state != State.CLOSED
                && columnIsValid(columnLabel)) {
            DBDatatype o = current.get(columnLabel);
            if (o == null) {
                return null;
            }
            return o;
        }
        throw new SQLException();
    }

    @Override
    public ResultSetMetaData getMetaData() throws SQLException {
        return metaData;
    }

    @Override
    public Statement getStatement() throws SQLException {
        return statement;
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
        return !recordSet.hasPrev() && current != null;
    }

    @Override
    public boolean isLast() throws SQLException {
        return !recordSet.hasNext() && current != null;
    }

    @Override
    public boolean next() throws SQLException {
        current = recordSet.next();
        if (current == null) {
            position = Position.AFTER_LAST;
            return false;
        }
        position = Position.IN;
        return true;
    }

    @Override
    public boolean previous() throws SQLException {
        current = recordSet.prev();
        if (current == null) {
            position = Position.BEFORE_FIRST;
            return false;
        }
        position = Position.IN;
        return true;
    }

    private boolean columnIsValid(int columnIndex) {
        try {
            recordSet.getColumnList().get(columnIndex - 1);
            return true;
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    private boolean columnIsValid(String columnLabel) {
        try {
            findColumn(columnLabel);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public RecordSet getRecordSet() {
        return recordSet;
    }

    private enum State {
        OPEN,
        CLOSED;
    }

    private enum Position {
        BEFORE_FIRST,
        IN,
        AFTER_LAST;
    }
}
