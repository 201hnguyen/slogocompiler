package slogo.frontend;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import slogo.backend.utils.TurtleHistory;
import slogo.frontend.creater.ButtonCreator;
import slogo.frontend.creater.ColorPalette;
import slogo.frontend.creater.DropDownCreator;
import slogo.frontend.turtlescreen.DisplayScreen;

import java.util.List;

public class Visualization {
    private static final double SCENE_WIDTH = 830;
    private static final double SCENE_HEIGHT = 630;
    private static final String TITLE = "SLOGO IDLE";

    private Scene scene;
    private AnchorPane root;
    private Stage stage;
    private DisplayScreen displayScreen = new DisplayScreen();
    private CommandLine commandLine = new CommandLine();
    private TabMaker tabPane = new TabMaker();
    private ButtonCreator buttonCreator = new ButtonCreator(displayScreen);
    private DropDownCreator dropDownCreator = new DropDownCreator(displayScreen);
    private ColorPalette colorPalette = new ColorPalette(displayScreen);
    private UIManager myUIManager;

    public Visualization(Stage stage) {
        this.stage = stage;
        root = new AnchorPane();
        root.getChildren().addAll(displayScreen, commandLine, colorPalette, tabPane, buttonCreator, dropDownCreator);
        myUIManager = new UIManager(commandLine, List.of(colorPalette, buttonCreator, dropDownCreator, tabPane));
        scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        startStage();
    }

    public String getLanguage() {return myUIManager.getLanguage();}

    public boolean needNewWindow() {
        return myUIManager.isNewButtonClicked();
    }

    public void update() {
        myUIManager.update();
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
        String command = myUIManager.getInput();
        if(!command.equals("")) {
            Text input = new Text(command);
            input.setOnMouseClicked(event -> commandLine.getCommand().setText(input.getText()));
            tabPane.addHistory(input);
        }
        return command;
    }

}