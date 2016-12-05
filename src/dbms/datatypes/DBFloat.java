package dbms.datatypes;

public class DBFloat implements DatatypeDBMS, Comparable<DBFloat> {
    private static final String KEY = "Float";
    private Float value;

    static  {
        DatatypeFactory.getFactory().register(KEY, DBFloat.class);
    }

    public DBFloat(Float value) {
        this.value = value;
    }

    @Override
    public Object toObj(String s) {
        try {
            return Float.parseFloat(s);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public int compareTo(DBFloat data) {
        return value.compareTo(data.getValue());
    }

    @Override
    public Float getValue() {
        return value;
    }
}
