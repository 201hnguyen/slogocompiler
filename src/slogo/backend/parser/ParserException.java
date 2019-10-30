package slogo.backend.parser;

/**
 *
 * @author Amber Johnson and Robert C. Duvall
 */
public class ParserException extends RuntimeException {

    private static final String ERROR_MESSAGE = "The following command is not recognized: ";
    private final String message;

    public ParserException(String command) {
        this.message = ERROR_MESSAGE + command;
    }

    /**
     * Gets the error message for a ParserException object
     * @return
     */
    public String getMessage() {return message;}
}
