package dbms.datatypes;

public interface DatatypeDBMS extends Comparable<DatatypeDBMS> {
	Object toObj(String s);
	Object getValue();
}
