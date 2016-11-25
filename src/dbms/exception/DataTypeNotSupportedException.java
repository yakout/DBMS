package dbms.exception;

@SuppressWarnings("serial")
public class DataTypeNotSupportedException extends Exception {
	public DataTypeNotSupportedException() {
		super();
	}

	public DataTypeNotSupportedException(String message) {
		super(message);
	}
}
