package slogo.backend.commands.basic.movementcommand;

import javafx.geometry.Point2D;
import slogo.backend.commands.basic.CommandInterface;
import slogo.backend.utils.*;

import java.util.List;

public class HomeCommand implements CommandInterface {
    private static final double INITIAL_ORIENTATION = 90;

    @Override
    public double getReturnValue(TurtleManager turtleManager, String turtleID) {
        Point2D homePos = new Point2D(0, 0);
        Turtle turtle = turtleManager.getTurtle(turtleID);
        Point2D curPos = new Point2D(turtle.getXPos(), turtle.getYPos());
        Movement movement = new Movement(curPos, homePos, INITIAL_ORIENTATION);

        turtleManager.updateTurtle(turtleID, movement, new DrawStatus(turtle.isShowing(), turtle.isPenDown()));

        return curPos.distance(homePos);
    }
}
