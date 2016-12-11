package dbms.exception;

public class ReservedKeyWordException extends Exception {
    private String message;

    public ReservedKeyWordException() {
        super();
    }

    public ReservedKeyWordException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String toString() {
        return "ReservedKeyWordException{" +
                "message='" + message + '\'' +
                '}';
    }
}
