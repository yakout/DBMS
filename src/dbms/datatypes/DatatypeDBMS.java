package dbms.datatypes;

public interface DatatypeDBMS {
	public Object toObj(String s);
	public boolean isComparable(Object o1, Object o2);
	public int compare(Object o1, Object o2);
}
