package slogo.frontend.creater;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import slogo.frontend.turtlescreen.DisplayScreen;
import slogo.frontend.controller.ButtonController;
import slogo.frontend.controller.NodeController;

import java.lang.reflect.Method;
import java.util.*;

public class ButtonCreator extends HBox implements ChangeableNode {
    private static final double INSET_PADDING = 10;
    private static final double SPACING = 25;
    private static final double Y_LAYOUT = 430;
    private static final double X_LAYOUT = 20;
    private static final String RESOURCE_PATH = "resources.frontend.ButtonResource";
    private static final String LANGUAGE_INDEX_PATH = "resources.frontend.changingfeature.LanguageIndex";
    private static final String BUTTON_NAMES = "resources.frontend.changingfeature.ButtonNames";

    private NodeController myButtonController;
    private ResourceBundle resourceBundle;
    private String language = "English";
    private ResourceBundle languageBundle = ResourceBundle.getBundle(LANGUAGE_INDEX_PATH);
    private ResourceBundle buttonNameBundle = ResourceBundle.getBundle(BUTTON_NAMES);


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

    public void setButtonCreator(NodeController nodeController) {
        myButtonController = nodeController;
    }

    public Map<String, String> getChangedValues() {
        Map<String, String> map = new HashMap<>();
        map.putAll(myButtonController.getChangedValues());
        return map;
    }

    @Override
    public void setLanguage(String language) {
        this.language = language;
        createButtons();
    }


    private void createButtons() {
        getChildren().clear();
        for(String key : Collections.list(resourceBundle.getKeys())) {
            Button button = new Button(getContentOfButton(key));
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

    private String getContentOfButton(String key) {
        int index = Integer.parseInt(languageBundle.getString(language));
        return buttonNameBundle.getString(key).split(",")[index];
    }
}
