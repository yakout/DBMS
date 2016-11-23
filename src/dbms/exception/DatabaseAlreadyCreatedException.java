package dbms.exception;

public class DatabaseAlreadyCreatedException extends Exception {
	public DatabaseAlreadyCreatedException() {
		super();
	}

	public DatabaseAlreadyCreatedException(String message) {
		super(message);
	}
}
