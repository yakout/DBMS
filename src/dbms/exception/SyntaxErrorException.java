package dbms.exception;

public class SyntaxErrorException extends Exception {
    public SyntaxErrorException() {
        super();
    }

    public SyntaxErrorException(String message) {
        super(message);
    }
}
