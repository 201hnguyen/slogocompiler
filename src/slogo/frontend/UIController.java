package slogo.frontend;

import javafx.scene.text.Text;

import java.util.HashMap;
import java.util.Map;

public class UIController {

    private CommandLine commandLine;
    private boolean startButtonClicked = false;
    private boolean newButtonClicked = false;
    private String language = "English";
    private Map<String, Double> changedVariables = new HashMap<>();

    public UIController(CommandLine commandLine) {
        this.commandLine = commandLine;
    }

    public void startAction(String key) {
        startButtonClicked = true;
    }

    public void clearAction(String key) {
        commandLine.getCommand().clear();
    }

    public void newWindowAction(String key) {
        newButtonClicked = true;
    }

    public void languageAction(String language) {
        this.language = language;
    }

    public void historyAction(String content) { commandLine.getCommand().setText(content);}

    public void variablesAction(String variable) {
        String[] variableArr = variable.split(" = ");
        changedVariables.put(variableArr[0], Double.parseDouble(variableArr[1]));
    }

    public String getInput() {
        String command = commandLine.getCommand().getText();
        if(startButtonClicked && !command.equals("")) {
            startButtonClicked = false;
            Text readerText = new Text(command + "\n");
            return readerText.getText();
        }
        return "";
    }

    public boolean isNewButtonClicked() {
        boolean bool = newButtonClicked;
        newButtonClicked = false;
        return bool;
    }

    public String getLanguage() {
        return language;
    }

    public Map<String, Double> getChangedVariables() {
        Map<String, Double> map  = new HashMap<>();
        map.putAll(changedVariables);
        changedVariables.clear();
        return map;
    }
}
