package slogo.backend.commands.control;

import slogo.backend.utils.TurtleHistory;

import java.util.List;

public interface ControlInterface {
    double execute(TurtleHistory turtleHistory, List<Object> parameters);
}
