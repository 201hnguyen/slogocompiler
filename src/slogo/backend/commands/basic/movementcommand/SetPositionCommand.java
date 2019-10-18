package slogo.backend.commands.basic.movementcommand;

import javafx.geometry.Point2D;
import slogo.backend.commands.basic.CommandInterface;
import slogo.backend.utils.*;

import java.util.List;

public class SetPositionCommand implements CommandInterface {

    private double xPos;
    private double yPos;

    public SetPositionCommand(double xPos, double yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    @Override
    public double getReturnValue(TurtleManager turtleManager, String turtleID) {
        Turtle turtle = turtleManager.getTurtle(turtleID);
        Point2D curPos = new Point2D(turtle.getXPos(), turtle.getYPos());
        Point2D newPos = new Point2D(xPos, yPos);

        Movement movement = new Movement(curPos, newPos, turtle.getOrientation());

        turtleManager.updateTurtle(turtleID, movement, new DrawStatus(turtle.isShowing(), turtle.isPenDown()));

        return curPos.distance(newPos);
    }
}
