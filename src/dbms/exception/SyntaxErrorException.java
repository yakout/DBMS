package dbms.exception;

/**
 * Signals a syntax error in statement.
 */
public class SyntaxErrorException extends Exception {
    public SyntaxErrorException() {
        super();
    }

    public SyntaxErrorException(String message) {
        super(message);
    }
}
