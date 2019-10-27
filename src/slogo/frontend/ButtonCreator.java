package slogo.frontend;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.ResourceBundle;

public class ButtonCreator extends HBox {
    private static final double INSET_PADDING = 10;
    private static final double SPACING = 25;
    private static final double Y_LAYOUT = 430;
    private static final double X_LAYOUT = 20;
    private static final String RESOURCE_PATH = "resources.frontend.ButtonResource";

    private ButtonController myButtonController = new ButtonController();
    private ResourceBundle resourceBundle;


    public ButtonCreator() {
        resourceBundle = ResourceBundle.getBundle(RESOURCE_PATH);
        GridPane buttons = new GridPane();
        buttons.setPadding(new Insets(INSET_PADDING, INSET_PADDING, INSET_PADDING, INSET_PADDING));
        createButtons();
        setSpacing(SPACING);
        setLayoutY(Y_LAYOUT);
        setLayoutX(X_LAYOUT);
    }

    public boolean isStartButtonClicked() {
        return myButtonController.isStartButtonClicked();
    }

    public boolean isClearButtonClicked() { return myButtonController.isClearButtonClicked();}

    private void createButtons() {
        for(String key : Collections.list(resourceBundle.getKeys())) {
            Button button = new Button(key);
            button.setOnAction(e -> callAction(key));
            getChildren().add(button);
        }
    }

    private void callAction(String key) {
        try {
            String methodName = resourceBundle.getString(key);
            Method m = myButtonController.getClass().getDeclaredMethod(methodName);
            m.invoke(myButtonController);
        } catch (Exception e) {
            /**
             * TODO: action for exception
             */
        }
    }
}
