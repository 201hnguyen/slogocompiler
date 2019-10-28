package slogo.backend.commands.basic.basiccommands;

import javafx.geometry.Point2D;
import slogo.backend.commands.basic.BasicCommandInterface;
import slogo.util.Movement;
import slogo.backend.utils.TurtleHistory;
import slogo.backend.utils.TurtleModel;

import java.util.List;

public class HomeBasicCommand implements BasicCommandInterface {

    private TurtleHistory turtleHistory;

    public HomeBasicCommand(TurtleHistory turtleHistory) {
        this.turtleHistory = turtleHistory;
    }

    @Override
    public double getReturnValue(List<Double> parameters, int turtleID) {
        Point2D homePos = new Point2D(0, 0);
        TurtleModel turtle = turtleHistory.getTurtleModel(turtleID);
        Point2D curPos = new Point2D(turtle.getXPos(), turtle.getYPos());
        Movement movement = new Movement(curPos, homePos, turtle.getOrientation());

        turtleHistory.updateTurtle(turtleID, movement, turtle.getDrawStatus(), turtle.getPenStatus());

        return curPos.distance(homePos);
    }
}
