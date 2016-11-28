package dbms.exception;

/**
 * Signals an attempt to add a non-supported type to table.
 */
public class TypeNotSupportedException extends Exception {

	public TypeNotSupportedException() {
		super();
	}

	public TypeNotSupportedException(String message) {
		super(message);
	}
}
