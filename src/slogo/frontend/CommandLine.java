package slogo.frontend;

import javafx.scene.control.TextArea;
import javafx.scene.layout.TilePane;


public class CommandLine extends TilePane {
    private static final double WIDTH = 600;
    private static final double HEIGHT = 100;
    private static final double LAYOUT = 500;
    private TextArea inputField;



    public CommandLine() {
        inputField = new TextArea();
        inputField.setPrefSize(WIDTH, HEIGHT);
        getChildren().addAll(inputField);
        setLayoutY(LAYOUT);
    }

    public TextArea getCommand() {
        return inputField;
    }
}
