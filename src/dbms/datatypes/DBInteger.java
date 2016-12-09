package dbms.datatypes;


public class DBInteger implements DBDatatype {
	public static final String KEY = "Integer";
	private Integer value;

	static {
		DatatypeFactory.getFactory().register(KEY, DBInteger.class);
	}

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
}
