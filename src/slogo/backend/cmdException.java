package slogo.backend;

public class cmdException {

    //constructor 1
    //Thrown when user-input language does not match language pattern
    public cmdException(String language) {


    }

    //constructor 2
    //Thrown when user-input command does not match a command in the given language
    public cmdException(String language, String cmd) {

    }



    public String cmdException() {
        return "";
    }

    //additional exceptions


}
