package slogo.frontend;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
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

    public Visualization() {
        startButton= buttonCreator("Start");
        clearButton = buttonCreator("Clear");

        languageDropDown = dropDown(languageList = new String[]{"1", "2", "3"});
        imageDropDown = dropDown(imageList = new String[]{"1", "2", "3"});

        root = new AnchorPane();
        root.getChildren().addAll(buttons(), commandBox(),colorPalette(), checkBoxes());
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
        buttons.setPadding(new Insets(INSET_PADDING, INSET_PADDING, INSET_PADDING, INSET_PADDING));
        buttonBox = new HBox();
        buttonBox.getChildren().addAll(startButton,languageDropDown, clearButton, imageDropDown);
        buttonBox.setSpacing(25);
        buttonBox.setLayoutY(430);
        buttonBox.setLayoutX(70);
        return buttonBox;
    }

    private HBox checkBoxes() {
        checkBoxes = new HBox();
        String[] choices = { "Background", "Pen"};
        for (String choice : choices) {
            CheckBox box = new CheckBox(choice);
            checkBoxes.getChildren().add(box);
            box.setPadding(new Insets(INSET_PADDING));;
            box.setIndeterminate(true);
        }
        checkBoxes.setLayoutY(565);
        checkBoxes.setLayoutX(600);
        return checkBoxes;
    }

    private VBox colorPalette() {
        VBox palette = new VBox();
        colorPicker = new ColorPicker();
        colorPicker.setMaxSize(DROP_WIDTH,DROP_HEIGHT);
        colorCircle = new Circle(CIRCLE_RADIUS);
        colorPicker.setOnAction(event -> colorCircle.setFill(colorPicker.getValue()));
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
