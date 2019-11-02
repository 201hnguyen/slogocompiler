package slogo.frontend.creater;

import java.util.Map;
/**
 * Interface that lets us work with the chaning node values.
 * */

public interface ChangeableNode {

    Map<String, String> getChangedValues();

    void setLanguage(String language);
}
