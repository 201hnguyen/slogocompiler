package slogo.backend.commands.basic.basiccommands;

import javafx.geometry.Point2D;
import slogo.backend.commands.basic.BasicCommandInterface;
import slogo.backend.utils.DrawStatus;
import slogo.backend.utils.Movement;
import slogo.backend.utils.TurtleHistory;
import slogo.backend.utils.TurtleModel;

import java.util.List;

public class SetBackgroundCommand implements BasicCommandInterface {
    private static final double ACCURACY = 0.001;

    private TurtleHistory turtleHistory;

    public SetBackgroundCommand(TurtleHistory turtleHistory) {
        this.turtleHistory = turtleHistory;
    }

    @Override
    public double getReturnValue(List<Double> parameters, int turtleID) {
        TurtleModel turtle = turtleHistory.getTurtleModel(turtleID);
        Point2D curPos = new Point2D(turtle.getXPos(), turtle.getYPos());
        Movement movement = new Movement(curPos, curPos,turtle.getOrientation());

        DrawStatus initialDrawStatus = turtle.getDrawStatus();
        int index = (int) (parameters.get(0) + ACCURACY);
        DrawStatus newDrawStatus = new DrawStatus(initialDrawStatus.isTurtleVisible(), index, initialDrawStatus.getImageNum(), false);
        turtleHistory.updateTurtle(turtleID, movement, newDrawStatus, turtle.getPenStatus());

        return index;
    }
}
