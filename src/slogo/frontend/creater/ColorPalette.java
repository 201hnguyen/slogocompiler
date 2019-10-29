package slogo.frontend.creater;

import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import slogo.frontend.ErrorShow;
import slogo.frontend.turtlescreen.DisplayScreen;
import slogo.frontend.controller.ColorPaletteController;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class ColorPalette extends VBox implements ChangeableNode{
    private static final double PICKER_WIDTH = 100;
    private static final double PICKER_HEIGHT = 40;
    private static final double CIRCLE_RADIUS = 50;
    private static final double INSET_PADDING = 10;
    private static final double PALETTE_Y = 420;
    private static final double PALETTE_X = 630;
    private static final double CHECK_X = 570;
    private static final double CHECK_Y = 630;
    private static final double CHECK_SPACING = 10;
    private static final double SPACING = 10;
    private static final String RESOURCE_PATH = "resources.frontend.ColorPalette";
    private static final String INITIAL_LANGUAGE = "English";

    private ColorPicker colorPicker;
    private Circle colorCircle;
    private ColorPaletteController colorPaletteController;
    private ResourceBundle resourceBundle = ResourceBundle.getBundle(RESOURCE_PATH);
    private Map<CheckBox, String> checkBoxMap = new HashMap<>();
    private String language = INITIAL_LANGUAGE;

    public ColorPalette(DisplayScreen displayScreen) {
        colorPaletteController = new ColorPaletteController(displayScreen);
        colorPicker = new ColorPicker();
        colorPicker.setMaxSize(PICKER_WIDTH,PICKER_HEIGHT);
        colorCircle = new Circle(CIRCLE_RADIUS);
        colorCircle.setFill(colorPicker.getValue());
        getChildren().addAll(colorCircle, colorPicker, checkBoxes());
        colorPicker.setOnAction(event -> {
            colorCircle.setFill(colorPicker.getValue());
            callMethods(colorPicker.getValue());
        });
        setPadding(new Insets(INSET_PADDING));
        setLayoutY(PALETTE_Y);
        setSpacing(SPACING);
        setLayoutX(PALETTE_X);
        System.out.println(checkBoxes().getChildren().size());
    }

    @Override
    public Map<String, String> getChangedValues() {
        return colorPaletteController.getChangedValues();
    }

    @Override
    public void setLanguage(String language) {
        this.language = language;
        /**
         * TODO: update language
         */
    }

    private HBox checkBoxes() {
        HBox checkBoxes = new HBox();
        addColorPalettes(checkBoxes);
        checkBoxes.setLayoutY(CHECK_Y);
        checkBoxes.setLayoutX(CHECK_X);
        checkBoxes.setSpacing(CHECK_SPACING);
        return checkBoxes;
    }

    private void addColorPalettes(HBox hbox) {
        for(String key : Collections.list(resourceBundle.getKeys())) {
            CheckBox checkBox = new CheckBox(key);
            checkBoxMap.put(checkBox, key);
            hbox.getChildren().add(checkBox);
        }
    }

    private void callMethods(Color color) {
        for(CheckBox checkBox : checkBoxMap.keySet()) {
            if(checkBox.isSelected()) {
                try {
                    String methodName = resourceBundle.getString(checkBoxMap.get(checkBox));
                    Method m = colorPaletteController.getClass().getDeclaredMethod(methodName, Color.class);
                    m.invoke(colorPaletteController, color);
                } catch (Exception e) {
                    ErrorShow errorShow = new ErrorShow(e, "ColorPicker Setting Wrong");
                    errorShow.show();
                }
            }
        }
    }
}
