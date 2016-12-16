package dbms.exception;

/**
 * Signals an incorrect data entry.
 */
public class IncorrectDataEntryException extends Exception {
    private String message;

    public IncorrectDataEntryException() {
        super();
    }

    public IncorrectDataEntryException(final String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String toString() {
        return "IncorrectDataEntryException{" +
                "message='" + message + '\'' +
                '}';
    }
}
