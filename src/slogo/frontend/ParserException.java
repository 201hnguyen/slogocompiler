package slogo.frontend;

public class ParserException extends RuntimeException {
    private String message;
    private Throwable ex;
    private String toDisplay;

    public ParserException(Exception ex, String message) {
        super(ex);
        this.message = message;
        this.ex = ex;
    }

    //Used in Parser.match()
    public ParserException(Exception ex, String message, String text) {
        super(ex);
        this.message = message;
        this.toDisplay = message + text;
    }

    public String getMessage() {return message;}

    public Throwable getException() {return ex;}

    public String getToDisplay() {return toDisplay;}
}
