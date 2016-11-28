package dbms.exception;

/**
 * Signals an attempt to create a database when it has
 * already been created.
 */
public class DatabaseAlreadyCreatedException extends Exception {
	public DatabaseAlreadyCreatedException() {
		super();
	}

	public DatabaseAlreadyCreatedException(String message) {
		super(message);
	}
}
