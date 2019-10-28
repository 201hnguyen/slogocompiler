package slogo.frontend;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import slogo.frontend.controller.ButtonController;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.ResourceBundle;

public class ButtonCreator extends HBox {
    private static final double INSET_PADDING = 10;
    private static final double SPACING = 25;
    private static final double Y_LAYOUT = 430;
    private static final double X_LAYOUT = 20;
    private static final String RESOURCE_PATH = "resources.frontend.ButtonResource";

    private ButtonController myButtonController;
    private ResourceBundle resourceBundle;


    public ButtonCreator(DisplayScreen displayScreen) {
        resourceBundle = ResourceBundle.getBundle(RESOURCE_PATH);
        myButtonController = new ButtonController("English", displayScreen);
        GridPane buttons = new GridPane();
        buttons.setPadding(new Insets(INSET_PADDING, INSET_PADDING, INSET_PADDING, INSET_PADDING));
        createButtons();
        setSpacing(SPACING);
        setLayoutY(Y_LAYOUT);
        setLayoutX(X_LAYOUT);
    }

    public boolean isStartButtonClicked() {
        return false;
    }

    public boolean isClearButtonClicked() { return false;}

    public boolean isNewButtonClicked() { return false;}

    private void createButtons() {
        for(String key : Collections.list(resourceBundle.getKeys())) {
            Button button = new Button(key);
            button.setOnAction(e -> callAction(key));
            button.setPrefHeight(getHeight());
            getChildren().add(button);
        }
    }

    private void callAction(String key) {
        try {
            String methodName = resourceBundle.getString(key).split(",")[0];
            Method m = myButtonController.getClass().getDeclaredMethod(methodName, String.class);
            m.invoke(myButtonController, key);
        } catch (Exception e) {
            /**
             * TODO: action for exception
             */
        }
    }
}
