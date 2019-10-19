package slogo.backend.commands.basic.movementcommand;

import javafx.geometry.Point2D;
import slogo.backend.commands.basic.BasicCommandInterface;
import slogo.backend.utils.*;

import java.util.List;

public class ClearScreenBasicCommand implements BasicCommandInterface {

    private static final double INITIAL_ORIENTATION = 90;

    private TurtleManager turtleManager;

    public ClearScreenBasicCommand(TurtleManager turtleManager) {
        this.turtleManager = turtleManager;
    }

    @Override
    public double getReturnValue(List<Double> parameters, String turtleID) {
        Turtle turtle = turtleManager.getTurtle(turtleID);
        Point2D curPos = new Point2D(turtle.getXPos(), turtle.getYPos());
        double returnValue = curPos.distance(0, 0);

        Movement movement = new Movement(curPos, new Point2D(0, 0), INITIAL_ORIENTATION);

        turtleManager.updateTurtle(turtleID, movement, new DrawStatus(turtle.isShowing(), turtle.isPenDown()));
        turtleManager.initialize();

        return returnValue;
    }
}
