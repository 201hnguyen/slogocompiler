package slogo.backend.commands.basic.basiccommands;

import javafx.geometry.Point2D;
import slogo.backend.commands.basic.BasicCommandInterface;
import slogo.backend.utils.Movement;
import slogo.backend.utils.TurtleHistory;
import slogo.backend.utils.TurtleModel;

import java.util.List;

public class SetHeadingBasicCommand implements BasicCommandInterface {
    private static final double HALF_CYCLE = 180d;
    private static final double FULL_CYCLE = 360d;

    private TurtleHistory turtleHistory;

    public SetHeadingBasicCommand(TurtleHistory turtleHistory) {
        this.turtleHistory = turtleHistory;
    }

    @Override
    public double getReturnValue(List<Double> parameters, int turtleID) {
        TurtleModel turtle = turtleHistory.getTurtleModel(turtleID);
        double degreeTurned = Math.abs(turtle.getOrientation() - parameters.get(0));

        Point2D curPos = new Point2D(turtle.getXPos(), turtle.getYPos());
        Movement movement = new Movement(curPos, curPos, parameters.get(0));

        turtleHistory.updateTurtle(turtleID, movement, turtle.getDrawStatus(), turtle.getPenStatus());

        return degreeTurned <= HALF_CYCLE ? degreeTurned : FULL_CYCLE - degreeTurned;
    }
}
