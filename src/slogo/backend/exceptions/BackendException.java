package slogo.backend.exceptions;

public class BackendException extends Exception{
    private String message;
    public BackendException(Exception ex, String message) {
        super(ex);
        this.message = message;
    }

    public BackendException(String message) {
        super();
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
