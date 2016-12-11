package dbms.datatypes;

public class DBString implements DBDatatype {
	public static final String KEY = "String";
	private String value;

	static {
		DatatypeFactory.getFactory().register(KEY, DBString.class);
	}

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
}
