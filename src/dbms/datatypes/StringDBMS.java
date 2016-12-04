package dbms.datatypes;

public class StringDBMS implements DatatypeDBMS {
	private static final String KEY = "String";
	private String value;

	static {
		DatatypeFactory.getFactory().register(KEY, StringDBMS.class);
	}

	@Override
	public Object toObj(String s) {
		return s;
	}

	@Override
	public int compareTo(DatatypeDBMS data) {
		return value.compareTo((String) data.getValue());
	}

	@Override
	public Object getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
