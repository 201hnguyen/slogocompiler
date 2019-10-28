package slogo.frontend.controller;

import java.util.Map;

public interface NodeController {
    Map<String, String> getChangedValues();

    void setLanguage(String language);
}
