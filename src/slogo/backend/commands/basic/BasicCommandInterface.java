package slogo.backend.commands.basic;

import java.util.List;

public interface BasicCommandInterface {
    double getReturnValue(List<Double> parameters, int turtleID);
}
