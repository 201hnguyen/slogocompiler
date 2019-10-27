package slogo.frontend.reference;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.Collections;
import java.util.ResourceBundle;

public class ReferenceWindow {
    private static final String RESOURCE_PATH = "resources.commandreference.CommandTypes";
    private static final String TITLE = "Command References";
    private static final double WIDTH = 2000;
    private static final double HEIGHT = 2000;
    private static final double XPOS = 1000;
    private static final double YPOS = 1500;

    private ResourceBundle resourceBundle;

    public ReferenceWindow() {
        resourceBundle = ResourceBundle.getBundle(RESOURCE_PATH);
    }

    public void createNewWindow() {
        Stage newStage = new Stage();
        newStage.setTitle(TITLE);
        newStage.setWidth(WIDTH);
        newStage.setHeight(HEIGHT);
        newStage.setX(newStage.getMaxWidth()/2 - newStage.getWidth()/2);
        newStage.show();
    }

    private HBox createReference() {
        HBox hBox = new HBox();
        for(String key : Collections.list(resourceBundle.getKeys())) {
            Label label = new Label(key);
            label.setUnderline(true);
        }
        return hBox;
    }
}
