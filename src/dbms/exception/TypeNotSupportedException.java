package dbms.exception;

public class TypeNotSupportedException extends Exception {
	private String message;

	public TypeNotSupportedException() {
		super();
	}

	public TypeNotSupportedException(String message) {
		super(message);
		this.message = message;
	}

	@Override
	public String toString() {
		return "TypeNotSupportedException{" +
				"message='" + message + '\'' +
				'}';
	}
}
