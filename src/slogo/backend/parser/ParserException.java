package slogo.backend.parser;

public class ParserException extends RuntimeException {

    private static final String ERROR_MESSAGE = "The following command is not recognized: ";

    private final String message;

    //Used in Parser.getResourceKey()
    public ParserException(String command) {
        //System.out.println("entered ParserException"); //testing
        this.message = ERROR_MESSAGE + command;
        //System.out.println(message); //testing
    }

    /**
     * Returns the error message for a ParserException
     * @return
     */
    public String getMessage() {return message;}
}
