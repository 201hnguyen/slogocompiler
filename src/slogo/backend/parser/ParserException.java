package slogo.backend.parser;

import java.util.ResourceBundle;

public class ParserException extends RuntimeException {

    private static final String ERROR_MESSAGE = "The following command is not recognized: ";

    private final String message;

    //Used in Parser.getResourceKey()
    public ParserException(String command) {
        System.out.println("entered ParserException");
        this.message = ERROR_MESSAGE + command;
        System.out.println(message);
    }

    /**
     * Returns the error message for a ParserException
     * @return
     */
    public String getMessage() {return message;}
}
