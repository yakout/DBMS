package dbms.exception;

/**
 * Signals an attempt to open a table that has already
 * been created.
 */
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
