package dbms.datatypes;

import dbms.sqlparser.syntax.SyntaxUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;

public class DatatypeFactory {
	private static HashMap<String, Class<? extends DBDatatype>> registeredDataTypes = null;
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

	public void register(String name, Class<? extends DBDatatype> datatype) {
		registeredDataTypes.put(name, datatype);
	}

	public Class<? extends DBDatatype> getRegisteredDatatype(String name) {
		return registeredDataTypes.get(name);
	}

	public Object toObj(String s, String type) {
		Class<? extends DBDatatype> datatype = registeredDataTypes
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

	public static DBDatatype convertToDataType(Object data) {
		if (data == null) {
			return null;
		}
		Class<?> classType = data.getClass();
        if (classType == Integer.class) {
			return new DBInteger((Integer) data);
        } else if(classType == Date.class) {
            return new DBDate((Date) data);
        } else if (classType == Float.class) {
            return new DBFloat((Float) data);
        } else if (classType == String.class) {
            return new DBString((String) data);
        }
		return null;
	}

	public static Object convertToObject(String value) {
		if (value.matches(SyntaxUtil.DATE_FORMAT)) {
            String string = "1996-W08-17";
            DateFormat format = new SimpleDateFormat("YYYY-'W'ww-u", Locale.ENGLISH);
            Date date = null;
            try {
                date = (Date) format.parse(string);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return date;
		} else if (value.matches(SyntaxUtil.NUMBER_FORMAT)) {

        }

        return null;
	}

	/*
	 * An alternate strategy to extensively load all datatypes
	 * is to walk through directories in .classpath and registers any
	 * class it finds that implements DBDatatype.
	 */
	private void loadDatatypes() {
		try {
			Class.forName("dbms.datatypes.DBInteger");
			Class.forName("dbms.datatypes.DBString");
			Class.forName("dbms.datatypes.DBFloat");
			Class.forName("dbms.datatypes.DBDate");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}


    public static void main(String[] args) {
        System.out.print(convertToObject("'1996-08-17'"));
    }
}
