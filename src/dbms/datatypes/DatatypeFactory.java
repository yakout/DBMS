package dbms.datatypes;

import dbms.sqlparser.syntax.SyntaxUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

/**
 * Registers and grants access to any of the supported datatypes
 * in DBMS.
 */
public class DatatypeFactory {
    /**
     * Holds all registered data types in factory.
     */
    private static HashMap<String, Class<
            ? extends DBDatatype>> registeredDataTypes = null;

    /**
     * Singleton static instance.
     */
    private static DatatypeFactory instance = null;

    private DatatypeFactory() {
        registeredDataTypes = new HashMap<>();
        loadDatatypes();
    }

    /**
     * Gets static instance to factory.
     * @return static instance.
     */
    public static DatatypeFactory getFactory() {
        if (instance == null) {
            instance = new DatatypeFactory();
        }
        return instance;
    }

    /**
     * Converts a primitive java object to its equivalent
     * {@link DBDatatype}.
     * @param o Object to get converted.
     * @return {@link DBDatatype} object to get converted to.
     */
    public static DBDatatype convertToDataType(Object o) {
        if (o == null) {
            return null;
        }
        Class< ? > classType = o.getClass();
        if (classType == Integer.class) {
            return new DBInteger((Integer) o);
        } else if (classType == Date.class) {
            return new DBDate((Date) o);
        } else if (classType == Float.class) {
            return new DBFloat((Float) o);
        } else if (classType == String.class) {
            return new DBString((String) o);
        }
        return null;
    }

    /**
     * Converts a string value to its equivalent
     * {@link DBDatatype} object.
     * @param value String value.
     * @return {@link DBDatatype} object.
     */
    public static Object convertToObject(String value) {
        if (value.matches(SyntaxUtil.DATE_FORMAT)) {
            value = value.replaceAll("'", "");
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
            try {
                date = new Date(format.parse(value).getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return date;
        } else if (value.matches(SyntaxUtil.NUMBER_FORMAT)) {
            try {
                return Integer.parseInt(value);
            } catch (Exception e) {
                try {
                    return Float.parseFloat(value);
                } catch (Exception e2) {

                }
            }
        }
        return value.replaceAll("('|\")", "");
    }

    /**
     * Registers a new datatype to factory.
     * @param key datatype key.
     * @param datatype Class of the datatype.
     */
    public void register(final String key, 
    		final Class< ? extends DBDatatype> datatype) {
        registeredDataTypes.put(key, datatype);
    }

    /**
     * Gets registered datatype
     * @param name
     * @return
     */
    public Class< ? extends DBDatatype> getRegisteredDatatype(
    		final String name) {
        return registeredDataTypes.get(name);
    }

    /**
     * Converts a string value to an object given
     * key to {@link DBDatatype}.
     * @param s string value.
     * @param key {@link DBDatatype} key.
     * @return Object.
     */
    public Object toObj(String s, String key) {
        Class< ? extends DBDatatype> datatype = registeredDataTypes
                .get(key);
        if (datatype == null) {
            return null;
        }
        Object ret = null;
        try {
            Method toObj = datatype.getMethod("toObj", String.class);
            ret = toObj.invoke(datatype.newInstance(), s);
        } catch (NoSuchMethodException | SecurityException
                | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        }
        return ret;
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
}
