package dbms.datatypes;

import java.sql.Date;

public class DBDate implements DatatypeDBMS, Comparable<DBDate> {
    private static final String KEY = "Date";
    private Date value;

    static {
        DatatypeFactory.getFactory().register(KEY, DBDate.class);
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
    public int compareTo(DBDate data) {
        return value.compareTo(data.getValue());
    }

    @Override
    public Date getValue() {
        return null;
    }
}
