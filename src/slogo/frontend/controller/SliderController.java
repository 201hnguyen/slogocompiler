package slogo.frontend.controller;

import slogo.frontend.turtlescreen.DisplayScreen;

import java.util.HashMap;
import java.util.Map;

public class SliderController implements NodeController {

    private DisplayScreen displayScreen;

    public SliderController(DisplayScreen displayScreen) {
        this.displayScreen = displayScreen;
    }

    public void speedSliderAction(double ratio) {
        displayScreen.setAnimation(ratio);
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


