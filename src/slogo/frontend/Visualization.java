package slogo.frontend;

import javafx.application.HostServices;
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

public class Visualization {
    private static final double BUTTON_WIDTH = 400;
    private static final double BUTTON_HEIGHT = 50;
    private static final double DROP_WIDTH = 165;
    private static final double DROP_HEIGHT = 40;
    private static final double PICKER_WIDTH = 100;
    private static final double PICKER_HEIGHT = 40;
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
    private Button helpButton;
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
    private VBox historyTracker;
    private ScrollPane historyPane;
    public HostServices helpHost;



    private DisplayScreen displayScreen = new DisplayScreen();

    public Visualization() {
        startButton = buttonCreator("Start", event -> historyField.getChildren().add(new Text(inputField.getText() + "\n")));
        clearButton = buttonCreator("Clear", event -> inputField.clear());
        helpButton = buttonCreator("Help", event ->  helpHost.showDocument("https://www2.cs.duke.edu/courses/compsci308/current/assign/03_parser/commands.php"));
        languageDropDown = dropDown(languageList = new String[]{"1", "2", "3"}, "Language");
        imageDropDown = dropDown(imageList = new String[]{"1", "2", "3"}, "Image");
        root = new AnchorPane();
        root.getChildren().addAll(displayScreen, trackHistory(), buttons(), commandBox(), colorPalette(), checkBoxes());
       // root.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
    }

    private ComboBox dropDown (String[] list, String tag){
        ComboBox comboBox = new ComboBox(FXCollections.observableArrayList(list));
        comboBox.setPrefSize(DROP_WIDTH, DROP_HEIGHT);
        comboBox.setPromptText("Choose" + " " + tag);
        return comboBox;
    }

    private TilePane commandBox() {
        inputField = new TextArea("Enter command here!");
        inputField.setPrefSize(INPUT_WIDTH,INPUT_HEIGHT);
        TilePane inputBox = new TilePane();
        inputBox.getChildren().addAll(inputField);
        inputBox.setLayoutY(500);
        return inputBox;
    }

    private VBox trackHistory() {
        Label historyLabel = new Label("Input History");
        historyTracker = new VBox();
        historyPane = new ScrollPane();
        historyPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        historyPane.setPrefSize(200,100);
        historyTracker.getChildren().addAll(historyLabel, historyPane);
        historyField = new TextFlow();
        historyPane.setContent(historyField);
        historyTracker.setLayoutX(600);
        historyTracker.setLayoutY(200);
        return historyTracker;
    }

    private HBox buttons() {
        GridPane buttons = new GridPane();
        buttons.setPadding(new Insets(INSET_PADDING, INSET_PADDING, INSET_PADDING, INSET_PADDING));
        buttonBox = new HBox();
        buttonBox.getChildren().addAll(startButton,languageDropDown, clearButton, imageDropDown, helpButton);
        buttonBox.setSpacing(25);
        buttonBox.setLayoutY(430);
        buttonBox.setLayoutX(20);
        return buttonBox;
    }

    private Button buttonCreator(String name, EventHandler<ActionEvent> handler) {
        Button button = new Button(name);
        button.setMaxSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        button.setOnAction(handler);
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
        colorPicker.setMaxSize(PICKER_WIDTH,PICKER_HEIGHT);
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
