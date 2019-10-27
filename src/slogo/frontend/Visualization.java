package slogo.frontend;

import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import slogo.backend.utils.TurtleHistory;

public class Visualization {
    private static final double SCENE_WIDTH = 800;
    private static final double SCENE_HEIGHT = 600;


    private Scene scene;
    private AnchorPane root;
    private Stage stage;
    private Text readerText;

    private DisplayScreen displayScreen = new DisplayScreen();
    private CommandLine commandLine = new CommandLine();
    private TabMaker tabPane = new TabMaker();
    private ButtonCreator buttonCreator = new ButtonCreator();
    //private String[] languageList;
    //private String[] imageList;
    //private DropDownCreator languageDropDown = new DropDownCreator(languageList = new String[]{"1", "2", "3"}, "Language");
    //private DropDownCreator imageDropDown = new DropDownCreator(imageList = new String[]{"1", "2", "3"}, "Image");

    public Visualization(Stage stage) {
        this.stage = stage;
        root = new AnchorPane();
        root.getChildren().addAll(displayScreen, commandLine, new ColorPalette(displayScreen), tabPane, buttonCreator);
        scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        startStage();
    }

    public void update() {
        if (buttonCreator.isClearButtonClicked()) {
            commandLine.getCommand().clear();
        }
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
        String command = commandLine.getCommand().getText();
        if(buttonCreator.isStartButtonClicked() && !command.equals("")) {
            readerText = new Text(command + "\n");
            return readerText.getText();
        }
        return "";
    }

}