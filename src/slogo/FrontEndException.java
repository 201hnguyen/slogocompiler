package slogo;

public class FrontEndException extends RuntimeException{
    private String message;
    private Throwable ex;

    public FrontEndException(Throwable ex, String message) {
        this.message = message;
        this.ex = ex;
    }

    public String getMessage() {return message;}

    public Throwable getException() {return ex;}
}
