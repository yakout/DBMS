package dbms.datatypes;

public interface DatatypeDBMS {
	Object toObj(String s);
	int compareTo(DatatypeDBMS data);
	Object getValue();
}
