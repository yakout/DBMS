package dbms.datatypes;

public class DBString implements DatatypeDBMS, Comparable<DBString> {
	private static final String KEY = "String";
	private String value;

	static {
		DatatypeFactory.getFactory().register(KEY, DBString.class);
	}

	public DBString(String value) {
		this.value = value;
	}

	@Override
	public Object toObj(String s) {
		return s;
	}

	@Override
	public int compareTo(DBString data) {
		return value.compareTo(data.getValue());
	}

	@Override
	public String getValue() {
		return value;
	}
}
