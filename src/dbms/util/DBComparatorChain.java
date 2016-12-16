package dbms.util;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Comparator;
import java.util.List;

public class DBComparatorChain<T extends Record> implements Comparator<T> {
    private final List<Comparator<T>> comparatorList;
    private BitSet orderingBits = null;

    public DBComparatorChain() {
        this.comparatorList = new ArrayList<Comparator<T>>();
        this.orderingBits = new BitSet();
    }

    void addComparator(Comparator<T> RecordComparator, boolean reverse) {
        comparatorList.add(RecordComparator);
        if (reverse) orderingBits.set(size() - 1);
    }

    public int size() {
        return comparatorList.size();
    }

    @Override
    public int compare(T o1, T o2) {
        int res = 0;
        for (int i = 0; i < size(); i++) {
            res = comparatorList.get(i).compare(o1, o2);
            if (res != 0) {
                if (orderingBits.get(i)) {
                    res = res > 0 ? -1 : 1;
                }
                return res;
            }
        }
        return res;
    }
}
