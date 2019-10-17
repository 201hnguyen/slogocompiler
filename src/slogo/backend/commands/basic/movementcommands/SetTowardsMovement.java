package slogo.backend.commands.basic.movementcommands;

import javafx.geometry.Point2D;
import slogo.backend.utils.Movement;
import slogo.backend.utils.TurtleSetting;

import java.util.List;

public class SetTowardsMovement implements MovementCommandsInterface {

    private static final double FULL_CYCLE = 360d;
    private static final double HALF_CYCLE = 180d;

    @Override
    public double getValue(TurtleSetting turtleSetting, List<Double> parameters) {
        double newOrientation = getSlope(turtleSetting.getCurCoordinate(), new Point2D(parameters.get(0), parameters.get(1)));
        double degreeTurned = Math.abs(turtleSetting.getCurOrientation() - newOrientation);
        return degreeTurned <= HALF_CYCLE ? degreeTurned : FULL_CYCLE - degreeTurned;
    }

    @Override
    public Movement getMovement(TurtleSetting turtleSetting, List<Double> parameters) {
        double newOrientation = getSlope(turtleSetting.getCurCoordinate(), new Point2D(parameters.get(0), parameters.get(1)));
        return new Movement(turtleSetting.getCurCoordinate(), turtleSetting.getCurCoordinate(), newOrientation,
                turtleSetting.isVisible(), turtleSetting.isPenDown(), false);
    }

    private double getSlope(Point2D curPos, Point2D targetPos) {
        return new Point2D(1, 0).angle(targetPos.getX() - curPos.getX(), targetPos.getY() - curPos.getY());
    }
}
