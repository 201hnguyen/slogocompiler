package slogo.frontend;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextFlow;

public class TabMaker extends VBox {
    private TextFlow historyField;
    private TextFlow variableField;
    private ScrollMaker historyPane = new ScrollMaker();
    private ScrollMaker variablePane = new ScrollMaker();

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

    TextFlow getHistory() { return historyField; }
    TextFlow getVariable() { return variableField; }
}
