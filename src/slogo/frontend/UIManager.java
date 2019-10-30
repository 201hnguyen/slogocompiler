package slogo.frontend;

import slogo.frontend.creater.ChangeableNode;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.HashMap;

public class UIManager {

    private static final String RESOURCE_PATH = "resources.frontend.UIControllerResource";

    private ResourceBundle resourceBundle = ResourceBundle.getBundle(RESOURCE_PATH);
    private List<ChangeableNode> changeableNodes = new ArrayList<>();
    private UIController myUIController;
    private Map<String, String> changedValues = new HashMap<>();
    private String language = "English";

    public UIManager(CommandLine commandLine, List<ChangeableNode> changeableNodes) {
        myUIController = new UIController(commandLine);
        this.changeableNodes.addAll(changeableNodes);
    }

    public void update() {
        for(ChangeableNode changeableNode : changeableNodes) {
            changedValues.putAll(changeableNode.getChangedValues());
        }
        if(changedValues.size() == 0) {
            return;
        }
        for(Map.Entry<String, String> entry : changedValues.entrySet()) {
            if(entry.getKey().equals("Functions")) {
                System.out.println("FOUND FOUND");
            }
            try {
                Method m = myUIController.getClass().getDeclaredMethod(resourceBundle.getString(entry.getKey()), String.class);
                m.invoke(myUIController, entry.getValue());
            } catch (Exception e) {
                ErrorShow errorShow = new ErrorShow(e, resourceBundle.getString(entry.getKey()) + " method not found");
                errorShow.show();
            }
        }
        changedValues.clear();
        updateLanguage(myUIController.getLanguage());
    }

    public String getInput() {
        return myUIController.getInput();
    }

    public boolean isNewButtonClicked() {
        return myUIController.isNewButtonClicked();
    }

    private void updateLanguage(String language) {
        if(!this.language.equals(language)) {
            for(ChangeableNode changeableNode : changeableNodes) {
                changeableNode.setLanguage(language);
            }
            this.language = language;
        }
    }

    public String getLanguage() {
        return language;
    }

    public Map<String, Double> getChangedVariables() {return myUIController.getChangedVariables();}
}
