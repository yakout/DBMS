package dbms.exception;

public class DatabaseAlreadyCreatedException extends Exception {
	private String message;

	public DatabaseAlreadyCreatedException() {
		super();
	}

	public DatabaseAlreadyCreatedException(String message) {
		super(message);
		this.message = message;
	}

	@Override
	public String toString() {
		return "DatabaseAlreadyCreatedException{" +
				"message='" + message + '\'' +
				'}';
	}
}
