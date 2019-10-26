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
    private ComboBox languageDropDown;
    private ComboBox imageDropDown;
    private VBox historyTracker;
    private Stage stage;
    private Text readerText;

    private DisplayScreen displayScreen = new DisplayScreen();
    private CommandLine commandLine = new CommandLine();
    private TabMaker tabPane = new TabMaker();
    private ButtonCreator buttonCreator = new ButtonCreator();

    public Visualization(Stage stage) {
        this.stage = stage;
        languageDropDown = dropDown(languageList = new String[]{"1", "2", "3"}, "Language");
        imageDropDown = dropDown(imageList = new String[]{"1", "2", "3"}, "Image");
        root = new AnchorPane();
        root.getChildren().addAll(displayScreen, commandLine, new ColorPalette(displayScreen), trackHistory(), buttonCreator);
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
        historyTracker.getChildren().addAll(tabPane);
        return historyTracker;
    }
    public void update() {
        displayScreen.update();
    }

    public void setHistory(TurtleHistory turtleHistory) {
        displayScreen.setHistory(turtleHistory);
    }

    private void startStage() {
        stage.setScene(scene);
        //stage.setResizable(false);
        stage.show();
        stage.setTitle("SLOGO IDLE");
    }

    public String getInput() {
        if(buttonCreator.isStartButtonClicked()) {
            readerText = new Text(commandLine.getCommand().getText() + "\n");
            return readerText.getText();
        }
        return "";
    }

}
