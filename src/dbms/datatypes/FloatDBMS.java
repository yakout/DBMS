package dbms.datatypes;

public class FloatDBMS implements DatatypeDBMS {
    private static final String KEY = "Float";
    private Float value;

    static  {
        DatatypeFactory.getFactory().register(KEY, FloatDBMS.class);
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
    public int compareTo(DatatypeDBMS data) {
        return value.compareTo((Float) data.getValue());
    }

    @Override
    public Object getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }
}
