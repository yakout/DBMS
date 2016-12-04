package dbms.util;

public enum Operator {
    GreaterThan {
        @Override
        public boolean apply(Object o1, Object o2) {
            if (o1 instanceof  Integer && o2 instanceof Integer) {
                return ((Integer) o1).compareTo((Integer) o2) > 0;
            } else if (o1 instanceof  String && o2 instanceof String) {
                return ((String) o1).compareTo((String) o2) > 0;
            }
            return false;
        }
    },
    SmallerThan {
        @Override
        public boolean apply(Object o1, Object o2) {
            if (o1 instanceof  Integer && o2 instanceof Integer) {
                return ((Integer) o1).compareTo((Integer) o2) < 0;
            } else if (o1 instanceof  String && o2 instanceof String) {
                return ((String) o1).compareTo((String) o2) < 0;
            }
            return false;
        }
    },
    GreaterThanOrEqual {
        @Override
        public boolean apply(Object o1, Object o2) {
            if (o1 instanceof  Integer && o2 instanceof Integer) {
                return ((Integer) o1).compareTo((Integer) o2) >= 0;
            } else if (o1 instanceof  String && o2 instanceof String) {
                return ((String) o1).compareTo((String) o2) >= 0;
            }
            return false;
        }
    },
    SmallerThanOrEqual {
        @Override
        public boolean apply(Object o1, Object o2) {
            if (o1 instanceof  Integer && o2 instanceof Integer) {
                return ((Integer) o1).compareTo((Integer) o2) <= 0;
            } else if (o1 instanceof  String && o2 instanceof String) {
                return ((String) o1).compareTo((String) o2) <= 0;
            }
            return false;
        }
    },
    Equal {
        @Override
        public boolean apply(Object o1, Object o2) {
            if (o1 instanceof  Integer && o2 instanceof Integer) {
                return ((Integer) o1).compareTo((Integer) o2) == 0;
            } else if (o1 instanceof  String && o2 instanceof String) {
                return ((String) o1).compareTo((String) o2) == 0;
            }
            return false;
        }
    },
    NotEqual {
        @Override
        public boolean apply(Object o1, Object o2) {
            if (o1 instanceof  Integer && o2 instanceof Integer) {
                return ((Integer) o1).compareTo((Integer) o2) != 0;
            } else if (o1 instanceof  String && o2 instanceof String) {
                return ((String) o1).compareTo((String) o2) != 0;
            }
            return false;
        }
    };

    public abstract boolean apply(Object o1, Object o2);

}
