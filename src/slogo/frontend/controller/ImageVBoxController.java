package slogo.frontend.controller;

import slogo.frontend.turtlescreen.DisplayScreen;

import java.util.HashMap;
import java.util.Map;

public class ImageVBoxController implements NodeController {

    private static final String INITIAL_LANGUAGE = "English";
    private String language;
    private Map<String, String> changedValues = new HashMap<>();
    private DisplayScreen displayScreen;

    /**
     * Constructor that takes in the DisplayScreen parameter. This allows for proper call
     * of this class in the GUI.
     *  */

    public ImageVBoxController(DisplayScreen displayScreen) {
        language = INITIAL_LANGUAGE;
        this.displayScreen = displayScreen;
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

    }
}
