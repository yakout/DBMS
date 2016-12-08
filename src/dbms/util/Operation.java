package dbms.util;

public interface Operation {
    <T extends Comparable<T>> boolean apply(T o1, T o2);
}
