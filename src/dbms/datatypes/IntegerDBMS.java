package dbms.datatypes;

public class IntegerDBMS implements DatatypeDBMS {
	public static final String KEY = "Integer";

	static {
		DatatypeFactory.getFactory().register(KEY, IntegerDBMS.class);
	}

	@Override
	public Integer toObj(String n) {
		if (n == null || n == "") {
			return null;
		}
		return Integer.parseInt(n);
	}

	@Override
	public boolean isComparable(Object o1, Object o2) {
		if (o1 instanceof Integer && o2 instanceof Integer) {
			return true;
		}
		return false;
	}

	@Override
	public int compare(Object o1, Object o2) {
		return 0;
	}
}
