package slogo.frontend;

import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class TabMaker extends VBox {
    private static final double LAYOUT_X = 600;
    private static final double LAYOUT_Y = 60;
    private List<String> myTabs = new ArrayList<>();
    private ResourceBundle resourceBundle;
    private static final String RESOURCE_PATH = "resources.frontend.TabsResource";
    private VBox inputHolder = new VBox();

    public TabMaker() {
        setLayoutX(LAYOUT_X);
        setLayoutY(LAYOUT_Y);
        createTabPane();
    }

    public void createTabPane() {
        resourceBundle = ResourceBundle.getBundle(RESOURCE_PATH);
        for (String key : Collections.list(resourceBundle.getKeys())) {
            VBox vBox = new VBox();
            Label label = new Label(key);
            ScrollMaker scrollMaker = new ScrollMaker(key);
            scrollMaker.setContent(label);
            scrollMaker.setId(key);
            myTabs.add(key);
            scrollMaker.setContent(inputHolder);
            vBox.getChildren().addAll(label,scrollMaker);
            getChildren().addAll(vBox);
        }
    }

    public void addHistory(Text command) {
        for (String tab : myTabs) {
            if (tab.equals("History")) {
                inputHolder.getChildren().add(command);
            }
        }
    }
}
