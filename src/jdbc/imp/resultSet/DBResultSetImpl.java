//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package jdbc.imp.resultSet;

import dbms.datatypes.DBDatatype;
import dbms.datatypes.DBInteger;
import dbms.datatypes.DBString;
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
        this.position = Position.BEFORE_FIRST;
        this.metaData = new DBResultSetMetaDataImpl(this);
        this.statement = statement;
    }

    public boolean absolute(int row) throws SQLException {
        if (row == 0) {
            this.current = null;
            this.position = Position.BEFORE_FIRST;
            return false;
        } else {
            if (row > 0) {
                try {
                    this.current = this.recordSet.getRecords().get(row - 1);
                } catch (IndexOutOfBoundsException var4) {
                    this.current = null;
                    this.position = Position.AFTER_LAST;
                    return false;
                }
            } else if (row < 0) {
                try {
                    this.current = this.recordSet.getRecords().get(this.recordSet.size() - row);
                } catch (IndexOutOfBoundsException var3) {
                    this.current = null;
                    this.position = Position.BEFORE_FIRST;
                    return false;
                }
            }
            this.position = Position.IN;
            return true;
        }
    }

    public void afterLast() throws SQLException {
        this.current = null;
        this.position = Position.AFTER_LAST;
    }

    public void beforeFirst() throws SQLException {
        this.current = null;
        this.position = Position.BEFORE_FIRST;
    }

    public void close() throws SQLException {
        this.recordSet = null;
        this.current = null;
        this.position = Position.BEFORE_FIRST;
    }

    public int findColumn(String columnLabel) throws SQLException {
        for(int i = 1; i <= this.recordSet.size(); ++i) {
            if (this.recordSet.getColumnList().get(i - 1).toLowerCase()
                    .equals(columnLabel)) {
                return i;
            }
        }

        throw new SQLException();
    }

    public boolean first() throws SQLException {
        if(this.recordSet.isEmpty()) {
            return false;
        } else {
            this.current = this.recordSet.getRecords().get(0);
            return true;
        }
    }

    public boolean last() throws SQLException {
        if(this.recordSet.isEmpty()) {
            return false;
        } else {
            this.current = this.recordSet.getRecords().get(this.recordSet.size() - 1);
            return true;
        }
    }

    public int getInt(int columnIndex) throws SQLException {
        if (this.state != State.CLOSED
                && this.columnIsValid(columnIndex)) {
            DBDatatype o = this.current.get(columnIndex - 1);
            if (o.getKey().equals(DBInteger.KEY)) {
                return (Integer) o.getValue();
            }
            return 0;
        }
        throw new SQLException();
    }

    public int getInt(String columnLabel) throws SQLException {
        if (this.state != State.CLOSED
                && this.columnIsValid(columnLabel)) {
            DBDatatype o = this.current.get(columnLabel);
            if (o.getKey().equals(DBInteger.KEY)) {
                return (Integer) o.getValue();
            }
            return 0;
        }
        throw new SQLException();
    }

    public Date getDate(int columnIndex) throws SQLException {
        return super.getDate(columnIndex);
    }

    public Date getDate(String columnLabel) throws SQLException {
        return super.getDate(columnLabel);
    }

    public String getString(int columnIndex) throws SQLException {
        if (this.state != State.CLOSED
                && this.columnIsValid(columnIndex)) {
            DBDatatype o = this.current.get(columnIndex - 1);
            if (o.getKey().equals(DBInteger.KEY)) {
                return (String) o.getValue();
            }
            return null;
        }
        throw new SQLException();
    }

    public String getString(String columnLabel) throws SQLException {
        if (this.state != State.CLOSED
                && this.columnIsValid(columnLabel)) {
            DBDatatype o = this.current.get(columnLabel);
            if (o.getKey().equals(DBString.KEY)) {
                return (String) o.getValue();
            }
            return null;
        }
        throw new SQLException();
    }

    public float getFloat(int columnIndex) throws SQLException {
        if(this.state != State.CLOSED && this.columnIsValid(columnIndex)) {
            DBDatatype o = ((Record)this.recordSet.getRecords().get(columnIndex - 1)).get(columnIndex - 1);
            return o.getKey().equals("Float")?((Float)o.getValue()).floatValue():0.0F;
        } else {
            throw new SQLException();
        }
    }

    public float getFloat(String columnLabel) throws SQLException {
        return this.getFloat(this.findColumn(columnLabel));
    }

    public Object getObject(int columnIndex) throws SQLException {
        if(this.state != State.CLOSED && this.columnIsValid(columnIndex)) {
            DBDatatype o = ((Record)this.recordSet.getRecords().get(columnIndex - 1)).get(columnIndex - 1);
            return o.getValue();
        } else {
            throw new SQLException();
        }
    }

    public Object getObject(String columnLabel) throws SQLException {
        return this.getObject(this.findColumn(columnLabel));
    }

    public ResultSetMetaData getMetaData() throws SQLException {
        return this.metaData;
    }

    public Statement getStatement() throws SQLException {
        return statement;
    }

    public boolean isAfterLast() throws SQLException {
        return this.position == Position.AFTER_LAST;
    }

    public boolean isBeforeFirst() throws SQLException {
        return this.position == Position.BEFORE_FIRST;
    }

    public boolean isClosed() throws SQLException {
        return this.state == State.CLOSED;
    }

    public boolean isFirst() throws SQLException {
        return !this.recordSet.hasPrev();
    }

    public boolean isLast() throws SQLException {
        return !this.recordSet.hasNext();
    }

    public boolean next() throws SQLException {
        if(!this.recordSet.hasNext()) {
            this.position = Position.AFTER_LAST;
            this.current = null;
            return false;
        } else {
            if(this.position == Position.BEFORE_FIRST) {
                this.position = Position.IN;
            }

            this.current = this.recordSet.next();
            return true;
        }
    }

    public boolean previous() throws SQLException {
        if(!this.recordSet.hasPrev()) {
            this.position = Position.BEFORE_FIRST;
            this.current = null;
            return false;
        } else {
            if(this.position == Position.AFTER_LAST) {
                this.position = Position.IN;
            }

            this.current = this.recordSet.prev();
            return true;
        }
    }

    private boolean columnIsValid(int columnIndex) {
        try {
            this.recordSet.getColumnList().get(columnIndex - 1);
            return true;
        } catch (IndexOutOfBoundsException var3) {
            return false;
        }
    }

    private boolean columnIsValid(String columnLabel) {
        try {
            this.findColumn(columnLabel);
            return true;
        } catch (SQLException var4) {
            return false;
        }
    }

    public RecordSet getRecordSet() {
        return this.recordSet;
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
