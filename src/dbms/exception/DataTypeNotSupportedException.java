package dbms.exception;

public class DataTypeNotSupportedException extends Exception {
    public DataTypeNotSupportedException() {
        super();
    }

    public DataTypeNotSupportedException(final String message) {
        super(message);
    }
}
