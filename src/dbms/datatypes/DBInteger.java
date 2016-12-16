package dbms.datatypes;


public class DBInteger implements DBDatatype {
    public static final String KEY = "Integer";

    static {
        DatatypeFactory.getFactory().register(KEY, DBInteger.class);
    }

    private Integer value;

    public DBInteger() {
    }

    public DBInteger(Integer value) {
        this.value = value;
    }

    @Override
    public Object toObj(String s) {
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public int compareTo(DBDatatype data) {
        return value.compareTo((Integer) data.getValue());
    }

    @Override
    public Integer getValue() {
        return value;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DBInteger dbInteger = (DBInteger) o;

        return value != null ? value.equals(dbInteger.value) : dbInteger.value == null;
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }
}
