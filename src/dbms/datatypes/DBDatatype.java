package dbms.datatypes;

public interface DBDatatype extends Comparable<DBDatatype> {
	Object toObj(String s);
	Object getValue();
	String getKey();
}
