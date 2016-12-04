package dbms.datatypes;

import java.sql.Date;

public class DateDBMS implements DatatypeDBMS {
    private static final String KEY = "Date";
    private Date value;

    static {
        DatatypeFactory.getFactory().register(KEY, DateDBMS.class);
    }

    @Override
    public Object toObj(String s) {
        try {
            return Date.valueOf(s);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    @Override
    public int compareTo(DatatypeDBMS data) {
        value.compareTo((Date) data.getValue());
        return 0;
    }

    @Override
    public Object getValue() {
        return null;
    }

    public void setValue(Date value) {
        this.value = value;
    }
}
