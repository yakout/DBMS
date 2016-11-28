package dbms.exception;

public class DatabaseNotFoundException extends Exception {
	private String message;

	public DatabaseNotFoundException() {
		super();
	}

	public DatabaseNotFoundException(String message) {
		super(message);
		this.message = message;
	}

	@Override
	public String toString() {
		return "DatabaseNotFoundException{" +
				"message='" + message + '\'' +
				'}';
	}
}
