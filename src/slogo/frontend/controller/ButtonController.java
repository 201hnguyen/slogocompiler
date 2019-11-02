package slogo.frontend.controller;

import slogo.frontend.turtlescreen.DisplayScreen;
import slogo.frontend.reference.ReferenceWindow;

import java.util.HashMap;
import java.util.Map;

/**
 * purpose: Controller used in ButtonCreater
 * assumptions: This is only assigned to ButtonCreater
 *
 * This will only be used within the ButtonCreater class. No other places should this be used.
 */

public class ButtonController implements NodeController {

    private static final String INITIAL_LANGUAGE = "English";
    private String language;
    private Map<String, String> changedValues = new HashMap<>();
    private DisplayScreen displayScreen;

    public ButtonController(DisplayScreen displayScreen) {
        language = INITIAL_LANGUAGE;
        this.displayScreen = displayScreen;
    }

    /**
     * action for start Button
     */
    public void startButtonAction(String key) {
        changedValues.put(key, key);
    }

    /**
     * action for clear Button
     */
    public void clearButtonAction(String key) {
        changedValues.put(key, key);
    }

    /**
     * action for help Button
     */
    public void helpButtonAction(String key) {
        ReferenceWindow referenceWindow = new ReferenceWindow(language);
        referenceWindow.createNewWindow();
    }

    /**
     * action for play Button
     */
    public void playButtonAction(String key) {
        //displayScreen.setAnimation();
    }

    /**
     * action for stop Button
     */
    public void stopButtonAction(String key) {
        displayScreen.setAnimation(0);
    }

    /**
     * action for step Button
     */
    public void stepButtonAction(String key) {
        displayScreen.step();
    }

    /**
     * action for new Button
     */
    public void newButtonAction(String key) {
        changedValues.put(key, key);
    }

    /**
     * returns the map that means the changes the UI should make
     */
    @Override
    public Map<String, String> getChangedValues() {
        Map<String, String> map = new HashMap<>();
        map.putAll(changedValues);
        changedValues.clear();
        return map;
    }

    /**
     * Sets the language of the controller
     */
    @Override
    public void setLanguage(String language) {
        this.language = language;
    }
}
