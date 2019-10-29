package slogo.frontend.controller;

import slogo.frontend.ChangedVariable;
import slogo.frontend.VariableScreen;
import slogo.frontend.turtlescreen.DisplayScreen;

import java.util.HashMap;
import java.util.Map;

public class TabController implements NodeController {

    private DisplayScreen displayScreen;
    private String variableKey;
    private ChangedVariable myChangedVariable = new ChangedVariable();
    private Map<String, String> changedValues = new HashMap<>();

    public TabController(DisplayScreen displayScreen) {
        this.displayScreen = displayScreen;
    }

    public void variableAction(String key, String content) {
        variableKey = key;
        myChangedVariable = new ChangedVariable();
        VariableScreen variableScreen = new VariableScreen(myChangedVariable, content);
        variableScreen.createNewStage();
    }

    public void historyAction(String key, String content) {
        changedValues.put(key, content);
    }

    public void functionsAction(String key, String content) {

    }

    @Override
    public Map<String, String> getChangedValues() {
        checkChangedVariables();
        Map<String, String> map = new HashMap<>();
        map.putAll(changedValues);
        changedValues.clear();
        return map;
    }

    @Override
    public void setLanguage(String language) {
        return;
    }

    private void checkChangedVariables() {
        String variable = myChangedVariable.getChangedVariable();
        if(variable!="") {
            changedValues.put(variableKey, variable);
            System.out.println(variableKey + ", " + variable);
        }
    }
}
