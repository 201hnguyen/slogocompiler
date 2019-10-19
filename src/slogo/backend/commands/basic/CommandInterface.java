package slogo.backend.commands.basic;

import slogo.backend.utils.TurtleManager;

public interface CommandInterface {
    double getReturnValue(TurtleManager turtleManager, String turtleID);
}
