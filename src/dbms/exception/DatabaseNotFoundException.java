package dbms.exception;

/**
 * Signals an attempt to open a database that doesn't exist.
 */
public class DatabaseNotFoundException extends Exception {
	public DatabaseNotFoundException() {
		super();
	}

	public DatabaseNotFoundException(String message) {
		super(message);
	}
}
