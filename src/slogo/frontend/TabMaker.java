package slogo.frontend;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.text.TextFlow;

public class TabMaker extends TabPane {
    private TextFlow historyField;
    private TextFlow variableField;
    private ScrollMaker historyPane = new ScrollMaker();
    private ScrollMaker variablePane = new ScrollMaker();

    public TabMaker() {
        historyField = new TextFlow();
        variableField = new TextFlow();
        historyPane.setContent(historyField);
        variablePane.setContent(variableField);
        Tab historyTab = new Tab("History");
        Tab variableTab = new Tab("Variables");
        historyTab.setContent(historyPane);
        variableTab.setContent(variablePane);
        getTabs().addAll(historyTab, variableTab);
        setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

    }

    TextFlow getHistory() { return historyField; }
    TextFlow getVariable() { return variableField; }
}
