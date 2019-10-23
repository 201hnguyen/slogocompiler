package slogo.backend.commands.basic.basiccommands;

import javafx.geometry.Point2D;
import slogo.backend.commands.basic.BasicCommandInterface;
import slogo.backend.utils.*;

import java.util.List;

public class BackwardBasicCommand implements BasicCommandInterface {

    private static final double FULL_CYCLE = 360;
    private static final double HALF_CYCLE = 180;

    private TurtleHistory turtleHistory;

    public BackwardBasicCommand(TurtleHistory turtleHistory) {
        this.turtleHistory = turtleHistory;
    }

    @Override
    public double getReturnValue(List<Double> parameters, int turtleID) {
        TurtleModel turtle = turtleHistory.getTurtleModel(turtleID);
        double angle = (turtle.getOrientation() + HALF_CYCLE) % FULL_CYCLE;
        Movement movement = new Movement(new Point2D(turtle.getXPos(), turtle.getYPos()), angle, parameters.get(0));

        turtleHistory.updateTurtle(turtleID, movement, new DrawStatus(turtle.isShowing(), turtle.isPenDown()));

        Movement movement2 = new Movement(movement.getEndPosition(), movement.getEndPosition(), (angle+HALF_CYCLE)%FULL_CYCLE);
        turtleHistory.updateTurtle(turtleID, movement2, new DrawStatus(turtle.isShowing(), turtle.isPenDown()));

        return parameters.get(0);
    }
}
