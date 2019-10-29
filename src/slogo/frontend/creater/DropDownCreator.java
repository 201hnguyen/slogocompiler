package slogo.frontend.creater;

import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import slogo.frontend.turtlescreen.DisplayScreen;
import slogo.frontend.controller.DropDownController;

import java.lang.reflect.Method;
import java.util.*;

public class DropDownCreator extends HBox implements ChangeableNode{
    private static final double DROP_WIDTH = 330;
    private static final double DROP_HEIGHT = 40;
    private static final double SPACING = 15;
    private static final String DROPDOWN_RESOURCE = "resources.frontend.DropDownResource";
    private static final String PATH = "resources.frontend.dropdown.";

    private List<ComboBox> myComboBoxes = new ArrayList<>();
    private ResourceBundle resourceBundle;
    private DropDownController myController;

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
        //
    }

    private void createDropDown() {
        for(String key : Collections.list(resourceBundle.getKeys())) {
            ComboBox dropdown = new ComboBox();
            dropdown.setId(key);
            myComboBoxes.add(dropdown);
            ResourceBundle dropDownResource = ResourceBundle.getBundle(PATH + key);

            List<String> list = new ArrayList<>();
            for(String item : Collections.list(dropDownResource.getKeys())) {
                list.add(item);
            }
            dropdown.setItems(FXCollections.observableArrayList(list));
            dropdown.setPromptText(key);
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
}
