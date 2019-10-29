package slogo.frontend;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextFlow;

import java.util.Collections;
import java.util.ResourceBundle;

public class TabMaker extends VBox {
    private TextFlow historyField;
    private TextFlow variableField;
    private ScrollMaker historyPane = new ScrollMaker();
    private ScrollMaker variablePane = new ScrollMaker();
    private ResourceBundle resourceBundle;
    private static final String RESOURCE_PATH = "resources.frontend.TabsResource";
    private TabPane tabPane = new TabPane();

    public TabMaker() {
        setLayoutX(600);
        setLayoutY(200);
        createTabPane();
    }

    public void createTabPane() {
        resourceBundle = ResourceBundle.getBundle(RESOURCE_PATH);
        for (String key : Collections.list(resourceBundle.getKeys())) {
            Tab tab = new Tab(key);
            TextFlow textFlow = new TextFlow();
            ScrollMaker scrollMaker = new ScrollMaker();
            scrollMaker.setContent(textFlow);
            tab.setContent(scrollMaker);
            tabPane.getTabs().addAll(tab);
        }
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        getChildren().addAll(tabPane);
    }
}
