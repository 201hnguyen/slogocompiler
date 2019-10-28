package slogo.frontend;

import java.util.Map;

public interface ChangeableNode {

    Map<String, String> getChangedValues();

    void setLanguage(String language);
}
