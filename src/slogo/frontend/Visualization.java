package slogo.frontend;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.util.ArrayList;

public class Visualization {
    private static final double BUTTON_WIDTH = 350;
    private static final double BUTTON_HEIGHT = 50;
    private static final double DROP_WIDTH = 100;
    private static final double DROP_HEIGHT = 40;
    private static final double SCENE_WIDTH = 800;
    private static final double SCENE_HEIGHT = 600;
    private static final double INPUT_HEIGHT = 99;
    private static final double INPUT_WIDTH = 600;
    private static final double INSET_PADDING = 10;
    private static final double CIRCLE_RADIUS = 50;


    private String[] languageList;
    private String[] imageList;
    private Scene scene;
    private AnchorPane root;
    private Button startButton;
    private Button clearButton;
    private HBox buttonBox;
    private HBox checkBoxes;
    private ComboBox languageDropDown;
    private ComboBox imageDropDown;
    private ColorPicker colorPicker;
    private Circle colorCircle;
    private CheckBox penBox;
    private CheckBox backgroundBox;
    private TextArea inputField;
    private TextFlow historyField;



    private DisplayScreen displayScreen = new DisplayScreen();

    public Visualization() {
        startButton = startButton();
        clearButton = clearButton();
        languageDropDown = dropDown(languageList = new String[]{"1", "2", "3"});
        imageDropDown = dropDown(imageList = new String[]{"1", "2", "3"});
        root = new AnchorPane();
        root.getChildren().addAll(displayScreen, historyTracker(), buttons(), commandBox(), colorPalette(), checkBoxes());
        scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);

    }

    private ComboBox dropDown (String[] list){
        ComboBox comboBox = new ComboBox(FXCollections.observableArrayList(list));
        comboBox.setPrefSize(DROP_WIDTH, DROP_HEIGHT);
        return comboBox;
    }

    private TilePane commandBox() {
        inputField = new TextArea("Enter your command here!");
        inputField.setPrefSize(INPUT_WIDTH,INPUT_HEIGHT);
        TilePane inputBox = new TilePane();
        inputBox.getChildren().addAll(inputField);
        inputBox.setLayoutY(500);
        return inputBox;
    }

    private TextFlow historyTracker() {
        historyField = new TextFlow();
        historyField.setLayoutX(620);
        historyField.setLayoutY(10);
        return historyField;
    }

    private HBox buttons() {
        GridPane buttons = new GridPane();
        buttons.setPadding(new Insets(INSET_PADDING, INSET_PADDING, INSET_PADDING, INSET_PADDING));
        buttonBox = new HBox();
        buttonBox.getChildren().addAll(startButton,languageDropDown, clearButton, imageDropDown);
        buttonBox.setSpacing(25);
        buttonBox.setLayoutY(430);
        buttonBox.setLayoutX(70);
        return buttonBox;
    }

    private Button startButton() {
        Button button = new Button("Start");
        button.setMaxSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        button.setOnAction(event -> {
            historyField.getChildren().add(new Text(inputField.getText() + "\n"));
           // historyField.getChildren().add(new Text(inputField.getText()));

        });
        return button;
    }

    private Button clearButton() {
        Button button = new Button("Clear");
        button.setMaxSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        button.setOnAction(event -> {
            inputField.clear();
        });
        return button;
    }

    private HBox checkBoxes() {
        checkBoxes = new HBox();
        penBox = new CheckBox("Pen");
        backgroundBox = new CheckBox ("Background");
        checkBoxes.getChildren().addAll(penBox,backgroundBox);
        checkBoxes.setLayoutY(570);
        checkBoxes.setLayoutX(620);
        checkBoxes.setSpacing(10);
        return checkBoxes;
    }

    private VBox colorPalette() {
        VBox palette = new VBox();
        colorPicker = new ColorPicker();
        colorPicker.setMaxSize(DROP_WIDTH,DROP_HEIGHT);
        colorCircle = new Circle(CIRCLE_RADIUS);
        colorCircle.setFill(colorPicker.getValue());
        displayScreen.setBackground(colorPicker.getValue());

        colorPicker.setOnAction(event -> {
            colorCircle.setFill(colorPicker.getValue());
            if (backgroundBox.isSelected()) {
                displayScreen.setBackground(colorPicker.getValue());
            }});
        palette.setPadding(new Insets(INSET_PADDING));
        palette.getChildren().addAll(colorCircle, colorPicker);
        palette.setLayoutY(420);
        palette.setSpacing(10);
        palette.setLayoutX(630);
        return palette;
    }

    public Scene getScene() {
            return scene;
        }
    }


