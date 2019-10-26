package slogo.frontend;

import javafx.application.HostServices;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import slogo.backend.utils.TurtleHistory;

public class Visualization {
    private static final double BUTTON_WIDTH = 400;
    private static final double BUTTON_HEIGHT = 50;
    private static final double DROP_WIDTH = 165;
    private static final double DROP_HEIGHT = 40;
    private static final double SCENE_WIDTH = 800;
    private static final double SCENE_HEIGHT = 600;
    private static final double INSET_PADDING = 10;


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
    private CheckBox penBox;
    private CheckBox backgroundBox;
    private TextFlow historyField;
    private TextFlow variableField;
    private VBox historyTracker;
    private ScrollPane historyPane;
    private ScrollPane variablePane;
    public HostServices helpHost;
    private Stage stage;
    private Text readerText;
    private boolean inputSent = false;

    private DisplayScreen displayScreen = new DisplayScreen();
    private CommandLine commandLine = new CommandLine();
    private ColorPalette colorPalette = new ColorPalette();

    public Visualization(Stage stage) {
        this.stage = stage;
        startButton = buttonCreator("Start", event -> {
            readerText = new Text(commandLine.getCommand().getText() + "\n");
            historyField.getChildren().add(readerText);
            inputSent = true;

            if (commandLine.getCommand().getText().contains(":")) {
                String variable = commandLine.getCommand().getText().substring(commandLine.getCommand().getText().lastIndexOf(":"));
                variableField.getChildren().addAll(new Text(variable + "\n")); }
        });
        displayScreen.setBackground(colorPalette.getColor());
        colorPalette.getPalette().setOnAction(event -> {
            if (backgroundBox.isSelected()) {
                displayScreen.setBackground(colorPalette.getColor());
            }
            if(penBox.isSelected()) {
                displayScreen.setLineColor(colorPalette.getColor());
            }
        });
        clearButton = buttonCreator("Clear", event -> commandLine.getCommand().clear());
        helpButton = buttonCreator("Help", event ->  helpHost.showDocument("https://www2.cs.duke.edu/courses/compsci308/current/assign/03_parser/commands.php"));
        languageDropDown = dropDown(languageList = new String[]{"1", "2", "3"}, "Language");
        imageDropDown = dropDown(imageList = new String[]{"1", "2", "3"}, "Image");
        root = new AnchorPane();
        root.getChildren().addAll(displayScreen, commandLine, colorPalette, trackHistory(), buttons(), checkBoxes());
        scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        startStage();
    }
    private ComboBox dropDown (String[] list, String tag){
        ComboBox comboBox = new ComboBox(FXCollections.observableArrayList(list));
        comboBox.setPrefSize(DROP_WIDTH, DROP_HEIGHT);
        comboBox.setPromptText("Choose" + " " + tag);
        return comboBox;
    }
    private VBox trackHistory() {
        historyTracker = new VBox();
        historyTracker.setLayoutX(600);
        historyTracker.setLayoutY(200);
        historyTracker.getChildren().addAll(tabPane());
        return historyTracker;
    }
    private TabPane tabPane() {
        TabPane tabs = new TabPane();
        historyField = new TextFlow();
        variableField = new TextFlow();
        historyPane = scrollMaker();
        variablePane = scrollMaker();
        historyPane.setContent(historyField);
        variablePane.setContent(variableField);
        Tab historyTab = new Tab("History");
        Tab variableTab = new Tab("Variables");
        historyTab.setContent(historyPane);
        variableTab.setContent(variablePane);
        tabs.getTabs().addAll(historyTab, variableTab);
        tabs.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        return tabs;
    }
    private ScrollPane scrollMaker() {
        ScrollPane pane = new ScrollPane();
        pane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        pane.setPrefSize(200,100);
        return pane;
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
    public void update() {
        displayScreen.update();
    }

    public void setHistory(TurtleHistory turtleHistory) {
        displayScreen.setHistory(turtleHistory);
    }

    private void startStage() {
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        stage.setTitle("SLOGO IDLE");
    }

    public String getInput() {
        if(inputSent) {
            inputSent = false;
            return readerText.getText();
        }
        return "";
    }
}
