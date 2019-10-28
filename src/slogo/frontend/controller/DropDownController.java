package slogo.frontend.controller;


import slogo.frontend.turtlescreen.DisplayScreen;

import java.util.HashMap;
import java.util.Map;

public class DropDownController implements NodeController {

    private static final String INITIAL_LANGUAGE = "English";
    private static final int INITIAL_IMAGE_NUM = 1;

    private String language;
    private int imageNum;
    private DisplayScreen displayScreen;
    private Map<String, String> changedValues = new HashMap<>();

    public DropDownController(DisplayScreen displayScreen) {
        this.displayScreen = displayScreen;
        language = INITIAL_LANGUAGE;
        imageNum = INITIAL_IMAGE_NUM;
    }

    public void chooseImage(String key, String imageNum) {
        this.imageNum = Integer.parseInt(imageNum);
        displayScreen.setImage(this.imageNum);
    }

    public void choosePenSize(String key, String penSize) {
        displayScreen.setPenSize(Integer.parseInt(penSize));
    }

    public void chooseLanguage(String key, String language) {
        changedValues.put(key, language);
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
