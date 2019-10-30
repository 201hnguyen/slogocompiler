package slogo.frontend.creater;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class SliderCreator extends VBox implements ChangeableNode {
    private static final String RESOURCE = "resources.frontend.SliderResource";
    private static final String RESOURCE_SLIDER = "resources.frontend.CheckBoxResource";
    private static final double LAYOUT_X= 600;
    private static final double LAYOUT_Y= 5;
    private static final double SPACING = 10;
    private static final double SLIDER_MIN = 0;
    private static final double SLIDER_MAX = 1;
    private static final double SLIDER_START = SLIDER_MAX/2;

    private ResourceBundle resourceBundle;
    private ResourceBundle resourceBundle_check;
    private HBox checkHBox = new HBox();
    private HBox sliderBox = new HBox();


    public SliderCreator() {
        resourceBundle = ResourceBundle.getBundle(RESOURCE);
        resourceBundle_check = ResourceBundle.getBundle(RESOURCE_SLIDER);
        setLayoutX(LAYOUT_X);
        setLayoutY(LAYOUT_Y);
        createSlider();

    }

    private void createSlider() {
        for(String key : Collections.list(resourceBundle.getKeys())) {
            Label sliderLabel = new Label(key);
            Slider slider = new Slider();
            slider.setMin(SLIDER_MIN);
            slider.setMax(SLIDER_MAX);
            slider.setValue(SLIDER_START);
            slider.setShowTickLabels(true);
            sliderBox.getChildren().addAll(sliderLabel,slider);
            for(String key_two : Collections.list(resourceBundle_check.getKeys())) {
            Label checkLabel = new Label(key_two);
            CheckBox checkBox = new CheckBox();
            sliderBox.setSpacing(SPACING);
            checkHBox.setSpacing(SPACING);
            checkHBox.getChildren().addAll(checkLabel,checkBox);
            getChildren().addAll(sliderBox, checkHBox);
        }
    }}

    @Override
    public Map<String, String> getChangedValues() {
        return new HashMap<>();
    }

    @Override
    public void setLanguage(String language) {

    }
}
