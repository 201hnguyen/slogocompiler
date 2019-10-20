package slogo.backend.commands.control;

import slogo.backend.utils.TurtleManager;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class ControlFactory {

    public double execute(String commandName, List<String> arguments, TurtleManager turtleManager) throws ClassNotFoundException {
        try {
            Class<?> clazz = Class.forName("slogo.backend.commands.control." + commandName);
            Constructor classConstructor = clazz.getConstructor();
            return ((ControlInterface) classConstructor.newInstance()).execute(turtleManager, arguments);
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace(); //FIXME!!
        }
        throw new ClassNotFoundException();
    }
}
