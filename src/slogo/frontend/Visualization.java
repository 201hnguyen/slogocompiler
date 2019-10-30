package slogo.frontend;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import slogo.backend.utils.TurtleHistory;
import slogo.frontend.controller.ButtonController;
import slogo.frontend.controller.DropDownController;
import slogo.frontend.controller.SliderController;
import slogo.frontend.controller.CheckBoxController;
import slogo.frontend.controller.ColorPaletteController;
import slogo.frontend.controller.TabController;
import slogo.frontend.creater.ButtonCreator;
import slogo.frontend.creater.CheckBoxCreator;
import slogo.frontend.creater.DropDownCreator;
import slogo.frontend.creater.SliderCreator;
import slogo.frontend.statusscreen.TabMaker;
import slogo.frontend.creater.ColorPalette;
import slogo.frontend.turtlescreen.DisplayScreen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Visualization {
    private static final double SCENE_WIDTH = 830;
    private static final double SCENE_HEIGHT = 630;
    private static final String TITLE = "SLOGO IDLE";

    private Scene scene;
    private Stage stage;
    private DisplayScreen displayScreen = new DisplayScreen();
    private CommandLine commandLine = new CommandLine();
    private UIManager myUIManager;
    private int index = 0;
    private TabMaker tabPane = new TabMaker(new TabController(displayScreen));
    private List<Map<String, Double>> myVariables = new ArrayList<>();

    public Visualization(Stage stage) {
        this.stage = stage;
        initialize();
    }

    public String getLanguage() {return myUIManager.getLanguage();}

    public boolean needNewWindow() {
        return myUIManager.isNewButtonClicked();
    }

    public void update() {
        myUIManager.update();
        displayScreen.update();
        if(displayScreen.getCurrentIndex() != index) {
            tabPane.setVariables(myVariables.get(index));
            index = displayScreen.getCurrentIndex();
        }
        Map<String, Double> changedVariables = myUIManager.getChangedVariables();
        if(!changedVariables.isEmpty()) {
            index = index < myVariables.size()? index : myVariables.size()-1;
            myVariables.get(index).putAll(changedVariables);
            tabPane.setVariables(myVariables.get(index));
        }
    }

    public void setHistory(TurtleHistory turtleHistory) {
        displayScreen.setHistory(turtleHistory);
        myVariables = turtleHistory.getMyVariables();
        index = 0;
    }

    private void startStage() {
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle(TITLE);
        stage.show();
    }

    public String getInput() {
        String command = myUIManager.getInput();
        if(!command.equals("")) {
            tabPane.addHistory(command);
        }
        return command;
    }

    public Map<String, Double> getUpdatedVariables() {
        Map<String, Double> map = new HashMap<>();
        map.putAll(myVariables.get(myVariables.size()-1));
        return map;
    }

    public void showError(String message) {
        ErrorShow errorShow = new ErrorShow(message);
        errorShow.show();
    }

    private void initialize() {
        Pane root = new AnchorPane();
        ButtonCreator buttonCreator = new ButtonCreator(new ButtonController(displayScreen));
        DropDownCreator dropDownCreator = new DropDownCreator(new DropDownController(displayScreen));
        SliderCreator sliderCreator = new SliderCreator(new SliderController(displayScreen));
        ColorPalette colorPalette = new ColorPalette(new ColorPaletteController(displayScreen));
        CheckBoxCreator checkBoxCreator = new CheckBoxCreator(new CheckBoxController(displayScreen));

        root.getChildren().addAll(displayScreen, commandLine, colorPalette, tabPane, buttonCreator, dropDownCreator, sliderCreator, checkBoxCreator);
        myUIManager = new UIManager(commandLine, List.of(colorPalette, buttonCreator, dropDownCreator, tabPane, checkBoxCreator, sliderCreator));
        scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        startStage();
    }

    public void showError(Throwable ex, String message) {
        ErrorShow errorShow = new ErrorShow(ex, message);
        errorShow.show();
    }

    public void setUserFunctions(List<String> userFunctions) {
        tabPane.setFunctions(userFunctions);
    }
}