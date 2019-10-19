package slogo.backend.commands.basic.movementcommand;

import javafx.geometry.Point2D;
import slogo.backend.commands.basic.BasicCommandInterface;
import slogo.backend.utils.*;

import java.util.List;

public class SetPositionBasicCommand implements BasicCommandInterface {

    private TurtleManager turtleManager;

    public SetPositionBasicCommand(TurtleManager turtleManager) {
        this.turtleManager = turtleManager;
    }

    @Override
    public double getReturnValue(List<Double> parameters, String turtleID) {
        Turtle turtle = turtleManager.getTurtle(turtleID);
        Point2D curPos = new Point2D(turtle.getXPos(), turtle.getYPos());
        Point2D newPos = new Point2D(parameters.get(0), parameters.get(1));

        Movement movement = new Movement(curPos, newPos, turtle.getOrientation());

        turtleManager.updateTurtle(turtleID, movement, new DrawStatus(turtle.isShowing(), turtle.isPenDown()));

        return curPos.distance(newPos);
    }
}
