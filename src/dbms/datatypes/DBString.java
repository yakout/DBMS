package dbms.datatypes;

public class DBString implements DBDatatype {

    /**
     * Key identifier to DBString.
     */
    public static final String KEY = "String";

    static {
        DatatypeFactory.getFactory().register(KEY, DBString.class);
    }

    private String value = null;

    public DBString() {
    }

    public DBString(String value) {
        this.value = value;
    }

    @Override
    public Object toObj(String s) {
        return s;
    }

    @Override
    public int compareTo(DBDatatype data) {
        return value.compareTo((String) data.getValue());
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override

    public String getKey() {
        return KEY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DBString dbString = (DBString) o;
        return value != null ? value.equals(dbString.value) :
        	dbString.value == null;
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }
}
