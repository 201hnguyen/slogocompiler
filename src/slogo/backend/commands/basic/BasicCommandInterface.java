package slogo.backend.commands.basic;

import slogo.backend.utils.TurtleManager;

import java.util.List;

public interface BasicCommandInterface {
    double getReturnValue(List<Double> parameters, String turtleID);
}
