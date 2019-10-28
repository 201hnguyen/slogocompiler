package slogo.frontend;

import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import slogo.backend.utils.TurtleHistory;

public class Visualization {
    private static final double SCENE_WIDTH = 800;
    private static final double SCENE_HEIGHT = 600;
    private static final String TITLE = "SLOGO IDLE";


    private Scene scene;
    private AnchorPane root;
    private Stage stage;
    private Text readerText;
    private String language = "English";

    private DisplayScreen displayScreen = new DisplayScreen();
    private CommandLine commandLine = new CommandLine();
    private TabMaker tabPane = new TabMaker();
    private ButtonCreator buttonCreator = new ButtonCreator();
    private DropDownCreator dropDownCreator = new DropDownCreator(displayScreen);

    public Visualization(Stage stage) {
        this.stage = stage;
        root = new AnchorPane();
        root.getChildren().addAll(displayScreen, commandLine, new ColorPalette(displayScreen), tabPane, buttonCreator, dropDownCreator);
        scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        startStage();
    }

    public String getLanguage() {return language;}

    public void update() {
        if(!language.equals(dropDownCreator.getLanguage())) {
            language = dropDownCreator.getLanguage();
        }
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
        stage.setTitle(TITLE);
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