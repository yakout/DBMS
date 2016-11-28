package dbms.exception;

/**
 * Signals an incorrect data entry.
 */
public class IncorrectDataEntryException extends Exception {
	public IncorrectDataEntryException() {
		super();
	}

	public IncorrectDataEntryException(String message) {
		super(message);
	}
}
