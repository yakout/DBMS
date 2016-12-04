package dbms.util;

import dbms.datatypes.DatatypeDBMS;

public enum Operator {
    GreaterThan {
        @Override
        public <T extends DatatypeDBMS> boolean apply(T dataType1, T dataType2) {
            return dataType1.compareTo(dataType2) > 0;
        }
    },
    SmallerThan {
        @Override
        public <T extends DatatypeDBMS> boolean apply(T dataType1, T dataType2) {
            return dataType1.compareTo(dataType2) < 0;
        }
    },
    GreaterThanOrEqual {
        @Override
        public <T extends DatatypeDBMS> boolean apply(T dataType1, T dataType2) {
            return dataType1.compareTo(dataType2) >= 0;
        }
    },
    SmallerThanOrEqual {
        @Override
        public <T extends DatatypeDBMS> boolean apply(T dataType1, T dataType2) {
            return dataType1.compareTo(dataType2) <= 0;
        }
    },
    Equal {
        @Override
        public <T extends DatatypeDBMS> boolean apply(T dataType1, T dataType2) {
            return dataType1.compareTo(dataType2) == 0;
        }
    },
    NotEqual {
        @Override
        public <T extends DatatypeDBMS> boolean apply(T dataType1, T dataType2) {
            return dataType1.compareTo(dataType2) != 0;
        }
    };

    public abstract <T extends DatatypeDBMS> boolean apply(T o1, T o2);

}
