package dbms.exception;

public class IncorrectDataEntryException extends Exception {
	public IncorrectDataEntryException() {
		super();
	}

	public IncorrectDataEntryException(String message) {
		super(message);
	}
}
