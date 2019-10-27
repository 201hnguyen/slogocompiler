package slogo.frontend;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class DropDownCreator extends HBox {
    private static final double DROP_WIDTH = 165;
    private static final double DROP_HEIGHT = 40;
    private ResourceBundle resourceBundle;

    public DropDownCreator() {
        setPrefSize(DROP_WIDTH, DROP_HEIGHT);
    }

    private void createDropDown() {
        List<String> list = new ArrayList<>();
        for(String key : Collections.list(resourceBundle.getKeys())) {
            ComboBox dropdown = new ComboBox();
            //Make another resourceBundle file.
            String[] dropDownArr = listToArray(list);
            //dropdown.setOnAction(e -> callAction(key));
            getChildren().add(dropdown);
            list.add(key);
        }
    }

    private void callAction(String key) {
    }

    private String[] listToArray(List<String> list) {
        String[] arr = new String[list.size()];
        for(int i=0; i<arr.length; i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }

}
