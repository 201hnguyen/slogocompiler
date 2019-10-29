package slogo.backend.commands.basic;

import slogo.backend.utils.TurtleHistory;

import java.util.List;

public interface BasicCommandInterface {
    double getReturnValue(TurtleHistory turtleManager, List<Double> parameters, int turtleID);
}
