package dbms.exception;

/**
 * Signals an attempt to open a table that doesn't exist.
 */
public class TableNotFoundException extends Exception {
	private String message;

	public TableNotFoundException() {
		super();
	}

	public TableNotFoundException(String message) {
		super(message);
		this.message = message;
	}

	@Override
	public String toString() {
		return "TableNotFoundException{" +
				"message='" + message + '\'' +
				'}';
	}
}
