package slogo.frontend.controller;

import javafx.scene.layout.HBox;
import slogo.frontend.turtlescreen.DisplayScreen;

import java.util.HashMap;
import java.util.Map;

public class CheckBoxController extends HBox implements NodeController {

    private static final double SETMAXSPEED = 10;

    private DisplayScreen displayScreen;

    public CheckBoxController(DisplayScreen displayScreen) {
        this.displayScreen = displayScreen;
    }

    public void animationAction() {
        displayScreen.setAnimation(SETMAXSPEED);
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
