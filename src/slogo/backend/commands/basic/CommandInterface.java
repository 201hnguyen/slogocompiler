package slogo.backend.commands.basic;

import slogo.backend.utils.TurtleManager;

import java.util.List;

public interface CommandInterface {
    double getReturnValue(List<Double> parameters, String turtleID);
}
