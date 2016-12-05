package dbms.util;

/**
 * Created by khlailmohammedyakout on 12/6/16.
 */
public interface Operation {
    <T extends Comparable<T>> boolean apply(T o1, T o2);
}
