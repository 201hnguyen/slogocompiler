package slogo.backend.commands.basic;

import slogo.backend.utils.TurtleManager;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
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

    public int getNumParameter(String command) throws ClassNotFoundException {
        for (String key : Collections.list(resourceBundle.getKeys())) {
            if (key.equals(command)) {
                return Integer.parseInt(resourceBundle.getString(key).split(",")[1]);
            }
        }
        throw new ClassNotFoundException();
    }

    public double execute(String command, String turtleID, List<Double> parameters) throws ClassNotFoundException {
        for (String key : Collections.list(resourceBundle.getKeys())) {
            if (key.equals(command)) {
                String commandName = resourceBundle.getString(key).split(",")[0];
                Class<?> clazz = Class.forName(commandName);
                Constructor constructor;
                try {
                    constructor = clazz.getDeclaredConstructor(TurtleManager.class);
                    return ((BasicCommandInterface) constructor.newInstance(myTurtleManager)).getReturnValue(parameters, turtleID);
                } catch (NoSuchMethodException e) {
                    try {
                        constructor = clazz.getConstructor();
                        return ((BasicCommandInterface) constructor.newInstance()).getReturnValue(parameters, turtleID);
                    } catch (NoSuchMethodException ex) {
                        System.out.println("Something wrong");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        throw new ClassNotFoundException();
    }
}
