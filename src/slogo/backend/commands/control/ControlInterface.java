package slogo.backend.commands.control;

import slogo.backend.utils.TurtleHistory;

import java.util.List;
import java.util.Map;

public interface ControlInterface {
    double execute(TurtleHistory turtleHistory, List<Object> parameters, Map<String, Double> variables);
}
