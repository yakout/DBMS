package dbms.datatypes;

public class StringDBMS implements DatatypeDBMS {

	static {
		DatatypeFactory.getFactory().register("String", StringDBMS.class);
	}

	@Override
	public Object toObj(String s) {
		return s;
	}
}
