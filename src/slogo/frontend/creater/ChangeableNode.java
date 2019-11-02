package slogo.frontend.creater;
import java.util.Map;
/**
 * purpose: To group all the sections of the UI that should change according to the language, and can affect other parts of the UI
 * Ex. ChangeableNode node = new ButtnoCreater(new ButtonController(displayScreen));
 * node.setLanguage("Chinese");
 * @author Eric Han
 *
 *  I selected this class because I believed that letting the UI features change its language was possible because
 *  for subclass cells to implement the rules. The only public methods needed for the subclasses are the public method
 *  of this class, except for CellWithInfo class, which defines additional public methods.
 */
public interface ChangeableNode {

    /** purpose : return the information that the UIManager will use to change UI
     * @return the map that holds the values in UI to be changed
     */
    Map<String, String> getChangedValues();

    /**Sets the language of the ChangeableNode
     * @param language = the language
     */
    void setLanguage(String language);
}