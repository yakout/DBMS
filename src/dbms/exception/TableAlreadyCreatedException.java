package dbms.exception;

/**
 * Signals an attempt to open a table that has already
 * been created.
 */
public class TableAlreadyCreatedException extends Exception {
	public TableAlreadyCreatedException() {
		super();
	}

	public TableAlreadyCreatedException(String message) {
		super(message);
	}
}
