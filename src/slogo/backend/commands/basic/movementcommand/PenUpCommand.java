package slogo.backend.commands.basic.movementcommand;

import javafx.geometry.Point2D;
import slogo.backend.commands.basic.CommandInterface;
import slogo.backend.utils.*;

import java.util.List;

public class PenUpCommand implements CommandInterface {

    private TurtleManager turtleManager;

    public PenUpCommand(TurtleManager turtleManager) {
        this.turtleManager = turtleManager;
    }

    @Override
    public double getReturnValue(List<Double> parameters, String turtleID) {
        Turtle turtle = turtleManager.getTurtle(turtleID);
        Point2D curPos = new Point2D(turtle.getXPos(), turtle.getYPos());
        Movement movement = new Movement(curPos, curPos, turtle.getOrientation());
        turtleManager.updateTurtle(turtleID, movement, new DrawStatus(turtle.isShowing(), false));

        return 0;
    }
}
