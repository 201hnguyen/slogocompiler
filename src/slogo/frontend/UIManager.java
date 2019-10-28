package slogo.frontend;

import java.lang.reflect.Method;
import java.util.*;

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

        for(String key : changedValues.keySet()) {
            System.out.println(key + ", " + changedValues.get(key));
            try {
                Method m = myUIController.getClass().getDeclaredMethod(resourceBundle.getString(key), String.class);
                m.invoke(myUIController, changedValues.get(key));
            } catch (Exception e) {
                e.printStackTrace();
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
}
