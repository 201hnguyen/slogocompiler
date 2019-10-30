package slogo.frontend.controller;

import javafx.scene.layout.HBox;
import slogo.frontend.turtlescreen.DisplayScreen;

import java.util.HashMap;
import java.util.Map;

public class CheckBoxController extends HBox implements NodeController {

    private DisplayScreen displayScreen;

    public CheckBoxController(DisplayScreen displayScreen) {
        this.displayScreen = displayScreen;
    }

    @Override
    public Map<String, String> getChangedValues() {
        return new HashMap<>();
    }

    @Override
    public void setLanguage(String language) {
        return;
    }
}
