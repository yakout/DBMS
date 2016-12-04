package dbms.datatypes;

public class StringDBMS implements DatatypeDBMS {

	static {
		DatatypeFactory.getFactory().register("String", StringDBMS.class);
	}

	@Override
	public Object toObj(String s) {
		return s;
	}

	@Override
	public boolean isComparable(Object o1, Object o2) {
		if (o1 instanceof String && o2 instanceof String) {
			return true;
		}
		return false;
	}

	@Override
	public int compare(Object o1, Object o2) {
		return 0;
	}
}
