package slogo;


import slogo.backend.Parser;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.scene.paint.Color;
import javafx.stage.*;
import slogo.backend.commands.CommandBlockManager;
import slogo.backend.utils.TurtleManager;
import slogo.frontend.Visualization;

import java.util.ResourceBundle;


public class Main extends Application {

    private static String test_input1 = "";
    private static String test_input2 = "fd 50";
    private static String test_input3 = "repeat 2 [ fd 50 ]";
    private static String myLanguage = "English";

       public static void main (String[] args) {
        launch(args);
        //Parser p = new Parser(myLanguage);
        //String toBackend = p.translateMyCommands(test_input3, myLanguage);
        //System.out.println(toBackend);

    }

    @Override
    public void start(Stage stage) throws Exception {
        Visualization visualization = new Visualization(stage);
        visualization.helpHost = getHostServices();
        TurtleManager turtleManager = visualization.getTurtle();
        CommandBlockManager commandBlockManager = new CommandBlockManager("Forward 50", turtleManager);
        commandBlockManager.executeInstructionBlock();

    }

}