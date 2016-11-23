package dbms.exception;

public class DatabaseNotFoundException extends Exception {
	public DatabaseNotFoundException() {
		super();
	}

	public DatabaseNotFoundException(String message) {
		super(message);
	}
}
