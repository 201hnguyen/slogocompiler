package slogo.frontend;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Visualization {
    private static final double BUTTON_WIDTH = 350;
    private static final double BUTTON_HEIGHT = 50;
    private static final double DROP_WIDTH = 100;
    private static final double DROP_HEIGHT = 40;
    private static final double SCENE_WIDTH = 800;
    private static final double SCENE_HEIGHT = 600;
    private static final double INPUT_HEIGHT = 99;
    private static final double INPUT_WIDTH = 600;


    private String[] languageList;
    private String[] imageList;
    private Scene scene;
    private AnchorPane root;
    private Button startButton;
    private Button clearButton;
    private HBox buttonBox;
    private ComboBox languageDropDown;
    private ComboBox imageDropDown;
    private ColorPicker colorPicker;
    private Circle colorCircle;

    public Visualization() {
        startButton= buttonCreator("Start");
        clearButton = buttonCreator("Clear");

        languageDropDown = dropDown(languageList = new String[]{"1", "2", "3"});
        imageDropDown = dropDown(imageList = new String[]{"1", "2", "3"});

        root = new AnchorPane();
        root.getChildren().addAll(buttons(), commandBox(),colorPalette());
        scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);

    }

    private Button buttonCreator(String text) {
        Button button = new Button(text);
        button.setMaxSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        return button;
    }

    private ComboBox dropDown (String[] list){
        ComboBox comboBox = new ComboBox(FXCollections.observableArrayList(list));
        comboBox.setPrefSize(DROP_WIDTH, DROP_HEIGHT);
        return comboBox;
    }

    private TilePane commandBox() {
        TextArea inputField = new TextArea("Enter your command here!");
        inputField.setPrefSize(INPUT_WIDTH,INPUT_HEIGHT);
        TilePane inputBox = new TilePane();
        inputBox.getChildren().addAll(inputField);
        inputBox.setLayoutY(500);
        return inputBox;
    }

    private HBox buttons() {
        GridPane buttons = new GridPane();
        buttons.setPadding(new Insets(10, 10, 10, 10));
        buttonBox = new HBox();
        buttonBox.getChildren().addAll(startButton,languageDropDown, clearButton, imageDropDown);
        buttonBox.setSpacing(25);
        buttonBox.setLayoutY(430);
        buttonBox.setLayoutX(70);
        return buttonBox;
    }

    private VBox colorPalette() {
        VBox palette = new VBox();
        colorPicker = new ColorPicker();
        colorPicker.setMaxSize(DROP_WIDTH,DROP_HEIGHT);
        colorCircle = new Circle(50);
        colorPicker.setOnAction(event -> colorCircle.setFill(colorPicker.getValue()));
        palette.setPadding(new Insets(10));
        palette.getChildren().addAll(colorCircle, colorPicker);
        palette.setLayoutY(450);
        palette.setSpacing(10);
        palette.setLayoutX(630);
        return palette;
    }

    public Scene getScene() {
            return scene;
        }
    }
