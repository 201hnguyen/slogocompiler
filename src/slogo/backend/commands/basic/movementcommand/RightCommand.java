package slogo.backend.commands.basic.movementcommand;

import javafx.geometry.Point2D;
import slogo.backend.commands.basic.CommandInterface;
import slogo.backend.utils.*;

import java.util.List;

public class RightCommand implements CommandInterface {
    private static final double FULL_CYCLE = 360;

    private TurtleManager turtleManager;

    public RightCommand(TurtleManager turtleManager) {
        this.turtleManager = turtleManager;
    }

    @Override
    public double getReturnValue(List<Double> parameters, String turtleID) {
        Turtle turtle = turtleManager.getTurtle(turtleID);
        double angle = ((turtle.getOrientation() - parameters.get(0)) + FULL_CYCLE) % FULL_CYCLE;
        Point2D curPos = new Point2D(turtle.getXPos(), turtle.getYPos());
        Movement movement = new Movement(curPos, curPos, angle);

        turtleManager.updateTurtle(turtleID, movement, new DrawStatus(turtle.isShowing(), turtle.isPenDown()));

        return parameters.get(0);
    }
}
