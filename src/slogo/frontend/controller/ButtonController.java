package slogo.frontend.controller;

import slogo.frontend.DisplayScreen;
import slogo.frontend.reference.ReferenceWindow;

import java.util.HashMap;
import java.util.Map;

public class ButtonController implements NodeController {
    private String language;
    private Map<String, String> changedValues = new HashMap<>();
    private DisplayScreen displayScreen;

    public ButtonController(String language, DisplayScreen displayScreen) {
        this.displayScreen = displayScreen;
        this.language = language;
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
