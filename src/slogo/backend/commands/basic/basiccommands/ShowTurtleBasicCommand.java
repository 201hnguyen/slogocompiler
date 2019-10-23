package slogo.backend.commands.basic.basiccommands;

import javafx.geometry.Point2D;
import slogo.backend.commands.basic.BasicCommandInterface;
import slogo.backend.utils.*;

import java.util.List;

public class ShowTurtleBasicCommand implements BasicCommandInterface {

    private TurtleHistory turtleHistory;

    public ShowTurtleBasicCommand(TurtleHistory turtleHistory) {
        this.turtleHistory = turtleHistory;
    }

    @Override
    public double getReturnValue(List<Double> parameters, int turtleID) {
        TurtleModel turtle = turtleHistory.getTurtleModel(turtleID);
        Point2D curPos = new Point2D(turtle.getXPos(), turtle.getYPos());
        Movement movement = new Movement(curPos, curPos,turtle.getOrientation());
        turtleHistory.updateTurtle(turtleID, movement, new DrawStatus(true, turtle.isPenDown()));

        return 1;
    }
}
