package slogo.backend.commands.basic;

import slogo.backend.utils.TurtleHistory;

import java.lang.reflect.Constructor;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class CommandFactory {
    private static final String RESOURCE_PATH = "resources.DefinedCommands";
    private static final String CLASS_PATH = "slogo.backend.commands.basic.basiccommands.";

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
        throw new ClassNotFoundException();
    }

    public double execute(String command, int turtleID, List<Double> parameters) throws ClassNotFoundException {
        Class<?> clazz = getClass(command);
        return getDoubleFromInstance(clazz, turtleID, parameters);
    }

    public boolean isMovementCommand(String command) {
        try {
            getClass(command).getConstructor(TurtleHistory.class);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private Class<?> getClass(String command) throws ClassNotFoundException {
        for (String key : Collections.list(resourceBundle.getKeys())) {
            if (key.equals(command)) {
                String commandName = resourceBundle.getString(key).split(",")[0];
                Class<?> clazz = Class.forName(CLASS_PATH + commandName);
                return clazz;
            }
        }
        throw new ClassNotFoundException();
    }

    private double getDoubleFromInstance(Class<?> clazz, int turtleID, List<Double> parameters) throws ClassNotFoundException {
        try {
            Constructor<?> constructor = clazz.getConstructor(TurtleHistory.class);
            return ((BasicCommandInterface) constructor.newInstance(myTurtleHistory)).getReturnValue(parameters, turtleID);
        } catch (NoSuchMethodException e) {
            return getDoubleFromDefaultConstructor(clazz, turtleID, parameters);
        }catch (Exception e) {
            throw new ClassNotFoundException();
        }
    }

    private double getDoubleFromDefaultConstructor(Class<?> clazz, int turtleID, List<Double> parameters) throws ClassNotFoundException {
        try {
            Constructor<?> constructor = clazz.getConstructor();
            return ((BasicCommandInterface) constructor.newInstance()).getReturnValue(parameters, turtleID);
        } catch (NoSuchMethodException e) {
            return getDoubleFromDefaultConstructor(clazz, turtleID, parameters);
        } catch (Exception e) {
            throw new ClassNotFoundException();
        }
    }
}