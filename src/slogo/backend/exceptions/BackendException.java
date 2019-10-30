package slogo.backend.exceptions;

public class BackendException extends Exception{
    private Throwable ex;
    private String message;

    public BackendException(String message) {
        this.message = message;
    }

    public BackendException(Throwable ex, String message) {
        this.ex = ex;
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
