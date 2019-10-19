package slogo.backend.commands.basic.movementcommand;

import javafx.geometry.Point2D;
import slogo.backend.commands.basic.BasicCommandInterface;
import slogo.backend.utils.*;

import java.util.List;

public class ForwardBasicCommand implements BasicCommandInterface {

    private TurtleManager turtleManager;

    public ForwardBasicCommand(TurtleManager turtleManager) {
        this.turtleManager = turtleManager;
    }

    @Override
    public double getReturnValue(List<Double> parameters, String turtleID) {
        Turtle turtle = turtleManager.getTurtle(turtleID);
        Movement movement = new Movement(new Point2D(turtle.getXPos(), turtle.getYPos()), turtle.getOrientation(), parameters.get(0));

        turtleManager.updateTurtle(turtleID, movement, new DrawStatus(turtle.isShowing(), turtle.isPenDown()));

        return parameters.get(1);
    }
}
