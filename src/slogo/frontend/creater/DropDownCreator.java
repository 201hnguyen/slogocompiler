package slogo.frontend.creater;
import java.util.Map;
import javafx.collections.FXCollections;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import slogo.frontend.turtlescreen.DisplayScreen;
import slogo.frontend.controller.DropDownController;
import java.util.ResourceBundle;

public class DropDownCreator extends HBox implements ChangeableNode{
    private static final double DROP_WIDTH = 330;
    private static final double DROP_HEIGHT = 40;
    private static final double SPACING = 15;
    private static final String DROPDOWN_RESOURCE = "resources.frontend.DropDownResource";
    private static final String PATH = "resources.frontend.dropdown.";
    private static final String LANGUAGE_INDEX_PATH = "resources.frontend.changingfeature.LanguageIndex";
    private static final String DROP_DOWN_NAMES = "resources.frontend.changingfeature.DropDownNames";

    private ResourceBundle resourceBundle;
    private DropDownController myController;
    private String language = "English";

    public DropDownCreator(DisplayScreen displayScreen) {
        resourceBundle = ResourceBundle.getBundle(DROPDOWN_RESOURCE);
        myController = new DropDownController(displayScreen);
        setPrefSize(DROP_WIDTH, DROP_HEIGHT);
        createDropDown();
        setSpacing(SPACING);
    }

    public Map<String, String> getChangedValues() {
        return myController.getChangedValues();
    }

    @Override
    public void setLanguage(String language) {
        this.language = language;
        createDropDown();
    }

    private void createDropDown() {
        getChildren().clear();
        for(String key : Collections.list(resourceBundle.getKeys())) {
            ComboBox dropdown = new ComboBox();
            ResourceBundle dropDownResource = ResourceBundle.getBundle(PATH + key);

            List<String> list = new ArrayList<>();
            for(String item : Collections.list(dropDownResource.getKeys())) {
                list.add(item);
            }
            dropdown.setItems(FXCollections.observableArrayList(list));
            System.out.println(getContentOfDropDown(key));
            dropdown.setPromptText(getContentOfDropDown(key));
            getChildren().add(dropdown);
            dropdown.setOnAction(e -> callAction(key, dropdown.getValue().toString()));
        }
    }

    private void callAction(String comboBoxId, String key) {
        System.out.println(comboBoxId + ", " + key);
        String methodName = resourceBundle.getString(comboBoxId);
        System.out.println(methodName);
        try {
            Method m = myController.getClass().getDeclaredMethod(methodName, String.class, String.class);
            m.invoke(myController, comboBoxId, key);
        } catch (Exception e) {
        }
    }

    private String getContentOfDropDown(String key) {
        ResourceBundle languageBundle = ResourceBundle.getBundle(LANGUAGE_INDEX_PATH);
        int index = Integer.parseInt(languageBundle.getString(language));
        ResourceBundle dropDownBundle = ResourceBundle.getBundle(DROP_DOWN_NAMES);
        return dropDownBundle.getString(key).split(",")[index];
    }
}
