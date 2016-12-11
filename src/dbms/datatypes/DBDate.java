package dbms.datatypes;

import java.sql.Date;

public class DBDate implements DBDatatype {
    public static final String KEY = "Date";
    private Date value;

    static {
        DatatypeFactory.getFactory().register(KEY, DBDate.class);
    }

    public DBDate() {
    }

    public DBDate(Date value) {
        this.value = value;
    }

    @Override
    public Object toObj(String s) {
        try {
            return Date.valueOf(s);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public int compareTo(DBDatatype data) {
        return value.compareTo((Date) data.getValue());
    }

    @Override
    public Date getValue() {
        return null;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public String getKey() {
        return KEY;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        DBDate dbDate = (DBDate) obj;
        if (value != null ? !value.equals(dbDate.getValue()) : dbDate.getValue() != null) {
            return false;
        }
        return true;
    }
}
