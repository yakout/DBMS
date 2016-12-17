package dbms.datatypes;

public interface DBDatatype extends Comparable<DBDatatype> {

    /**
     * Gets an equivalent primitive java object of a string value.
     * @param s String value.
     * @return Equivalent object.
     */
    Object toObj(String s);

    /**
     * Gets the java primitive equivalent to the {@link DBDatatype}.
     * @return equivalent primitive java object.
     */
    Object getValue();

    /**
     * Gets String key that is used as an id of a data type.
     * @return KEY.
     */
    String getKey();
}
