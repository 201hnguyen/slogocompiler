package slogo.backend.commands.basic;

import slogo.backend.utils.TurtleHistory;

import java.lang.reflect.Constructor;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class CommandFactory {
    private static final String RESOURCE_PATH = "resources.DefinedCommands";
    private static final String CLASS_PATH = "slogo.backend.commands.basic.basiccommands.";
    private static final String ERROR_MSSG = " not recognized";

    private ResourceBundle resourceBundle = ResourceBundle.getBundle(RESOURCE_PATH);
    private TurtleHistory myTurtleHistory;
    //private boolean wasCommand = false;

    public CommandFactory(TurtleHistory turtleHistory) {
        myTurtleHistory = turtleHistory;
    }

    public int getNumParameter(String command) throws ClassNotFoundException {
        for (String key : Collections.list(resourceBundle.getKeys())) {
            if (key.equals(command)) {
                return Integer.parseInt(resourceBundle.getString(key).split(",")[1]);
            }
        }
        throw new ClassNotFoundException(command + ERROR_MSSG);
    }

    public double execute(String command, int turtleID, List<Double> parameters) throws ClassNotFoundException {
        return getDoubleFromInstance(getClass(command), turtleID, parameters, command);
    }

    private Class<?> getClass(String command) throws ClassNotFoundException {
        for (String key : Collections.list(resourceBundle.getKeys())) {
            if (key.equals(command)) {
                String commandName = resourceBundle.getString(key).split(",")[0];
                return Class.forName(CLASS_PATH + commandName);
            }
        }
        throw new ClassNotFoundException();
    }

    private double getDoubleFromInstance(Class<?> clazz, int turtleID, List<Double> parameters, String command) throws ClassNotFoundException {
        try {
            Constructor<?> constructor = clazz.getConstructor();
            return ((BasicCommandInterface) constructor.newInstance()).getReturnValue(myTurtleHistory, parameters, turtleID);
        } catch (Exception e) {
            throw new ClassNotFoundException(command + ERROR_MSSG, e);
        }
    }
}