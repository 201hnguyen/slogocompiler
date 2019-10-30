package slogo.frontend.controller;

import slogo.frontend.turtlescreen.DisplayScreen;

import java.util.HashMap;
import java.util.Map;

public class SliderController implements NodeController {
    public SliderController(DisplayScreen displayScreen) {

    }

    public void speedSliderAction() {

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


