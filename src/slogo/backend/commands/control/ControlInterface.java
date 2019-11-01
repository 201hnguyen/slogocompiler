package slogo.backend.commands.control;

import slogo.backend.exceptions.BackendException;
import slogo.backend.utils.TurtleHistory;

import java.util.List;
import java.util.Map;

/**
 * Interface that all control commands must follow; contents of List<Object> of parameters are different according to each command
 */
public interface ControlInterface {
    double execute(TurtleHistory turtleHistory, List<Object> parameters, List<Map<String, Double>> accessibleVariables, Map<String, List<Object>> definedFunctions) throws ClassNotFoundException, BackendException;
}
