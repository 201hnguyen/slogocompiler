package slogo.backend.commands.basic.movementcommand;

import javafx.geometry.Point2D;
import slogo.backend.commands.basic.BasicCommandInterface;
import slogo.backend.utils.DrawStatus;
import slogo.backend.utils.Movement;
import slogo.backend.utils.Turtle;
import slogo.backend.utils.TurtleManager;

import java.util.List;

public class BackwardBasicCommand implements BasicCommandInterface {

    private static final double FULL_CYCLE = 360;
    private static final double HALF_CYCLE = 180;

    private TurtleManager turtleManager;

    public BackwardBasicCommand(TurtleManager turtleManager) {
        this.turtleManager = turtleManager;
    }

    @Override
    public double getReturnValue(List<Double> parameters, String turtleID) {
        Turtle turtle = turtleManager.getTurtle(turtleID);
        double angle = (turtle.getOrientation() + HALF_CYCLE) % FULL_CYCLE;
        Movement movement = new Movement(new Point2D(turtle.getXPos(), turtle.getYPos()), angle, parameters.get(0));

        turtleManager.updateTurtle(turtleID, movement, new DrawStatus(turtle.isShowing(), turtle.isPenDown()));

        return parameters.get(0);
    }
}
