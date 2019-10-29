package slogo.frontend.controller;

import slogo.frontend.turtlescreen.DisplayScreen;
import slogo.frontend.reference.ReferenceWindow;

import java.util.HashMap;
import java.util.Map;

public class ButtonController implements NodeController {

    private static final String INITIAL_LANGUAGE = "English";
    private String language;
    private Map<String, String> changedValues = new HashMap<>();
    private DisplayScreen displayScreen;

    public ButtonController(DisplayScreen displayScreen) {
        language = INITIAL_LANGUAGE;
        this.displayScreen = displayScreen;
    }

    public void startButtonAction(String key) {
        changedValues.put(key, key);
    }

    public void clearButtonAction(String key) {
        changedValues.put(key, key);
    }

    public void helpButtonAction(String key) {
        ReferenceWindow referenceWindow = new ReferenceWindow(language);
        referenceWindow.createNewWindow();
    }

    public void playButtonAction(String key) {
        displayScreen.setAnimation(key);
    }

    public void stopButtonAction(String key) {
        displayScreen.setAnimation(key);
    }

    public void stepButtonAction(String key) {
        displayScreen.setAnimation(key);
    }

    public void newButtonAction(String key) {
        changedValues.put(key, key);
    }

    @Override
    public Map<String, String> getChangedValues() {
        Map<String, String> map = new HashMap<>();
        map.putAll(changedValues);
        changedValues.clear();
        return map;
    }

    @Override
    public void setLanguage(String language) {
        this.language = language;
    }
}
