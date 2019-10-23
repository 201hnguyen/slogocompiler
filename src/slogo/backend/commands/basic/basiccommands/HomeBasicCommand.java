package slogo.backend.commands.basic.basiccommands;

import javafx.geometry.Point2D;
import slogo.backend.commands.basic.BasicCommandInterface;
import slogo.backend.utils.*;

import java.util.List;

public class HomeBasicCommand implements BasicCommandInterface {
    private static final double INITIAL_ORIENTATION = 90;

    private TurtleHistory turtleHistory;

    public HomeBasicCommand(TurtleHistory turtleHistory) {
        this.turtleHistory = turtleHistory;
    }

    @Override
    public double getReturnValue(List<Double> parameters, int turtleID) {
        Point2D homePos = new Point2D(0, 0);
        TurtleModel turtle = turtleHistory.getTurtleModel(turtleID);
        Point2D curPos = new Point2D(turtle.getXPos(), turtle.getYPos());
        Movement movement = new Movement(curPos, homePos, INITIAL_ORIENTATION);

        turtleHistory.updateTurtle(turtleID, movement, new DrawStatus(turtle.isShowing(), turtle.isPenDown()));

        return curPos.distance(homePos);
    }
}
