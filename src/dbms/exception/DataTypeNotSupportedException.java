package dbms.exception;

@SuppressWarnings("serial")
public class DataTypeNotSupportedException extends Exception {
	private String message;

	public DataTypeNotSupportedException() {
		super();
	}

	public DataTypeNotSupportedException(String message) {
		super(message);
		this.message = message;
	}

	@Override
	public String toString() {
		return "DataTypeNotSupportedException{" +
				"message='" + message + '\'' +
				'}';
	}
}
