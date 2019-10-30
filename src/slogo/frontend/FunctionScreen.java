package slogo.frontend;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import slogo.frontend.statusscreen.ChangedString;

import java.util.regex.Pattern;

public class FunctionScreen {

    private static final String INTRO_FUNCTION = "Set parameters of ";
    private static final String DECIMAL_PATTERN = "-?[0-9]+\\.?[0-9]*";

    private ChangedString changedString;
    private String functionName;
    private Stage stage;
    private TextField inputText;
    private int requiredLength;

    public FunctionScreen(ChangedString changedString, String function) {
        this.changedString = changedString;
        functionName = function.split(" ")[0];
        requiredLength = function.split(" ").length-1;
    }

    public void createNewStage() {
        stage = new Stage();
        VBox vBox = new VBox();
        stage.setScene(new Scene(vBox));
        vBox.getChildren().add(new Label(INTRO_FUNCTION + functionName));
        inputText = new TextField();
        vBox.getChildren().addAll(inputText, createButton());
        stage.show();
    }

    private Button createButton() {
        Button button = new Button("Confirm");
        button.setOnAction(e -> buttonAction());
        return button;
    }

    private void buttonAction() {
        String str = inputText.getText();
        String[] arr = str.split(" ");

        if(checkRightString(arr)) {
            changedString.setChangedVariable(functionName + " " + str);
        }
    }

    private boolean checkRightString(String[] arr) {
        if(arr.length != requiredLength) {
            alertShow("Need " + requiredLength + " parameters!");
            return false;
        }
        for(String str : arr) {
            if(!isThisStringDouble(str)) {
                alertShow("All parameters must be a number! " + str + " is not a number!");
                return false;
            }
        }
        return true;
    }

    private boolean isThisStringDouble(String value) {
        return Pattern.matches(DECIMAL_PATTERN, value);
    }

    private void alertShow(String mssg) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText(mssg);
        alert.show();
    }

}
