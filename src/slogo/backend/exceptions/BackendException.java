package slogo.backend.exceptions;

/**
 * @author  Eric Han, Amber Johnson, Ha Nguyen
 */

public class BackendException extends Exception{
    private final Throwable ex = new Exception();
    private final String message;

    public BackendException(String message) {
        this.message = message;
    }

    public BackendException(Throwable ex, String message) {
        this.message = message;
    }

    /**
     * Gets the error message for a BackendException object
     * @return
     */
    public String getMessage() {
        return message;
    }
}
