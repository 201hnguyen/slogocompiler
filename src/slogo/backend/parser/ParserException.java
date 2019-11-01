package slogo.backend.parser;

/**
 *
 * purpose (why would anyone use it)
 * assumptions (what situations or values might cause it to fail)
 * dependencies (what other classes or packages it depends on)
 * an example of how to use it
 * any other details users should know
 *
 * @author Amber Johnson
 */
public class ParserException extends RuntimeException {

    private static final String ERROR_MESSAGE = "The following command is not recognized: ";
    private final String message;

    public ParserException(String command) {
        this.message = ERROR_MESSAGE + command;
    }

    /**
     * Gets the error message for a ParserException object
     *
     * @return a String of the error message
     */
    public String getMessage() {return message;}
}
