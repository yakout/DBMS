package dbms.datatypes;


public class IntegerDBMS implements DatatypeDBMS {
	private static final String KEY = "Integer";
	private Integer value;

	static {
		DatatypeFactory.getFactory().register(KEY, IntegerDBMS.class);
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
	public int compareTo(DatatypeDBMS data) {
		return value.compareTo((Integer) data.getValue());
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	@Override
	public Integer getValue() {
		return value;
	}
}
