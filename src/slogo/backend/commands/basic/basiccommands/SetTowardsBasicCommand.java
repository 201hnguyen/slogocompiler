package slogo.backend.commands.basic.basiccommands;

import javafx.geometry.Point2D;
import slogo.backend.commands.basic.BasicCommandInterface;
import slogo.backend.utils.*;

import java.util.List;

public class SetTowardsBasicCommand implements BasicCommandInterface {

    private static final double FULL_CYCLE = 360d;
    private static final double HALF_CYCLE = 180d;

    private TurtleManager turtleManager;

    public SetTowardsBasicCommand(TurtleManager turtleManager) {
        this.turtleManager = turtleManager;
    }

    @Override
    public double getReturnValue(List<Double> parameters, String turtleID) {
        Turtle turtle = turtleManager.getTurtle(turtleID);
        Point2D curPos = new Point2D(turtle.getXPos(), turtle.getYPos());
        double newAngle = getSlope(curPos, new Point2D(parameters.get(0), parameters.get(1)));
        double degreeTurned = Math.abs(turtle.getOrientation() - newAngle);

        Movement movement = new Movement(curPos, curPos, newAngle);

        turtleManager.updateTurtle(turtleID, movement, new DrawStatus(turtle.isShowing(), turtle.isPenDown()));

        return degreeTurned <= HALF_CYCLE ? degreeTurned : FULL_CYCLE - degreeTurned;
    }


    private double getSlope(Point2D curPos, Point2D targetPos) {
        return new Point2D(1, 0).angle(targetPos.getX() - curPos.getX(), targetPos.getY() - curPos.getY());
    }
}
