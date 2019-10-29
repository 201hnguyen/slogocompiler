package slogo.frontend;

import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class TabMaker extends VBox {
    private TextFlow historyField;
    private TextFlow variableField;
    private List<String> myTabs = new ArrayList<String>();
    private ResourceBundle resourceBundle;
    private static final String RESOURCE_PATH = "resources.frontend.TabsResource";
    private TabPane tabPane = new TabPane();
    private VBox vbox = new VBox();

    public TabMaker() {
        setLayoutX(600);
        setLayoutY(60);
        createTabPane();
    }

    public void createTabPane() {
        resourceBundle = ResourceBundle.getBundle(RESOURCE_PATH);
        for (String key : Collections.list(resourceBundle.getKeys())) {
            ScrollMaker scrollMaker = new ScrollMaker(key);
            scrollMaker.setId(key);
            myTabs.add(key);
            scrollMaker.setContent(vbox);
            getChildren().addAll(scrollMaker);
        }
    }

    public void addHistory(Text command) {
        for (String tab : myTabs) {
            if (tab.equals("History")) {
                vbox.getChildren().add(command);
            }
        }




    }
}
