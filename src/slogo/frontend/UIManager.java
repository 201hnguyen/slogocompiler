package slogo.frontend;

import slogo.frontend.creater.ChangeableNode;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.HashMap;

/**
 * Holds all the ChangeableNode instances in the Visualization, extracts the changed data by user action on the
 * UI elements, and makes UI update language.
 * @author Eric Han
 *
 * Why I chose this class: I chose this class as it clearly demonstrates why the use of ChangeableNode interface and reflection pattern
 * greatly increased the flexbility of the code. In the update() method, all the information from the different ChangeableNode instances
 * get stored in changedValues just by iterating through the instances and calling getChangedValues() method. Then, by using reflection,
 * the corresponding method in UIController class is called without using switch statements. Then, in the updateLanguage(String language)
 * method, the UIManager can make each ChangeableNode interface to change its language setting just by calling the setLanguage(String language)
 * method, without any need to know how that is implemented within each instances.
 */
public class UIManager {
    private static final String RESOURCE_PATH = "resources.frontend.UIControllerResource";
    private static final String DEFAULT_LANGUAGE = "English";
    private static final String NOT_FOUND = " method not found";

    private ResourceBundle resourceBundle = ResourceBundle.getBundle(RESOURCE_PATH);
    private List<ChangeableNode> changeableNodes = new ArrayList<>();
    private UIController myUIController;
    private Map<String, String> changedValues = new HashMap<>();
    private String language = DEFAULT_LANGUAGE;

    public UIManager(CommandLine commandLine, List<ChangeableNode> changeableNodes) {
        myUIController = new UIController(commandLine);
        this.changeableNodes.addAll(changeableNodes);
    }
    /**
     * Makes the UIManager update the UI if anything has to be changed.
     */
    public void update() {
        for(ChangeableNode changeableNode : changeableNodes) {
            changedValues.putAll(changeableNode.getChangedValues());
        }
        if(changedValues.size() != 0) {
            for (Map.Entry<String, String> entry : changedValues.entrySet()) {
                try {
                    Method m = myUIController.getClass().getDeclaredMethod(resourceBundle.getString(entry.getKey()), String.class);
                    m.invoke(myUIController, entry.getValue());
                } catch (Exception e) {
                    ErrorShow errorShow = new ErrorShow(e, resourceBundle.getString(entry.getKey()) + NOT_FOUND);
                    errorShow.show();
                }
            }
        }
        changedValues.clear();
        updateLanguage(myUIController.getLanguage());
    }
    /**
     * Called by Visualization to get data from commandline.
     */
    public String getInput() { return myUIController.getInput(); }
    /**
     * true if new button is clicked.
     */
    public boolean isNewButtonClicked() { return myUIController.isNewButtonClicked(); }
    /**
     * returns the chosen language
     */
    public String getLanguage() {
        return language;
    }
    /**
     * returns the information that the visualization will use to implement changes to the UI (changes that are not dealt directly in UIController)
     */
    public Map<String, Double> getChangedVariables() {return myUIController.getChangedVariables();}

    private void updateLanguage(String language) {
        if(!this.language.equals(language)) {
            for(ChangeableNode changeableNode : changeableNodes) {
                changeableNode.setLanguage(language);
            }
            this.language = language;
        }
    }
}