package slogo.frontend;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import slogo.backend.utils.TurtleHistory;

import java.io.File;

public class Visualization {
    private static final double SCENE_WIDTH = 800;
    private static final double SCENE_HEIGHT = 600;


    private Scene scene;
    private AnchorPane root;
    private Stage stage;
    private Text readerText;
    private File selectedFile;

    private DisplayScreen displayScreen = new DisplayScreen();
    private CommandLine commandLine = new CommandLine();
    private TabMaker tabPane = new TabMaker();
    private ButtonCreator buttonCreator = new ButtonCreator();
    private DropDownCreator dropDownCreator = new DropDownCreator(displayScreen);
    private FileChooser fileChooser = new FileChooser();

    public Visualization(Stage stage) {
        this.stage = stage;
        Button button = new Button("Select File");
        button.setOnAction(e -> {
            File selectedFile = fileChooser.showOpenDialog(stage);
        });
        VBox vBox = new VBox(button);
        root = new AnchorPane();
        root.getChildren().addAll(displayScreen, commandLine, new ColorPalette(displayScreen), tabPane, buttonCreator, dropDownCreator, button);
        scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        upload();
        startStage();
    }

    private void upload() {
        if (buttonCreator.isStartButtonClicked()) {
            File selectedFile = fileChooser.showOpenDialog(stage); }
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
            tabPane.getHistory().getChildren().add(readerText);
         /*   readerText.setOnMouseClicked(event -> commandLine.getCommand().setText();*/
            return readerText.getText();
        }
        return "";
    }

}
