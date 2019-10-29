package slogo.frontend;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.regex.Pattern;

public class VariableScreen {

    private static final String INTRO_VARIABLE = "Set value of ";
    private static final String DECIMAL_PATTERN = "-?[0-9]+\\.?[0-9]*";

    private ChangedVariable changedVariable;
    private TextField inputText;
    private String variableName;
    private String value;
    private Stage stage;

    public VariableScreen(ChangedVariable changedVariable, String variable) {
        this.changedVariable = changedVariable;
        String[] variableArr = variable.split(" = ");
        variableName = variableArr[0];
        value = variableArr[1];
    }

    public void createNewStage() {
        stage = new Stage();
        VBox vBox = new VBox();
        stage.setScene(new Scene(vBox));
        vBox.getChildren().add(new Label(INTRO_VARIABLE + variableName));
        inputText = new TextField(value);
        vBox.getChildren().addAll(inputText, createButton());
        stage.show();
    }

    private Button createButton() {
        Button button = new Button("Confirm");
        button.setOnAction(e -> buttonAction());
        return button;
    }

    private void buttonAction() {
        if(isThisStringDouble(inputText.getText())) {
            changedVariable.setChangedVariable(variableName + " = " + inputText.getText());
            stage.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Value needs to be a double!");
            alert.setHeaderText("Wrong type of value!");
            alert.show();
        }
    }

    private boolean isThisStringDouble(String value) {
        return Pattern.matches(DECIMAL_PATTERN, value);
    }
}
