package slogo.backend.exceptions;

public class BackendException extends Exception{
    private String message;
    public BackendException(Exception ex, String message) {
        super(ex);
        this.message = message;
    }

    /**
     *
     * @return
     */
    public String getMessage() {
        return message;
    }
}
