package slogo.backend.commands.basic;

import javafx.geometry.Point2D;
import slogo.backend.utils.DrawStatus;
import slogo.backend.utils.Movement;
import slogo.backend.utils.TurtleManager;

import java.util.List;

public interface CommandInterface {
    double getReturnValue(TurtleManager turtleManager, String turtleID);
}
