package dbms.exception;

public class DatabaseAlreadyFoundException extends Exception {
	public DatabaseAlreadyFoundException() {
		super();
	}

	public DatabaseAlreadyFoundException(String message) {
		super(message);
	}
}
