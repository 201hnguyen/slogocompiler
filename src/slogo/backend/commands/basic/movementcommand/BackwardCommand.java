package slogo.backend.commands.basic.movementcommand;

import javafx.geometry.Point2D;
import slogo.backend.commands.basic.CommandInterface;
import slogo.backend.utils.DrawStatus;
import slogo.backend.utils.Movement;
import slogo.backend.utils.Turtle;
import slogo.backend.utils.TurtleManager;

import java.util.List;

public class BackwardCommand implements CommandInterface {

    private static final double FULL_CYCLE = 360;
    private static final double HALF_CYCLE = 180;

    private double length;

    public BackwardCommand(double length) {
        this.length = length;
    }

    @Override
    public double getReturnValue(TurtleManager turtleManager, String turtleID) {
        Turtle turtle = turtleManager.getTurtle(turtleID);
        double angle = (turtle.getOrientation() + HALF_CYCLE) % FULL_CYCLE;
        Movement movement = new Movement(new Point2D(turtle.getXPos(), turtle.getYPos()), angle, length);

        turtleManager.updateTurtle(turtleID, movement, new DrawStatus(turtle.isShowing(), turtle.isPenDown()));

        return length;
    }
}
