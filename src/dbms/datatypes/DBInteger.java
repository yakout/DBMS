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

	@Override
	public String toString() {
		return value.toString();
	}

	@Override
	public String getKey() {
		return KEY;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || obj.getClass() != getClass()) {
			return false;
		}
		DBInteger dbInteger = (DBInteger) obj;
		if (value != null ? !value.equals(dbInteger.getValue()) : dbInteger.getValue() != null) {
			return false;
		}
		return true;
	}
}
