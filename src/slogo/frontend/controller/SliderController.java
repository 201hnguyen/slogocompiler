package slogo.frontend.controller;

import slogo.frontend.turtlescreen.DisplayScreen;

import java.util.HashMap;
import java.util.Map;

/**
 * purpose: Controller used in SliderCreater
 * assumptions: This is only assigned to SliderCreater
 * This will only be used within the SliderCreater class. As it won't be useful if used in other classes
 * @author Eric Han, Michael Castro
 *
 * Why I chose this class: I chose this class as it demonstrates how the controller class needed for
 * SliderCreator.java is implemented, and how an instance of NodeController is implemented.
 */
public class SliderController implements NodeController {

    private DisplayScreen displayScreen;

    public SliderController(DisplayScreen displayScreen) {
        this.displayScreen = displayScreen;
    }

    /**
     * action for speed slider
     * @param ratio = relative speed of turtle's movement
     */
    public void speedSliderAction(double ratio) {
        displayScreen.setAnimation(ratio);
    }

    @Override
    public Map<String, String> getChangedValues() {
        return new HashMap<>();
    }

    @Override
    public void setLanguage(String language) {
        return; //for SliderControl, no action is required when this method is called
    }
}