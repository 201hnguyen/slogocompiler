package slogo;

import javafx.scene.layout.Pane;
import slogo.backend.commands.CommandBlockManager;
import slogo.backend.commands.control.Repeat;
import slogo.backend.utils.TurtleManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Feel free to completely change this code or delete it entirely. 
 */
public class Main {
    /**
     * Start of the program.
     */
    public static void main (String[] args) {
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

        commandBlockManager.executeInstructionBlock();
    }
}
