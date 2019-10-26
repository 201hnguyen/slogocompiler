package slogo.backend.commands.control;

import slogo.backend.utils.TurtleHistory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

public class ControlExecutor {

    public double execute(String commandName, List<Object> arguments, TurtleHistory turtleHistory, List<Map<String, Double>> accessibleVariables) throws ClassNotFoundException {
        try {
            Class<?> clazz = Class.forName("slogo.backend.commands.control.controlcommands." + commandName);
            Constructor classConstructor = clazz.getConstructor();
            return ((ControlInterface) classConstructor.newInstance()).execute(turtleHistory, arguments, accessibleVariables);
            // TODO: Currently working on user defined instructions; look like we'll have to use constructor for those versus these we didn't have to
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace(); //FIXME!!
        }
        throw new ClassNotFoundException();
    }
}
