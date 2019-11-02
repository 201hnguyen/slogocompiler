package slogo.frontend.controller;
import java.util.Map;

/**
 * purpose: Controller used in the ChangeableNode
 *
 * @author Eric Han
 * Why I chose this class : This class shows the behaviours that the Controllers used for the ChangeableNodes commonly implement
 */
public interface NodeController {
    /**
     * purpose : return the information that the UIManager will use to change UI
     * @return the map that holds the values in UI to be changed
     */
    Map<String, String> getChangedValues();

    /**
     * purpose : set the language of the controller
     */
    void setLanguage(String language);
}