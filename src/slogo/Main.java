package slogo;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.scene.paint.Color;
import javafx.stage.*;
import slogo.frontend.Visualization;

public class Main extends Application{

<<<<<<< src/slogo/Main.java
/**
 * Feel free to completely change this code or delete it entirely. 
 */
public class Main {
    /**
     * Start of the program.
     */
    public static void main (String[] args) {
        launch(args)
//        Repeat repeat = new Repeat();
//        List<Object> parameters = new ArrayList<>();
//        parameters.add(3);
//        parameters.add("fd 50 fd 10"); // FIXME: currently split by comma just for sake of demo
//        repeat.execute(parameters);
        Pane pane = new Pane();
        TurtleManager turtleManager = new TurtleManager(pane);
//        CommandBlockManager commandBlockManager = new CommandBlockManager("Forward 50 IfElse LessThan 5 50 [ Forward 10 ] [ Forward 20 ] Forward 30", turtleManager);
//        CommandBlockManager commandBlockManager = new CommandBlockManager("Forward 50 Repeat 3 [ Forward 10 ] Forward 30", turtleManager);
        CommandBlockManager commandBlockManager = new CommandBlockManager("Sum 10 50 If LessThan 5 50 [ Sum 10 10 ] ", turtleManager);
//        CommandBlockManager commandBlockManager = new CommandBlockManager("MakeVariable :a Sum 10 10 ", turtleManager);
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        Visualization visualization = new Visualization(stage);
        visualization.helpHost = getHostServices();
    }

}
