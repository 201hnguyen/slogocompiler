package slogo.frontend.statusscreen;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import slogo.frontend.creater.ChangeableNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.HashMap;
import java.util.Map;


public class TabMaker extends VBox implements ChangeableNode {
    private static final double LAYOUT_X = 600;
    private static final double LAYOUT_Y = 60;
    private static final String RESOURCE_PATH = "resources.frontend.TabsResource";
    private static final String LANGUAGE_INDEX_PATH = "resources.frontend.changingfeature.LanguageIndex";
    private static final String SCROLL_NAME_PATH = "resources.frontend.changingfeature.ScrollPaneNames";

    private List<ScrollMaker> myScrolls = new ArrayList<>();
    private String language = "English";

    public TabMaker() {
        setLayoutX(LAYOUT_X);
        setLayoutY(LAYOUT_Y);
        createTabPane();
    }

    public void addHistory(Text command) {
        for (ScrollMaker scrollMaker : myScrolls) {
            if (scrollMaker.getKey().equals("History")) {
                scrollMaker.addText(command);
            }
        }
    }

    public void setVariables(Map<String, Double> variables) {
        for (ScrollMaker scrollMaker : myScrolls) {
            if (!scrollMaker.getKey().equals("Variables")) {
                continue;
            }
            scrollMaker.clearAll();
            for (Map.Entry<String, Double> entry : variables.entrySet()) {
                scrollMaker.addText(new Text(entry.getKey() + " = " + entry.getValue() + "\n"));
            }
        }
    }

    @Override
    public Map<String, String> getChangedValues() {
        return new HashMap<>();
    }

    @Override
    public void setLanguage(String language) {
        this.language = language;
        for(ScrollMaker scroll : myScrolls) {
            scroll.setLabelText(getScrollPaneName(scroll.getKey()));
        }
    }

    private void createTabPane() {
        getChildren().clear();
        ResourceBundle resourceBundle = ResourceBundle.getBundle(RESOURCE_PATH);
        for (String key : Collections.list(resourceBundle.getKeys())) {
            ScrollMaker myScroll = new ScrollMaker(key);
            getChildren().addAll(myScroll);
            myScroll.setLabelText(getScrollPaneName(key));
            myScrolls.add(myScroll);
        }
    }
    
    private String getScrollPaneName(String key) {
        ResourceBundle languageBundle = ResourceBundle.getBundle(LANGUAGE_INDEX_PATH);
        int index = Integer.parseInt(languageBundle.getString(language));
        ResourceBundle scrollNameBundle = ResourceBundle.getBundle(SCROLL_NAME_PATH);
        return scrollNameBundle.getString(key).split(",")[index];
    }
}
