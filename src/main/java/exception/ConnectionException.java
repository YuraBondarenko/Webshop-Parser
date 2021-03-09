package exception;

public class ConnectionException extends RuntimeException {
    public ConnectionException(String message, Exception cause) {
        super(message, cause);
    }
}
