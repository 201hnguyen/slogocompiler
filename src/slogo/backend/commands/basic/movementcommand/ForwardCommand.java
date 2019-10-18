package slogo.backend.commands.basic.movementcommand;

import javafx.geometry.Point2D;
import slogo.backend.commands.basic.CommandInterface;
import slogo.backend.utils.*;

import java.util.List;

public class ForwardCommand implements CommandInterface {
    private double length;

    public ForwardCommand(double length) {
        this.length = length;
    }

    @Override
    public double getReturnValue(TurtleManager turtleManager, String turtleID) {
        Turtle turtle = turtleManager.getTurtle(turtleID);
        Movement movement = new Movement(new Point2D(turtle.getXPos(), turtle.getYPos()), turtle.getOrientation(), length);

        turtleManager.updateTurtle(turtleID, movement, new DrawStatus(turtle.isShowing(), turtle.isPenDown()));

        return length;
    }
}
