package dbms.datatypes;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

public class DatatypeFactory {
	private static HashMap<String, Class<? extends DatatypeDBMS>> registeredDataTypes = null;
	private static DatatypeFactory instance = null;

	private DatatypeFactory() {
		registeredDataTypes = new HashMap<>();
		loadDatatypes();
	}

	public static DatatypeFactory getFactory() {
		if (instance == null) {
			instance = new DatatypeFactory();
		}
		return instance;
	}

	public void register(String name, Class<? extends DatatypeDBMS> datatype) {
		registeredDataTypes.put(name, datatype);
	}

	public Class<? extends DatatypeDBMS> getRegisteredDatatype(String name) {
		return registeredDataTypes.get(name);
	}

	public Object toObj(String s, String type) {
		Class<? extends DatatypeDBMS> datatype = registeredDataTypes
				.get(type);
		if (datatype == null) {
			return null;
		}
		Object ret = null;
		try {
			Method toObj = datatype.getMethod("toObj", String.class);
			ret = toObj.invoke(datatype.newInstance(), s);
		} catch (NoSuchMethodException| SecurityException
				| IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | InstantiationException e) {
			e.printStackTrace();
		}
		return ret;
	}

	public int compare(Object o1, Object o2) throws Exception {
		boolean found = false;
		int ret = 0;
		for (String s : registeredDataTypes.keySet()) {
			Class<? extends DatatypeDBMS> c =
					registeredDataTypes.get(s);
			try {
				Method check = c.getMethod("isComparable", Object.class, Object.class);
				boolean comparable = (boolean) check.invoke(c.newInstance(), o1, o2);
				if (comparable) {
					found = true;
					Method compare = c.getMethod("compare", Object.class, Object.class);
					ret = (int) compare.invoke(c.newInstance(), o1, o2);
					break;
				}
			} catch (NoSuchMethodException| SecurityException
					| IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | InstantiationException e) {
				e.printStackTrace();
			}
		}
		if (!found) {
			throw new Exception();
		}
		return ret;
	}

	/*
	 * An alternate strategy to extensively load all datatypes
	 * is to walk through directories in .classpath and registers any
	 * class it finds that implements DatatypeDBMS.
	 */
	private void loadDatatypes() {
		try {
			Class.forName("dbms.datatypes.IntegerDBMS");
			Class.forName("dbms.datatypes.StringDBMS");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
