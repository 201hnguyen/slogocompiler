package slogo.frontend;

import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class DropDownCreator extends HBox {
    private static final double DROP_WIDTH = 165;
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
            dropdown.setPromptText(list.get(0));
            getChildren().add(dropdown);
            dropdown.setOnAction(e -> callAction(key, dropdown.getValue().toString()));
        }
    }

    private void callAction(String comboBoxId, String key) {
        System.out.println(key);
        String methodName = resourceBundle.getString(comboBoxId);
        try {
            Method m = myController.getClass().getDeclaredMethod(methodName, String.class);
            m.invoke(myController, key);
        } catch (Exception e) {

        }
    }

    public String getLanguage() {
        return myController.getLanguage();
    }
}
