package dbms.exception;

/**
 * Signals a syntax error in statement.
 */
public class SyntaxErrorException extends Exception {
    private String message;

    public SyntaxErrorException() {
        super();
    }

    public SyntaxErrorException(final String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String toString() {
        return "Syntax Error " + message ;
    }
}
