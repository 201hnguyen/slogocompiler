package slogo.frontend.controller;

import slogo.frontend.turtlescreen.DisplayScreen;

import java.util.HashMap;
import java.util.Map;

public class TabController implements NodeController {

    private DisplayScreen displayScreen;
    private Map<String, String> changedValues = new HashMap<>();

    public TabController(DisplayScreen displayScreen) {
        this.displayScreen = displayScreen;
    }

    public void variableAction(String key, String content) {
        System.out.println(content + "sdfsdfs");
    }

    public void historyAction(String key, String content) {
        changedValues.put(key, content);
    }

    public void functionsAction(String key, String content) {

    }

    @Override
    public Map<String, String> getChangedValues() {
        Map<String, String> map = new HashMap<>();
        map.putAll(changedValues);
        changedValues.clear();
        return map;
    }

    @Override
    public void setLanguage(String language) {
        return;
    }
}
