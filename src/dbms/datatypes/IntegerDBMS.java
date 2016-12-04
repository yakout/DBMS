package dbms.datatypes;

public class IntegerDBMS implements DatatypeDBMS {

	static {
		DatatypeFactory.getFactory().register("Integer", IntegerDBMS.class);
	}

	@Override
	public Integer toObj(String n) throws RuntimeException {
		if (n == null || n == "") {
			return null;
		}
		return Integer.parseInt(n);
	}
}
