package dbms.util;


public enum Operator implements Operation {
    GreaterThan {
        @Override
        public <T extends Comparable<T>> boolean apply(T dataType1, T dataType2) {
            return dataType1 != null && dataType1.compareTo(dataType2) > 0;
        }
    },
    SmallerThan {
        @Override
        public <T extends Comparable<T>> boolean apply(T dataType1, T dataType2) {
            return dataType1 != null && dataType1.compareTo(dataType2) < 0;
        }
    },
    GreaterThanOrEqual {
        @Override
        public <T extends Comparable<T>> boolean apply(T dataType1, T dataType2) {
            return dataType1 != null && dataType1.compareTo(dataType2) >= 0;
        }
    },
    SmallerThanOrEqual {
        @Override
        public <T extends Comparable<T>> boolean apply(T dataType1, T dataType2) {
            return dataType1 != null && dataType1.compareTo(dataType2) <= 0;
        }
    },
    Equal {
        @Override
        public <T extends Comparable<T>> boolean apply(T dataType1, T dataType2) {
            return dataType1 != null && dataType1.compareTo(dataType2) == 0;
        }
    },
    NotEqual {
        @Override
        public <T extends Comparable<T>> boolean apply(T dataType1, T dataType2) {
            return dataType1 != null && dataType1.compareTo(dataType2) != 0;
        }
    };
}
