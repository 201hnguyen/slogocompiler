package slogo.backend.commands.control;

import slogo.backend.utils.TurtleHistory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class ControlExecutor {

    public double execute(String commandName, List<Object> arguments, TurtleHistory turtleHistory, List<Map<String, Double>> accessibleVariables, Map<String, List<Object>> definedFunctions) throws ClassNotFoundException {
        try {
            ResourceBundle controlCommandsBundle = ResourceBundle.getBundle("resources/DefinedControls");
            if (controlCommandsBundle.containsKey(commandName) && arguments.size() != Integer.parseInt(controlCommandsBundle.getString(commandName))) {
                //TODO: Throw unmatched argument exception.
            }
            Class<?> clazz = Class.forName("slogo.backend.commands.control.controlcommands." + commandName);
            Constructor classConstructor = clazz.getConstructor();
            return ((ControlInterface) classConstructor.newInstance()).execute(turtleHistory, arguments, accessibleVariables, definedFunctions);
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace(); //FIXME!!
        }
        throw new ClassNotFoundException();
    }
}
