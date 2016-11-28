package dbms.exception;

/**
 * Signals an attempt to open a table that doesn't exist.
 */
public class TableNotFoundException extends Exception {
	public TableNotFoundException() {
		super();
	}

	public TableNotFoundException(String message) {
		super(message);
	}
}
