package slogo.backend.exceptions;

public class ParserException extends RuntimeException {

    //constructor 1
    //Thrown when user-input language does not match language pattern
    public ParserException(String language) {


    }

    //constructor 2
    //Thrown when user-input command does not match a command in the given language
    public ParserException(String language, String cmd) {

    }



}
