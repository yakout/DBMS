package dbms.exception;

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
