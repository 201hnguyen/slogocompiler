package slogo.backend.exceptions;

public class NeedValueOfParameterException extends Exception {
    private String parameter;

    public NeedValueOfParameterException(String parameter) {
        this.parameter = parameter;
    }

    public String getParameterName() {
        return parameter;
    }

}
