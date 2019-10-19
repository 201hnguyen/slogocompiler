package slogo.backend.commands.basic;

import slogo.backend.utils.TurtleManager;

import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class CommandFactory {
    private static final String RESOURCE_PATH = "resources/DefinedCommands";

    private ResourceBundle resourceBundle = ResourceBundle.getBundle(RESOURCE_PATH);

    private TurtleManager myTurtleManager;

    public CommandFactory(TurtleManager turtleManager) {
        myTurtleManager = turtleManager;
    }

    public int getNumParameter(String command) throws ClassNotFoundException{
        for(String key : Collections.list(resourceBundle.getKeys())) {
            if(key.equals(command)) {
                return Integer.parseInt(resourceBundle.getString(key).split(",")[1]);
            }

        throw new ClassNotFoundException();
    }

    public CommandInterface getCommand(String command, List<Double> parameters) {

    }
}
