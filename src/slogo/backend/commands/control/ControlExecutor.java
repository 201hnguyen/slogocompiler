package slogo.backend.commands.control;

import slogo.backend.exceptions.BackendException;
import slogo.backend.utils.TurtleHistory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class ControlExecutor {

    public static final String DEFINED_CONTROLS_RESOURCE_PATH = "resources/DefinedControls";
    public static final String CONTROLS_PACKAGE_PATH = "slogo.backend.commands.control.controlcommands.";

    public double execute(String commandName, List<Object> arguments, TurtleHistory turtleHistory, List<Map<String, Double>> accessibleVariables, Map<String, List<Object>> definedFunctions) throws BackendException {
        try {
            ResourceBundle controlCommandsBundle = ResourceBundle.getBundle(DEFINED_CONTROLS_RESOURCE_PATH);
            if (controlCommandsBundle.containsKey(commandName) && arguments.size() != Integer.parseInt(controlCommandsBundle.getString(commandName))) {
                throw new BackendException("Unmatched number of arguments for this method.");
            }
            Class<?> clazz = Class.forName(CONTROLS_PACKAGE_PATH + commandName);
            Constructor classConstructor = clazz.getConstructor();
            return ((ControlInterface) classConstructor.newInstance()).execute(turtleHistory, arguments, accessibleVariables, definedFunctions);
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new BackendException(e, "Unable to execute method due to ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, or InvocationTargetException.");
        }
    }
}
