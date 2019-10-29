package slogo.frontend;

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
    private List<String> myTabs = new ArrayList<>();
    private static final String RESOURCE_PATH = "resources.frontend.TabsResource";
    private static final String LANGUAGE_INDEX_PATH = "resources.frontend.changingfeature.LanguageIndex";
    private static final String SCROLL_NAME_PATH = "resources.frontend.changingfeature.ScrollPaneNames";

    private VBox inputHolder = new VBox();
    private String language = "English";

    public TabMaker() {
        setLayoutX(LAYOUT_X);
        setLayoutY(LAYOUT_Y);
        createTabPane();
    }

    public void createTabPane() {
        getChildren().clear();
        ResourceBundle resourceBundle = ResourceBundle.getBundle(RESOURCE_PATH);
        for (String key : Collections.list(resourceBundle.getKeys())) {
            VBox vBox = new VBox();
            Label label = new Label(getScrollPaneName(key));
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

    private String getScrollPaneName(String key) {
        ResourceBundle languageBundle = ResourceBundle.getBundle(LANGUAGE_INDEX_PATH);
        int index = Integer.parseInt(languageBundle.getString(language));
        ResourceBundle scrollNameBundle = ResourceBundle.getBundle(SCROLL_NAME_PATH);
        return scrollNameBundle.getString(key).split(",")[index];
    }

    @Override
    public Map<String, String> getChangedValues() {
        return new HashMap<>();
    }

    @Override
    public void setLanguage(String language) {
        this.language = language;
        createTabPane();
    }
}
