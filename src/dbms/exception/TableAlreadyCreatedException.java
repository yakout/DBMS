package dbms.exception;

public class TableAlreadyCreatedException extends Exception {
	public TableAlreadyCreatedException() {
		super();
	}

	public TableAlreadyCreatedException(String message) {
		super(message);
	}
}
