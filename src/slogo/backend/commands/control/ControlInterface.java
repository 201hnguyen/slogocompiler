package slogo.backend.commands.control;

import slogo.backend.utils.TurtleManager;

import java.util.List;

public interface ControlInterface {
    double execute(TurtleManager turtleManager, List<Object> parameters);
}
