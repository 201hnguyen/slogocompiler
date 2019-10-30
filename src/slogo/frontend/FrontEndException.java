package slogo.frontend;

public class FrontEndException extends Exception {
    private final String message;
    private final Throwable ex;

    public FrontEndException(Exception ex, String message) {
        this.message = message;
        this.ex = ex;
    }

    public String getMessage() {return message;}

    public Throwable getException() {return ex;}
}
