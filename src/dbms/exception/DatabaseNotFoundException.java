package dbms.exception;

/**
 * Signals an attempt to open a database that doesn't exist.
 */
public class DatabaseNotFoundException extends Exception {
	private String message;

	public DatabaseNotFoundException() {
		super();
	}

	public DatabaseNotFoundException(String message) {
		super(message);
		this.message = message;
	}

	@Override
	public String toString() {
		return "DatabaseNotFoundException{" +
				"message='" + message + '\'' +
				'}';
	}
}
