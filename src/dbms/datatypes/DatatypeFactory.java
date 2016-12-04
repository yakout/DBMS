package dbms.datatypes;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

public class DatatypeFactory {
	private static HashMap<String, Class<? extends DatatypeDBMS>>registeredDataTypes = null;
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
			Method method = datatype.getMethod("toObj", String.class);
			ret = method.invoke(datatype.newInstance(), s);
		} catch (NoSuchMethodException| SecurityException
				| IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | InstantiationException e) {
			e.printStackTrace();
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
