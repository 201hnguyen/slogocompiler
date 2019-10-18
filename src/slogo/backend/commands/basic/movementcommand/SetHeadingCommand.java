package slogo.backend.commands.basic.movementcommand;

import javafx.geometry.Point2D;
import slogo.backend.commands.basic.CommandInterface;
import slogo.backend.utils.*;

public class SetHeadingCommand implements CommandInterface {
    private static final double HALF_CYCLE = 180d;
    private static final double FULL_CYCLE = 360d;

    private double angle;

    public SetHeadingCommand(double angle) {
        this.angle = angle;
    }

    @Override
    public double getReturnValue(TurtleManager turtleManager, String turtleID) {
        Turtle turtle = turtleManager.getTurtle(turtleID);
        double degreeTurned = Math.abs(turtle.getOrientation() - angle);

        Point2D curPos = new Point2D(turtle.getXPos(), turtle.getYPos());
        Movement movement = new Movement(curPos, curPos, angle);

        turtleManager.updateTurtle(turtleID, movement, new DrawStatus(turtle.isShowing(), turtle.isPenDown()));

        return degreeTurned <= HALF_CYCLE ? degreeTurned : FULL_CYCLE - degreeTurned;
    }
}
