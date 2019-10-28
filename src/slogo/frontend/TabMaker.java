package slogo.frontend;

import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.util.Collections;
import java.util.ResourceBundle;

public class TabMaker extends VBox {
    private TextFlow historyField;
    private TextFlow variableField;
    private ScrollMaker historyPane = new ScrollMaker();
    private ScrollMaker variablePane = new ScrollMaker();
    private ResourceBundle resourceBundle;

    public TabMaker() {
        setLayoutX(600);
        setLayoutY(200);
        getChildren().addAll(createTabPane());
    }

    public TabPane createTabPane() {
        TabPane tabPane = new TabPane();

        historyField = new TextFlow();
        variableField = new TextFlow();
        historyPane.setContent(historyField);
        variablePane.setContent(variableField);

        Tab historyTab = new Tab("History");
        Tab variableTab = new Tab("Variables");

        historyTab.setContent(historyPane);

        tabPane.getTabs().addAll(historyTab, variableTab);
        tabPane. setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        return tabPane;

    }

/*    public void createTabs() {
            for(String key : Collections.list(resourceBundle.getKeys())) {
                Tab tab = new Tab(key);
            }
        }

    private void callAction(String key) {
    }*/


    TextFlow getHistory() { return historyField; }
    TextFlow getVariable() { return variableField; }
}
