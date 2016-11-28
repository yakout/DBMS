package dbms.exception;

public class SyntaxErrorException extends Exception {
    private String message;

    public SyntaxErrorException() {
        super();
    }

    public SyntaxErrorException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String toString() {
        return "SyntaxErrorException{" +
                "message='" + message + '\'' +
                '}';
    }
}
