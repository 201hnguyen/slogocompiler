package slogo.backend.commands.basic.basiccommands;

import javafx.geometry.Point2D;
import slogo.backend.commands.basic.BasicCommandInterface;
import slogo.backend.utils.*;

import java.util.List;

public class LeftBasicCommand implements BasicCommandInterface {
    private static final double FULL_CYCLE = 360;

    private TurtleHistory turtleHistory;

    public LeftBasicCommand(TurtleHistory turtleHistory) {
        this.turtleHistory = turtleHistory;
    }


    @Override
    public double getReturnValue(List<Double> parameters, int turtleID) {
        TurtleModel turtle = turtleHistory.getTurtleModel(turtleID);
        double angle = ((turtle.getOrientation() + parameters.get(0))) % FULL_CYCLE;
        Point2D curPos = new Point2D(turtle.getXPos(), turtle.getYPos());
        Movement movement = new Movement(curPos, curPos, angle);

        turtleHistory.updateTurtle(turtleID, movement, new DrawStatus(turtle.isShowing(), turtle.isPenDown()));

        return parameters.get(0);
    }
}
