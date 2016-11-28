package dbms.exception;

public class TableAlreadyCreatedException extends Exception {
	private String message;

	public TableAlreadyCreatedException() {
		super();
	}

	public TableAlreadyCreatedException(String message) {
		super(message);
		this.message = message;
	}

	@Override
	public String toString() {
		return "TableAlreadyCreatedException{" +
				"message='" + message + '\'' +
				'}';
	}
}
